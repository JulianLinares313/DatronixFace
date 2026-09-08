package uniminuto.datronix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
// Reúne los errores del backend para que todas las respuestas tengan una forma predecible.
public class GlobalExceptionHandler {

    // --- Cliente ---
    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleClienteNotFound(ClienteNotFoundException ex) {
        // Un cliente inexistente se comunica como 404, no como un error inesperado del servidor.
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .codigo("CLI-001")
                .mensaje(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // --- Producto ---
    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductoNotFound(ProductoNotFoundException ex) {
        // Mantiene el mismo formato de error para las búsquedas fallidas de productos.
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .codigo("PROD-001")
                .mensaje(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // --- Proveedor ---
    @ExceptionHandler(ProveedorNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProveedorNotFound(ProveedorNotFoundException ex) {
        // Mantiene el mismo formato de error para los proveedores que no existen.
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .codigo("PROV-001")
                .mensaje(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // --- Genérico ---
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
        // Los errores no previstos se ocultan al cliente y se responden como fallo interno.
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .codigo("GEN-500")
                .mensaje("Ocurrió un error interno en el servidor")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}