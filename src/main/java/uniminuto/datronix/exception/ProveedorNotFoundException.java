package uniminuto.datronix.exception;

// Señala que el proveedor solicitado no existe en la base de datos.
public class ProveedorNotFoundException extends RuntimeException {
    public ProveedorNotFoundException(String id) {
        super("Proveedor con ID " + id + " no encontrado");
    }

    //excepcion opcional en el caso de que existas un error en la base de datos
    public ProveedorNotFoundException(String mensaje, Throwable causa) {

        super(mensaje, causa);

    }
}