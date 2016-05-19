package br.furb.consultor.yahoo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="query")
@XmlAccessorType(XmlAccessType.FIELD)
public class AcoesYahoo{

	@XmlElement(name = "results")
	private AcoesQuote results;

	public AcoesQuote getResults() {
		return results;
	}

	public void setResults(AcoesQuote results) {
		this.results = results;
	}
	
}

