package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LeitorTeclado {
    static Scanner entrada = new Scanner(System.in);
    public static int getNumero(String frase) {

        System.out.print(frase);
        try {
            return entrada.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Digite uma opção válida.");
            return getNumero(frase);
        }
    }
}
