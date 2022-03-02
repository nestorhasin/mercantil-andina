package ar.com.mercantilandina.challenge.response;

import java.util.ArrayList;
import java.util.List;

import ar.com.mercantilandina.challenge.exception.ValidationViolationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ValidationViolationResponse {
    
    private List<ValidationViolationException> errores;

    public void addError(ValidationViolationException error){
        if(errores == null){
            errores = new ArrayList<>();
        }
        this.errores.add(error);
    }

}
