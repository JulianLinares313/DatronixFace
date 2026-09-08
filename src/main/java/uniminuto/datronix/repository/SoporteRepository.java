package uniminuto.datronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uniminuto.datronix.entity.Soporte;

// Spring Data ofrece aquí el CRUD de solicitudes de soporte.
public interface SoporteRepository extends JpaRepository<Soporte,Long> {
    
}
