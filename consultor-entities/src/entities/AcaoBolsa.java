package entities;


import javax.jws.WebService;

import org.joda.time.LocalDateTime;

import br.furb.acao.BOAcaoBolsaValores;
import br.furb.usuario.BOUsuario;

@WebService(endpointInterface = "entities.IAcaoBolsa")
public class AcaoBolsa implements IAcaoBolsa {
	
	
	@Override
	public AcaoBolsaValores getAcaoBolsa(String codigoAcao) {
		return BOAcaoBolsaValores.getAcaoBolsaValores(codigoAcao);
	}

	@Override
	public AcaoBolsaValores getAcaoBolsaDentroValidade(String codigoAcao, LocalDateTime dataHoraValide) {
		return BOAcaoBolsaValores.getAcaoBolsaValores(codigoAcao);
	}

	@Override
	public Usuario getUsuario(long codigoUsuario) {
		return BOUsuario.getUsuario(codigoUsuario);
	}


}
