package ar.com.mercantilandina.challenge.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mercantilandina.challenge.dto.PedidoCabeceraDto;
import ar.com.mercantilandina.challenge.response.PedidoCabeceraResponse;
import ar.com.mercantilandina.challenge.service.interfaces.IPedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    @Autowired
    private IPedidoService iPedidoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
        public ResponseEntity<?> create(@Valid @RequestBody PedidoCabeceraDto pedidoCabeceraDto){
        return new ResponseEntity<>(iPedidoService.create(pedidoCabeceraDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> readByFecha(@RequestParam(value = "fecha", required = false) String fecha){
        return new ResponseEntity<>(iPedidoService.readByFecha(fecha).stream().map(dto -> modelMapper.map(dto, PedidoCabeceraResponse.class)).collect(Collectors.toList()), HttpStatus.OK);
    }

}
