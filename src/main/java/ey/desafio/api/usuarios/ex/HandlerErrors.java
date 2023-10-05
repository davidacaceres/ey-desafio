package ey.desafio.api.usuarios.ex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandlerErrors extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, List<TOException>> body = new HashMap<>();

		List<TOException> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).map(this::mapTO).collect(Collectors.toList());
		body.put("errores", errors);

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ExUserNotFound.class)
	public ResponseEntity<Object> handleUserNotFoundException(ExUserNotFound exDeleteUser, WebRequest request) {

		return new ResponseEntity<>(getBody(exDeleteUser.getMessage()), HttpStatus.NOT_FOUND);
	}

//	@ExceptionHandler(ExEmailAlreadyAssign.class)
//	public ResponseEntity<Object> handleEmailAlreadyExistException(ExEmailAlreadyAssign exDeleteUser,
//			WebRequest request) {
//		return new ResponseEntity<>(getBody(exDeleteUser.getMessage()), HttpStatus.BAD_REQUEST);
//	}
//
//	@ExceptionHandler(ExUuidNotFound.class)
//	public ResponseEntity<Object> handleUserNotFoundException(ExUuidNotFound exUuid, WebRequest request) {
//		return new ResponseEntity<>(getBody(exUuid.getMessage()), HttpStatus.BAD_REQUEST);
//	}

	@ExceptionHandler(value = { ExUuidNotFound.class, ExUuidInvalid.class, ExEmailAlreadyAssign.class })
	public ResponseEntity<Object> handleDesafioException(ExDesafio exception, WebRequest request) {
		return new ResponseEntity<>(getBody(exception.getMessage()), HttpStatus.BAD_REQUEST);
	}

	private TOException mapTO(String message) {
		return TOException.builder().mensaje(message).build();
	}

	private Map<String, TOException> getBody(String message) {
		Map<String, TOException> body = new HashMap<>();
		TOException error = TOException.builder().mensaje(message).build();
		body.put("error", error);
		return body;
	}

}
