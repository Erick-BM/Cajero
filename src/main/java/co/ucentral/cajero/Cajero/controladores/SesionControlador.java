package co.ucentral.cajero.Cajero.controladores;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class SesionControlador {

    @GetMapping("/cerrarSesion")
    public String cerrarSesion(HttpSession session) {
        session.invalidate(); // Invalidar la sesión actual
        return "redirect:/iniciarSesion"; // Redirigir al inicio de sesión
    }
}
