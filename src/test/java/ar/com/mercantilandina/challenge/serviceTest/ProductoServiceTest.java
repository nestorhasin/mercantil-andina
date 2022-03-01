package ar.com.mercantilandina.challenge.serviceTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import ar.com.mercantilandina.challenge.annotation.ServiceTest;
import ar.com.mercantilandina.challenge.dto.ProductoDto;
import ar.com.mercantilandina.challenge.entity.Producto;
import ar.com.mercantilandina.challenge.repository.IProductoRespository;
import ar.com.mercantilandina.challenge.service.interfaces.IProductoService;
import ar.com.mercantilandina.challenge.util.ProductoUtilTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ServiceTest
public class ProductoServiceTest {
    
    @MockBean
    IProductoRespository iProductoRepository;

    @MockBean
    ModelMapper modelMapper;

    @Autowired
    IProductoService iProductoService;

    @Test
    public void readTest() {
        // Given
        Producto productoOne = ProductoUtilTest.PRODUCTO_ONE;
        Producto productoTwo = ProductoUtilTest.PRODUCTO_TWO;

        List<Producto> productos = Arrays.asList(productoOne, productoTwo);
        
        ProductoDto productoDtoOne = ProductoUtilTest.PRODUCTO_DTO_ONE;
        ProductoDto productoDtoTwo = ProductoUtilTest.PRODUCTO_DTO_TWO;

        when(iProductoRepository.findAll()).thenReturn(productos);
        when(modelMapper.map(productoOne, ProductoDto.class)).thenReturn(productoDtoOne);
        when(modelMapper.map(productoTwo, ProductoDto.class)).thenReturn(productoDtoTwo);
        
        // When
        List<ProductoDto> productoDTOs = iProductoService.read();
        
        // Then
        assertFalse(productos.isEmpty());
        assertEquals(2, productos.size());
        assertTrue(productoDTOs.contains(productoDtoOne));
        
        verify(iProductoRepository).findAll();
    }

    @Test
    public void readByIdTest(){
        // Given
        ProductoDto productoDtoOne = ProductoUtilTest.PRODUCTO_DTO_ONE;

        Producto productoOne = ProductoUtilTest.PRODUCTO_ONE;

        Optional<Producto> optionalProductoOne = Optional.of(productoOne);

        when(iProductoRepository.findById(productoOne.getId())).thenReturn(optionalProductoOne);
        when(modelMapper.map(productoOne, ProductoDto.class)).thenReturn(productoDtoOne);
        when(modelMapper.map(productoDtoOne, Producto.class)).thenReturn(productoOne);
        
        // When
        ProductoDto productoObtenido = iProductoService.readById(productoOne.getId());
        
        // Then
        assertEquals(productoOne.getNombre(), productoObtenido.getNombre());
        assertEquals(productoOne.getDescripcionCorta(), productoObtenido.getDescripcionCorta());
        assertEquals(productoOne.getDescripcionLarga(), productoObtenido.getDescripcionLarga());
        
        verify(iProductoRepository).findById(productoOne.getId());
    }

    @Test
    public void createTest(){
        // Given
        ProductoDto productoDtoOne = ProductoUtilTest.PRODUCTO_DTO_ONE;

        Producto productoOne = ProductoUtilTest.PRODUCTO_ONE;
        
        when(iProductoRepository.save(any())).thenReturn(productoOne);
        when(modelMapper.map(productoOne, ProductoDto.class)).thenReturn(productoDtoOne);
        when(modelMapper.map(productoDtoOne, Producto.class)).thenReturn(productoOne);
        
        // When
        ProductoDto productoDtoOneCreado = iProductoService.create(productoDtoOne);
        
        // Then
        assertEquals(productoOne.getNombre(), productoDtoOneCreado.getNombre());
        assertEquals(productoOne.getDescripcionCorta(), productoDtoOneCreado.getDescripcionCorta());
        assertEquals(productoOne.getDescripcionLarga(), productoDtoOneCreado.getDescripcionLarga());
        
        verify(iProductoRepository).save(any());
    }

    @Test
    public void updateTest(){
        // Given
        ProductoDto productoDtoOne = ProductoUtilTest.PRODUCTO_DTO_ONE;

        Producto productoOne = ProductoUtilTest.PRODUCTO_ONE;

        Optional<Producto> optionalProductoOne = Optional.of(productoOne);

        when(iProductoRepository.findById(productoOne.getId())).thenReturn(optionalProductoOne);
        when(iProductoRepository.save(any())).thenReturn(productoOne);
        when(modelMapper.map(productoOne, ProductoDto.class)).thenReturn(productoDtoOne);
        when(modelMapper.map(productoDtoOne, Producto.class)).thenReturn(productoOne);

        // When
        ProductoDto productoDtoTwoActualizado = iProductoService.update(productoOne.getId(), productoDtoOne);
        
        // Then
        assertEquals(productoOne.getNombre(), productoDtoTwoActualizado.getNombre());
        assertEquals(productoOne.getDescripcionCorta(), productoDtoTwoActualizado.getDescripcionCorta());
        assertEquals(productoOne.getDescripcionLarga(), productoDtoTwoActualizado.getDescripcionLarga());
        
        verify(iProductoRepository).save(productoOne);
    }

    @Test
    public void deleteTest(){
        // Given
        Producto productoTwo = ProductoUtilTest.PRODUCTO_TWO;

        Optional<Producto> optionalProductoTwo = Optional.of(productoTwo);

        when(iProductoRepository.findById(productoTwo.getId())).thenReturn(optionalProductoTwo);

        // When
        iProductoService.delete(productoTwo.getId());
        
        // Then
        verify(iProductoRepository).delete(productoTwo);
    }

}
