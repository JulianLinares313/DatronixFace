package uniminuto.datronix.exception;

// Señala que el cliente solicitado no existe en la base de datos.
public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String id) {
        super("Cliente con ID " + id + " no encontrado");
    }

    public ClienteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}