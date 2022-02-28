package ar.com.mercantilandina.challenge.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pedidos_detalle")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoDetalle implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    // FK pedido_cabecera_id

    // FK producto_id

    private Integer cantidad;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

}
