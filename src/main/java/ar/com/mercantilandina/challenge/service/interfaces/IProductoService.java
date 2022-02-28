package ar.com.mercantilandina.challenge.service.interfaces;

import java.util.List;

import ar.com.mercantilandina.challenge.dto.ProductoDto;

public interface IProductoService {
    
    ProductoDto create(ProductoDto productoDto);
    List<ProductoDto> read();
    ProductoDto update(ProductoDto productoDto);
    void delete(Long id);

}
