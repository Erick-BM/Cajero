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

    // Mostrar formulario de registro
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    // Procesar registro de usuario
    @PostMapping("/registro")
    public String procesarRegistroUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioServicio.guardar(usuario);
        return "redirect:/iniciarSesion";
    }

    // Mostrar formulario de inicio de sesión
    @GetMapping("/iniciarSesion")
    public String mostrarInicioSesion(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "iniciarSesion";
    }

    // Procesar inicio de sesión de usuario
    @PostMapping("/iniciarSesion")
    public String procesarInicioSesion(@ModelAttribute("usuario") Usuario usuario, Model model) {
        Usuario usuarioAutenticado = usuarioServicio.validarUsuario(usuario.getNombre(), usuario.getContrasena());
        if (usuarioAutenticado != null) {
            return "menuCajero"; // Cambiar a la página principal del cajero
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "iniciarSesion";
        }
    }
}
