package ey.desafio.api.usuarios.jwt.impl;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import ey.desafio.api.usuarios.jwt.ITokenProvider;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider implements ITokenProvider {

	@Value("${token.key}")
	private String secretKey;
	@Value("${token.valid.milliseconds}")
	private long tokenValidityMilliseconds;

	@PostConstruct
	private void init() {
		log.debug("JwtTokenProvider inicializado!!!!");
	}

	@Override
	public String generate(String value) {
		log.debug("Generando token para " + value);
		Date now = new Date();
		Date expiration = new Date(now.getTime() + tokenValidityMilliseconds);

		JWTCreator.Builder jwtBuilder = JWT.create().withSubject(value).withNotBefore(now).withExpiresAt(expiration);
		// @formatter:on
		return jwtBuilder.sign(Algorithm.HMAC256(secretKey));// NOSONAR
	}

}
