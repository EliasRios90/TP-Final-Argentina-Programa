package org.veterinaria;

import org.entidades.*;
import org.util.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Menu extends MenuTemplate {
    Scanner sc = new Scanner(System.in);

    public void verMenu(){
        Integer opcion;
        Integer menu = display();
        if(menu==1){
            opcion = seleccionUsuario();
            if(opcion==1){// Registrar mascota
                MascotaDAO mascotaDAO = new MascotaRepository();
                System.out.println("Tipo de mascota:\n1) Perro\n2) Gato");
                opcion = sc.nextInt();
                System.out.print("Ingrese el nombre de la mascota: ");
                String nombre = sc.next();
                System.out.print("Ingrese la raza de la mascota: ");
                String raza = sc.next();
                System.out.print("Ingrese la edad de la mascota: ");
                Integer edad = sc.nextInt();

                Integer numeroId = mascotaDAO.cantidadMascotas() +1;
                Mascota mascota = (opcion==1) ? new Perro(numeroId, nombre, raza, edad) : new Gato(numeroId, nombre, raza, edad);
                mascotaDAO.guardar(mascota);

                System.out.println("\n****************************************\n");
                System.out.println("Mascota registrada");
            }else{// Sacar turno
                TurnoDAO turnoDAO = new TurnoRepository();
                MascotaDAO mascotaDAO = new MascotaRepository();
                PrestacionDAO prestacionDAO = new PrestacionRepository();

                LocalDate fecha = LocalDate.of(2023, 12, 7);
                LocalTime hora = LocalTime.of(14, 30, 00);

                System.out.println("Seleccione a la mascota:");
                for(Mascota mascota : mascotaDAO.obtenerTodos()){
                    System.out.println(mascota.toString());
                }
                opcion = sc.nextInt();
                Mascota mascota = mascotaDAO.obtenerPorID(opcion);

                System.out.println("Elija el servicio:");
                for(Prestacion prestacion : prestacionDAO.obtenerTodos()){
                    System.out.println(prestacion.toString());
                }
                opcion = sc.nextInt();
                Prestacion prestacion = prestacionDAO.obtenerPorID(opcion);

                Integer numeroId = turnoDAO.cantidadTurnos() +1;
                Turno turnoPerro = new Turno(numeroId, fecha, hora, mascota, "libre", prestacion);
                turnoDAO.guardar(turnoPerro);

                System.out.println("\n****************************************\n");
                System.out.println("Turno registrado");
            }
        }
        else{// Asignacion de turno
            TurnoDAO turnoDAO = new TurnoRepository();

            System.out.println("Seleccione un turno disponible");
            List<Turno> lista = turnoDAO.obtenerTurnosLibres();
            if(lista != null){
                for (Turno turno : lista) {
                    System.out.println(turno.toString());
                }
                opcion = sc.nextInt();
                Turno turnoLibre = turnoDAO.obtenerPorID(opcion);
                turnoDAO.asignarTurno(turnoLibre);

                System.out.println("\n****************************************\n");
                System.out.println("Turno asignado");
            }
            else System.out.println("No hay turnos disponibles para asignación");
        }
    }

    @Override
    public Integer seleccionUsuario(){
        Integer opcion = 0;
        do{
            System.out.println("Elija una opcion:");
            System.out.println("1. Registrar mascota");
            System.out.println("2. Sacar turno");
            opcion = sc.nextInt();
        }while (opcion != 1 && opcion != 2);
        return opcion;
    }
}
