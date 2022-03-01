package ar.com.mercantilandina.challenge.dto;

import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
public class ProductoDto {
    
    private UUID id;

    @NotBlank(message = "el nombre no puede ser null ni estar en blanco, ingreselo nuevamente por favor")
    private String nombre;

    @NotEmpty(message = "la descripción corta no puede ser null ni estar en blanco, ingresela nuevamente por favor")
    @Size(min = 1, max = 20, message = "superó la cantidad máxima de caracteres, solo tiene 20 caracteres para ingresar en la descripción corta, ingresela nuevamente por favor")
    private String descripcionCorta;

    @NotBlank(message = "la descripción corta no puede ser null ni estar en blanco, ingresela nuevamente por favor")
    private String descripcionLarga;

    @Min(value = 1, message = "el precio unitario tiene que ser mayor o igual a 1, ingresela nuevamente por favor")
    private Double precioUnitario;

}
