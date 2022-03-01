package ar.com.mercantilandina.challenge.service.interfaces;

import java.util.List;

import ar.com.mercantilandina.challenge.dto.PedidoCabeceraDto;

public interface IPedidoService {

    PedidoCabeceraDto create(PedidoCabeceraDto pedidoCabeceraDto);
    List<PedidoCabeceraDto> readByFecha(String fecha);

}
