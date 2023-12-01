package org.entidades;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "mascotas")
public abstract class Mascota implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String raza;
    private Integer edad;

    public String obtenerTipoMascota(Mascota mascota){
        // Encuentra la posición del último punto (.)
        String nombreClase = mascota.getClass().getName();
        int ultimaPosicionPunto = nombreClase.lastIndexOf('.');

        // Utiliza substring para obtener la parte después del último punto
        String resultado = nombreClase.substring(ultimaPosicionPunto + 1);
        return resultado;
    }
}
