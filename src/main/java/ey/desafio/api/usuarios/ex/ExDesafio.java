package ey.desafio.api.usuarios.ex;

public class ExDesafio extends Exception {

	private static final long serialVersionUID = -7917182793280610227L;

	public ExDesafio(String message, Throwable cause) {
		super(message, cause);
	}

	public ExDesafio(String message) {
		super(message);
	}

}
