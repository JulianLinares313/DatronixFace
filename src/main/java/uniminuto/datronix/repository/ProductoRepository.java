package uniminuto.datronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uniminuto.datronix.entity.Producto;

// Spring Data crea las operaciones básicas para consultar y guardar productos.
public interface ProductoRepository extends JpaRepository<Producto,Long>{
    
}
