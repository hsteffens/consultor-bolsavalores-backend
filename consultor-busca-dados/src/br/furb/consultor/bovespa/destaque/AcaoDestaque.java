package br.furb.consultor.bovespa.destaque;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="DestaqueTV")
@XmlAccessorType(XmlAccessType.FIELD)
public class AcaoDestaque {
	
	@XmlElement(name = "MaioresOscilacoes")
	private AcaoMaioresOscilacoes maioresOscilacoes;

	public AcaoMaioresOscilacoes getMaioresOscilacoes() {
		return maioresOscilacoes;
	}

	public void setMaioresOscilacoes(AcaoMaioresOscilacoes maioresOscilacoes) {
		this.maioresOscilacoes = maioresOscilacoes;
	}
	
}
