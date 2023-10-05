package ey.desafio.api.usuarios.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import ey.desafio.api.usuarios.dom.Usuario;
import ey.desafio.api.usuarios.util.UtilData;

/**
 * Test que revisa la correcta implementacion de la capa de persistencia.
 * 
 * @author dcaceres
 *
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "classpath:test-user-load.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class RepoUserTest {

	@Autowired
	private IRepoUsuario repo;

	@BeforeEach
	void setUp() throws Exception {
	}

	@DisplayName("Test lista de usuarios")
	@Test
	void testFindAll() throws Exception {
		List<Usuario> list = assertDoesNotThrow(() -> repo.findAll());
		assertEquals(1, list.size());
	}

	/**
	 * Test Repositorio guardar ok
	 * 
	 * @throws Exception
	 */
	@DisplayName("Test verifica almacenamiento correcto en basde de datos")
	@Test
	void testSaveOk() throws Exception {
		Usuario user = UtilData.createUser();
		user.setEmail("lramirezu@gmail.com");
		Usuario result = assertDoesNotThrow(() -> repo.saveAndFlush(user));
		assertNotNull(result);
		assertThat(user.getIdUser()).isNotNull();
	}

	/**
	 * Test repositorio guardar
	 * 
	 * @throws Exception cuando existen problemas para realizar la consulta al
	 *                   repositorio
	 */
	@DisplayName("Test verifica validacion de email unico")
	@Test
	void testSaveDuplicatedEmail() throws Exception {
		Usuario user = UtilData.createUser();

		assertThrows(DataIntegrityViolationException.class, () -> repo.saveAndFlush(user));
	}

	/**
	 * Test repositorio busqueda email existente
	 * 
	 * @throws Exception cuando existen problemas para realizar la consulta al
	 *                   repositorio
	 */
	@DisplayName("Test verifica busqueda por email encontrado")
	@Test
	void testExistByEmail() throws Exception {
		Boolean find = assertDoesNotThrow(() -> repo.existsByEmail("dcaceres@gmail.com"));
		assertEquals(true, find);
	}

	/**
	 * Test repositorio busqueda email no existe
	 * 
	 * @throws Exception cuando existen problemas para realizar la consulta al
	 *                   repositorio
	 */
	@DisplayName("Test verifica busqueda por email no encontrado")
	@Test
	void testNoExistByEmail() {
		assertEquals(false, repo.existsByEmail("dcaceres_1@gmail.com"));
	}

	/**
	 * Test repositorio elimina email no existe
	 * 
	 * @throws Exception cuando existen problemas para realizar la consulta al
	 *                   repositorio
	 */
	@DisplayName("Test eliminacion email valido")
	@Test
	void testEliminarByEmailOk() {
		assertEquals(1, repo.deleteByEmail("dcaceres@gmail.com"));
	}

	/**
	 * test para validar respuesta de email no existe.
	 */
	@DisplayName("Test eliminacion email no existente")
	@Test
	void testEliminarByEmailNotFound() {
		assertEquals(0, repo.deleteByEmail("dcaceres1@gmail.com"));

	}

}
