package uniminuto.datronix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uniminuto.datronix.entity.Nomina;

// Spring Data ofrece aquí el CRUD de registros de nómina.
public interface NominaRepository extends JpaRepository<Nomina, Long> {

}