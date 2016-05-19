package br.furb.consultor.bovespa.destaque;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AcaoMaioresOscilacoes {

	@XmlElement(name = "Altas")
	private AcoesAlta alta;
	
	@XmlElement(name = "Baixas")
	private AcoesBaixa baixa;

	public AcoesAlta getAlta() {
		return alta;
	}

	public void setAlta(AcoesAlta alta) {
		this.alta = alta;
	}

	public AcoesBaixa getBaixa() {
		return baixa;
	}

	public void setBaixa(AcoesBaixa baixa) {
		this.baixa = baixa;
	}
	
}
