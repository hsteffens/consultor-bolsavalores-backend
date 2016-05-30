package br.furb.consultor.usuario;

public enum EnTipoInvestidor {
	
	CONSERVADOR(1), MODERADO(2), AGRESSIVO(3);
	
	private Integer codigo;
	
	EnTipoInvestidor(Integer codigo) {
		this.codigo = codigo;
	}

	public short getCodigo() {
		return codigo.shortValue();
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	
	
}
