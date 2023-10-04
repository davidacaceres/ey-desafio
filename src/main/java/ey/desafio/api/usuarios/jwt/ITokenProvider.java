package ey.desafio.api.usuarios.jwt;

public interface ITokenProvider {

	/**
	 * Metodo que genera token en base al parametro
	 * 
	 * @param value
	 * @return
	 */
	String generate(String value);

}
