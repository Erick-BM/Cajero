package co.ucentral.cajero.Cajero.servicios;

import co.ucentral.cajero.Cajero.persistencia.entidades.Transferencia;
import co.ucentral.cajero.Cajero.persistencia.entidades.Usuario;
import co.ucentral.cajero.Cajero.persistencia.repositorios.TransferenciaRepositorio;
import co.ucentral.cajero.Cajero.persistencia.repositorios.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class TransferenciaServicio {

    private final TransferenciaRepositorio transferenciaRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    public void realizarTransferencia(String usuarioOrigen, String usuarioDestino, Double monto) {
        // Verificar que el usuario origen existe
        Usuario origen = usuarioRepositorio.findByNombre(usuarioOrigen);
        if (origen == null) {
            throw new RuntimeException("El usuario origen no existe");
        }

        // Verificar que el usuario destino existe
        Usuario destino = usuarioRepositorio.findByNombre(usuarioDestino);
        if (destino == null) {
            throw new RuntimeException("El usuario destino no existe");
        }

        // Verificar que el usuario origen tiene saldo suficiente
        if (origen.getSaldo() < monto) {
            throw new RuntimeException("Saldo insuficiente para realizar la transferencia");
        }

        // Realizar la transferencia
        origen.setSaldo(origen.getSaldo() - monto);
        destino.setSaldo(destino.getSaldo() + monto);

        usuarioRepositorio.save(origen);
        usuarioRepositorio.save(destino);

        // Registrar la transferencia
        Transferencia transferencia = new Transferencia(null, usuarioOrigen, usuarioDestino, monto, LocalDateTime.now());
        transferenciaRepositorio.save(transferencia);
    }
}
