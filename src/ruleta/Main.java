package ruleta;

import java.util.Scanner;

public class Main {
    private static Scanner in = new Scanner(System.in).useDelimiter("\n");

    public static void inicioR() {
        System.out.println("BIENVENIDO A LA RULETA!");
        System.out.print("Ingrese su nombre: \n->");
        String nombre = in.next();
        Jugadores j1 = new Jugadores(nombre, 25000);
        j1.setNombre(j1.getNombre().toUpperCase());
        System.out.println("BIENVENIDO " + j1.getNombre() + "!!");
        System.out.println("QUE DESEA HACER A CONTINUACION?");

    }
}
