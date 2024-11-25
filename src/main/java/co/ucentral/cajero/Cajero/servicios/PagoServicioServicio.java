package co.ucentral.cajero.Cajero.servicios;

import co.ucentral.cajero.Cajero.persistencia.entidades.PagoServicio;
import co.ucentral.cajero.Cajero.persistencia.entidades.Usuario;
import co.ucentral.cajero.Cajero.persistencia.repositorios.PagoServicioRepositorio;
import co.ucentral.cajero.Cajero.persistencia.repositorios.UsuarioRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class PagoServicioServicio {

    private final PagoServicioRepositorio pagoServicioRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    public void pagarServicio(String usuario, String servicio, Double monto) {
        // Verificar que el usuario existe
        Usuario usuarioEntidad = usuarioRepositorio.findByNombre(usuario);
        if (usuarioEntidad == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // Verificar que el usuario tiene saldo suficiente
        if (usuarioEntidad.getSaldo() < monto) {
            throw new RuntimeException("Saldo insuficiente");
        }

        // Actualizar el saldo del usuario
        usuarioEntidad.setSaldo(usuarioEntidad.getSaldo() - monto);
        usuarioRepositorio.save(usuarioEntidad);

        // Registrar el pago del servicio
        PagoServicio pago = new PagoServicio(null, usuario, servicio, monto, LocalDateTime.now());
        pagoServicioRepositorio.save(pago);
    }
}
