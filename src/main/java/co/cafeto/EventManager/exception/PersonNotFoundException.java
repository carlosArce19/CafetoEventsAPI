package co.cafeto.EventManager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MESSAGE = "Error: Person not found.";

	public PersonNotFoundException() {
		super(DEFAULT_MESSAGE);
	}

	public PersonNotFoundException(String message) {
		super(message);
	}
}