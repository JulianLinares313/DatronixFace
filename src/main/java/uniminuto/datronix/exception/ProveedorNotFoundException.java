package uniminuto.datronix.exception;

// Señala que el proveedor solicitado no existe en la base de datos.
public class ProveedorNotFoundException extends RuntimeException {
    public ProveedorNotFoundException(Long id) {
        super("Proveedor con ID " + id + " no encontrado");
    }
}