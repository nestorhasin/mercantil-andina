package ar.com.mercantilandina.challenge.repository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.mercantilandina.challenge.entity.PedidoCabecera;

@Repository
public interface IPedidoCabeceraRepository extends JpaRepository<PedidoCabecera, Serializable> {
    
    List<PedidoCabecera> findAllByFechaAlta(LocalDate fechaAlta);

}
