package br.furb.consultor.usuario;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.furb.consultor.acao.IAcaoBolsa;
import br.furb.consultor.entities.Usuario;

public final class BOUsuario {

	private BOUsuario(){
		
	}
	
	public static Usuario getUsuario(long codigoUsuario) throws Exception {
		IAcaoBolsa acao = connect();
		return acao.getUsuario(codigoUsuario);
	}
	
	public static Long getTimeExpiracao(Long codigoUsuario){
		Long expira = 0l;
		try {
			Usuario usuario = getUsuario(codigoUsuario);
			if (usuario != null) {
				if (usuario.getTipoInvestidor() == EnTipoInvestidor.CONSERVADOR.getCodigo()) {
					expira = 1800000l;//Cada 30 Minutos
				}else if (usuario.getTipoInvestidor() == EnTipoInvestidor.MODERADO.getCodigo()) {
					expira = 600000l;//Cada 10 Minutos
				} else if (usuario.getTipoInvestidor() == EnTipoInvestidor.AGRESSIVO.getCodigo()){
					expira = 60000l;//Cada Minuto
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return expira;
	}

	private static IAcaoBolsa connect() throws MalformedURLException {
		URL url = new URL("http://127.0.0.1:9876/entities?wsdl");
		QName qname = new QName("http://entities/", "AcaoBolsaService");
		Service ws = Service.create(url, qname);
		IAcaoBolsa acao = ws.getPort(IAcaoBolsa.class);
		return acao;
	}
}
