package br.furb.consultor.entities;

import org.joda.time.LocalDate;

public class TokenDTO {

	private String token;
	private LocalDate dataExpiracao;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDate getDataExpiracao() {
		return dataExpiracao;
	}
	public void setDataExpiracao(LocalDate dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}
	
	
}
