package Compras;

import org.omg.CORBA.ORB;

public class CompraObj extends CompraPOA {
	
	private ORB orb;

	public void setORB(ORB orb_val) {
		orb = orb_val;
	}

	@Override
	public void getMelhoresOpcoesCompras(AcoesCodHolder codigosAcoes) {
		codigosAcoes.value = new String[]{"PETRO4","GOOG"};
		// TODO Auto-generated method stub

	}

	@Override
	public void getMelhoresOpcoesComprasPorBolsa(int bolsa,
			AcoesCodHolder codigosAcoes) {
		codigosAcoes.value = new String[]{"PETRO4","GOOG"};
	}

	@Override
	public void getMelhoresOpcoesComprasPorCliente(int codigoCliente,
			AcoesCodHolder codigosAcoes) {
		codigosAcoes.value = new String[]{"PETRO4","GOOG"};
	}

	@Override
	public void shutdown() {
		orb.shutdown(false);
	}

}
