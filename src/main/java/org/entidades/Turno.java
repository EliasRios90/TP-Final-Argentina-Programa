package org.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "turnos")
public class Turno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate fecha_turno;
    private LocalTime hora_turno;
    @OneToOne
    @JoinColumn(name = "mascota_id", foreignKey = @ForeignKey(name = "fk_turno_mascota"))
    private Mascota mascota;
    private String estadoTurno;
    @OneToOne
    @JoinColumn(name = "prestacion_id", foreignKey = @ForeignKey(name = "fk_turno_prestacion"))
    private Prestacion prestacion;

    public String retornaTurnos(){
        return getId() +" "+ getMascota().obtenerTipoMascota(mascota) +" "+ prestacion.getDescripcion() +" "+ estadoTurno;
    }
}
