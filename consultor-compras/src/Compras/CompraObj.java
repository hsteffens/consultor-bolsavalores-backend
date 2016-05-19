package Compras;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.joda.time.LocalDateTime;
import org.omg.CORBA.ORB;

import br.furb.papeis.PapelDTO;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import entities.AcaoBolsa;
import entities.Usuario;

/**
 * Classe que representa a impletamentação das ações disponivel no sistema.
 * 
 * @author helinton.steffens
 *
 */
public class CompraObj extends CompraPOA {
	
	private ORB orb;

	public void setORB(ORB orb_val) {
		orb = orb_val;
	}

	/**
	 * Responsável por retornar uma lista contendo o codigo das ações sugeridas para se comprar.
	 * 
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public void getMelhoresOpcoesCompras(AcoesCodHolder codigosAcoes) {
		ClientConfig cc = new DefaultClientConfig();
		cc.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(cc);
		
		WebResource webResource = client.resource("http://localhost:8080/br.furb.consultor-busca-dados/consultor/acaobolsa/melhoropcao/compra");

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
		
		
		codigosAcoes.value = (String[]) acoes.toArray(new String[]{});
		// TODO Auto-generated method stub

	}

	/**
	 * Responsável por retornar uma lista contendo o codigo das ações sugeridas para se comprar, 
	 * está sugestão é por bolsa de valores.<p>
	 * Ex: BM&FBOVESPA aqui representada pelo o codigo 1 e NYSE NASDAQ representada pelo o codigo 2
	 * 
	 * @param codigoBolsa
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public void getMelhoresOpcoesComprasPorBolsa(int bolsa, AcoesCodHolder codigosAcoes) {
		String nomeAcao = "";
		try {
			nomeAcao = AcaoBolsa.getBolsaValores("PETR4").getNomeAcao();
		} catch (Exception e) {
			e.printStackTrace();
		}
		codigosAcoes.value = new String[]{nomeAcao};
	}

	/**
	 * Responsável por retornar uma lista contendo o codigo das ações sugeridas para se vender, 
	 * está sugestão é baseada na carteira virtual de cada usuário.
	 * 
	 * @param codigoUsuario
	 * @return
	 * @throws RemoteException
	 */
	@Override
	public void getMelhoresOpcoesComprasPorCliente(int codigoCliente, AcoesCodHolder codigosAcoes) {
		String nomeAcao = "";
		try {
			Usuario usuario = AcaoBolsa.getUsuario(codigoCliente);
			if (usuario != null) {
				if (usuario.getTipoInvestidor() == 1) {
					LocalDateTime dataHora = LocalDateTime.now().minusDays(1);
					nomeAcao = AcaoBolsa.getAcaoBolsaDentroValidade("GOOG",dataHora).getNomeAcao();
				}else if (usuario.getTipoInvestidor() == 2) {
					LocalDateTime dataHora = LocalDateTime.now().minusHours(1);
					nomeAcao = AcaoBolsa.getAcaoBolsaDentroValidade("GOOG",dataHora).getNomeAcao();
				} else{
					LocalDateTime dataHora = LocalDateTime.now();
					nomeAcao = AcaoBolsa.getAcaoBolsaDentroValidade("GOOG", dataHora).getNomeAcao();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		codigosAcoes.value = new String[]{nomeAcao};
	}

	@Override
	public void shutdown() {
		orb.shutdown(false);
	}

}
