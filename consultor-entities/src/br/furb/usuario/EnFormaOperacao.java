package br.furb.usuario;

public enum EnFormaOperacao {
	
	DAY_TRADE(1), SWING_TRADE(2), POSITION_TRADE(3),BUY_HOLD(4);
	
	private Integer codigo;
	
	EnFormaOperacao(Integer codigo){
		this.codigo = codigo;
	}

	public short getCodigo() {
		return codigo.shortValue();
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	
}
