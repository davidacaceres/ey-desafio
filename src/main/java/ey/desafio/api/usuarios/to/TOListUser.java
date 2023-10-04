package ey.desafio.api.usuarios.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que se utiliza para la lista de usuarios.
 * 
 * @author dcaceres
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TOListUser {
	
	/**
	 * id
	 */
	private String uuid;
	
	/**
	 * Nombre
	 */
	private String name;

	/**
	 * Email
	 */
	private String email;

	/**
	 * Telefonos
	 */
	private String token;
}
