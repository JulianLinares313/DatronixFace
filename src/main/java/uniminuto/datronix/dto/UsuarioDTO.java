package uniminuto.datronix.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder
@AllArgsConstructor 
@NoArgsConstructor 
 
public class UsuarioDTO {


    private String idUsuario; 

    private String nombreUsuario;

    private String correoUsuario;

    private String contrasenaUsuario;


    
}
