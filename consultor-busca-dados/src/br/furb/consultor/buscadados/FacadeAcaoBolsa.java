package br.furb.consultor.buscadados;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.LocalDateTime;

import br.furb.consultor.bovespa.destaque.AcaoDestaque;
import br.furb.consultor.converter.ConverterPapel;
import br.furb.consultor.entities.AcaoBolsaDTO;
import br.furb.consultor.entities.PapelDTO;
import br.furb.consultor.redis.ClientRedis;
import br.furb.consultor.redis.IClientRedis;
import br.furb.consultor.redis.RedisConn;
import br.furb.consultor.time.BOClock;
import br.furb.consultor.yahoo.BOYahoo;

import com.lambdaworks.redis.RedisConnection;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public final class FacadeAcaoBolsa {

	private FacadeAcaoBolsa(){

	}

	public static AcaoBolsaDTO getAcaoBolsaValores(String codigoAcao,LocalDateTime horaInicial, Long expire){
		AcaoBolsaDTO acaoBolsaDTO = new AcaoBolsaDTO();

		IClientRedis client = new ClientRedis(RedisConn.REDIS_URI);
		RedisConnection<String, String> params = client.getRedisConnection();
		try{
			acaoBolsaDTO = carregaAcaoBolsaCache(codigoAcao,horaInicial, expire, params);
			if (acaoBolsaDTO == null) {
				acaoBolsaDTO = carregaAcaoBolsaYahoo(codigoAcao, expire, params);
			}

		}finally{
			client.closeConnection(params);
		}

		return acaoBolsaDTO;
	}

	private static AcaoBolsaDTO carregaAcaoBolsaYahoo(String codigoAcao,
			Long time, RedisConnection<String, String> params) {
		AcaoBolsaDTO acaoBolsaDTO;
		acaoBolsaDTO = BOYahoo.getAcaoBolsaValores(codigoAcao);
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			//Object to JSON in String
			String jsonInString = mapper.writeValueAsString(acaoBolsaDTO);
			
			params.set(codigoAcao, jsonInString);
			params.expire(codigoAcao, time);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return acaoBolsaDTO;
	}

	private static AcaoBolsaDTO carregaAcaoBolsaCache(String codigoAcao,LocalDateTime horaInicial, Long time, RedisConnection<String, String> params) {
		LocalDateTime timeClockServer = BOClock.getTimeExternalServer("129.6.15.28");
		System.out.println("Hora Servidor" + timeClockServer);
		System.out.println("Hora Inicio" + horaInicial);
		Long tempoHaExpirar = params.ttl(codigoAcao);
		System.out.println("Expira em:" + tempoHaExpirar);
		
		long delay = timeClockServer.toDate().getTime() - horaInicial.toDate().getTime();
		long tempoExpiracao = delay + time;
		System.out.println("Tempo de expera:" + tempoExpiracao);

		if (tempoHaExpirar > 0 && tempoHaExpirar > tempoExpiracao) {
			String acaoJson = params.get(codigoAcao);
			if (!StringUtils.isBlank(acaoJson)) {
				StringReader reader = new StringReader(acaoJson);
				ObjectMapper mapper = new ObjectMapper();
				try {
					return mapper.readValue(reader, AcaoBolsaDTO.class);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}

	public static List<PapelDTO> getMelhoresOpcoesVenda(){
		AcaoDestaque acoes = runClientAcoesDestaque();

		return ConverterPapel.converterPapel(acoes.getMaioresOscilacoes().getAlta().getPapeis());
	}

	public static List<PapelDTO> getMelhoresOpcoesCompra(){
		AcaoDestaque acoes = runClientAcoesDestaque();

		return ConverterPapel.converterPapel(acoes.getMaioresOscilacoes().getBaixa().getPapeis());
	}

	private static AcaoDestaque runClientAcoesDestaque() {
		Client client = Client.create();
		WebResource webResource = client.resource("http://bvmf.bmfbovespa.com.br/DestaqueTV/DestaqueTV.asp");

		ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		String output = response.getEntity(String.class);

		AcaoDestaque acoes = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(AcaoDestaque.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(output);
			acoes = (AcaoDestaque) unmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return acoes;
	}

}
