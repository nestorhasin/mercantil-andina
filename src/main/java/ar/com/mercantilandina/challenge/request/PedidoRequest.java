package ar.com.mercantilandina.challenge.request;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.com.mercantilandina.challenge.entity.PedidoDetalle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoRequest {
    
    private String direccion;

    private String email;

    private String telefono;

    private LocalTime horario;

    private List<DetalleRequest> detalle;

}
