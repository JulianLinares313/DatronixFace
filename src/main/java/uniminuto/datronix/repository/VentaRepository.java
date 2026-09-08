package uniminuto.datronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uniminuto.datronix.entity.Venta;

// Spring Data crea las operaciones básicas para las ventas.
public interface VentaRepository extends JpaRepository<Venta,Long> {
    
}
