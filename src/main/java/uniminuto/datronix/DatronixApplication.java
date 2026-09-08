package uniminuto.datronix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de Datronix: desde aquí Spring Boot levanta toda la aplicación.
 * Al estar en el paquete principal, Spring también descubre los controladores,
 * servicios, repositorios y entidades que forman parte del sistema.
 */
@SpringBootApplication
public class DatronixApplication {

	// Inicia el servidor y prepara el contexto donde vive el resto de la aplicación.
	public static void main(String[] args) {
		SpringApplication.run(DatronixApplication.class, args);
	}

}
