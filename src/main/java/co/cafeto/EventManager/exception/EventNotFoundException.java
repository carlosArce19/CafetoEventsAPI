package co.cafeto.EventManager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EventNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MESSAGE = "Error: Event not found.";

	public EventNotFoundException() {
		super(DEFAULT_MESSAGE);
	}

	public EventNotFoundException(String message) {
		super(message);
	}
}