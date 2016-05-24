package entities;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class AcaoBolsa {

	public static AcaoBolsaValores getBolsaValores(String codigo) throws Exception {
		IAcaoBolsa acao = connect();
		return acao.getAcaoBolsa(codigo);
	}
	
	public static AcaoBolsaValores getAcaoBolsaDentroValidade(String codigo,Long inicio, Long expira) throws Exception {
		IAcaoBolsa acao = connect();
		return acao.getAcaoBolsaDentroValidade(codigo, inicio, expira);
	}

	public static Usuario getUsuario(long codigoUsuario) throws Exception {
		IAcaoBolsa acao = connect();
		return acao.getUsuario(codigoUsuario);
	}

	private static IAcaoBolsa connect() throws MalformedURLException {
		URL url = new URL("http://127.0.0.1:9876/entities?wsdl");
		QName qname = new QName("http://entities/", "AcaoBolsaService");
		Service ws = Service.create(url, qname);
		IAcaoBolsa acao = ws.getPort(IAcaoBolsa.class);
		return acao;
	}

}
