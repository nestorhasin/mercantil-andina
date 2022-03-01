package ar.com.mercantilandina.challenge.dtoTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ar.com.mercantilandina.challenge.dto.PedidoCabeceraDto;
import ar.com.mercantilandina.challenge.dto.PedidoDetalleDto;
import ar.com.mercantilandina.challenge.service.interfaces.IPedidoService;
import ar.com.mercantilandina.challenge.util.PedidoUtilTest;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PedidoCabeceraDtoTest {
    
    @Autowired
    IPedidoService iPedidoService;

    @Test
    public void whenCreatePedidoCabeceraDtoConDireccionEnBlanco_thenSeActivaLaValidacionNotBlanck(){
        assertThrows(ConstraintViolationException.class, () -> {
            PedidoDetalleDto pedidoDetalleDto = PedidoUtilTest.PEDIDO_DETALLE_DTO_ONE;
            iPedidoService.create(new PedidoCabeceraDto(LocalDate.now(), "", "email@emailTest.com", "(1234) 12345678", LocalTime.now(), Arrays.asList(pedidoDetalleDto), null, null, null));
        });
    }

    @Test
    public void whenCreatePedidoCabeceraDtoConDireccionEnNull_thenSeActivaLaValidacionNotBlanck(){
        assertThrows(ConstraintViolationException.class, () -> {
            PedidoDetalleDto pedidoDetalleDto = PedidoUtilTest.PEDIDO_DETALLE_DTO_ONE;
            iPedidoService.create(new PedidoCabeceraDto(LocalDate.now(), null, "email@emailTest.com", "(1234) 12345678", LocalTime.now(), Arrays.asList(pedidoDetalleDto), null, null, null));
        });
    }

    @Test
    public void whenCreatePedidoCabeceraDtoConDireccionMenorACincoCaracteres_thenSeActivaLaValidacionSize(){
        assertThrows(ConstraintViolationException.class, () -> {
            PedidoDetalleDto pedidoDetalleDto = PedidoUtilTest.PEDIDO_DETALLE_DTO_ONE;
            iPedidoService.create(new PedidoCabeceraDto(LocalDate.now(), "dir", "email@emailTest.com", "(1234) 12345678", LocalTime.now(), Arrays.asList(pedidoDetalleDto), null, null, null));
        });
    }

    @Test
    public void whenCreatePedidoCabeceraDtoConEmailEnBlanco_thenSeActivaLaValidacionNotBlanck(){
        assertThrows(ConstraintViolationException.class, () -> {
            PedidoDetalleDto pedidoDetalleDto = PedidoUtilTest.PEDIDO_DETALLE_DTO_ONE;
            iPedidoService.create(new PedidoCabeceraDto(LocalDate.now(), "direccion", "", "(1234) 12345678", LocalTime.now(), Arrays.asList(pedidoDetalleDto), null, null, null));
        });
    }

    @Test
    public void whenCreatePedidoCabeceraDtoConEmailEnNull_thenSeActivaLaValidacionNotBlanck(){
        assertThrows(ConstraintViolationException.class, () -> {
            PedidoDetalleDto pedidoDetalleDto = PedidoUtilTest.PEDIDO_DETALLE_DTO_ONE;
            iPedidoService.create(new PedidoCabeceraDto(LocalDate.now(), "direccion", null, "(1234) 12345678", LocalTime.now(), Arrays.asList(pedidoDetalleDto), null, null, null));
        });
    }

    @Test
    public void whenCreatePedidoCabeceraDtoConEmailEnFormatoErroneo_thenSeActivaLaValidacionEmail(){
        assertThrows(ConstraintViolationException.class, () -> {
            PedidoDetalleDto pedidoDetalleDto = PedidoUtilTest.PEDIDO_DETALLE_DTO_ONE;
            iPedidoService.create(new PedidoCabeceraDto(LocalDate.now(), "direccion", "email", "(1234) 12345678", LocalTime.now(), Arrays.asList(pedidoDetalleDto), null, null, null));
        });
    }

    @Test
    public void whenCreatePedidoCabeceraDtoSinPedidoDetalleDto_thenSeActivaLaValidacionSize(){
        assertThrows(ConstraintViolationException.class, () -> {
            iPedidoService.create(new PedidoCabeceraDto(LocalDate.now(), "direccion", "email@email.com", "(1234) 12345678", LocalTime.now(), Collections.EMPTY_LIST, null, null, null));
        });
    }

}
