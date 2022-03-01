package ar.com.mercantilandina.challenge.dto;

import java.util.UUID;

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

    private String nombre;

    private String descripcionCorta;

    private String descripcionLarga;

    private Double precioUnitario;

}
