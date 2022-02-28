package ar.com.mercantilandina.challenge.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "pedidos_cabecera")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoCabecera implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    
    private String direccion;

    private String email;

    private String telefono;

    private LocalTime horario;

    @Column(name = "fecha_alta")
    private LocalDate fechaAlta;

    @Column(name = "monto_total")
    private Double montoTotal;

    @Column(name = "aplico_descuento")
    private Boolean aplicoDescuento;

    private String estado;

    @OneToMany(mappedBy = "pedidoCabecera", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PedidoDetalle> pedidosDetalle = new ArrayList<>();

    // [Pedido Detalle] ACTIVE COMMUNICATION 
    public void addPedidoDetalle(PedidoDetalle pedidoDetalle){
        if(!this.pedidosDetalle.contains(pedidoDetalle)){
            this.pedidosDetalle.add(pedidoDetalle);
            pedidoDetalle.setPedidoCabecera(this);
        }
    }

    // [Pedido Detalle] ACTIVE COMMUNICATION
    public void removePedidoDetalle(PedidoDetalle pedidoDetalle){
        if(this.pedidosDetalle.contains(pedidoDetalle)){
            this.pedidosDetalle.remove(pedidoDetalle);
            pedidoDetalle.setPedidoCabecera(null);
        }
    }
    
}
