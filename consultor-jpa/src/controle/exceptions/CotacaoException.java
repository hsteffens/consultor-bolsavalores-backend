package controle.exceptions;

public class CotacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String error;
	
	public CotacaoException(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}
	
	
}