package ar.com.mercantilandina.challenge.exception;

import java.util.Comparator;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ar.com.mercantilandina.challenge.response.ProductNotFoundResponse;
import ar.com.mercantilandina.challenge.response.ValidationViolationResponse;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> manageResourceNotFoundException(ProductNotFoundException recursoNoEncontradoException, WebRequest webRequest){
        ProductNotFoundResponse exceptionDetailsResponse = new ProductNotFoundResponse(recursoNoEncontradoException.getMessage());        
        return new ResponseEntity<>(exceptionDetailsResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> manageException(Exception exception, WebRequest webRequest){
        ProductNotFoundResponse exceptionDetailsResponse = new ProductNotFoundResponse(exception.getMessage());
        return new ResponseEntity<>(exceptionDetailsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
            ValidationViolationResponse errores = new ValidationViolationResponse();
            ex.getBindingResult().getAllErrors().forEach(error -> {
                String field = ((FieldError)error).getField();
                String message = error.getDefaultMessage();
                errores.addError(new ValidationViolationException(field + " " + message));
                errores.getErrores().sort(Comparator.comparing(a -> a.getError()));
            });
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

}
