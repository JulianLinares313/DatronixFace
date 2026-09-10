package uniminuto.datronix.exception;

public class ProveedorDuplicadoException extends  RuntimeException{
    
public ProveedorDuplicadoException(String nombreEmpresa){
    super("Ya existe una empresa con el nombre "+nombreEmpresa);

}




}
