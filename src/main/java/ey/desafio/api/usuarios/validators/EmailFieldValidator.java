package ey.desafio.api.usuarios.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ey.desafio.api.usuarios.repo.IRepoUsuario;

/**
 * Implementacion de validador de email unico.
 * @author dcaceres
 *
 */
@Component
public class EmailFieldValidator implements ConstraintValidator<EmailFieldUnique, String> {

	@Autowired
	IRepoUsuario repoUser;

	public EmailFieldValidator(IRepoUsuario ruser) {
		this.repoUser = ruser;
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return !repoUser.existsByEmail(email);
	}
}