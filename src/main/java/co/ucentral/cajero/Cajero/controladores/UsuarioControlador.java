package co.ucentral.cajero.Cajero.controladores;

import co.ucentral.cajero.Cajero.persistencia.entidades.Usuario;
import co.ucentral.cajero.Cajero.servicios.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    // Mostrar formulario de inicio de sesión
    @GetMapping("/iniciarSesion")
    public String mostrarInicioSesion(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "iniciarSesion";
    }

    // Procesar inicio de sesión
    @PostMapping("/iniciarSesion")
    public String procesarInicioSesion(@ModelAttribute("usuario") Usuario usuario, Model model) {
        // Validar las credenciales del usuario
        Usuario usuarioAutenticado = usuarioServicio.validarUsuario(usuario.getNombre(), usuario.getContrasena());

        if (usuarioAutenticado != null) {
            // Credenciales correctas, redirigir al menú principal
            model.addAttribute("usuario", usuarioAutenticado);
            return "menuCajero"; // Redirige a la vista del menú principal
        } else {
            // Credenciales incorrectas, mostrar mensaje de error
            model.addAttribute("error", "Credenciales incorrectas. Por favor, inténtalo de nuevo.");
            return "iniciarSesion"; // Vuelve a la página de inicio de sesión
        }
    }
}

