package co.ucentral.cajero.Cajero.persistencia.repositorios;





import co.ucentral.cajero.Cajero.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}