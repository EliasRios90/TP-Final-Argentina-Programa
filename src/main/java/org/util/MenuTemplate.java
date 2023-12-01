package org.util;

import java.util.Scanner;

public abstract class MenuTemplate {
    Scanner sc = new Scanner(System.in);
    public final Integer display(){
        Integer opcion = seleccionar();
        return opcion;
    }

    public Integer seleccionar(){
        Integer opcion = 0;
        do {
            System.out.println("Seleccione una opción");
            System.out.println("1. Cliente");
            System.out.println("2. Veterinaria");
            opcion = sc.nextInt();
        }while (opcion !=1 && opcion != 2);
        return opcion;
    }

    protected abstract Integer seleccionUsuario();
}
