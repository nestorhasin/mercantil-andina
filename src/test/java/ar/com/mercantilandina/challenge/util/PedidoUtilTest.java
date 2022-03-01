package ar.com.mercantilandina.challenge.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import ar.com.mercantilandina.challenge.dto.PedidoCabeceraDto;
import ar.com.mercantilandina.challenge.dto.PedidoDetalleDto;
import ar.com.mercantilandina.challenge.entity.PedidoCabecera;
import ar.com.mercantilandina.challenge.entity.PedidoDetalle;

public class PedidoUtilTest {

    public static final PedidoDetalle PEDIDO_DETALLE_ONE = new PedidoDetalle(UUID.randomUUID(), null, null, 1, 1.0);
    public static final PedidoDetalle PEDIDO_DETALLE_TWO = new PedidoDetalle(UUID.randomUUID(), null, null, 2, 2.0);
    public static final PedidoDetalle PEDIDO_DETALLE_THREE = new PedidoDetalle(UUID.randomUUID(), null, null, 3, 3.0);

    public static final PedidoCabecera PEDIDO_CABECERA_ONE = new PedidoCabecera(UUID.randomUUID(), "direccionOne", "email@emailOne.com", "(1111) 11111111", LocalTime.now(), LocalDate.now(), 1.0, false, "PENDIENTE", null);
    public static final PedidoCabecera PEDIDO_CABECERA_TWO = new PedidoCabecera(UUID.randomUUID(), "direccionTwo", "email@emailTwo.com", "(2222) 22222222", LocalTime.now(), LocalDate.now(), 2.0, false, "PENDIENTE", null);
    public static final PedidoCabecera PEDIDO_CABECERA_THREE = new PedidoCabecera(UUID.randomUUID(), "direccionThree", "email@emailThree.com", "(3333) 33333333", LocalTime.now(), LocalDate.now(), 3.0, false, "PENDIENTE", null);
    
    public static final PedidoDetalleDto PEDIDO_DETALLE_DTO_ONE = new PedidoDetalleDto(UUID.randomUUID(), "nombreOne", 1, 1.0);
    public static final PedidoDetalleDto PEDIDO_DETALLE_DTO_TWO = new PedidoDetalleDto(UUID.randomUUID(), "nombreTwo", 2, 2.0);
    public static final PedidoDetalleDto PEDIDO_DETALLE_DTO_THREE = new PedidoDetalleDto(UUID.randomUUID(), "nombreThree", 3, 3.0);

    public static final PedidoCabeceraDto PEDIDO_CABECERA_DTO_ONE = new PedidoCabeceraDto(LocalDate.now(), "direccionOne", "email@emailOne.com", "(1111) 11111111", LocalTime.now(), null, 1.0, false, "PENDIENTE");
    public static final PedidoCabeceraDto PEDIDO_CABECERA_DTO_TWO = new PedidoCabeceraDto(LocalDate.now(), "direccionTwo", "email@emailTwo.com", "(2222) 22222222", LocalTime.now(), null, 2.0, false, "PENDIENTE");
    public static final PedidoCabeceraDto PEDIDO_CABECERA_DTO_THREE = new PedidoCabeceraDto(LocalDate.now(), "direccionThree", "email@emailThree.com", "(3333) 33333333", LocalTime.now(), null, 3.0, false, "PENDIENTE");

}
