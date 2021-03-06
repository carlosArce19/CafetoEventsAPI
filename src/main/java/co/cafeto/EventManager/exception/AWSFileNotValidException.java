package co.cafeto.EventManager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AWSFileNotValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MESSAGE = "File not valid to upload.";

	public AWSFileNotValidException() {
		super(DEFAULT_MESSAGE);
	}

	public AWSFileNotValidException(String message) {
		super(message);
	}
}