package models.Jogador;

import models.Tabuleiro;

import java.util.Random;

public class JogadaMaquina {
    private JogadaMaquina(){}
    /**
     * obtém o char com o valor da posição na horizontal
     */
    public static char setLinha() {
        Random random = new Random();
        //vai gerar uma letra randomicamente que corresponde de 0 a 9 ao alfabeto (0 = A, 1 = B...).
        char linha = (char) (random.nextInt(9) + 'A');
        return linha;
    }


    /**
     * obtém o char com o valor da posição na vertical
     */
    public static int setColuna() {
        Random random = new Random();
        int coluna = random.nextInt(9);
        return coluna;
    }

    /**
     * Sempre que for enviar uma jogada envie também na chamada o tabuleiro do objeto do inimigo, seja
     * o humano ou computador para ele poder fazer as devidas comparações
     */
    public static void jogadaMaquina(Tabuleiro jogador, Tabuleiro inimigo) {
        //try, pois se já tiver preenchido a jogada vai retornar exceção. Se retornar vai pedir nova jogada até mesmo pra máquina (MELHORAR ESSA LÓGICA)
        try {
            jogador.setJogada(JogadaMaquina.setLinha(), JogadaMaquina.setColuna(), inimigo);
        } catch (Exception e) {
            JogadaHumano.jogadaHumano(jogador, inimigo);
        }
    }


}
