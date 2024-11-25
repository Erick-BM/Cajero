package co.ucentral.cajero.Cajero.servicios;

import co.ucentral.cajero.Cajero.persistencia.entidades.Usuario;
import co.ucentral.cajero.Cajero.persistencia.repositorios.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;

    // Método para validar el usuario al iniciar sesión
    public Usuario validarUsuario(String nombre, String contrasena) {
        Usuario usuario = usuarioRepositorio.findByNombre(nombre);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            return usuario;
        }
        return null;
    }

    // Método para consultar el saldo
    public Double consultarSaldo(String nombre) {
        Usuario usuario = usuarioRepositorio.findByNombre(nombre);
        if (usuario != null) {
            return usuario.getSaldo();
        }
        return null; // Devuelve null si el usuario no existe


    }
    public boolean retirarSaldo(String nombreUsuario, double monto) {
        Usuario usuario = usuarioRepositorio.findByNombre(nombreUsuario);
        if (usuario != null && usuario.getSaldo() >= monto) {
            usuario.setSaldo(usuario.getSaldo() - monto);
            usuarioRepositorio.save(usuario);
            return true;
        }
        return false;
    }

}