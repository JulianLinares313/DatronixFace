package uniminuto.datronix.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// Es el formato ligero con el que el frontend recibe y envía clientes.
public class ClienteDTO {
    private String idCliente;
    private String nombreCliente;
    private Long telefonoCliente;
    private String emailCliente;
    private String direccionCliente;
    private String tipoCliente;
}