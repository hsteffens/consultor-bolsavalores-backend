package br.furb.consultor.resources;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DoubleAdapter extends XmlAdapter<String, Double>{

	@Override
	public Double unmarshal(String v) throws Exception {
		return Double.parseDouble(v.replace(".", "").replace(",", "."));
	}

	@Override
	public String marshal(Double v) throws Exception {
		return String.valueOf(v);
	}

}
