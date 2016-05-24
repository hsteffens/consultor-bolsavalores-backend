package br.furb.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import br.furb.consultor.time.BOClock;
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
		List<String> acoes = new ArrayList<String>();
		try {
			acoes.add(AcaoBolsa.getBolsaValores("PETR4").getNomeAcao());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acoes;
	}

	public List<String> getMelhoresOpcoesVendasPorCliente(long codigoUsuario) {
		List<String> acoes = new ArrayList<String>();
		try {
			Usuario usuario = AcaoBolsa.getUsuario(codigoUsuario);
			if (usuario != null) {
				Long expira = 0l;
				if (usuario.getTipoInvestidor() == EnTipoInvestidor.CONSERVADOR.getCodigo()) {
					expira = 1800000l;//Cada 30 Minutos
				}else if (usuario.getTipoInvestidor() == EnTipoInvestidor.MODERADO.getCodigo()) {
					expira = 600000l;//Cada 10 Minutos
				} else if (usuario.getTipoInvestidor() == EnTipoInvestidor.AGRESSIVO.getCodigo()){
					expira = 60000l;//Cada Minuto
				}
				acoes.add(AcaoBolsa.getAcaoBolsaDentroValidade("PETR4.SA",BOClock.getUTCTimeServer().toDate().getTime(), expira).getNomeAcao());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acoes;
	}

}
