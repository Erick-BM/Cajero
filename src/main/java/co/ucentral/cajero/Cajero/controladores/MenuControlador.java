package co.ucentral.cajero.Cajero.controladores;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class MenuControlador {

    @GetMapping("/menuCajero")
    public String mostrarMenu(HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/iniciarSesion"; // Redirigir al inicio de sesión si no hay usuario
        }
        return "menuCajero"; // Mostrar el menú principal
    }
}
