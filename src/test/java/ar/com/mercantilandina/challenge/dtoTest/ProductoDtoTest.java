package ar.com.mercantilandina.challenge.dtoTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ar.com.mercantilandina.challenge.dto.ProductoDto;
import ar.com.mercantilandina.challenge.service.interfaces.IProductoService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductoDtoTest {
    
    @Autowired
    IProductoService iProductoService;

    @Test
    public void whenCreateProductoDtoConNombreEnBlanco_thenSeActivaLaValidacionNotBlanck(){
        assertThrows(ConstraintViolationException.class, () -> {
            iProductoService.create(new ProductoDto(UUID.randomUUID(), "", "descripcionCorta", "descripcionLarga", 1.0));
        });
    }

    @Test
    public void whenCreateProductoDtoConNombreEnNull_thenSeActivaLaValidacionNotBlanck(){
        assertThrows(ConstraintViolationException.class, () -> {
            iProductoService.create(new ProductoDto(UUID.randomUUID(), null, "descripcionCorta", "descripcionLarga", 1.0));
        });
    }

    @Test
    public void whenCreateProductoDtoConDescripcionCortaEnBlanco_thenSeActivaLaValidacionNotEmpty(){
        assertThrows(ConstraintViolationException.class, () -> {
            iProductoService.create(new ProductoDto(UUID.randomUUID(), "nombre", "", "descripcionLarga", 1.0));
        });
    }

    @Test
    public void whenCreateProductoDtoConDescripcionCortaEnNull_thenSeActivaLaValidacionNotEmpty(){
        assertThrows(ConstraintViolationException.class, () -> {
            iProductoService.create(new ProductoDto(UUID.randomUUID(), "nombre", null, "descripcionLarga", 1.0));
        });
    }

    @Test
    public void whenCreateProductoDtoConDescripcionCortaSuperandoLosVeinteCaracteres_thenSeActivaLaValidacionSize(){
        assertThrows(ConstraintViolationException.class, () -> {
            iProductoService.create(new ProductoDto(UUID.randomUUID(), "nombre", "descripcionCortaQueSuperaLosVeinteCaracteres", "descripcionLarga", 1.0));
        });
    }

    @Test
    public void whenCreateProductoDtoConDescripcionLargaEnBlanco_thenSeActivaLaValidacionNotBlank(){
        assertThrows(ConstraintViolationException.class, () -> {
            iProductoService.create(new ProductoDto(UUID.randomUUID(), "nombre", "descripcionCorta", "", 1.0));
        });
    }

    @Test
    public void whenCreateProductoDtoConDescripcionLargaEnNull_thenSeActivaLaValidacionNotBlank(){
        assertThrows(ConstraintViolationException.class, () -> {
            iProductoService.create(new ProductoDto(UUID.randomUUID(), "nombre", "descripcionCorta", null, 1.0));
        });
    }

    @Test
    public void whenCreateProductoDtoConPrecioUnitarioEnCero_thenSeActivaLaValidacionMin(){
        assertThrows(ConstraintViolationException.class, () -> {
            iProductoService.create(new ProductoDto(UUID.randomUUID(), "nombre", "descripcionCorta", "descripcionLarga", 0.0));
        });
    }

}
