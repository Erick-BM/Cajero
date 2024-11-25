package co.ucentral.cajero.Cajero.persistencia.repositorios;

import co.ucentral.cajero.Cajero.persistencia.entidades.DepositoEfectivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositoEfectivoRepositorio extends JpaRepository<DepositoEfectivo, Long> {
}
