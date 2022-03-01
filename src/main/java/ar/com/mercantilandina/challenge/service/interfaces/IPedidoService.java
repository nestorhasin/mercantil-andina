package ar.com.mercantilandina.challenge.service.interfaces;

import java.util.List;

import javax.validation.Valid;

import ar.com.mercantilandina.challenge.dto.PedidoCabeceraDto;
import ar.com.mercantilandina.challenge.dto.PedidoDetalleDto;
import ar.com.mercantilandina.challenge.entity.PedidoDetalle;

public interface IPedidoService {

    PedidoDetalle createPedidoDetalle(@Valid PedidoDetalleDto pedidoDetalleDto);

    PedidoCabeceraDto create(@Valid PedidoCabeceraDto pedidoCabeceraDto);
    List<PedidoCabeceraDto> readByFecha(String fecha);

}
