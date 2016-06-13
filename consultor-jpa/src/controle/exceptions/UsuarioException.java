package controle.exceptions;

public class UsuarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String error;
	
	public UsuarioException(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}
	
	
}
