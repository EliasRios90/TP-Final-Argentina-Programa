package org.entidades;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Entity
public class Gato extends Mascota{
    public Gato(Integer id, String nombre, String raza, Integer edad){
        super(id, nombre, raza, edad);
    }
}
