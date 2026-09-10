package uniminuto.datronix.exception;

public class ProveedorDuplicadoException extends  RuntimeException{
    
public ProveedorDuplicadoException(String nombreEmpresa){
    super("Ya existe uan empresa con el nombre "+nombreEmpresa);

}


}
