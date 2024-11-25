package co.ucentral.cajero.Cajero.controladores;

import co.ucentral.cajero.Cajero.servicios.RetiroServicio;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class RetiroEfectivoControlador {

    private final RetiroServicio retiroServicio;

    @GetMapping("/retirarEfectivo")
    public String mostrarFormularioRetiro(Model model, HttpSession session) {
        // Obtener el usuario autenticado de la sesión
        Object usuario = session.getAttribute("usuario");
        if (usuario != null) {
            model.addAttribute("nombreUsuario", ((co.ucentral.cajero.Cajero.persistencia.entidades.Usuario) usuario).getNombre());
        } else {
            return "redirect:/iniciarSesion"; // Redirige al inicio de sesión si no hay usuario
        }
        return "RetiroEfectivo"; // Renderiza el formulario de retiro
    }

    @PostMapping("/retirarEfectivo")
    public String procesarRetiro(@RequestParam String nombreUsuario, @RequestParam Double monto, Model model) {
        try {
            boolean exito = retiroServicio.retirarSaldo(nombreUsuario, monto);
            if (exito) {
                model.addAttribute("mensaje", "Retiro realizado exitosamente.");
            } else {
                model.addAttribute("error", "Saldo insuficiente o error en la operación.");
            }
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage()); // Muestra el mensaje de error
        }
        return "RetiroEfectivo"; // Regresa a la página de retiro
    }
}
