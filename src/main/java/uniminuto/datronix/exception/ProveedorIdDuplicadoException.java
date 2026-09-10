package uniminuto.datronix.exception;

public class ProveedorIdDuplicadoException extends RuntimeException {

    public ProveedorIdDuplicadoException(String id) {
        super("Ya existe un proveedor con este Id " + id);

    }

}
