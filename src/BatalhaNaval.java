import models.Jogo;

public class BatalhaNaval {
    /**
     * Primeiro deve-se inserir a LINHA preenchendo com LETRAS de A a I
     * Por segundo, deve-se inserir COLUNA preenchendo com números de 0 a 9
     * E depois serão lançadas as jogadas da máquina
     */
    public static void main(String[] args) {
        System.out.println("▒█▀▀█ ░█▀▀█ ▀▀█▀▀ ░█▀▀█ ▒█░░░ ▒█░▒█ ░█▀▀█   ▒█▄░▒█ ░█▀▀█ ▒█░░▒█ ░█▀▀█ ▒█░░░ ");
        System.out.println("▒█▀▀▄ ▒█▄▄█ ░▒█░░ ▒█▄▄█ ▒█░░░ ▒█▀▀█ ▒█▄▄█   ▒█▒█▒█ ▒█▄▄█ ░▒█▒█░ ▒█▄▄█ ▒█░░░ ");
        System.out.println("▒█▄▄█ ▒█░▒█ ░▒█░░ ▒█░▒█ ▒█▄▄█ ▒█░▒█ ▒█░▒█   ▒█░░▀█ ▒█░▒█ ░░▀▄▀░ ▒█░▒█ ▒█▄▄█ ");
        Jogo.menu();
    }
}