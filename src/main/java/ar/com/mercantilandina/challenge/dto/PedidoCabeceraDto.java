package ar.com.mercantilandina.challenge.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import ar.com.mercantilandina.challenge.util.ValidationUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Component

@AllArgsConstructor
@Getter
@Setter
public class PedidoCabeceraDto {

    private LocalDate fecha;
    
    @NotNull(message = ValidationUtil.NOT_NULL)
    @NotEmpty(message = ValidationUtil.NOT_EMPTY)
    private String direccion;

    @NotNull(message = ValidationUtil.NOT_NULL)
    @NotEmpty(message = ValidationUtil.NOT_EMPTY)
    @Email(message = ValidationUtil.EMAIL)
    private String email;

    @Pattern(regexp = "\\([0-9]{4}\\)\\s[0-9]{8}", message = ValidationUtil.PATTERN_TELEFONO)
    private String telefono;

    // Ver porque no valida...
    //@Pattern(regexp = "([0-1]\\d|2[0-3])(:)[0-5]\\d", message = ValidationUtil.PATTERN_HORARIO)
    private LocalTime horario;

    @NotNull(message =  ValidationUtil.NOT_NULL)
    private List<PedidoDetalleDto> detalle = new ArrayList<>();

    private Double total;

    private Boolean descuento;

    private String estado;

    public PedidoCabeceraDto(){
        setEstado("PEDIENTE");
    }

}
