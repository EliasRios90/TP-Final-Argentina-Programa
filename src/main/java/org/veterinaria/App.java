package org.veterinaria;

import org.entidades.*;
import org.util.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class App 
{
    public static void main( String[] args )
    {
        /*Menu menu = new Menu();
        menu.verMenu();*/

        /*TurnoDAO turnoDAO = new TurnoRepository();
        List<Turno> lista = turnoDAO.obtenerTodos();

        for(Turno turno : lista){
            System.out.println(turno.retornaTurnos());
        }

        System.out.println("*************************************");
        MascotaDAO mascotaDAO = new MascotaRepository();
        List<Mascota> perros = mascotaDAO.obtenerTiposMascotas("Gato");
        System.out.println("Tamaño lista: "+perros.size());
        for(Mascota perro : perros){
            System.out.println(perro.toString());
        }*/

        MascotaDAO mascotaDAO = new MascotaRepository();
        PrestacionDAO prestacionDAO = new PrestacionRepository();
        TurnoDAO turnoDAO = new TurnoRepository();

        Mascota perro1 = new Perro(1, "Toby", "Pequinez", 11);
        Mascota gato1 = new Gato(2, "Michi", "Siberiano", 3);
        mascotaDAO.guardar(perro1);
        mascotaDAO.guardar(gato1);

        Mascota perro2 = new Perro(3, "Sancho", "Rottweiler", 17);
        Mascota gato2 = new Gato(4, "Lulu", "Birmano", 5);
        mascotaDAO.guardar(perro2);
        mascotaDAO.guardar(gato2);

        Prestacion prestacion1 = new Prestacion(1, "Vacunación", 5500.90);
        Prestacion prestacion2 = new Prestacion(2, "Castración", 15500.0);
        Prestacion prestacion3 = new Prestacion(3, "Desparasitación", 7500.50);
        Prestacion prestacion4 = new Prestacion(4, "Radiografía digital", 27500.50);
        prestacionDAO.guardar(prestacion1);
        prestacionDAO.guardar(prestacion2);
        prestacionDAO.guardar(prestacion3);
        prestacionDAO.guardar(prestacion4);

        LocalDate fecha = LocalDate.of(2023, 12, 7);
        LocalTime hora = LocalTime.of(14, 30, 00);
        Turno turno1 = new Turno(1, fecha, hora, perro1, "libre", prestacion1);
        Turno turno2 = new Turno(2, fecha, hora, perro2, "libre", prestacion2);
        Turno turno3 = new Turno(3, fecha, hora, gato1, "libre", prestacion3);
        Turno turno4 = new Turno(4, fecha, hora, gato2, "libre", prestacion4);

        turnoDAO.guardar(turno1);
        turnoDAO.guardar(turno2);
        turnoDAO.guardar(turno3);
        turnoDAO.guardar(turno4);
    }
}
