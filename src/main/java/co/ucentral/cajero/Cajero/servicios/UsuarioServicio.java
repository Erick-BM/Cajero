package co.ucentral.cajero.Cajero.servicios;

import co.ucentral.cajero.Cajero.persistencia.entidades.Usuario;
import co.ucentral.cajero.Cajero.persistencia.repositorios.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;

    // Validar usuario por nombre y contraseña
    public Usuario validarUsuario(String nombre, String contrasena) {
        Usuario usuario = usuarioRepositorio.findByNombre(nombre); // Buscar usuario por nombre
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            return usuario; // Credenciales correctas
        }
        return null; // Credenciales incorrectas
    }
}

