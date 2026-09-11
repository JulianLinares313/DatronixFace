package uniminuto.datronix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uniminuto.datronix.entity.Producto;

// Spring Data crea las operaciones básicas para consultar y guardar productos.
public interface ProductoRepository extends JpaRepository<Producto,Long>{
    
    @Query("SELECT p FROM Producto p JOIN FETCH p.proveedor")
    List<Producto> findAllWithProveedor();
    
}
