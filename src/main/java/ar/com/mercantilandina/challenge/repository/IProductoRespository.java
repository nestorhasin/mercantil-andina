package ar.com.mercantilandina.challenge.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.mercantilandina.challenge.entity.Producto;

@Repository
public interface IProductoRespository extends JpaRepository<Producto, Serializable> {
    
}
