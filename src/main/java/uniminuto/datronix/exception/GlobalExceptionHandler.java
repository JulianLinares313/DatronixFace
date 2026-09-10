package uniminuto.datronix.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Manejador global de errores.
 * Captura todas las excepciones de los controladores y devuelve respuestas
 * bonitas.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // 1. Cliente no encontrado → 404
    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleClienteNotFound(ClienteNotFoundException ex) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .codigo("CLI-001")
                .mensaje(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Si el cliente existe al momento de craerlo -> 409

    @ExceptionHandler(ClienteDuplicadoException.class)
    public ResponseEntity<ErrorResponseDTO> handleClienteDuplicado(ClienteDuplicadoException ex) {

        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .codigo("CLI-002")
                .mensaje(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

    }

    // 2. Producto no encontrado → 404
    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductoNotFound(ProductoNotFoundException ex) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .codigo("PROD-001")
                .mensaje(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Si el Id proveedor existe antes de crearlo -> 409

    @ExceptionHandler(ProveedorIdDuplicadoException.class)
    public ResponseEntity<ErrorResponseDTO> handdleProveedorIdDuplicado(ProveedorIdDuplicadoException ex) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .codigo("PROD-002")
                .mensaje(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

    }

    // Si la empresa existe al momento de craerlo -> 409
    @ExceptionHandler(ProveedorDuplicadoException.class)
    public ResponseEntity<ErrorResponseDTO> handdleProveedorDuplicado(ProveedorDuplicadoException ex) {

        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .codigo("PROD-003")
                .mensaje(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

    }

    @ExceptionHandler(ProveedorTelefonoDuplicadoException.class)
    public ResponseEntity<ErrorResponseDTO> handdleProveedorTelefonoDuplicado(ProveedorTelefonoDuplicadoException ex) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .codigo("PROD-004")
                .mensaje(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

    }

    @ExceptionHandler(ProveedorEmailDuplicadoException.class)
    public ResponseEntity<ErrorResponseDTO> handdleProveedorEmailDuplicado(ProveedorEmailDuplicadoException ex) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .codigo("PROD-005")
                .mensaje(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);

    }

    // 3. Proveedor no encontrado → 404
    @ExceptionHandler(ProveedorNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProveedorNotFound(ProveedorNotFoundException ex) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .codigo("PROV-001")
                .mensaje(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // 4. No se puede eliminar porque tiene dependencias (ej. proveedor con compras)
    // → 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String mensaje = "No se puede eliminar el registro porque tiene dependencias asociadas.";

        // Personalizar mensaje según la tabla afectada
        if (ex.getMessage() != null && ex.getMessage().contains("compra")) {
            mensaje = "No se puede eliminar el proveedor porque tiene compras asociadas. Elimina primero las compras o desactiva el proveedor.";
        } else if (ex.getMessage() != null && ex.getMessage().contains("producto")) {
            mensaje = "No se puede eliminar el proveedor porque tiene productos asociados. Reasigna los productos a otro proveedor o desactívalo.";
        }

        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .codigo("INTEG-001")
                .mensaje(mensaje)
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error); // 409 Conflict
    }

    // 5. Cualquier otro error no controlado → 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .codigo("GEN-500")
                .mensaje("Ocurrió un error interno en el servidor. Contacta al administrador.")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}