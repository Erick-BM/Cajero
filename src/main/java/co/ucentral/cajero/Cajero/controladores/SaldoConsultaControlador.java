package co.ucentral.cajero.Cajero.controladores;

import co.ucentral.cajero.Cajero.persistencia.entidades.SaldoConsulta;
import co.ucentral.cajero.Cajero.servicios.SaldoConsultaServicio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class SaldoConsultaControlador {

    private final SaldoConsultaServicio saldoConsultaServicio;

    @GetMapping("/consultarSaldo")
    public String consultarSaldo(@RequestParam("nombre") String nombreUsuario, Model model) {
        try {
            SaldoConsulta saldoConsulta = saldoConsultaServicio.consultarSaldo(nombreUsuario);
            model.addAttribute("saldo", saldoConsulta.getSaldo());
            model.addAttribute("nombreUsuario", saldoConsulta.getNombreUsuario());
            return "consultaSaldo"; // Nombre de la vista
        } catch (RuntimeException e) {
            model.addAttribute("error", "Usuario no encontrado");
            return "error"; // Vista de error opcional
        }
    }
}
