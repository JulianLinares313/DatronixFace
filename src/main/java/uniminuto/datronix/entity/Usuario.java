package uniminuto.datronix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder 
// Representa la cuenta que puede identificarse y entrar al sistema.
public class Usuario {

    @Id
    @Column(name = "idusuario", nullable = false)
    private String idUsuario; // Cédula / documento

    @Column(name = "nombreusuario", length = 255)
    private String nombreUsuario;

    @Column(name = "correousuario", length = 255)
    private String correoUsuario;

    @Column(name = "contrasenausuario", length = 255)
    private String contrasenaUsuario;

}