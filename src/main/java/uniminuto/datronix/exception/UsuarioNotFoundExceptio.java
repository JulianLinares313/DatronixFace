package uniminuto.datronix.exception;

public class UsuarioNotFoundExceptio extends RuntimeException {

    public UsuarioNotFoundExceptio(String id) {

        super("Usuario con ID " + id + " no encontrado");

    }
    // excepcion opcional en el caso de que existas un error en la base de datos

    public UsuarioNotFoundExceptio(String mensaje, Throwable causa) {

        super(mensaje, causa);

    }

}
