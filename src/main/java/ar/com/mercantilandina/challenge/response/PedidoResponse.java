package ar.com.mercantilandina.challenge.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
public class PedidoResponse {
    
    private LocalDate fecha;

    private String direccion;

    private String email;

    private String telefono;

    private LocalTime horario;

    private List<DetalleResponse> detalle;

    private Double total;

    private Boolean descuento;

    private String estado;

}
