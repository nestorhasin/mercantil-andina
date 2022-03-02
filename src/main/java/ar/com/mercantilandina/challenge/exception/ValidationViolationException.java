package ar.com.mercantilandina.challenge.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ValidationViolationException {
    
    private String error;

    public ValidationViolationException(String error){
        setError(error);
    }

}
