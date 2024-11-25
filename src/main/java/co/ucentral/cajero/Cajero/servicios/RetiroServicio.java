package co.ucentral.cajero.Cajero.servicios;

import co.ucentral.cajero.Cajero.persistencia.entidades.RetiroEfectivo;
import co.ucentral.cajero.Cajero.persistencia.repositorios.RetiroEfectivoRepositorio;
import co.ucentral.cajero.Cajero.persistencia.repositorios.UsuarioRepositorio;
import co.ucentral.cajero.Cajero.persistencia.entidades.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RetiroServicio {

    private final RetiroEfectivoRepositorio retiroRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    public void realizarRetiro(String nombreUsuario, Double monto) {
        Usuario usuario = usuarioRepositorio.findByNombre(nombreUsuario);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        if (usuario.getSaldo() < monto) {
            throw new RuntimeException("Saldo insuficiente");
        }

        // Actualizar el saldo del usuario
        usuario.setSaldo(usuario.getSaldo() - monto);
        usuarioRepositorio.save(usuario);

        // Registrar el retiro
        RetiroEfectivo retiro = new RetiroEfectivo(null, nombreUsuario, monto, LocalDateTime.now());
        retiroRepositorio.save(retiro);
    }
    public boolean retirarSaldo(String nombreUsuario, double monto) {
        Usuario usuario = usuarioRepositorio.findByNombre(nombreUsuario);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        if (usuario.getSaldo() < monto) {
            throw new RuntimeException("Saldo insuficiente");
        }
        usuario.setSaldo(usuario.getSaldo() - monto); // Actualiza el saldo
        usuarioRepositorio.save(usuario); // Guarda los cambios en la base de datos
        return true;
    }

}
