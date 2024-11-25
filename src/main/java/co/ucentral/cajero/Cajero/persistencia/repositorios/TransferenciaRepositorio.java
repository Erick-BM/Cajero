package co.ucentral.cajero.Cajero.persistencia.repositorios;

import co.ucentral.cajero.Cajero.persistencia.entidades.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaRepositorio extends JpaRepository<Transferencia, Long> {
}
