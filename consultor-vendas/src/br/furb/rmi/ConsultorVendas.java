package br.furb.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import br.furb.papel.PapelDTO;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import entities.AcaoBolsa;
import entities.Usuario;

/**
 * Classe responsável por implementar as ações disponiveis do sistema.
 *  
 * @author helinton.steffens
 * 
 */
public class ConsultorVendas extends UnicastRemoteObject implements IConsultorVendas{

	public ConsultorVendas()  throws RemoteException{
		super();
	}

	public List<String> getMelhoresOpcoesVendas() {
		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(cc);
		
		WebResource webResource = client.resource("http://localhost:8080/br.furb.consultor-busca-dados/consultor/acaobolsa/melhoropcao/venda");

		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		List<PapelDTO> lPapeis = response.getEntity(new GenericType<List<PapelDTO>>(){});
		
		List<String> acoes = new ArrayList<String>();
		for (PapelDTO Papel : lPapeis) {
			acoes.add(Papel.getCodigo());
		}
		return acoes;
	}

	public List<String> getMelhoresOpcoesVendasPorBolsa(long codigoBolsa) {
		if (codigoBolsa == 1) {
			return Collections.singletonList("PETR4");
		}else{
			return Collections.singletonList("ABEV3");
			
		}
	}

	public List<String> getMelhoresOpcoesVendasPorCliente(long codigoUsuario) {
		try {
			Usuario usuario = AcaoBolsa.getUsuario(codigoUsuario);
			if (usuario != null) {
				return Collections.singletonList("PETR4");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.singletonList("ABEV3");
	}

}
