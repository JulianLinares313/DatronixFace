package uniminuto.datronix.service;

import java.util.List;

import org.springframework.stereotype.Service;

import uniminuto.datronix.entity.Usuario;
import uniminuto.datronix.repository.UsuarioRepository;

@Service
// Contiene las consultas de usuarios y la comprobación usada por el inicio de sesión.
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {

        this.usuarioRepository = usuarioRepository;

    }

    public List<Usuario> listarUsuarios() {
        // Devuelve todos los usuarios que conoce el repositorio.
        return usuarioRepository.findAll();

    }

    public Usuario buscarUsuarioPorId(String id) {
        // La búsqueda termina con un mensaje de error si el usuario no existe.
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encotrado"));

    }

    public Usuario guardarUsuario(Usuario usuario) {
        // Persiste el usuario que llegó desde el controlador.
        return usuarioRepository.save(usuario);

    }

    public void eliminarUsuario(String id) {
        // Primero se comprueba que el registro exista para evitar un borrado silencioso.
        if (!usuarioRepository.existsById(id)) {

            throw new RuntimeException("Usuario no encontrado");

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