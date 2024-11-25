package co.ucentral.cajero.Cajero.controladores;

import co.ucentral.cajero.Cajero.persistencia.entidades.Usuario;
import co.ucentral.cajero.Cajero.servicios.UsuarioServicio;
import jakarta.servlet.http.HttpSession;
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
    public String procesarInicioSesion(@ModelAttribute("usuario") Usuario usuario, HttpSession session, Model model) {
        Usuario usuarioAutenticado = usuarioServicio.validarUsuario(usuario.getNombre(), usuario.getContrasena());

        if (usuarioAutenticado != null) {
            session.setAttribute("usuario", usuarioAutenticado); // Guardar el usuario en la sesión
            return "redirect:/menuCajero"; // Redirigir al menú principal
        } else {
            model.addAttribute("error", "Credenciales incorrectas. Por favor, inténtalo de nuevo.");
            return "iniciarSesion"; // Volver a la página de inicio de sesión
        }
    }
}

