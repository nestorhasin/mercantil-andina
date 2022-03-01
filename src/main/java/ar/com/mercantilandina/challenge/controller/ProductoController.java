package ar.com.mercantilandina.challenge.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mercantilandina.challenge.dto.ProductoDto;
import ar.com.mercantilandina.challenge.service.interfaces.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    private IProductoService iProductoService;

    @GetMapping
    public ResponseEntity<?> read() {
        return new ResponseEntity<>(iProductoService.read(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable UUID id){
        return new ResponseEntity<>(iProductoService.readById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductoDto productoDto){
        return new ResponseEntity<>(iProductoService.create(productoDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody ProductoDto productoDto){
        return new ResponseEntity<>(iProductoService.update(id, productoDto), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        iProductoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
