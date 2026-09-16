package uniminuto.datronix.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import uniminuto.datronix.dto.UsuarioDTO;
import uniminuto.datronix.entity.Usuario;
import uniminuto.datronix.exception.UsuarioNotFoundException;
import uniminuto.datronix.mapper.UsuarioMapper;
import uniminuto.datronix.repository.UsuarioRepository;

@Service
// Contiene las consultas de usuarios y la comprobación usada por el inicio de sesión.
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {

        this.usuarioRepository = usuarioRepository;

    }

    public List<UsuarioDTO> listarUsuarios() {
        // Devuelve todos los usuarios que conoce el repositorio.
        return usuarioRepository.findAll()
        .stream()
        .map(UsuarioMapper::toDTO)
        .collect(Collectors.toList());
        
    }

    public UsuarioDTO buscarUsuarioPorId(String id) {
        // La búsqueda termina con un mensaje de error si el usuario no existe.
        Usuario usuario= usuarioRepository.findById(id)
                .orElseThrow(()-> new UsuarioNotFoundException(id));
                return UsuarioMapper.toDTO(usuario);

    }

    public UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO) {
        // Persiste el usuario que llegó desde el controlador.
        Usuario usuario=UsuarioMapper.toEntity(usuarioDTO);
        Usuario creado =usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(creado);
    }


    public void eliminarUsuario(String id) {
        // Primero se comprueba que el registro exista para evitar un borrado silencioso.
        if (!usuarioRepository.existsById(id)) {

            throw new UsuarioNotFoundException(id);

        } else {

            usuarioRepository.deleteById(id);

        }

    }

    public Usuario autenticar(String correoUsuario, String contrasenaUsuario) {
        // Busca por correo y solo devuelve el usuario cuando la contraseña coincide.
        return usuarioRepository.findByCorreoUsuario(correoUsuario)
                .filter(u -> u.getContrasenaUsuario() != null && u.getContrasenaUsuario().equals(contrasenaUsuario))
                .orElse(null);

    }

}