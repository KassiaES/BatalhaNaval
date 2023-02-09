package models.Jogador;

import models.Tabuleiro;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JogadaHumano {
    private JogadaHumano(){}

    /**
     * obtém o char com o valor da posição na horizontal
     */
    public static char getLinha() {
        String linha = null;
        boolean controle = true;
        System.out.print("Informe a linha: ");
        while (controle) {
            //tpUpperCase converte o caracter digitado para maiúsculo sempre.
            linha = new Scanner(System.in).nextLine().toUpperCase();
            if (linha.equals("A")
                    || linha.equals("B")
                    || linha.equals("C")
                    || linha.equals("D")
                    || linha.equals("E")
                    || linha.equals("F")
                    || linha.equals("G")
                    || linha.equals("H")
                    || linha.equals("I")
                    || linha.equals("J")) {
                controle = false;
            } else {
                System.out.print("Digite um valor de linha válido (A, B, C, D, E, F, G, H ou I: ");
            }
        }
        //convertendo para char pegando a primeira letra da composição
        char linhaChar = linha.charAt(0);
        return linhaChar;
    }

    /**
     * obtém o char com o valor da posição na vertical
     */
    public static int getColuna() {
        int coluna = 0;
        boolean controle = true;
        System.out.print("Informe a coluna: ");
        while (controle) {
            //verifica se foi número digitado
            try {
                coluna = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Digite um valor de coluna válido (0, 1, 2, 3, 4, 5, 6, 7, 8, 9).");
                getColuna();
            }
            //verifica se foi um dos números da coluna
            if (coluna == 0
                    || coluna == 1
                    || coluna == 2
                    || coluna == 3
                    || coluna == 4
                    || coluna == 5
                    || coluna == 6
                    || coluna == 7
                    || coluna == 8
                    || coluna == 9) {
                controle = false;
            } else {
                System.out.print("Digite um valor de coluna válido (0, 1, 2, 3, 4, 5, 6, 7, 8, 9: ");
            }
        }
        return coluna;
    }

    /**
     * Sempre que for enviar uma jogada envie também na chamada o tabuleiro do objeto do inimigo, seja
     * o humano ou computador para ele poder fazer as devidas comparações
     */
    public static void jogadaHumano(Tabuleiro jogador, Tabuleiro inimigo) {
        //try, pois se já tiver preenchido a jogada vai retornar exceção. Se retornar vai pedir nova jogada.
        try {
            jogador.setJogada(JogadaHumano.getLinha(), JogadaHumano.getColuna(), inimigo);
        } catch (Exception e) {
            System.out.println("Posição já jogada anteriormente, insira uma nova posição");
            jogadaHumano(jogador, inimigo);
        }
    }
}
