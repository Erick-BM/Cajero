package co.ucentral.cajero.Cajero.servicios;

import co.ucentral.cajero.Cajero.persistencia.entidades.Usuario;
import co.ucentral.cajero.Cajero.persistencia.repositorios.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;

    // Método para obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        return (List<Usuario>) usuarioRepositorio.findAll();
    }

    // Método para borrar un usuario
    public boolean borrar(Usuario usuario) {
        try {
            usuarioRepositorio.delete(usuario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Método para guardar un usuario
    public void guardar(Usuario usuario) {
        try {
            usuarioRepositorio.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el usuario", e);
        }
    }

    // Método para validar el usuario en el inicio de sesión
    public Usuario validarUsuario(String nombre, String contrasena) {
        Usuario usuario = usuarioRepositorio.findByNombre(nombre);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            return usuario;
        }
        return null;
    }

    // Método para consultar el saldo del usuario
    public Double consultarSaldo(String nombre) {
        Usuario usuario = usuarioRepositorio.findByNombre(nombre);
        if (usuario != null) {
            return usuario.getSaldo();
        }
        return null;
    }

    // Método para actualizar el saldo del usuario (retiro o depósito)
    public boolean actualizarSaldo(String nombre, Double nuevoSaldo) {
        Usuario usuario = usuarioRepositorio.findByNombre(nombre);
        if (usuario != null) {
            usuario.setSaldo(nuevoSaldo);
            usuarioRepositorio.save(usuario);
            return true;
        }
        return false;
    }
}
