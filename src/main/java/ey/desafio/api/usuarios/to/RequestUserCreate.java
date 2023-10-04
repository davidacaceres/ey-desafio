package ey.desafio.api.usuarios.to;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;

import ey.desafio.api.usuarios.validators.EmailFieldUnique;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un Objeto de transferencia para la creacion de un
 * usuario
 * 
 * @author dcaceres
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestUserCreate {

	/**
	 * Nombre
	 */
	@NotBlank(message = "El nombre es requerido.")
	private String name;

	/**
	 * Email
	 */
	@NotBlank(message = "El email es requerido.")
	@Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$", flags = {
			Flag.CASE_INSENSITIVE }, message = "debe ingresar un email valido en formato aaaaa@dominio.cl")
	@EmailFieldUnique
	private String email;
	/**
	 * Clave
	 */
	@NotBlank(message = "La passwrod es requerida.")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d.*\\d)[A-Za-z\\d]*$", flags = {
			Flag.CASE_INSENSITIVE }, message = " la password debe contener Una Mayuscula, letras minúsculas, y dos numeros")
	private String password;
	/**
	 * Telefonos
	 */
	private List<TOPhone> phones;

}
