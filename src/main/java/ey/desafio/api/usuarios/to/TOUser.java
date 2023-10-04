package ey.desafio.api.usuarios.to;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TOUser {

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
	 * Token
	 */
	private String token;
	/**
	 * Password
	 */
	private String password;

	private List<TOPhone> phones;

}
