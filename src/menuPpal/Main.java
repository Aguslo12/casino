package menuPpal;

import java.util.Scanner;

public class Main {

    private static Scanner in = new Scanner(System.in).useDelimiter("\n");
    public static void main(String[] args) {
        while (true) {
            System.out.print("Ingrese el juego al cual quiere jugar: \n" +
                    "1. BLACKJACK\n" +
                    "2. RULETA\n" +
                    "->");
            int decision = in.nextInt();

            switch (decision) {
                case 1:
                    blackJack.Main.inicioBJ();
                    break;
                case 2:
                    ruleta.Main.inicioR();
                    break;
                default:
                    System.out.println("OPCION INVÁLIDA");
            }
        }
    }
}
