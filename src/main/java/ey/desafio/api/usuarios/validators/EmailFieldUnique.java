package ey.desafio.api.usuarios.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Anotacion para validar email unico.
 * 
 * @author dcaceres
 *
 */
@Constraint(validatedBy = EmailFieldValidator.class)
@Target(value = { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailFieldUnique {
	String message() default "El correo ya registrado";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
