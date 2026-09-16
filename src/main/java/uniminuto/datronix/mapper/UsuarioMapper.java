package uniminuto.datronix.mapper;

import uniminuto.datronix.dto.UsuarioDTO;
import uniminuto.datronix.entity.Usuario;

public class UsuarioMapper {

    // Entity -> DTO
    public static UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null)
            return null;
        return UsuarioDTO.builder()
                .idUsuario(usuario.getIdUsuario())
                .nombreUsuario(usuario.getNombreUsuario())
                .contrasenaUsuario(usuario.getContrasenaUsuario())
                .correoUsuario(usuario.getCorreoUsuario())
                .build();
    }

    // DTO -> Entity
    public static Usuario toEntity(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null)
            return null;
        return Usuario.builder()
                .idUsuario(usuarioDTO.getIdUsuario())
                .nombreUsuario(usuarioDTO.getNombreUsuario())
                .correoUsuario(usuarioDTO.getCorreoUsuario())
                .contrasenaUsuario(usuarioDTO.getContrasenaUsuario())
                .build();
    }

}
