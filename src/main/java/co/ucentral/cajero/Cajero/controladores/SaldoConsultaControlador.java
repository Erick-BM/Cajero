package co.ucentral.cajero.Cajero.controladores;

import co.ucentral.cajero.Cajero.servicios.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class SaldoConsultaControlador {

    private final UsuarioServicio usuarioServicio;

    @GetMapping("/consultaSaldo")
    public String consultaSaldo(Model model) {
        // Usuario de ejemplo para la consulta
        String nombreUsuario = "usuarioEjemplo";
        Double saldo = usuarioServicio.consultarSaldo(nombreUsuario);
        model.addAttribute("saldo", saldo != null ? saldo : 0.0); // Default en caso de error
        return "ConsultaSaldo";
    }
}