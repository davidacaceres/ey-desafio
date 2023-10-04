package ey.desafio.api.usuarios.ex;

/**
 * Excepcion cuando no se puede eliminar un usuario
 * 
 * @author dcaceres
 *
 */
public class ExEmailAlreadyAssign extends ExDesafio {

	private static final long serialVersionUID = -2420892473720413377L;

	public ExEmailAlreadyAssign(String message, Throwable e) {
		super(message, e);
	}

	public ExEmailAlreadyAssign(String string) {
		super(string);
	}
}
