package ey.desafio.api.usuarios.to;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un Objeto de transferencia para usuario
 * 
 * @author dcaceres
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseUser {

	private String id;
	private Date created;
	private Date modified;
	private Date last_login;
	private String token;
	private boolean isactive;

	private TOUser user;

}
