package ar.com.mercantilandina.challenge.dto;

import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class ProductoDto {
    
    private UUID id;

    @NotNull(message = ValidationUtil.NOT_NULL)
    @NotEmpty(message = ValidationUtil.NOT_EMPTY)
    private String nombre;

    @NotNull(message = ValidationUtil.NOT_NULL)
    @NotEmpty(message = ValidationUtil.NOT_EMPTY)
    @Size(max = 20, message = ValidationUtil.SIZE_MAX)
    private String descripcionCorta;

    @NotNull(message = ValidationUtil.NOT_NULL)
    @NotEmpty(message = ValidationUtil.NOT_EMPTY)
    private String descripcionLarga;

    @Min(value = 1, message = ValidationUtil.MIN)
    private Double precioUnitario;

}
