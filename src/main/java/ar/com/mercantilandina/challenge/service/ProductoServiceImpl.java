package ar.com.mercantilandina.challenge.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import ar.com.mercantilandina.challenge.dto.ProductoDto;
import ar.com.mercantilandina.challenge.entity.Producto;
import ar.com.mercantilandina.challenge.exception.RecursoNoEncontradoException;
import ar.com.mercantilandina.challenge.repository.IProductoRespository;
import ar.com.mercantilandina.challenge.service.interfaces.IProductoService;

@Service
@Validated
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoRespository iProductoRespository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public ProductoDto create(ProductoDto productoDto) {
        Producto producto = iProductoRespository.save(modelMapper.map(productoDto, Producto.class));
        return modelMapper.map(producto, ProductoDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDto> read() {
        List<Producto> productos = iProductoRespository.findAll();
        return productos.stream().map(producto -> modelMapper.map(producto, ProductoDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoDto readById(UUID id) {
        Producto producto = iProductoRespository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Producto", "id", id));
        return modelMapper.map(producto, ProductoDto.class);
    }

    @Override
    @Transactional
    public ProductoDto update(UUID id, ProductoDto productoDto) {
        Producto producto = iProductoRespository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Producto", "id", id));
            producto.setNombre(productoDto.getNombre());
            producto.setDescripcionCorta(productoDto.getDescripcionCorta());
            producto.setDescripcionLarga(productoDto.getDescripcionLarga());
            producto.setPrecioUnitario(productoDto.getPrecioUnitario());
        return modelMapper.map(iProductoRespository.save(producto), ProductoDto.class);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Producto producto = iProductoRespository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Producto", "id", id));
        iProductoRespository.delete(producto);
    }

}
