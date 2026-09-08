package uniminuto.datronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uniminuto.datronix.entity.DetalleVenta;

// Spring Data ofrece aquí el CRUD de detalles de venta.
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta,Long>{
    
}
