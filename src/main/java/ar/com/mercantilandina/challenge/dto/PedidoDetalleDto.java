package ar.com.mercantilandina.challenge.dto;

import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class PedidoDetalleDto {

    @NotBlank(message = "el id del producto no puede ser null ni estar en blanco, ingreselo nuevamente por favor")
    private UUID producto;

    private String nombre;

    @Min(value = 1, message = "la cantidad tiene que ser mayor o igual a 1, ingresela nuevamente por favor")
    private Integer cantidad;

    private Double importe;

}
