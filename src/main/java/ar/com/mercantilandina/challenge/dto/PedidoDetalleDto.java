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
public class PedidoDetalleDto {

    //
    private UUID producto;

    private String nombre;

    //
    private Integer cantidad;

    private Double importe;

}
