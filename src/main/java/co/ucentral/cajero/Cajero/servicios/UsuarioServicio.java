package co.ucentral.cajero.Cajero.servicios;


import co.ucentral.cajero.Cajero.entidades.Usuario;
import co.ucentral.cajero.Cajero.persistencia.repositorios.UsuarioRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    // Método para registrar un nuevo usuario
    public Usuario registrarUsuario(String nombre, String email, String password) {
        // Verificar si el usuario ya existe
        if (usuarioRepositorio.findByEmail(email) != null) {
            throw new IllegalArgumentException("El usuario con este email ya existe.");
        }

        Usuario nuevoUsuario = new Usuario(nombre, email, password, true);
        return usuarioRepositorio.save(nuevoUsuario);
    }

    // Método para autenticar un usuario
    public Usuario autenticarUsuario(String email, String password) {
        Usuario usuario = usuarioRepositorio.findByEmail(email);

        if (usuario != null && usuario.getPassword().equals(password) && usuario.isActivo()) {
            return usuario;
        } else {
            throw new IllegalArgumentException("Email o contraseña incorrectos, o la cuenta está inactiva.");
        }
    }

    // Método para desactivar un usuario (opcional)
    public void desactivarUsuario(Long id) {
        Optional<Usuario> usuarioOpt = usuarioRepositorio.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setActivo(false);
            usuarioRepositorio.save(usuario);
        } else {
            throw new IllegalArgumentException("Usuario no encontrado.");
        }
    }
}
