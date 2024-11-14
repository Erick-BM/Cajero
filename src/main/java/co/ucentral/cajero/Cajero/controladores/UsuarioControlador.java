package co.ucentral.cajero.Cajero.controladores;



import co.ucentral.cajero.Cajero.entidades.Usuario;
import co.ucentral.cajero.Cajero.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    // Endpoint para registrar un nuevo usuario
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarUsuario(@RequestBody Usuario usuario) {
        try {
            usuarioServicio.registrarUsuario(usuario.getNombre(), usuario.getEmail(), usuario.getPassword());
            return new ResponseEntity<>("Usuario registrado exitosamente.", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint para autenticar un usuario
    @PostMapping("/autenticar")
    public ResponseEntity<String> autenticarUsuario(@RequestParam String email, @RequestParam String password) {
        try {
            Usuario usuario = usuarioServicio.autenticarUsuario(email, password);
            return new ResponseEntity<>("Autenticación exitosa. Bienvenido, " + usuario.getNombre(), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    // Endpoint opcional para desactivar un usuario
    @PutMapping("/desactivar/{id}")
    public ResponseEntity<String> desactivarUsuario(@PathVariable Long id) {
        try {
            usuarioServicio.desactivarUsuario(id);
            return new ResponseEntity<>("Usuario desactivado exitosamente.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
