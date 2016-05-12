package Compras;

import java.rmi.RemoteException;

import org.joda.time.LocalDateTime;
import org.omg.CORBA.ORB;

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
		codigosAcoes.value = new String[]{"PETR4","GOOG"};
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
