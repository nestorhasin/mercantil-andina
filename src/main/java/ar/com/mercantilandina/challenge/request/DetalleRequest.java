package ar.com.mercantilandina.challenge.request;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetalleRequest {
    
    private Long producto;

    private Integer cantidad;

}
