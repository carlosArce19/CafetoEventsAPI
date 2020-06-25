package co.cafeto.EventManager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class JWTFormatException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MESSAGE = "JWT Token not valid";

	public JWTFormatException() {
		super(DEFAULT_MESSAGE);
	}

	public JWTFormatException(String message) {
		super(message);
	}
}
