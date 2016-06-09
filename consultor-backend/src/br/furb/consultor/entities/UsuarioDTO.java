package br.furb.consultor.entities;

public class UsuarioDTO {

	private String userName;
	private String password;
	private Long cpf;
	private String nome;
	private String email;
	private String profissao;
	private Integer perfilInvestidor;
	private Integer formaOperacao;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public Integer getPerfilInvestidor() {
		return perfilInvestidor;
	}
	public void setPerfilInvestidor(Integer perfilInvestidor) {
		this.perfilInvestidor = perfilInvestidor;
	}
	public Integer getFormaOperacao() {
		return formaOperacao;
	}
	public void setFormaOperacao(Integer formaOperacao) {
		this.formaOperacao = formaOperacao;
	}
	
}
