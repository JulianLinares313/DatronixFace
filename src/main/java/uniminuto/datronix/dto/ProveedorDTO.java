package uniminuto.datronix.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// Define los datos de proveedor que necesita el formulario y la API.
public class ProveedorDTO {
    private String idProveedor;
    private String nombreEmpresa;
    private String contactoProveedor;
    private Long telefonoProveedor;
    private String emailProveedor;
    private String direccionProveedor;
}
