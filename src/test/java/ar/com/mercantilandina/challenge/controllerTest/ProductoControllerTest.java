package ar.com.mercantilandina.challenge.controllerTest;

import java.util.Arrays;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ar.com.mercantilandina.challenge.annotation.ControllerTest;
import ar.com.mercantilandina.challenge.dto.ProductoDto;
import ar.com.mercantilandina.challenge.service.interfaces.IProductoService;
import ar.com.mercantilandina.challenge.util.ProductoUtilTest;

import static org.mockito.Mockito.*;

@ControllerTest
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductoService iProductoService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void readTest() throws Exception {
        // Given
        ProductoDto productoDtoOne = ProductoUtilTest.PRODUCTO_DTO_ONE;
        ProductoDto productoDtoTwo = ProductoUtilTest.PRODUCTO_DTO_TWO;
        ProductoDto productoDtoThree = ProductoUtilTest.PRODUCTO_DTO_THREE;
 
        when(iProductoService.read())
                .thenReturn(Arrays.asList(productoDtoOne, productoDtoTwo, productoDtoThree));
        
        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/productos").contentType(MediaType.APPLICATION_JSON))
        
        // Then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].nombre").value("nombreOne"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].descripcionCorta").value("descripcionCortaTwo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[2].descripcionLarga").value("descripcionLargaThree"));
 
        verify(iProductoService).read();
    }

    @Test
    public void readByIdTest() throws Exception {
        // Given
        ProductoDto productoDtoOne = ProductoUtilTest.PRODUCTO_DTO_ONE;
        
        UUID id = UUID.randomUUID();

        when(iProductoService.readById(id)).thenReturn(productoDtoOne);
        
        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/productos/" + id).contentType(MediaType.APPLICATION_JSON))
        
        // Then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("nombreOne"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descripcionCorta").value("descripcionCortaOne"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descripcionLarga").value("descripcionLargaOne"));
        
        verify(iProductoService).readById(id);
    }

    @Test
    public void createTest() throws JsonProcessingException, Exception {
        // Given
        ProductoDto productoDtoTwo = ProductoUtilTest.PRODUCTO_DTO_TWO;
        
        when(iProductoService.create(any())).thenReturn(productoDtoTwo);
        
        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productoDtoTwo)))
                
        // Then
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("nombreTwo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descripcionCorta").value("descripcionCortaTwo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descripcionLarga").value("descripcionLargaTwo"));
        
        verify(iProductoService).create(any());
    }

    /*
    @Test
    public void updateTest() throws JsonProcessingException, Exception{
        // Given
        ProductoDto productoDtoThree = ProductoUtilTest.PRODUCTO_DTO_THREE;

        UUID id = UUID.randomUUID();
        
        when(iProductoService.update(id, productoDtoThree)).thenReturn(productoDtoThree);
        
        // When
        mockMvc.perform(MockMvcRequestBuilders.put("/productos/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productoDtoThree)))
        
        // Then
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("nombreThree"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.descripcionCorta").value("descripcionCortaThree"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
                
        
        verify(iProductoService).update(id, productoDtoThree);
    }
    */

}
