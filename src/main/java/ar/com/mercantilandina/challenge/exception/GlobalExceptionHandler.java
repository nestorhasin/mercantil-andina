package ar.com.mercantilandina.challenge.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ar.com.mercantilandina.challenge.response.ExceptionDetailsResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<?> manageResourceNotFoundException(RecursoNoEncontradoException recursoNoEncontradoException, WebRequest webRequest){
        ExceptionDetailsResponse exceptionDetailsResponse = new ExceptionDetailsResponse(recursoNoEncontradoException.getMessage());        
        return new ResponseEntity<>(exceptionDetailsResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ChallengeException.class)
    public ResponseEntity<?> manageChallengeException(ChallengeException challengeException, WebRequest webRequest){
        ExceptionDetailsResponse exceptionDetailsResponse = new ExceptionDetailsResponse(challengeException.getMessage());
        return new ResponseEntity<>(exceptionDetailsResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> manageException(Exception exception, WebRequest webRequest){
        ExceptionDetailsResponse exceptionDetailsResponse = new ExceptionDetailsResponse(exception.getMessage());
        return new ResponseEntity<>(exceptionDetailsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach(error -> {
                String field = ((FieldError)error).getField();
                String message = error.getDefaultMessage();
                errors.put(field, message);
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
