package Compras;

import java.rmi.RemoteException;

import org.omg.CORBA.ORB;

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
		codigosAcoes.value = new String[]{"PETR4"};
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
	public void getMelhoresOpcoesComprasPorCliente(int codigoCliente,
			AcoesCodHolder codigosAcoes) {
		codigosAcoes.value = new String[]{"GOOG"};
	}

	@Override
	public void shutdown() {
		orb.shutdown(false);
	}

}
