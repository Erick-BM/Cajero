package co.ucentral.cajero.Cajero.servicios;

import co.ucentral.cajero.Cajero.persistencia.entidades.DepositoEfectivo;
import co.ucentral.cajero.Cajero.persistencia.repositorios.DepositoEfectivoRepositorio;
import co.ucentral.cajero.Cajero.persistencia.repositorios.UsuarioRepositorio;
import co.ucentral.cajero.Cajero.persistencia.entidades.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class DepositoServicio {

    private final DepositoEfectivoRepositorio depositoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    public void realizarDeposito(String nombreUsuario, Double monto) {
        Usuario usuario = usuarioRepositorio.findByNombre(nombreUsuario);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        if (monto <= 0) {
            throw new RuntimeException("El monto debe ser mayor a 0");
        }

        // Actualizar el saldo del usuario
        usuario.setSaldo(usuario.getSaldo() + monto);
        usuarioRepositorio.save(usuario);

        // Registrar el depósito
        DepositoEfectivo deposito = new DepositoEfectivo(null, nombreUsuario, monto, LocalDateTime.now());
        depositoRepositorio.save(deposito);
    }
}
