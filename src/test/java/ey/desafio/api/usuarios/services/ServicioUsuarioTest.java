package ey.desafio.api.usuarios.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import ey.desafio.api.usuarios.services.impl.ServicioUsuario;
import ey.desafio.api.usuarios.to.RequestUserCreate;
import ey.desafio.api.usuarios.to.ResponseUser;
import ey.desafio.api.usuarios.to.TOListUser;
import ey.desafio.api.usuarios.util.UtilData;
import lombok.extern.slf4j.Slf4j;

/**
 * Pruebas del servicio de usuario
 * 
 * @author dcaceres
 *
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Sql(scripts = "classpath:test-user-load.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Slf4j
class ServicioUsuarioTest {

	@Autowired
	private ServicioUsuario service;

	@BeforeEach
	void setUp() throws Exception {

	}

	@Test
	@DisplayName("Test obtiene usuarios")
	void testGetUsers() {
		log.debug("testGetUsers");
		List<TOListUser> response = assertDoesNotThrow(() -> service.getUsers());
		assertEquals(1, response.size());
	}

	@Test
	@DisplayName("Test guarda usuario")

	void testSaveUsersOk() {
		log.debug("testSaveUsersOk");
		RequestUserCreate req = UtilData.createRequestUser();
		req.setEmail("dcaceres@yahoo.com");
		ResponseUser user = assertDoesNotThrow(() -> service.saveUser(req));
        assertThat(user.getId()).isNotNull();
	}

	@Test
	@DisplayName("Test error al guardar usuario con email existente")
	void testSaveUsersError() throws Exception {
		log.debug("testSaveUsersError");
		RequestUserCreate req = UtilData.createRequestUser();
		req.setEmail("dcaceres@gmail.com");
		assertThrows(DataIntegrityViolationException.class, () -> service.saveUser(req));
	}

}
