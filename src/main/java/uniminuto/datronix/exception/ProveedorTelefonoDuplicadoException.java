package uniminuto.datronix.exception;

public class ProveedorTelefonoDuplicadoException extends RuntimeException {

public ProveedorTelefonoDuplicadoException(Long telefonoProveedor){

    super("Ya existe un proveedor con este telefono "+telefonoProveedor);


}
    
}
