package uniminuto.datronix.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// Es la respuesta sencilla que el frontend recibe cuando una operación falla.
public class ErrorResponseDTO {
    private String codigo;      // Ej. "CLI-001"
    private String mensaje;     // Descripción amigable
}