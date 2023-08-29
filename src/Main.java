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
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

    //JUEGO
    public static void jugada(Jugadores j1, Jugadores crupier) {
        int total = 0, totalCru = 0;
        System.out.print("Ingrese su apuesta\n->$");
        int apuesta = in.nextInt(); // APUESTA REALIZADA
        if (apuesta > j1.getDinero()) { // VERIFICAMOS QUE LA APUESTA NO SEA MAYOR AL DINERO DISPONIBLE)
            System.out.println("FONDOS NO DISPONIBLES!\n" +
                    "-> DINERO " + j1.getNombre() + " $" + j1.getDinero());
            jugada(j1, crupier);
        }
        System.out.println("\n\n" + j1.getNombre() + "\t\t\t\t\t\t\t\tCRUPIER");
        int num = ThreadLocalRandom.current().nextInt(52);
        int num3 = ThreadLocalRandom.current().nextInt(52); // NUMERO CARTA CRUPIER
        int num2 = ThreadLocalRandom.current().nextInt(52);
        Carta carta1 = cartasEnBaraja.get(num);
        Carta carta3 = cartasEnBaraja.get(num3); // CARTA CRUPIER
        Carta carta2 = cartasEnBaraja.get(num2);
        j1.agregarMano(carta1); // AGREGAMOS LAS CARTAS A LA MANO DEL JUGADOR
        j1.agregarMano(carta2);
        crupier.agregarMano(carta3); // AGREGAMOS CARTA A LA MANO DEL CRUPIER
        System.out.println(carta1.getValor() + " de " + carta1.getPalo());
        tiempoEspera();
        System.out.println("\t\t\t\t\t\t\t\t" + carta3.getValor() + " de " + carta3.getPalo());
        tiempoEspera();
        System.out.println(carta2.getValor() + " de " + carta2.getPalo());
        tiempoEspera();

        // SUMAR CARTAS DE LA MANO DEL JUGADOR A TRAVES DE UN FOREACH
        for (Carta carta : j1.getMano()) {
            total += carta.getValorNumerico();
        }
        for (Carta carta : crupier.getMano()) {
            totalCru += carta.getValorNumerico();
        }
        System.out.println("TOTAL: " + (total) + "\t\t\t\t\t\t\t" +
                "TOTAL: " + totalCru);

        //DESCUBRIR RESULTADO
        int mano = sigJug(total);
        if (mano > 21) {
            System.out.print("------------------------------------------------------\n" +
                    "TE PASASTE\n-$" + apuesta +
                    "------------------------------------------------------");
            j1.setDinero(j1.getDinero() - apuesta);

        } else if (mano == 21) {
            System.out.println("------------------------------------------------------\n" +
                    "BLACKJACK!\n+$" + apuesta * 2 +
                    "------------------------------------------------------");
            j1.setDinero(j1.getDinero() + apuesta);
        } else {
            if (mano > totalCru) {
                System.out.println("------------------------------------------------------\n" +
                        "GANASTE\n+$" + apuesta * 2 +
                        "------------------------------------------------------");
                j1.setDinero(j1.getDinero() + (apuesta * 2));
            } else if (mano == totalCru) {
                System.out.println("------------------------------------------------------\n" +
                        "PUSH\n+$" + apuesta +
                        "------------------------------------------------------");
                j1.setDinero(j1.getDinero());
            } else {
                System.out.println("------------------------------------------------------\n" +
                        "PERDISTE\n-$" + apuesta +
                        "------------------------------------------------------");
                j1.setDinero(j1.getDinero() - apuesta);
            }
        }

        // REINICIAMOS LA MANO DE DEL JUGADOR Y DEL CRUPIER PARA IMPEDIR SUMAR LA MANO ANTERIOR
        j1.reinicarMano();
        crupier.reinicarMano();

        // VERIFICAMOS BANCARROTA
        if (j1.getDinero() <= 0) {
            System.out.println("BANCARROTA!!!!!");
            System.exit(0);
        }

        // VUELVE A LA FUNCION ELEGIR PARA DAR LA POSIBILIDAD DE VOLVER A JUGAR
        elegir(j1, crupier);
    }

    public static int sigJug(int mano) {
        System.out.println("------------------------------------------------------\n" +
                "1. QUEDARSE\n" +
                "2. PEDIR\n" +
                "3. DOBLAR\n" +
                "->");
        int decision = in.nextInt();
        switch (decision) {
            case 1:
                return mano;
            case 2:
                return mano;
            case 3:
                return mano;
        }
        return decision;
    }

    public static void tiempoEspera() {
        try {
            Thread.sleep(3000); // TIEMPO DE ESPERA PARA GENERAR REALISMO
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}