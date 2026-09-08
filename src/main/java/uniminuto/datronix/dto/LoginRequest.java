package uniminuto.datronix.dto;

import lombok.Data;

@Data
// Agrupa el correo y la contraseña que llegan desde el formulario de acceso.
public class LoginRequest {

    private String correoUsuario;
    private String contrasenaUsuario;

}
