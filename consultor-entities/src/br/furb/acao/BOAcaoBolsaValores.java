package br.furb.acao;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import br.furb.converter.ConverterAcaoBolsaValor;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import entities.AcaoBolsaValores;

public class BOAcaoBolsaValores {

	public static AcaoBolsaValores getAcaoBolsaValores(String codigoAcao){
		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(cc);
		
		WebResource webResource = client.resource("http://localhost:8080/br.furb.consultor-busca-dados/consultor/acaobolsa/"+codigoAcao);

		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		AcaoBolsaDTO acaoBolsaDTO = response.getEntity(AcaoBolsaDTO.class);

		return ConverterAcaoBolsaValor.converterBolsaValores(acaoBolsaDTO);
	}
}
