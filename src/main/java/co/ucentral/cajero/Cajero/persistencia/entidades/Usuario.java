package co.ucentral.cajero.Cajero.persistencia.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(name = "usu_nombre")
    private String nombre;

    @Column(name = "usu_apellido")
    private String apellido;

    @Column(name = "usu_contrasena", nullable = false)
    private String contrasena;

    @Column(name = "usu_correo")
    private String correo;

    @Column(name = "usu_saldo")
    private Double saldo; // Saldo en la cuenta del usuario, útil para operaciones del cajero
}
