package co.cafeto.EventManager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class AWSFailUploadException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_MESSAGE = "Error uploading file.";

	public AWSFailUploadException() {
		super(DEFAULT_MESSAGE);
	}

	public AWSFailUploadException(String message) {
		super(message);
	}
}