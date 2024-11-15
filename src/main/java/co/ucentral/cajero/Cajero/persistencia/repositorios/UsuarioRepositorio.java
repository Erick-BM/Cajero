package co.ucentral.cajero.Cajero.persistencia.repositorios;

import co.ucentral.cajero.Cajero.persistencia.entidades.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, String> {

    Usuario findByNombre(String nombre);
}
