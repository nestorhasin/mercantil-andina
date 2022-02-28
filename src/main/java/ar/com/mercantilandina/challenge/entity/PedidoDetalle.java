package ar.com.mercantilandina.challenge.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_cabecera_id")
    @JsonBackReference
    private PedidoCabecera pedidoCabecera;

    // FK producto_id
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    @JsonBackReference
    private Producto producto;

    private Integer cantidad;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    // [PedidoCabecera] ACTIVE COMMUNICATION
    public void addPedidoCabecera(PedidoCabecera pedidoCabecera){
        if(this.pedidoCabecera == null){
            setPedidoCabecera(pedidoCabecera);
            pedidoCabecera.addPedidoDetalle(this);
        }
    }

    // [Producto] ACTIVE COMMUNICATION
    public void addProducto(Producto producto){
        if(this.producto == null){
            setProducto(producto);
            producto.addPedidoDetalle(this);
        }
    }

    // [Pedido Cabecera] ACTIVE COMMUNICATION
    public void removePedidoCabecera(PedidoCabecera pedidoCabecera){
        if(this.pedidoCabecera.equals(pedidoCabecera)){
            this.pedidoCabecera = null;
            pedidoCabecera.removePedidoDetalle(this);
        }
    }

    // [Producto] ACTIVE COMMUNICATION
    public void removeProducto(Producto producto){
        if(this.producto.equals(producto)){
            this.producto = null;
            producto.removePedidoDetalle(this);
        }
    }

}
