package ar.com.mercantilandina.challenge.dto;

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
    
    private Long id;

    // FK pedido_cabecera_id

    // FK producto_id

    private Integer cantidad;

    private Double precioUnitario;

}
