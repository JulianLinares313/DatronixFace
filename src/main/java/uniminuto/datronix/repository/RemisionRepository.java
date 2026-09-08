package uniminuto.datronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uniminuto.datronix.entity.Remision;

// Spring Data ofrece aquí el CRUD de remisiones.
public interface RemisionRepository extends JpaRepository<Remision,Long> {
    
}
