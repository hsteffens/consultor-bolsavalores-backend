package br.furb.acao;

import java.io.Serializable;

import org.joda.time.LocalDate;

public class AcaoBolsaDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String codigo;
	private String nomeAcao;
	private double volumeMedioDiario;
	private double valorAtual;
	private double variacaoValor;
	private String variacaoPercentual;
	private LocalDate dataUltimaNegociacao;
	private String horaUltimaNegociacao;
	private double menorValorDia;
	private double maiorValorDia;
	private double menorValorAno;
	private double maiorValorAno;
	private String capitalMercado;
	private String EBITDA;
	private double valorAbertura;
	private double ultimoValorTransacao;
	private String exchange;
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNomeAcao() {
		return nomeAcao;
	}
	public void setNomeAcao(String nomeAcao) {
		this.nomeAcao = nomeAcao;
	}
	public double getVolumeMedioDiario() {
		return volumeMedioDiario;
	}
	public void setVolumeMedioDiario(double volumeMedioDiario) {
		this.volumeMedioDiario = volumeMedioDiario;
	}
	public double getVariacaoValor() {
		return variacaoValor;
	}
	public void setVariacaoValor(double variacaoValor) {
		this.variacaoValor = variacaoValor;
	}
	public String getVariacaoPercentual() {
		return variacaoPercentual;
	}
	public void setVariacaoPercentual(String variacaoPercentual) {
		this.variacaoPercentual = variacaoPercentual;
	}
	public LocalDate getDataUltimaNegociacao() {
		return dataUltimaNegociacao;
	}
	public void setDataUltimaNegociacao(LocalDate dataUltimaNegociacao) {
		this.dataUltimaNegociacao = dataUltimaNegociacao;
	}
	public String getHoraUltimaNegociacao() {
		return horaUltimaNegociacao;
	}
	public void setHoraUltimaNegociacao(String horaUltimaNegociacao) {
		this.horaUltimaNegociacao = horaUltimaNegociacao;
	}
	public double getMenorValorDia() {
		return menorValorDia;
	}
	public void setMenorValorDia(double menorValorDia) {
		this.menorValorDia = menorValorDia;
	}
	public double getMaiorValorDia() {
		return maiorValorDia;
	}
	public void setMaiorValorDia(double maiorValorDia) {
		this.maiorValorDia = maiorValorDia;
	}
	public double getMenorValorAno() {
		return menorValorAno;
	}
	public void setMenorValorAno(double menorValorAno) {
		this.menorValorAno = menorValorAno;
	}
	public double getMaiorValorAno() {
		return maiorValorAno;
	}
	public void setMaiorValorAno(double maiorValorAno) {
		this.maiorValorAno = maiorValorAno;
	}
	public String getCapitalMercado() {
		return capitalMercado;
	}
	public void setCapitalMercado(String capitalMercado) {
		this.capitalMercado = capitalMercado;
	}
	public String getEBITDA() {
		return EBITDA;
	}
	public void setEBITDA(String eBITDA) {
		EBITDA = eBITDA;
	}
	public double getValorAbertura() {
		return valorAbertura;
	}
	public void setValorAbertura(double valorAbertura) {
		this.valorAbertura = valorAbertura;
	}
	public double getUltimoValorTransacao() {
		return ultimoValorTransacao;
	}
	public void setUltimoValorTransacao(double ultimoValorTransacao) {
		this.ultimoValorTransacao = ultimoValorTransacao;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public double getValorAtual() {
		return valorAtual;
	}
	public void setValorAtual(double valorAtual) {
		this.valorAtual = valorAtual;
	}
}
