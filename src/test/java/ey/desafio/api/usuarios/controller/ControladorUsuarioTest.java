package ey.desafio.api.usuarios.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import ey.desafio.api.usuarios.to.RequestUserCreate;
import ey.desafio.api.usuarios.util.UtilData;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@AutoConfigureMockMvc
@Sql(scripts = "classpath:test-user-load.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Slf4j
class ControladorUsuarioTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper om;

	@BeforeEach
	void setUp() throws Exception {

	}

	@Test
	@DisplayName("Test obtiene usuarios utilizando url")
	void testGetUsers() throws Exception {
		log.debug("Ejecutanto test: testGetUsers");
		mvc.perform(get("/api/v1/usuario").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$", hasSize(1)));

	}

	@Test
	@DisplayName("Test guarda Usuario Ok")
	@Transactional
	@Rollback(false)
	void testPostUserOk() {
		log.debug("Ejecutanto test: testPostUserOk");
		RequestUserCreate ru = UtilData.createRequestUser();
		ru.setEmail(String.format("faldea%s@gmail.com", System.nanoTime()));
		ResultActions resultActions = assertDoesNotThrow(
				() -> mvc.perform(post("/api/v1/usuario").content(om.writeValueAsString(ru))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)));
		assertDoesNotThrow(() -> resultActions.andExpect(status().isCreated()));

	}

	@Test
	@DisplayName("Test guarda Usuario email duplicado")
	@Transactional
	@Rollback(false)
	void testPostUserError() {
		log.debug("Ejecutanto test: testPostUserError");
		RequestUserCreate ru = UtilData.createRequestUser();
		ResultActions resultActions = assertDoesNotThrow(
				() -> mvc.perform(post("/api/v1/usuario").content(om.writeValueAsString(ru))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)));
		assertDoesNotThrow(() -> resultActions.andExpect(status().isBadRequest()));

	}

	@Test
	@DisplayName("Test validacion email existente")
	@Transactional
	@Rollback(false)
	void testPostUserEmailAleadyExist() throws Exception {
		log.debug("Ejecutanto test: testPostUserEmailAleadyExist");
		RequestUserCreate ru = UtilData.createRequestUser();
		ResultActions resultActions = mvc.perform(post("/api/v1/usuario").content(om.writeValueAsString(ru))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		assertDoesNotThrow(() -> resultActions.andExpect(status().isBadRequest()));
	}

	@Test
	@DisplayName("Test eliminacion por email")
	@Transactional
	@Rollback(false)
	void testDeleteUserByEmailOk() throws Exception {
		log.debug("Ejecutanto test: testDeleteUserByEmailOk");
		String email = "dcaceres@gmail.com";

		ResultActions resultActions = mvc
				.perform(delete("/api/v1/usuario/{email}", email).contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isNoContent());
	}

	@Test
	@DisplayName("Test eliminacion por email que no existe en la base de datos")
	@Transactional
	@Rollback(false)
	void testDeleteUserByEmailNotFound() throws Exception {
		log.debug("Ejecutanto test: testDeleteUserByEmailOk");
		String email = "dcaceres1@gmail.com";
		ResultActions resultActions = mvc
				.perform(delete("/api/v1/usuario/{email}", email).contentType(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isNotFound());
	}
}
