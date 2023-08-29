import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static List<Carta> cartasEnBaraja = new ArrayList<>();
    private static Scanner in = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) {
        baraja();
        System.out.println("BIENVENIDO A BLACKJACK!");
        System.out.print("Ingrese su nombre: \n->");
        String nombre = in.next();
        Jugadores j1 = new Jugadores(nombre, 25000); // Creamos el jugador con el nombre antes ingresado
        Jugadores cru = new Jugadores("crupier", 1147483647);// Creamos al crupier
        j1.setNombre(j1.getNombre().toUpperCase());
        System.out.println("BIENVENIDO " + j1.getNombre() + "!!");
        System.out.println("QUE DESEA HACER A CONTINUACION?");
        elegir(j1, cru);

    }

    // Creamos la baraja
    public static void baraja() {

        String[] palos = {"Corazones", "Diamantes", "Tréboles", "Picas"};
        String[] valores = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jota", "Reina", "Rey"};


        for (String palo : palos) {
            for (String valor : valores) {
                cartasEnBaraja.add(new Carta(valor, palo));
            }
        }
    }

    // Funcion que permite elegir al jugador que hacer
    public static void elegir(Jugadores j1, Jugadores crupier) {
        System.out.print("1. JUGAR\n2. VER DINERO\n3. SALIR\n->");
        int decision = in.nextInt();
        switch (decision) {
            case 1:
                System.out.println("-------------------EMPIEZA EL JUEGO-------------------");
                System.out.println("CRUPIER LISTO");
                System.out.print("------------------------------------------------------\n" +
                        "ESPERE MIENTRAS EL GRUPIER DA LAS CARTAS\n" +
                        "------------------------------------------------------\n");
                try {
                    Thread.sleep(3000); // TIEMPO DE ESPERA PARA GENERAR REALISMO
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                jugada(j1, crupier);
                break;
            case 2:
                System.out.println("------------------------------------------------------\n" +
                        "SU DINERO ACTUAL ES DE: $" + j1.getDinero()
                        + "\n------------------------------------------------------");
                elegir(j1, crupier);
            case 3:
                System.exit(0);
        }
    }

    //JUEGO
    public static void jugada(Jugadores j1, Jugadores crupier) {
        System.out.print("Ingrese su apuesta\n->$");
        int apuesta = in.nextInt(); // APUESTA REALIZADA
        System.out.println("\n\n" + j1.getNombre() + "\t\t\t\t\t\t\t\tCRUPIER");
        int num = ThreadLocalRandom.current().nextInt(52);
        int num3 = ThreadLocalRandom.current().nextInt(52); // NUMERO CARTA CRUPIER
        int num2 = ThreadLocalRandom.current().nextInt(52);
        Carta carta1 = cartasEnBaraja.get(num);
        Carta carta3 = cartasEnBaraja.get(num3); // CARTA CRUPIER
        Carta carta2 = cartasEnBaraja.get(num2);
        j1.agregarMano(carta1); // AGREGAMOS LAS CARTAS A LA MANO DEL JUGADOR
        j1.agregarMano(carta2);
        System.out.println(carta1.getValor() + " de " + carta1.getPalo());
        try {
            Thread.sleep(3000); // TIEMPO DE ESPERA PARA GENERAR REALISMO
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\t\t\t\t\t\t\t\t" + carta3.getValor() + " de " + carta3.getPalo());
        try {
            Thread.sleep(3000); // TIEMPO DE ESPERA PARA GENERAR REALISMO
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(carta2.getValor() + " de " + carta2.getPalo());
        try {
            Thread.sleep(1000); // TIEMPO DE ESPERA PARA GENERAR REALISMO
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("TOTAL: " + (carta1.getValorNumerico() + carta2.getValorNumerico()) + "\t\t\t\t\t\t\t" +
                "TOTAL: " + carta3.getValorNumerico());

        // SACAMOS LOS RESULTADOS DE LA APUESTA
        if (sigJug(apuesta)>21){
            System.out.printf("TE PASASTE!!!");
        } else if(sigJug(apuesta)==21){
            System.out.println("BLACKJACK!");
            j1.setDinero(j1.getDinero()+apuesta);
        } else{
            if (sigJug(apuesta) > carta3.getValorNumerico()){
                j1.setDinero(j1.getDinero()+(apuesta*2));
            }else if(sigJug(apuesta) == carta3.getValorNumerico()){
                j1.setDinero(j1.getDinero());
            }else{
                j1.setDinero(j1.getDinero()-apuesta);
            }
        }
    }

    public static int sigJug(int apuesta) {
        System.out.println("------------------------------------------------------\n" +
                "1. QUEDARSE\n" +
                "2. PEDIR\n" +
                "3. DOBLAR\n" +
                "->");
        int decision = in.nextInt();
        switch (decision){
            case 1:
                return apuesta;
            case 2:
                break;
            case 3:
                apuesta = apuesta * 2;
                return apuesta;
        };
        return decision;
    }

}