package entities;

import org.joda.time.LocalDate;

public class AcaoBolsaValores {

	private String codigo;
	private String nomeAcao;
	private double volumeMedioDiario;
	private double valorAtual;
	private double variacaoValor;
	private double variacaoPercentual;
	private EnTipoMoeda moeda;
	private LocalDate dataUltimaNegociacao;
	private int horaUltimaNegociacao;
	private double menorValorDia;
	private double maiorValorDia;
	private double menorValorAno;
	private double maiorValorAno;
	private String capitalMercado;
	private String EBITDA;
	private double valorAbertura;
	private double ultimoValorFechamento;
	private double percentual;
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
	public double getVariacaoPercentual() {
		return variacaoPercentual;
	}
	public void setVariacaoPercentual(double variacaoPercentual) {
		this.variacaoPercentual = variacaoPercentual;
	}
	public EnTipoMoeda getMoeda() {
		return moeda;
	}
	public void setMoeda(EnTipoMoeda moeda) {
		this.moeda = moeda;
	}
	public LocalDate getDataUltimaNegociacao() {
		return dataUltimaNegociacao;
	}
	public void setDataUltimaNegociacao(LocalDate dataUltimaNegociacao) {
		this.dataUltimaNegociacao = dataUltimaNegociacao;
	}
	public int getHoraUltimaNegociacao() {
		return horaUltimaNegociacao;
	}
	public void setHoraUltimaNegociacao(int horaUltimaNegociacao) {
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
	public double getUltimoValorFechamento() {
		return ultimoValorFechamento;
	}
	public void setUltimoValorFechamento(double ultimoValorFechamento) {
		this.ultimoValorFechamento = ultimoValorFechamento;
	}
	public double getPercentual() {
		return percentual;
	}
	public void setPercentual(double percentual) {
		this.percentual = percentual;
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