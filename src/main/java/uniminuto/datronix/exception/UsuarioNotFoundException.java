package uniminuto.datronix.exception;

public class UsuarioNotFoundException extends RuntimeException {

    public UsuarioNotFoundException(String id) {

        super("Usuario con ID " + id + " no encontrado");

    }
    // excepcion opcional en el caso de que existas un error en la base de datos

    public UsuarioNotFoundException(String mensaje, Throwable causa) {

        super(mensaje, causa);

    }

}
