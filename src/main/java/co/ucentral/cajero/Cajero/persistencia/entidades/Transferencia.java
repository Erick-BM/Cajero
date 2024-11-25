package co.ucentral.cajero.Cajero.persistencia.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transferencias")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_origen", nullable = false)
    private String usuarioOrigen;

    @Column(name = "usuario_destino", nullable = false)
    private String usuarioDestino;

    @Column(name = "monto", nullable = false)
    private Double monto;

    @Column(name = "fecha_transferencia", nullable = false)
    private LocalDateTime fechaTransferencia;
}
