package ar.com.mercantilandina.challenge.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import ar.com.mercantilandina.challenge.dto.PedidoCabeceraDto;
import ar.com.mercantilandina.challenge.dto.PedidoDetalleDto;
import ar.com.mercantilandina.challenge.entity.PedidoCabecera;
import ar.com.mercantilandina.challenge.entity.PedidoDetalle;
import ar.com.mercantilandina.challenge.entity.Producto;
import ar.com.mercantilandina.challenge.exception.RecursoNoEncontradoException;
import ar.com.mercantilandina.challenge.repository.IPedidoCabeceraRepository;
import ar.com.mercantilandina.challenge.repository.IPedidoDetalleRepository;
import ar.com.mercantilandina.challenge.repository.IProductoRespository;
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

    public PedidoDetalle createPedidoDetalle(PedidoDetalleDto pedidoDetalleDto) {
        UUID id = pedidoDetalleDto.getProducto();
        Integer cantidad = pedidoDetalleDto.getCantidad();
        
        Producto producto = iProductoRespository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Producto", "id", id));
        
        PedidoDetalle pedidoDetalle = new PedidoDetalle();
            pedidoDetalle.setCantidad(cantidad);
            pedidoDetalle.setPrecioUnitario(producto.getPrecioUnitario());
        
        // LINKEO PRODUCTO CON PEDIDO DETALLE
        pedidoDetalle.addProducto(producto);
        
        // GUARDO EN LA TABLA 'pedidos_detalle' EL OBJETO ARMADO
        PedidoDetalle pedidoDetalleGuardado = iPedidoDetalleRepository.save(pedidoDetalle);
        
        return pedidoDetalleGuardado;
    }

    public PedidoCabeceraDto createPedidoCabeceraDto(PedidoCabeceraDto pedidoCabeceraDto, PedidoCabecera pedidoCabecera, List<PedidoDetalleDto> pedidoDetalleDtos){
        pedidoCabeceraDto.setDireccion(pedidoCabecera.getDireccion());
        pedidoCabeceraDto.setEmail(pedidoCabecera.getEmail());
        pedidoCabeceraDto.setTelefono(pedidoCabecera.getTelefono());
        pedidoCabeceraDto.setHorario(pedidoCabecera.getHorario());
        pedidoCabeceraDto.setFecha(pedidoCabecera.getFechaAlta());
		pedidoCabeceraDto.setDetalle(pedidoDetalleDtos);
		pedidoCabeceraDto.setTotal(pedidoCabecera.getMontoTotal());
        pedidoCabeceraDto.setDescuento(pedidoCabecera.getAplicoDescuento());
        pedidoCabeceraDto.setEstado(pedidoCabecera.getEstado());
        return pedidoCabeceraDto;
    }

    public PedidoCabecera createPedidoCabecera(PedidoCabecera pedidoCabecera, PedidoCabeceraDto pedidoCabeceraDto){
        pedidoCabecera.setDireccion(pedidoCabeceraDto.getDireccion());
        pedidoCabecera.setEmail(pedidoCabeceraDto.getEmail());
        pedidoCabecera.setTelefono(pedidoCabeceraDto.getTelefono());
        pedidoCabecera.setHorario(pedidoCabeceraDto.getHorario());
        pedidoCabecera.setFechaAlta(LocalDate.now());
        return pedidoCabecera;
    }

    public PedidoCabecera aplicaDescuento(PedidoCabecera pedidoCabecera, Integer cantidad){
        if(cantidad > 3){
            pedidoCabecera.setAplicoDescuento(true);
        }else{
            pedidoCabecera.setAplicoDescuento(false);
        }
        return pedidoCabecera;
    }

    public PedidoCabecera calcularMonto(PedidoCabecera pedidoCabecera, Double total){
        if(pedidoCabecera.getAplicoDescuento()){
            pedidoCabecera.setMontoTotal(total * 0.7);
        }else{
            pedidoCabecera.setMontoTotal(total);
        }
        return pedidoCabecera;
    }

    @Override
    public PedidoCabeceraDto create(PedidoCabeceraDto pedidoCabeceraDto) {
        Double monto = 0.0;
        Double total = 0.0;
        Integer cantidad = 0;
        
        List<PedidoDetalleDto> listDetalleResponse = new ArrayList<>();
        PedidoCabecera pedidoCabecera = new PedidoCabecera();
        
        for(PedidoDetalleDto detalle: pedidoCabeceraDto.getDetalle()){
            Producto producto = iProductoRespository.findById(detalle.getProducto()).orElseThrow(() -> new RecursoNoEncontradoException("Producto", "id", detalle.getProducto()));
           
            monto = producto.getPrecioUnitario() * detalle.getCantidad();
            total += monto;
            cantidad += detalle.getCantidad();
            
            // VOY ARMANDO UNA PARTE DE LA RESPUESTA (PedidoDetalleDto.class)
            PedidoDetalleDto pedidoDetalleDtoResponse = new PedidoDetalleDto(producto.getId(), producto.getNombre(), detalle.getCantidad(), monto);
            listDetalleResponse.add(pedidoDetalleDtoResponse);
            
            // LINKEO EL PEDIDO CABECERA CON EL PEDIDO DETALLE QUE RECIEN ARME
            pedidoCabecera.addPedidoDetalle(createPedidoDetalle(detalle));
        }   
            pedidoCabecera = createPedidoCabecera(pedidoCabecera, pedidoCabeceraDto);
            pedidoCabecera = aplicaDescuento(pedidoCabecera, cantidad);
            pedidoCabecera = calcularMonto(pedidoCabecera, total);
        
        PedidoCabecera pedidoCabeceraGuardado = iPedidoCabeceraRepository.save(pedidoCabecera);
        
        return createPedidoCabeceraDto(pedidoCabeceraDto, pedidoCabeceraGuardado, listDetalleResponse);
    }

    @Override
    public List<PedidoCabeceraDto> readByFecha(String fecha) {
        List<PedidoCabeceraDto> pedidoResponses = new ArrayList<>();

        LocalDate localDate = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		List<PedidoCabecera> pedidoCabeceras = iPedidoCabeceraRepository.findAllByFechaAlta(localDate);
        
		for(PedidoCabecera pedidoCabecera: pedidoCabeceras){
            List<PedidoDetalleDto> listDetalle = new ArrayList<>();
			PedidoCabeceraDto pedidoCabeceraDto = new PedidoCabeceraDto();
			List<PedidoDetalle> pedidoDetalles = pedidoCabecera.getPedidosDetalle();

			for(PedidoDetalle pedidoDetalle: pedidoDetalles){
                PedidoDetalleDto detalleResponse = new PedidoDetalleDto(pedidoDetalle.getProducto().getId(), pedidoDetalle.getProducto().getNombre(), pedidoDetalle.getCantidad(), (pedidoDetalle.getCantidad() * pedidoDetalle.getPrecioUnitario()));
                listDetalle.add(detalleResponse);
            }

            pedidoCabeceraDto = createPedidoCabeceraDto(pedidoCabeceraDto, pedidoCabecera, listDetalle);
            pedidoResponses.add(pedidoCabeceraDto);
        }

        return pedidoResponses;
    }
    
}
