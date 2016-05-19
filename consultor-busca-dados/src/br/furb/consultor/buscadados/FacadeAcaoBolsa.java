package br.furb.consultor.buscadados;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import br.furb.consultor.bovespa.destaque.AcaoDestaque;
import br.furb.consultor.converter.ConverterPapel;
import br.furb.consultor.converter.ConverterYahooAcao;
import br.furb.consultor.entities.AcaoBolsaDTO;
import br.furb.consultor.entities.PapelDTO;
import br.furb.consultor.yahoo.AcoesYahoo;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public final class FacadeAcaoBolsa {

	private FacadeAcaoBolsa(){
		
	}
	
	public static AcaoBolsaDTO getAcaoBolsaValores(String codigoAcao){
		
		Client client = Client.create();
		WebResource webResource = client.resource(getConsultaDadosYahoo(codigoAcao));

		ClientResponse response = webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		String output = response.getEntity(String.class);
		
		AcoesYahoo acoes = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(AcoesYahoo.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	
			StringReader reader = new StringReader(output);
			acoes = (AcoesYahoo) unmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return ConverterYahooAcao.converterYahooToAcaoBolsaDTO(acoes.getResults().getAcoesBolsa()).get(0);
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
	
	private static String getConsultaDadosYahoo(String... codigos){
		StringBuilder sql = new StringBuilder();
		sql.append("https://query.yahooapis.com/v1/public/yql?");
		sql.append("q=select%20");
		//Fields Consultadas
		sql.append("Symbol,Name,AverageDailyVolume,Ask,Change,PercentChange,");
		sql.append("LastTradeDate,LastTradeTime,DaysLow,DaysHigh,YearLow,");
		sql.append("YearHigh,MarketCapitalization,EBITDA,Open,LastTradePriceOnly,StockExchange");
		sql.append("%20from%20yahoo.finance.quotes%20");
		//Filtros
		sql.append("where%20");
		sql.append("symbol%20in%20(");
		
		for (int i = 0; i < codigos.length; i++) {
			sql.append("%22");
			sql.append(codigos[i]);
			sql.append("%22");
			if (i < codigos.length -1) {
				sql.append(",");
			}
			
		}
		sql.append(")%0A%09%09&");
		sql.append("env=http%3A%2F%2Fdatatables.org%2Falltables.env");
		return sql.toString();
	}
}
