package br.furb.consultor.bovespa.destaque;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.joda.time.LocalDateTime;

import br.furb.consultor.resources.DoubleAdapter;
import br.furb.consultor.resources.LocalDateTimeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class Papel {

	@XmlAttribute(name = "Nome")
	private String nome;

	@XmlAttribute(name = "Especificacao")
	private String especificacao;

	@XmlJavaTypeAdapter( LocalDateTimeAdapter.class )
	@XmlAttribute(name = "Data")
	private LocalDateTime data;

	@XmlJavaTypeAdapter( DoubleAdapter.class )
	@XmlAttribute(name = "Preco")
	private Double preco;

	@XmlJavaTypeAdapter( DoubleAdapter.class )
	@XmlAttribute(name = "Oscilacao")
	private Double oscilacao;
	
	@XmlAttribute(name = "Codigo")
	private String codigo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Double getOscilacao() {
		return oscilacao;
	}

	public void setOscilacao(Double oscilacao) {
		this.oscilacao = oscilacao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
