package ey.desafio.api.usuarios.util;

import java.util.ArrayList;
import java.util.List;

import ey.desafio.api.usuarios.dom.Telefono;
import ey.desafio.api.usuarios.dom.Usuario;
import ey.desafio.api.usuarios.to.RequestUserCreate;
import ey.desafio.api.usuarios.to.TOPhone;

/**
 * Clase utilitaria para crear datos de prueba para los test
 * 
 * @author dcaceres
 *
 */
public class UtilData {

	/**
	 * Metodo que crea un usuario con telefonos asociados.
	 * 
	 * @return {@link Usuario}
	 */
	public static Usuario createUser() {
		List<Telefono> phones = new ArrayList<>();
		phones.add(Telefono.builder().cityCode("32").countryCode("56").number("972008711").build());
		phones.add(Telefono.builder().cityCode("32").countryCode("56").number("972008712").build());
		Usuario user = Usuario.builder().email("dcaceres@gmail.com").name("David Caceres").password("a_AAAA")
				.phones(phones).build();
		return user;
	}

	/**
	 * Metodo que crea un RequestUser para pruebas
	 * 
	 * @return
	 */
	public static RequestUserCreate createRequestUser() {
		List<TOPhone> phones = new ArrayList<>();
		phones.add(TOPhone.builder().citycode("32").countrycode("56").number("972008711").build());
		phones.add(TOPhone.builder().citycode("32").countrycode("56").number("972008712").build());
		RequestUserCreate user = RequestUserCreate.builder().email("dcaceres@gmail.com").name("David Caceres").password("aaaaAssss33")
				.phones(phones).build();
		return user;
	}

}
