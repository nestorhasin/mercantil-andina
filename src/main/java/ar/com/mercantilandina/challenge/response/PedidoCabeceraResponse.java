package ar.com.mercantilandina.challenge.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import ar.com.mercantilandina.challenge.dto.PedidoDetalleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoCabeceraResponse {
    
    private LocalDate fecha;
    
    private String direccion;

    private String email;

    private String telefono;

    private LocalTime horario;

    private List<PedidoDetalleDto> detalle = new ArrayList<>();

    private Double total;

    private Boolean descuento;

}
