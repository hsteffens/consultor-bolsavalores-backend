package br.furb.consultor.yahoo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AcoesQuote {

	@XmlElement(name = "quote", type = AcaoBolsaYahoo.class)
	private List<AcaoBolsaYahoo> acoesBolsa;

	public List<AcaoBolsaYahoo> getAcoesBolsa() {
		return acoesBolsa;
	}

	public void setAcoesBolsa(List<AcaoBolsaYahoo> acoesBolsa) {
		this.acoesBolsa = acoesBolsa;
	}

}
