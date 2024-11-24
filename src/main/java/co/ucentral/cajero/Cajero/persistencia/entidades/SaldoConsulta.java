package co.ucentral.cajero.Cajero.persistencia.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaldoConsulta {
    private String nombreUsuario;
    private Double saldo;
}
