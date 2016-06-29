package br.furb.consultor.acao;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import br.furb.consultor.cotacao.BOCotacao;
import br.furb.consultor.entities.AcaoBolsaDTO;
import br.furb.consultor.time.BOClock;
import br.furb.consultor.usuario.BOUsuario;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public final class BOAcaoBolsaValores {

	private BOAcaoBolsaValores(){
		
	}
	
	public static AcaoBolsaDTO getAcaoBolsa(String codigoAcao){
		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(cc);
		
		WebResource webResource = client.resource("http://localhost:8080/br.furb.consultor-busca-dados/consultor/acaobolsa/"+codigoAcao).
				queryParam("inicio", String.valueOf(BOClock.getUTCTimeServer().toDate().getTime())).
				queryParam("expira", "600000");
		
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		AcaoBolsaDTO acaoBolsaDTO = response.getEntity(AcaoBolsaDTO.class);
		
		
		Integer hora = formatTime(acaoBolsaDTO.getHoraUltimaNegociacao());
		double porcentagem = getDoubleValue(acaoBolsaDTO.getVariacaoPercentual());
		BOCotacao.createCotacao(codigoAcao, acaoBolsaDTO.getDataUltimaNegociacao(), hora, //
								acaoBolsaDTO.getValorAbertura(), acaoBolsaDTO.getValorAtual(), porcentagem, acaoBolsaDTO.getMenorValorDia(), acaoBolsaDTO.getMaiorValorDia());
		
		return acaoBolsaDTO;
	}

	public static AcaoBolsaDTO getAcaoBolsa(String codigoAcao, Long codigoUsuario){
		Long expira = BOUsuario.getTimeExpiracao(codigoUsuario);
		
		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(cc);
		
		WebResource webResource = client.resource("http://localhost:8080/br.furb.consultor-busca-dados/consultor/acaobolsa/"+codigoAcao).
				queryParam("inicio", String.valueOf(BOClock.getUTCTimeServer().toDate().getTime())).
				queryParam("expira", expira.toString());
		
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
		
		return response.getEntity(AcaoBolsaDTO.class);
	}
	
	private static int formatTime(String horaString){
		String[] particionamento = horaString.split(":");
		int hora = 0;
		if (particionamento[1].contains("pm")) {
			 hora = 12 + Integer.parseInt(particionamento[0]);
		}else{
			hora = Integer.parseInt(particionamento[0]);
		}
			int minutos = Integer.parseInt(particionamento[1].replace("am", "").replace("pm", ""));
		return (hora * 60) + minutos;
	}
	
	private static double getDoubleValue(String porcentagemStr){
		String porcentagem = porcentagemStr.substring(0, porcentagemStr.length() - 1);
		return Double.parseDouble(porcentagem);
	}
}
