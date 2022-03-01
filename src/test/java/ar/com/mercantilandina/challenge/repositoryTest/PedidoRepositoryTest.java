package ar.com.mercantilandina.challenge.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.mercantilandina.challenge.annotation.RepositoryTest;
import ar.com.mercantilandina.challenge.entity.PedidoCabecera;
import ar.com.mercantilandina.challenge.entity.PedidoDetalle;
import ar.com.mercantilandina.challenge.repository.IPedidoCabeceraRepository;
import ar.com.mercantilandina.challenge.repository.IPedidoDetalleRepository;
import ar.com.mercantilandina.challenge.util.PedidoUtilTest;

@RepositoryTest
public class PedidoRepositoryTest {
    
    @Autowired
    private IPedidoCabeceraRepository iPedidoCabeceraRepository;

    @Autowired
    private IPedidoDetalleRepository iPedidoDetalleRepository;

    @Test
    public void savePedidoCabeceraTest(){
        PedidoCabecera pedidoCabecera = PedidoUtilTest.PEDIDO_CABECERA_ONE;
        PedidoCabecera pedidoCabeceraGuardado = iPedidoCabeceraRepository.save(pedidoCabecera);
        assertNotNull(pedidoCabeceraGuardado);
    }

    @Test
    public void findAllByFechaAltaPedidoCabeceraTest(){
        PedidoCabecera pedidoCabecera = PedidoUtilTest.PEDIDO_CABECERA_ONE;
        PedidoCabecera pedidoCabeceraGuardado = iPedidoCabeceraRepository.save(pedidoCabecera);
        Optional<PedidoCabecera> optionalPedidoCabecera = iPedidoCabeceraRepository.findById(pedidoCabeceraGuardado.getId());
        assertTrue(optionalPedidoCabecera.isPresent());
    }

    @Test
    public void savePedidoDetalleTest(){
        PedidoDetalle pedidoDetalle = PedidoUtilTest.PEDIDO_DETALLE_ONE;
        PedidoDetalle pedidoDetalleGuardado = iPedidoDetalleRepository.save(pedidoDetalle);
        assertNotNull(pedidoDetalleGuardado);
    }

}
