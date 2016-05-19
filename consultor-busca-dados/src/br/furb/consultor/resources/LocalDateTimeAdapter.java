package br.furb.consultor.resources;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

	public LocalDateTime unmarshal(String v) throws Exception {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/mm/yyyy HH:mm:ss");

		return LocalDateTime.parse(v, fmt);
	}

	public String marshal(LocalDateTime v) throws Exception {
		return v.toString();
	}

}