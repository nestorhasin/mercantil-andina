package ar.com.mercantilandina.challenge.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ar.com.mercantilandina.challenge.annotation.ControllerTest;
import ar.com.mercantilandina.challenge.dto.PedidoCabeceraDto;
import ar.com.mercantilandina.challenge.dto.PedidoDetalleDto;
import ar.com.mercantilandina.challenge.service.interfaces.IPedidoService;
import ar.com.mercantilandina.challenge.util.PedidoUtilTest;

@ControllerTest
public class PedidoControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPedidoService iPedidoService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void readByFechaTest() throws Exception {
        // Given
        PedidoCabeceraDto pedidoCabeceraDtoOne = PedidoUtilTest.PEDIDO_CABECERA_DTO_ONE;
        PedidoCabeceraDto pedidoCabeceraDtoTwo = PedidoUtilTest.PEDIDO_CABECERA_DTO_TWO;
        PedidoCabeceraDto pedidoCabeceraDtoThree = PedidoUtilTest.PEDIDO_CABECERA_DTO_THREE;
            pedidoCabeceraDtoThree.setFecha(LocalDate.of(2010, 05, 30));

        String fecha = LocalDate.now().toString();

        when(iPedidoService.readByFecha(fecha)).thenReturn(Arrays.asList(pedidoCabeceraDtoOne, pedidoCabeceraDtoTwo));
        
        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/pedidos?fecha=" + fecha).contentType(MediaType.APPLICATION_JSON))
        
        // Then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].direccion").value("direccionOne"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].direccion").value("direccionTwo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].email").value("email@emailTwo.com"));
        
        verify(iPedidoService).readByFecha(fecha);
    }

    @Test
    public void createTest() throws JsonProcessingException, Exception {
        // Given
        PedidoCabeceraDto pedidoCabeceraDto = PedidoUtilTest.PEDIDO_CABECERA_DTO_ONE;

        when(iPedidoService.create(any())).thenReturn(pedidoCabeceraDto);
        
        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pedidoCabeceraDto)))
                
        // Then
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.direccion").value("direccionOne"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("email@emailOne.com"));
        
        verify(iPedidoService).create(any());
    }

}
