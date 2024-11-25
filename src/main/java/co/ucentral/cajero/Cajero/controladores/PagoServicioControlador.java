package co.ucentral.cajero.Cajero.controladores;

import co.ucentral.cajero.Cajero.servicios.PagoServicioServicio;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class PagoServicioControlador {

    private final PagoServicioServicio pagoServicioServicio;

    @GetMapping("/pagarServicios")
    public String mostrarFormularioPago(Model model, HttpSession session) {
        Object usuario = session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/iniciarSesion";
        }

        String nombreUsuario = ((co.ucentral.cajero.Cajero.persistencia.entidades.Usuario) usuario).getNombre();
        model.addAttribute("nombreUsuario", nombreUsuario);

        return "PagoServicios"; // Renderiza el formulario
    }

    @PostMapping("/pagarServicios")
    public String procesarPago(
            @RequestParam String servicio,
            @RequestParam Double monto,
            HttpSession session,
            Model model) {
        Object usuario = session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/iniciarSesion";
        }

        String nombreUsuario = ((co.ucentral.cajero.Cajero.persistencia.entidades.Usuario) usuario).getNombre();

        try {
            pagoServicioServicio.pagarServicio(nombreUsuario, servicio, monto);
            model.addAttribute("mensaje", "Pago realizado exitosamente.");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "PagoServicios";
    }
}
