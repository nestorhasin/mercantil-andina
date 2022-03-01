package ar.com.mercantilandina.challenge.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
public class PedidoCabeceraDto {

    private LocalDate fecha;
    
    //
    private String direccion;

    //
    private String email;

    //
    private String telefono;

    //
    private LocalTime horario;

    //
    private List<PedidoDetalleDto> detalle = new ArrayList<>();

    private Double total;

    private Boolean descuento;

    private String estado;

}
