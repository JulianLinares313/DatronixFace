package uniminuto.datronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uniminuto.datronix.entity.Cliente;

// Spring Data crea automáticamente las consultas básicas de clientes.
public interface ClienteRepository  extends JpaRepository<Cliente,String> {

    
}