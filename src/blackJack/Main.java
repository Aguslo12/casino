package blackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static List<Carta> cartasEnBaraja = new ArrayList<>();
    private static Scanner in = new Scanner(System.in).useDelimiter("\n");

    // BIENVENIDA
    public static void inicioBJ() {
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

    // CREAMOS LA BARAJA
    public static void baraja() {

        String[] palos = {"Corazones", "Diamantes", "Tréboles", "Picas"};
        String[] valores = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jota", "Reina", "Rey"};


        for (String palo : palos) {
            for (String valor : valores) {
                cartasEnBaraja.add(new Carta(valor, palo));
            }
        }
    }

    // FUNCION QUE PERMITE AL JUGADOR ELEGIR QUE HACER
    public static void elegir(Jugadores j1, Jugadores crupier) {
        System.out.print("1. JUGAR\n2. VER DINERO\n3. VOLVER AL MENU PRINCIPAL\n->");
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
                menuPpal.Main.main(new String[]{});
                break;
            default:
                System.out.println("OPCION INCORRECTA. POR FAVOR INGRESE UNA OPCION VALIDA");
                elegir(j1, crupier);
        }
    }

    // JUEGO
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

        // CREAMOS LOS NUMEROS RANDOMS PARA ELEGIR LAS CARTAS PRINCIPALES

        int num = ThreadLocalRandom.current().nextInt(cartasEnBaraja.size());
        Carta carta1 = cartasEnBaraja.get(num);
        cartasEnBaraja.remove(num);

        int num2 = ThreadLocalRandom.current().nextInt(cartasEnBaraja.size());
        Carta carta2 = cartasEnBaraja.get(num2);
        cartasEnBaraja.remove(num2);

        int num3 = ThreadLocalRandom.current().nextInt(cartasEnBaraja.size()); // NUMERO CARTA CRUPIER 1
        Carta carta3 = cartasEnBaraja.get(num3); // CARTA CRUPIER 1
        cartasEnBaraja.remove(num3);

        int num4 = ThreadLocalRandom.current().nextInt(cartasEnBaraja.size()); // NUMERO CARTA CRUPIER 2
        Carta carta4 = cartasEnBaraja.get(num4); // CARTA CRUPIER 2
        cartasEnBaraja.remove(num4);


        // AGREGAMOS LAS CARTAS A LA MANO DEL JUGADOR
        j1.agregarMano(carta1);
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
        System.out.println("\t\t\t\t\t\t\t\t**************");
        System.out.println("TOTAL: " + (total) + "\t\t\t\t\t\t\t" +
                "TOTAL: " + totalCru);


        // ELEGIR QUE HACER
        int mano = sigJug(total, j1, crupier, totalCru, apuesta);

        // MOSTRAMOS LA SEGUNDA CARTA DEL CRUPIER
        crupier.agregarMano(carta4);
        totalCru=0;
        for (Carta carta : crupier.getMano()) {
            totalCru += carta.getValorNumerico();
        }

        // MOSTRAMOS DE NUEVO LA MANO CON LA SUMA DE LA CARTA NUMERO 4
        System.out.println("\n" + j1.getNombre() + "\t\t\t\t\t\t\t\tCRUPIER\n");
        System.out.println(carta1.getValor() + " de " + carta1.getPalo());
        System.out.println("\t\t\t\t\t\t\t\t" + carta3.getValor() + " de " + carta3.getPalo());
        System.out.println(carta2.getValor() + " de " + carta2.getPalo());
        System.out.println("\t\t\t\t\t\t\t\t" + carta4.getValor() + " de " + carta4.getPalo() + "\n");


        // VALIDACION DE SI EL CRUPIER DEBE SACAR MAS CARTAS O NO
        // IMPORTANTISIMO NO TOCAR
        if (totalCru < 17){
            totalCru = jugarCrupier(crupier, totalCru);
        }


        tiempoEspera();
        System.out.println("TOTAL: " + (mano) + "\t\t\t\t\t\t\t" +
                "TOTAL: " + totalCru);


        // DESCUBRIR RESULTADO
        descubrirResultado(mano, apuesta, j1, totalCru);

        // REINICIAMOS LA MANO DE DEL JUGADOR Y DEL CRUPIER PARA IMPEDIR SUMAR LA MANO ANTERIOR
        j1.reinicarMano();
        crupier.reinicarMano();

        // VERIFICAMOS CUANTAS CARTAS QUEDAN DENTRO DEL MAZO Y SE REINICIA LA BARAJA

        if(cartasEnBaraja.size() <= 15){
            System.out.println("POCAS CARTAS EN EL MAZO!\n" +
                    "ESPERE MIENTRAS SE MEZCLA UNO NUEVO");
            tiempoEspera();
            System.out.println("JUEGO REANUDADO");
            tiempoEspera();
            cartasEnBaraja.clear();
            baraja();
        }

        // VERIFICAMOS BANCARROTA
        if (j1.getDinero() <= 0) {
            System.out.println("BANCARROTA!!!!!");
            System.exit(0);
        }

        // VUELVE A LA FUNCION ELEGIR PARA DAR LA POSIBILIDAD DE VOLVER A JUGAR
        elegir(j1, crupier);
    }

    // LUEGO DE LA ENTREGA DE LA PRIMERA MANO LE DECIMOS AL JUGADOR QUE QUIERE HACER
    public static int sigJug(int mano, Jugadores j1, Jugadores crupier, int totalCru, int apuesta) {
        System.out.print("""
                ------------------------------------------------------
                1. QUEDARSE
                2. PEDIR
                3. DOBLAR
                4. RENDIRSE
                ->""");
        int decision = in.nextInt();
        switch (decision) {
            case 1:
                return mano;
            case 2:
                int num1 = ThreadLocalRandom.current().nextInt(cartasEnBaraja.size());
                Carta carta = cartasEnBaraja.get(num1);
                cartasEnBaraja.remove(num1);
                j1.agregarMano(carta);
                System.out.println(carta.getValor() + " de " + carta.getPalo());
                mano += carta.getValorNumerico();
                System.out.println("TOTAL: " + (mano) + "\t\t\t\t\t\t\t" +
                        "TOTAL: " + totalCru);
                if (mano > 21) {
                    return mano;
                } else{
                    sigJug(mano, j1, crupier, totalCru, apuesta);
                    return mano;
                }
            case 3:
                int num2 = ThreadLocalRandom.current().nextInt(cartasEnBaraja.size());
                Carta carta1 = cartasEnBaraja.get(num2);
                cartasEnBaraja.remove(num2);
                j1.agregarMano(carta1);
                System.out.println(carta1.getValor() + " de " + carta1.getPalo());
                mano += carta1.getValorNumerico();
                System.out.println("TOTAL: " + (mano) + "\t\t\t\t\t\t\t" +
                        "TOTAL: " + totalCru);
                apuesta = apuesta *2;
                descubrirResultado(mano,apuesta,j1,totalCru);
                break;
            case 4:
                int mitad = apuesta / 2;
                j1.setDinero(j1.getDinero() - apuesta + mitad);
                System.out.println("+$" + mitad);
                elegir(j1, crupier);
                break;
        }
        return decision;
    }

    // FUNCION PARA CUANDO EL CRUPIER DEBE JUGAR
    public static int jugarCrupier(Jugadores crupier, int totalCru) {
        while (totalCru < 17) {
            int numCarta = ThreadLocalRandom.current().nextInt(cartasEnBaraja.size());
            Carta carta = cartasEnBaraja.get(numCarta);
            cartasEnBaraja.remove(numCarta);
            crupier.agregarMano(carta);
            tiempoEspera();
            totalCru = calcularTotalCrupier(crupier);
            System.out.println("\t\t\t\t\t\t\t\t" + carta.getValor() + " de " + carta.getPalo() + "\n");
        }
        return totalCru;
    }

    // CALCULA EL TOTAL DE PUNTOS QUE TIENE EL CRUPIER
    public static int calcularTotalCrupier(Jugadores crupier) {
        int total = 0;

        for (Carta carta : crupier.getMano()) {
            total += carta.getValorNumerico();
        }

        return total;
    }

    // TE DICE EL RESULTADO DE LA APUESTA
    private static void descubrirResultado(int mano, int apuesta, Jugadores j1, int totalCru){
        if (mano > 21) {
            System.out.println("------------------------------------------------------\n" +
                    "TE PASASTE\n-$" + apuesta +
                    "\n------------------------------------------------------");
            j1.setDinero(j1.getDinero() - apuesta);

        } else if (mano == 21) {
            System.out.println("------------------------------------------------------\n" +
                    "BLACKJACK!\n+$" + apuesta * 2 +
                    "\n------------------------------------------------------");
            j1.setDinero(j1.getDinero() + apuesta);
        } else {
            if (mano > totalCru || totalCru > 21) {
                System.out.println("------------------------------------------------------\n" +
                        "GANASTE\n+$" + apuesta * 2 +
                        "\n------------------------------------------------------");
                j1.setDinero(j1.getDinero() + (apuesta * 2));
            } else if (mano == totalCru) {
                System.out.println("------------------------------------------------------\n" +
                        "PUSH\n+$" + apuesta +
                        "\n------------------------------------------------------");
                j1.setDinero(j1.getDinero());
            } else if (totalCru < 21){
                System.out.println("------------------------------------------------------\n" +
                        "PERDISTE\n-$" + apuesta +
                        "\n------------------------------------------------------");
                j1.setDinero(j1.getDinero() - apuesta);
            }
        }
    }
    public static void tiempoEspera() {
        try {
            Thread.sleep(2000); // TIEMPO DE ESPERA PARA GENERAR REALISMO
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}