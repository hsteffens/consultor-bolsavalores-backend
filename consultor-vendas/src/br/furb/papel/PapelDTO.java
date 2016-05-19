package br.furb.papel;

import org.joda.time.LocalDateTime;

public class PapelDTO {

		private String codigo;
		private String nome;
		private String especificacao;
		private LocalDateTime data;
		private double preco;
		private double oscilacao;
		public String getCodigo() {
			return codigo;
		}
		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}
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
		public double getPreco() {
			return preco;
		}
		public void setPreco(double preco) {
			this.preco = preco;
		}
		public double getOscilacao() {
			return oscilacao;
		}
		public void setOscilacao(double oscilacao) {
			this.oscilacao = oscilacao;
		}
		
}
