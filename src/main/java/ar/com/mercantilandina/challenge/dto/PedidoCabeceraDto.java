package ar.com.mercantilandina.challenge.dto;

import java.time.LocalDate;
import java.time.LocalTime;

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
    
    private Long id;
    
    private String direccion;

    private String email;

    private String telefono;

    private LocalTime horario;

    private LocalDate fechaAlta;

    private Double montoTotal;

    private Boolean aplicoDescuento;

    private String estado;

}
