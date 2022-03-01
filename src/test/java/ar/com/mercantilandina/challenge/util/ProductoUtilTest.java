package ar.com.mercantilandina.challenge.util;

import java.util.UUID;

import ar.com.mercantilandina.challenge.dto.ProductoDto;
import ar.com.mercantilandina.challenge.entity.Producto;

public class ProductoUtilTest {
    
    public static final ProductoDto PRODUCTO_DTO_ONE = new ProductoDto(UUID.randomUUID(), "nombreOne", "descripcionCortaOne", "descripcionLargaOne", 1.0);
    public static final ProductoDto PRODUCTO_DTO_TWO = new ProductoDto(UUID.randomUUID(), "nombreTwo", "descripcionCortaTwo", "descripcionLargaTwo", 2.0);
    public static final ProductoDto PRODUCTO_DTO_THREE = new ProductoDto(UUID.randomUUID(), "nombreThree", "descripcionCortaThree", "descripcionLargaThree", 3.0);

    public static final Producto PRODUCTO_ONE = new Producto(UUID.randomUUID(), "nombreOne", "descripcionCortaOne", "descripcionLargaOne", 1.0);
    public static final Producto PRODUCTO_TWO = new Producto(UUID.randomUUID(), "nombreTwo", "descripcionCortaTwo", "descripcionLargaTwo", 2.0);
    public static final Producto PRODUCTO_THREE = new Producto(UUID.randomUUID(), "nombreThree", "descripcionCortaThree", "descripcionLargaThree", 3.0);

}
