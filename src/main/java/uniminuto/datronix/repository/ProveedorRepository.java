package uniminuto.datronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uniminuto.datronix.entity.Proveedor;

// Spring Data crea las operaciones básicas para proveedores.
public interface ProveedorRepository extends JpaRepository<Proveedor,Long> {
    
}
