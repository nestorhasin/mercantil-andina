package ar.com.mercantilandina.challenge.service.interfaces;

import java.time.LocalDate;
import java.util.List;

import ar.com.mercantilandina.challenge.request.PedidoRequest;
import ar.com.mercantilandina.challenge.response.PedidoResponse;

public interface IPedidoService {

    PedidoResponse create(PedidoRequest pedidoRequest);
    List<PedidoResponse> readByFecha(String fecha);

}
