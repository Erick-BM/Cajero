package co.ucentral.cajero.Cajero.persistencia.repositorios;

import co.ucentral.cajero.Cajero.persistencia.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SaldoConsultaRepositorio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Double obtenerSaldoPorNombre(String nombreUsuario) {
        Usuario usuario = usuarioRepositorio.findByNombre(nombreUsuario);
        if (usuario != null) {
            return usuario.getSaldo();
        }
        throw new RuntimeException("Usuario no encontrado");
    }
}
