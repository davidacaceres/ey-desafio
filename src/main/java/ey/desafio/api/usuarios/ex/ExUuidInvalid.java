package ey.desafio.api.usuarios.ex;

/**
 * Excepcion cuando no se puede eliminar un usuario
 * 
 * @author dcaceres
 *
 */
public class ExUuidInvalid extends ExDesafio {

	private static final long serialVersionUID = -2420892473720413377L;

	public ExUuidInvalid(String message, Throwable e) {
		super(message, e);
	}

	public ExUuidInvalid(String string) {
		super(string);
	}
}
