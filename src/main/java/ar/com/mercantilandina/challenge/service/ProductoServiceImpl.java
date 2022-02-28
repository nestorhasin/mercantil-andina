package ar.com.mercantilandina.challenge.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.mercantilandina.challenge.dto.ProductoDto;
import ar.com.mercantilandina.challenge.entity.Producto;
import ar.com.mercantilandina.challenge.repository.IProductoRespository;
import ar.com.mercantilandina.challenge.service.interfaces.IProductoService;

@Service
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
    @Transactional
    public ProductoDto update(ProductoDto productoDto) {
        Producto producto = iProductoRespository.findById(productoDto.getId()).get();
            producto.setNombre(productoDto.getNombre());
            producto.setDescripcionCorta(productoDto.getDescripcionCorta());
            producto.setDescripcionLarga(productoDto.getDescripcionLarga());
            producto.setPrecioUnitario(productoDto.getPrecioUnitario());
        return modelMapper.map(iProductoRespository.save(producto), ProductoDto.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Producto producto = iProductoRespository.findById(id).get();
        iProductoRespository.delete(producto);
    }

}
