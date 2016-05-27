package br.furb.consultor.acao;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import br.furb.consultor.entities.AcaoBolsaDTO;
import br.furb.consultor.time.BOClock;

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

		return response.getEntity(AcaoBolsaDTO.class);
	}
}
