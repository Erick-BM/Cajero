package co.ucentral.cajero.Cajero.controladores;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class SaldoConsultaControlador {

    @GetMapping("/consultarSaldo")
    public String mostrarSaldo(HttpSession session, Model model) {
        // Obtener el usuario autenticado desde la sesión
        Object usuario = session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/iniciarSesion"; // Redirige si no hay usuario
        }

        // Agregar el saldo del usuario al modelo
        double saldo = ((co.ucentral.cajero.Cajero.persistencia.entidades.Usuario) usuario).getSaldo();
        model.addAttribute("saldo", saldo);

        // Mostrar la página de consulta de saldo
        return "ConsultaSaldo";
    }
}
