package uniminuto.datronix.exception;

public class ProveedorEmailDuplicadoException extends RuntimeException {

    public  ProveedorEmailDuplicadoException(String EmailProveedor){
        super("Ya existe un proveedor con este email "+EmailProveedor);


    }


}
