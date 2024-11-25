package co.ucentral.cajero.Cajero.controladores;

import co.ucentral.cajero.Cajero.servicios.TransferenciaServicio;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class TransferenciaControlador {

    private final TransferenciaServicio transferenciaServicio;

    @GetMapping("/transferirDinero")
    public String mostrarFormularioTransferencia(Model model, HttpSession session) {
        Object usuario = session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/iniciarSesion";
        }

        String nombreUsuario = ((co.ucentral.cajero.Cajero.persistencia.entidades.Usuario) usuario).getNombre();
        model.addAttribute("nombreUsuario", nombreUsuario);

        return "TransferenciaDinero"; // Renderiza el formulario
    }

    @PostMapping("/transferirDinero")
    public String procesarTransferencia(
            @RequestParam Double monto,
            @RequestParam String usuarioDestino,
            HttpSession session,
            Model model) {
        Object usuario = session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/iniciarSesion";
        }

        String usuarioOrigen = ((co.ucentral.cajero.Cajero.persistencia.entidades.Usuario) usuario).getNombre();

        try {
            transferenciaServicio.realizarTransferencia(usuarioOrigen, usuarioDestino, monto);
            model.addAttribute("mensaje", "Transferencia realizada exitosamente.");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "TransferenciaDinero";
    }
}
