package co.ucentral.cajero.Cajero.controladores;

import co.ucentral.cajero.Cajero.servicios.DepositoServicio;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class DepositoEfectivoControlador {

    private final DepositoServicio depositoServicio;

    @GetMapping("/depositarEfectivo")
    public String mostrarFormularioDeposito(Model model, HttpSession session) {
        // Obtener el usuario autenticado de la sesión
        Object usuario = session.getAttribute("usuario");
        if (usuario != null) {
            model.addAttribute("nombreUsuario", ((co.ucentral.cajero.Cajero.persistencia.entidades.Usuario) usuario).getNombre());
        } else {
            return "redirect:/iniciarSesion"; // Redirige al inicio de sesión si no hay usuario
        }
        return "DepositoEfectivo"; // Renderiza el formulario de depósito
    }

    @PostMapping("/depositarEfectivo")
    public String procesarDeposito(@RequestParam Double monto, HttpSession session, Model model) {
        // Obtener el usuario autenticado desde la sesión
        Object usuario = session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/iniciarSesion"; // Redirige si no hay usuario
        }

        // Obtener el nombre del usuario
        String nombreUsuario = ((co.ucentral.cajero.Cajero.persistencia.entidades.Usuario) usuario).getNombre();

        try {
            // Llamar al servicio para realizar el depósito
            depositoServicio.realizarDeposito(nombreUsuario, monto);
            model.addAttribute("mensaje", "Depósito realizado exitosamente.");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage()); // Mostrar mensaje de error
        }
        return "DepositoEfectivo"; // Volver a la página del formulario
    }
}
