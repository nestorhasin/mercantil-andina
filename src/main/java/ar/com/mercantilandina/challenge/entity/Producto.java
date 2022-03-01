package ar.com.mercantilandina.challenge.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "productos")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Producto implements Serializable {
    
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id", unique = true, nullable = false)
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "BINARY(16)")
    @GeneratedValue
    private UUID id;

    private String nombre;

    private String descripcionCorta;

    private String descripcionLarga;

    private Double precioUnitario;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PedidoDetalle> pedidosDetalle = new ArrayList<>();

    

    public Producto(UUID id, String nombre, String descripcionCorta, String descripcionLarga, Double precioUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcionCorta = descripcionCorta;
        this.descripcionLarga = descripcionLarga;
        this.precioUnitario = precioUnitario;
    }

    // [Pedido Detalle] ACTIVE COMMUNICATION 
    public void addPedidoDetalle(PedidoDetalle pedidoDetalle){
        if(!this.pedidosDetalle.contains(pedidoDetalle)){
            this.pedidosDetalle.add(pedidoDetalle);
            pedidoDetalle.setProducto(this);
        }
    }

    // [Pedido Detalle] ACTIVE COMMUNICATION
    public void removePedidoDetalle(PedidoDetalle pedidoDetalle){
        if(this.pedidosDetalle.contains(pedidoDetalle)){
            this.pedidosDetalle.remove(pedidoDetalle);
            pedidoDetalle.setProducto(null);
        }
    }

}
