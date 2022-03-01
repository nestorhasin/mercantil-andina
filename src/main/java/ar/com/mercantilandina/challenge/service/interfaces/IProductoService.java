package ar.com.mercantilandina.challenge.service.interfaces;

import java.util.List;
import java.util.UUID;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import ar.com.mercantilandina.challenge.dto.ProductoDto;

public interface IProductoService {
    
    ProductoDto create(@Valid ProductoDto productoDto) throws ConstraintViolationException;
    List<ProductoDto> read();
    ProductoDto readById(UUID id);
    ProductoDto update(UUID id, @Valid ProductoDto productoDto) throws ConstraintViolationException;
    void delete(UUID id);

}
