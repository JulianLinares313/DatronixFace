package uniminuto.datronix.exception;

public class ClienteDuplicadoException extends RuntimeException {

    public ClienteDuplicadoException(String id) {
        super("Ya existe un cliente con este Id " + id);

    }

}
