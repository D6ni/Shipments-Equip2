package cat.institutmarianao.shipmentsws.exception.handlers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cat.institutmarianao.shipmentsws.exception.ForbiddenException;
import cat.institutmarianao.shipmentsws.exception.NotFoundException;
import cat.institutmarianao.shipmentsws.exception.model.ErrorResponse;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Argument not valid: error handle for @Valid
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		ErrorResponse errorResponse = new ErrorResponse(status, ex.getBindingResult());
		return new ResponseEntity<>(errorResponse.getBody(), headers, status);
	}

	/**
	 * Message not readable
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		ErrorResponse errorResponse = new ErrorResponse(status, ex.getLocalizedMessage());
		return new ResponseEntity<>(errorResponse.getBody(), headers, status);
	}

	/**
	 * For validating Path Variables and Request Parameters
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getLocalizedMessage());
		return new ResponseEntity<>(errorResponse.getBody(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	/**
	 * Any other exception
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
		return new ResponseEntity<>(errorResponse.getBody(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Custom ResourceNotFoundException handler
	 */
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(NotFoundException e) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, "Not found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_PROBLEM_JSON)
				.body(errorResponse);
	}

	/**
	 * Custom ResourceNotFoundException handler
	 */
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(ForbiddenException e) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN, "Forbidden");
		return ResponseEntity.status(HttpStatus.FORBIDDEN).contentType(MediaType.APPLICATION_PROBLEM_JSON)
				.body(errorResponse);
	}
}
