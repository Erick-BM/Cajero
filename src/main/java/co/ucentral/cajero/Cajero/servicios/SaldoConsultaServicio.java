package co.ucentral.cajero.Cajero.servicios;

import co.ucentral.cajero.Cajero.persistencia.entidades.SaldoConsulta;
import co.ucentral.cajero.Cajero.persistencia.repositorios.SaldoConsultaRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SaldoConsultaServicio {

    private final SaldoConsultaRepositorio saldoConsultaRepositorio;

    public SaldoConsulta consultarSaldo(String nombreUsuario) {
        Double saldo = saldoConsultaRepositorio.obtenerSaldoPorNombre(nombreUsuario);
        return new SaldoConsulta(nombreUsuario, saldo);
    }
}
