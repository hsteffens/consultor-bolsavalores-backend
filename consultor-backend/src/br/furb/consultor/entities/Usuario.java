package br.furb.consultor.entities;

public class Usuario {
	
	private long codigo;
	private long cpf;
	private String nome;
	private String profissao;
	private String email;
	private short tipoInvestidor;
	private short tipoTransacao;
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public short getTipoInvestidor() {
		return tipoInvestidor;
	}
	public void setTipoInvestidor(short tipoInvestidor) {
		this.tipoInvestidor = tipoInvestidor;
	}
	public short getTipoTransacao() {
		return tipoTransacao;
	}
	public void setTipoTransacao(short tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
	
	
}
