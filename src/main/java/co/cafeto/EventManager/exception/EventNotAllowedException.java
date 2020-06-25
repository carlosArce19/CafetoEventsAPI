package co.cafeto.EventManager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class EventNotAllowedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MESSAGE = "Error: Not allowed to operate over Event.";

	public EventNotAllowedException() {
		super(DEFAULT_MESSAGE);
	}

	public EventNotAllowedException(String message) {
		super(message);
	}
}