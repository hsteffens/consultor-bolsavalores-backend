package entities;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.joda.time.LocalDateTime;

import java.net.MalformedURLException;
import java.net.URL;

public class AcaoBolsa {

	public static AcaoBolsaValores getBolsaValores(String codigo) throws Exception {
		IAcaoBolsa acao = connect();
		return acao.getAcaoBolsa(codigo);
	}

	public static AcaoBolsaValores getAcaoBolsaDentroValidade(String codigo, LocalDateTime dataHoraValidade) throws Exception {
		IAcaoBolsa acao = connect();
		return acao.getAcaoBolsaDentroValidade(codigo, dataHoraValidade);
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
