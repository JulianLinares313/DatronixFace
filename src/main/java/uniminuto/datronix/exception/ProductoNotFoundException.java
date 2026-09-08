package uniminuto.datronix.exception;

// Señala que el producto solicitado no existe en la base de datos.
public class ProductoNotFoundException extends RuntimeException {
    public ProductoNotFoundException(Long id) {
        super("Producto con ID " + id + " no encontrado");
    }
}