package entities;


import javax.jws.WebService;

import br.furb.acao.BOAcaoBolsaValores;
import br.furb.usuario.BOUsuario;

@WebService(endpointInterface = "entities.IAcaoBolsa")
public class AcaoBolsa implements IAcaoBolsa {
	
	
	@Override
	public AcaoBolsaValores getAcaoBolsa(String codigoAcao) {
		return BOAcaoBolsaValores.getAcaoBolsaValores(codigoAcao, 0l, 0l);
	}

	@Override
	public AcaoBolsaValores getAcaoBolsaDentroValidade(String codigoAcao,Long inicio, Long expira) {
		return BOAcaoBolsaValores.getAcaoBolsaValores(codigoAcao, inicio, expira);
	}

	@Override
	public Usuario getUsuario(long codigoUsuario) {
		return BOUsuario.getUsuario(codigoUsuario);
	}


}
