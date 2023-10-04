package ey.desafio.api.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication

public class AppUsuario extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AppUsuario.class, args);
	}

}
