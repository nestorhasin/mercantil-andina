package ar.com.mercantilandina.challenge.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChallengeException extends RuntimeException {
    
    private HttpStatus status;
    private String message;

    public ChallengeException(HttpStatus status, String message, String newMessage){
        setStatus(status);
        setMessage(message);
        setMessage(newMessage);
    }

}
