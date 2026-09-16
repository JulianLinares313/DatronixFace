package uniminuto.datronix.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uniminuto.datronix.dto.LoginRequest;
import uniminuto.datronix.dto.UsuarioDTO;
import uniminuto.datronix.entity.Usuario;
import uniminuto.datronix.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen
// Atiende el registro, las consultas y el inicio de sesión de los usuarios.
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {

        this.usuarioService = usuarioService;

    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        // Devuelve los usuarios que el servicio encuentra en la base de datos.
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable String id) {
        // Usa el documento o identificador recibido en la URL para buscar un usuario.
        UsuarioDTO usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuario);

    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> guardarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        // Guarda directamente los datos del usuario enviados por el formulario.
        UsuarioDTO usuarioCreado = usuarioService.guardarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);

    }

    @DeleteMapping
    public void eliminarUsuario(@PathVariable String id) {
        // Solicita al servicio que elimine el usuario indicado.
        usuarioService.eliminarUsuario(id);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Comprueba las credenciales y devuelve al usuario o un aviso de acceso
        // rechazado.
        Usuario usuario = usuarioService.autenticar(loginRequest.getCorreoUsuario(),
                loginRequest.getContrasenaUsuario());

        if (usuario != null) {

            return ResponseEntity.ok(usuario);

        } else {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña incorrecta");

        }

    }

}
