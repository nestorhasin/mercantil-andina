package ar.com.mercantilandina.challenge.service.interfaces;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import ar.com.mercantilandina.challenge.dto.ProductoDto;

public interface IProductoService {
    
    ProductoDto create(@Valid ProductoDto productoDto);
    List<ProductoDto> read();
    ProductoDto readById(UUID id);
    ProductoDto update(UUID id, @Valid ProductoDto productoDto);
    void delete(UUID id);

}
