package workshop.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseEntityException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MyException.class)
	public ResponseEntity<ErrorInfo> handleCustomException(MyException myException, WebRequest webRequest) {
		ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), myException.getLocalizedMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
	}
}
