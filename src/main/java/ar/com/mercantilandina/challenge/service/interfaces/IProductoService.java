package ar.com.mercantilandina.challenge.service.interfaces;

import java.util.List;

import javax.validation.Valid;

import ar.com.mercantilandina.challenge.dto.ProductoDto;

public interface IProductoService {
    
    ProductoDto create(@Valid ProductoDto productoDto);
    List<ProductoDto> read();
    ProductoDto readById(Long id);
    ProductoDto update(Long id, @Valid ProductoDto productoDto);
    void delete(Long id);

}
