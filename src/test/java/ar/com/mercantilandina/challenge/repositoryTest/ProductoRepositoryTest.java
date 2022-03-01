package ar.com.mercantilandina.challenge.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.mercantilandina.challenge.annotation.RepositoryTest;
import ar.com.mercantilandina.challenge.entity.Producto;
import ar.com.mercantilandina.challenge.repository.IProductoRespository;
import ar.com.mercantilandina.challenge.util.ProductoUtilTest;

@RepositoryTest
public class ProductoRepositoryTest {
    
    @Autowired
    private IProductoRespository iProductoRepository;

    @Test
    public void saveTest(){
        Producto producto = ProductoUtilTest.PRODUCTO_ONE;
        Producto productoGuardado = iProductoRepository.save(producto);
        assertNotNull(productoGuardado);
    }

    @Test
    public void findAllTest(){
        Producto productoOne = ProductoUtilTest.PRODUCTO_ONE;
        Producto productoTwo = ProductoUtilTest.PRODUCTO_TWO;
        Producto productoThree = ProductoUtilTest.PRODUCTO_THREE;
        iProductoRepository.save(productoOne);
        iProductoRepository.save(productoTwo);
        iProductoRepository.save(productoThree);
        List<Producto> productos = iProductoRepository.findAll();
        Assertions.assertThat(productos).size().isGreaterThan(2);
    }

    
    @Test
    public void findByIdTest(){
        Producto producto = ProductoUtilTest.PRODUCTO_ONE;
        Producto productoGuardado = iProductoRepository.save(producto);
        Optional<Producto> optionalProducto = iProductoRepository.findById(productoGuardado.getId());
        assertTrue(optionalProducto.isPresent());
    }

    @Test
    public void deleteTest(){
        Producto producto = ProductoUtilTest.PRODUCTO_ONE;
        Producto productoGuardado = iProductoRepository.save(producto);
        Optional<Producto> optionalProducto = iProductoRepository.findById(productoGuardado.getId());
        assertTrue(optionalProducto.isPresent());
        iProductoRepository.delete(optionalProducto.get());
        Optional<Producto> productoFinal = iProductoRepository.findById(productoGuardado.getId());
        assertFalse(productoFinal.isPresent());
    }

}
