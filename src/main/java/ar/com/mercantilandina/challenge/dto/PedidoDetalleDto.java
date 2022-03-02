package ar.com.mercantilandina.challenge.dto;

import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import ar.com.mercantilandina.challenge.util.ValidationUtil;
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

    @NotNull(message = ValidationUtil.NOT_NULL)
    @NotEmpty(message = ValidationUtil.NOT_EMPTY)
    private UUID producto;

    private String nombre;

    @Min(value = 1, message = ValidationUtil.MIN)
    private Integer cantidad;

    private Double importe;

}
