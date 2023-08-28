import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static List<Carta> cartasEnBaraja = new ArrayList<>();


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        baraja();
        System.out.println("BIENVENIDO A BLACKJACK!");
        System.out.print("Ingrese su nombre a continuacion: \n->");
        String nombre = scan.next();
        Jugadores j1 = new Jugadores(nombre, 25000); //Creamos el jugador con el nombre antes ingresado
        j1.setNombre(j1.getNombre().toUpperCase());
        System.out.println("BIENVENIDO " + j1.getNombre() + "!!");
        System.out.println("QUE DESEA HACER A CONTINUACION?");
        elegir(j1, scan);

    }

    //Creamos la baraja
    public static void baraja() {

        String[] palos = {"Corazones", "Diamantes", "Tréboles", "Picas"};
        String[] valores = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jota", "Reina", "Rey"};


        for (String palo : palos) {
            for (String valor : valores) {
                cartasEnBaraja.add(new Carta(valor, palo));
            }
        }
        for (Carta carta : cartasEnBaraja) {
            System.out.println(carta.getValor() + " de " + carta.getPalo());
        }
    }

    //Funcion que permite elegir al jugador que hacer
    public static void elegir(Jugadores j1, Scanner scan) {
        System.out.print("1. JUGAR\n2. VER DINERO\n3. SALIR\n->");
        int decision = scan.nextInt();
        switch (decision) {
            case 1:
                System.out.println("-------------------EMPIEZA EL JUEGO-------------------");
                System.out.println("CRUPIER LISTO");
                System.out.print("------------------------------------------------------\n" +
                        "ESPERE MIENTRAS EL GRUPIER DA LAS CARTAS\n" +
                        "------------------------------------------------------\n");
                try {
                    Thread.sleep(5000);//TIEMPO DE ESPERA PARA GENERAR REALISMO
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                jugada(scan,j1);
                break;
            case 2:
                System.out.println("------------------------------------------------------\n" +
                        "SU DINERO ACTUAL ES DE: $" + j1.getDinero()
                        + "\n------------------------------------------------------");
                elegir(j1, scan);
            case 3:
                System.exit(0);
        }
    }

    //JUEGO
    public static void jugada(Scanner scan, Jugadores j1) {
        Random random = new Random();
        System.out.print("Ingrese su apuesta\n->$");
        int apuesta = scan.nextInt();//APUESTA REALIZADA
        System.out.println(j1.getNombre());
        int num = random.nextInt(53);
        int num2 = random.nextInt(53);
        Carta carta1 = cartasEnBaraja.get(num);
        Carta carta2 = cartasEnBaraja.get(num2);
        System.out.println(carta1.getValor() + " de " + carta1.getPalo());
        System.out.println(carta2.getValor() + " de " + carta1.getPalo());
        System.out.println("TOTAL ->" + (carta1.getValorNumerico()+ carta2.getValorNumerico()));
        System.out.println();
    }
}