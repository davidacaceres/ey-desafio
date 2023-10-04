package ey.desafio.api.usuarios.to;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que reprensenta un Objeto de transferencia de telefono asignable a un
 * usuario
 * 
 * @author dcaceres
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TOPhone {

	private String uuid;
	@NotBlank(message = "El numero de telefono es requerido.")
	private String number;
	@NotBlank(message = "El codigo de ciudad del telefono es requerido.")
	private String citycode;
	@NotBlank(message = "El codigo de pais del numero de telefono es requerido.")
	private String countrycode;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TOPhone other = (TOPhone) obj;
		return Objects.equals(citycode, other.citycode) && Objects.equals(countrycode, other.countrycode)
				&& Objects.equals(number, other.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(citycode, countrycode, number);
	}

}
