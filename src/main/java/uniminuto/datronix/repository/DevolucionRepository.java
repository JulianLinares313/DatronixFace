package uniminuto.datronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uniminuto.datronix.entity.Devolucion;

// Spring Data ofrece aquí el CRUD de devoluciones.
public interface DevolucionRepository extends JpaRepository<Devolucion,Long>{
    
}
