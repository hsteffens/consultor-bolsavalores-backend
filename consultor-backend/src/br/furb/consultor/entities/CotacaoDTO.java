package br.furb.consultor.entities;

import org.joda.time.LocalDate;

public class CotacaoDTO {

	private String codigo;
	private LocalDate data;
	private String hora;
	private Double valorAbertura;
	private Double valorAtual;
	private Double variacaoPercentual;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public Double getValorAbertura() {
		return valorAbertura;
	}
	public void setValorAbertura(Double valorAbertura) {
		this.valorAbertura = valorAbertura;
	}
	public Double getValorAtual() {
		return valorAtual;
	}
	public void setValorAtual(Double valorAtual) {
		this.valorAtual = valorAtual;
	}
	public Double getVariacaoPercentual() {
		return variacaoPercentual;
	}
	public void setVariacaoPercentual(Double variacaoPercentual) {
		this.variacaoPercentual = variacaoPercentual;
	}
	
}
