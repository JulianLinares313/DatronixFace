package uniminuto.datronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uniminuto.datronix.entity.Compra;

// Spring Data ofrece aquí el CRUD de compras sin código adicional.
public interface CompraRepository extends JpaRepository<Compra,Long>{

    
} 