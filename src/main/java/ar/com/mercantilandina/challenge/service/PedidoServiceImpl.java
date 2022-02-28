package ar.com.mercantilandina.challenge.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import ar.com.mercantilandina.challenge.dto.PedidoDetalleDto;
import ar.com.mercantilandina.challenge.entity.PedidoCabecera;
import ar.com.mercantilandina.challenge.entity.PedidoDetalle;
import ar.com.mercantilandina.challenge.entity.Producto;
import ar.com.mercantilandina.challenge.exception.RecursoNoEncontradoException;
import ar.com.mercantilandina.challenge.repository.IPedidoCabeceraRepository;
import ar.com.mercantilandina.challenge.repository.IPedidoDetalleRepository;
import ar.com.mercantilandina.challenge.repository.IProductoRespository;
import ar.com.mercantilandina.challenge.request.DetalleRequest;
import ar.com.mercantilandina.challenge.request.PedidoRequest;
import ar.com.mercantilandina.challenge.response.DetalleResponse;
import ar.com.mercantilandina.challenge.response.PedidoResponse;
import ar.com.mercantilandina.challenge.service.interfaces.IPedidoService;

@Service
@Validated
public class PedidoServiceImpl implements IPedidoService{

    @Autowired
    private IProductoRespository iProductoRespository;

    @Autowired
    private IPedidoCabeceraRepository iPedidoCabeceraRepository;

    @Autowired
    private IPedidoDetalleRepository iPedidoDetalleRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PedidoDetalle createPedidoDetalle(DetalleRequest detalleRequest) {
        // ALMACENO DATOS NECESARIOS
        Long id = detalleRequest.getProducto();
        Integer cantidad = detalleRequest.getCantidad();
        // BUSCO PRODUCTO
        Producto producto = iProductoRespository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Producto", "id", id));
        // ARMO EL OBJETO PEDIDO DETALLE
        PedidoDetalle pedidoDetalle = new PedidoDetalle();
            pedidoDetalle.setCantidad(cantidad);
            pedidoDetalle.setPrecioUnitario(producto.getPrecioUnitario());
        // LINKEO PRODUCTO CON PEDIDO DETALLE
        pedidoDetalle.addProducto(producto);
        // GUARDO EN LA TABLA 'pedidos_detalle' EL OBJETO ARMADO
        PedidoDetalle pedidoDetalleGuardado = iPedidoDetalleRepository.save(pedidoDetalle);
        // RETORNO EL OBJETO
        return pedidoDetalleGuardado;
    }

    public PedidoResponse create(PedidoRequest pedidoRequest) {
        // DECLARO VARIABLES QUE LUEGO NECESITO
        Double monto = 0.0;
        Double total = 0.0;
        Integer cantidad = 0;
        List<DetalleResponse> listDetalle = new ArrayList<>();
        // DECLARO EL OBJETO PARA LUEGO COMPLETARLO Y GUARDARLO EN LA BASE DE DATOS
        PedidoCabecera pedidoCabecera = new PedidoCabecera();
        for(DetalleRequest detalleRequest: pedidoRequest.getDetalle()){
            Producto producto = iProductoRespository.findById(detalleRequest.getProducto()).orElseThrow(() -> new RecursoNoEncontradoException("Producto", "id", detalleRequest.getProducto()));
            //
            monto = producto.getPrecioUnitario() * detalleRequest.getCantidad();
            total += monto;
            cantidad += detalleRequest.getCantidad();
            //
            DetalleResponse detalleResponse = new DetalleResponse(producto.getId(), producto.getNombre(), detalleRequest.getCantidad(), monto);
            listDetalle.add(detalleResponse);
            //
            pedidoCabecera.addPedidoDetalle(createPedidoDetalle(detalleRequest));
        }
        // RARO
            if(cantidad > 3){
                total *= 0.7;
            }
        // ARMO EL OBJETO PEDIDO CABECERA
            pedidoCabecera.setDireccion(pedidoRequest.getDireccion());
            pedidoCabecera.setEmail(pedidoRequest.getEmail());
            pedidoCabecera.setTelefono(pedidoRequest.getTelefono());
            pedidoCabecera.setHorario(pedidoRequest.getHorario());
            pedidoCabecera.setFechaAlta(LocalDate.now());
            // RARO
                pedidoCabecera.setMontoTotal(total);
                pedidoCabecera.setAplicoDescuento(cantidad > 3);
                pedidoCabecera.setEstado("PENDIENTE");
        // GUARDO EN LA TABLE 'pedidos_cabecera' EL OBJETO ARMADO
        PedidoCabecera pedidoCabeceraGuardado = iPedidoCabeceraRepository.save(pedidoCabecera);
        // ARMO LA RESPUESTA
        PedidoResponse pedidoResponse = new PedidoResponse();
            pedidoResponse.setFecha(LocalDate.now());
            pedidoResponse.setDireccion(pedidoRequest.getDireccion());
            pedidoResponse.setEmail(pedidoRequest.getEmail());
            pedidoResponse.setTelefono(pedidoRequest.getTelefono());
            pedidoResponse.setHorario(pedidoRequest.getHorario());
            pedidoResponse.setDetalle(listDetalle);
            pedidoResponse.setTotal(pedidoCabeceraGuardado.getMontoTotal());
            pedidoResponse.setDescuento(pedidoCabeceraGuardado.getAplicoDescuento());
            pedidoResponse.setEstado(pedidoCabeceraGuardado.getEstado());
        // RETORNO LA RESPUESTA ARMADA
        return pedidoResponse;
    }

    @Override
    public List<PedidoResponse> readByFecha(String fecha) {
        List<PedidoResponse> pedidoResponses = new ArrayList<>();
        // TRAIGO LOS PEDIDOS DE UNA FECHA ESPECÍFICA DEL REPO
        LocalDate localDate = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<PedidoCabecera> pedidoCabeceras = iPedidoCabeceraRepository.findAllByFechaAlta(localDate);
        for(PedidoCabecera pedidoCabecera: pedidoCabeceras){
            // DECLARO ALGO QUE ME SIRVE LUEGO
            List<DetalleResponse> listDetalle = new ArrayList<>();
            PedidoResponse pedidoResponse = new PedidoResponse();
            pedidoResponse.setFecha(pedidoCabecera.getFechaAlta());
            pedidoResponse.setDireccion(pedidoCabecera.getDireccion());
            pedidoResponse.setEmail(pedidoCabecera.getEmail());
            pedidoResponse.setTelefono(pedidoCabecera.getTelefono());
            pedidoResponse.setHorario(pedidoCabecera.getHorario());
            List<PedidoDetalle> pedidoDetalles = pedidoCabecera.getPedidosDetalle();
            for(PedidoDetalle pedidoDetalle: pedidoDetalles){
                DetalleResponse detalleResponse = new DetalleResponse(pedidoDetalle.getProducto().getId(), pedidoDetalle.getProducto().getNombre(), pedidoDetalle.getCantidad(), (pedidoDetalle.getCantidad() * pedidoDetalle.getPrecioUnitario()));
                listDetalle.add(detalleResponse);
            }
            pedidoResponse.setDetalle(listDetalle);
            pedidoResponse.setTotal(pedidoCabecera.getMontoTotal());
            pedidoResponse.setDescuento(pedidoCabecera.getAplicoDescuento());
            pedidoResponses.add(pedidoResponse);
        }
        return pedidoResponses;
    }
    
}
