package co.ucentral.cajero.Cajero.persistencia.repositorios;

import co.ucentral.cajero.Cajero.persistencia.entidades.PagoServicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoServicioRepositorio extends JpaRepository<PagoServicio, Long> {
}
