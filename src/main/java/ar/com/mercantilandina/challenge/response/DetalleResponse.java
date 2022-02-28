package ar.com.mercantilandina.challenge.response;

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
public class DetalleResponse {
    
    private Long producto;

    private String nombre;

    private Integer cantidad;

    private Double importe;

}
