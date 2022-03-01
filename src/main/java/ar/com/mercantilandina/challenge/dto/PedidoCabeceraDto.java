package ar.com.mercantilandina.challenge.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Component

@AllArgsConstructor
@Getter
@Setter
public class PedidoCabeceraDto {

    private LocalDate fecha;
    
    @NotBlank(message = "la dirección no puede ser null ni estar en blanco, ingresela nuevamente por favor")
    @Size(min = 5, message = "la dirección tiene que tener como mínimo 5 caracteres, ingresela nuevamente por favor")
    private String direccion;

    @NotBlank(message = "el email no puede ser null ni estar en blanco, ingreselo nuevamente por favor")
    @Email(message = "el formato del email ingresado no es válido, tenga en cuenta un formato igual a nombre@dominio.extensión")
    private String email;

    @Pattern(regexp = "\\([0-9]{4}\\)\\s[0-9]{8}", message = "el formato del teléfono ingresado no es válido, tenga en cuenta un formato igual a (xxxx) xxxxxxxx siendo x un número entre 0 y 9")
    private String telefono;

    //@Pattern(regexp = "([0-1]\\d|2[0-3])(:)[0-5]\\d", message = "el horario ingresado no se encuentra dentro de los parámetros válidos, tenga en cuenta a la hora en un rango de 00:00 a 23:59")
    private LocalTime horario;

    @Size(min = 1, message = "no se olvide de ingresar los datos del producto para completar el pedido, vuelva a intentarlo")
    private List<PedidoDetalleDto> detalle = new ArrayList<>();

    private Double total;

    private Boolean descuento;

    private String estado;

    
    public PedidoCabeceraDto(){
        setEstado("PEDIENTE");
    }

}
