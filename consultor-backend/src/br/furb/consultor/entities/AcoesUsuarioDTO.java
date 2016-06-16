package br.furb.consultor.entities;

public class AcoesUsuarioDTO {
	private String acao;
	private Integer quant;
	private Float preco;
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public Integer getQuant() {
		return quant;
	}
	public void setQuant(Integer quant) {
		this.quant = quant;
	}
	public Float getPreco() {
		return preco;
	}
	public void setPreco(Float preco) {
		this.preco = preco;
	}
	
}
