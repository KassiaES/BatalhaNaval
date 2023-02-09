package models;

import enumeradores.Jogador;
import models.Jogador.JogadaHumano;
import models.Jogador.JogadaMaquina;
import utils.LeitorTeclado;

public class Jogo {
    static Jogador vencedor = null;
    public static void menu() {
        System.out.println("\nEscolha uma das opções abaixo:\n");
        System.out.println("0 - Para Encerrar.");
        System.out.println("1 - Iniciar um novo jogo escolhendo as posições dos navios.");
        System.out.println("2 - Iniciar um novo jogo com as posições geradas automaticamente.");
        System.out.println("3 - Exibir placar.");

        int opcao = LeitorTeclado.getNumero("\nDigite uma das opções: ");
        switch (opcao) {
            case 0:
                System.out.println("\nO programa será finalizado.");
                System.exit(1);
                break;
            case 1:
                novoJogo('M');
                break;
            case 2:
                novoJogo('A');
                break;
            case 3:
                if (vencedor == null){
                    System.out.println("\nNão houveram novos jogos, inicie um jogo para gerar um placar.");
                }else{
                    Placar.imprimePlacar();
                }
                break;
            default:
                System.out.println("Opação inválida. Tente novamente.");
                break;
        }
        menu();
    }

    /**
     * Realiza um novo jogo recebendo a descisão do jogador (se deseja que gere de forma automática ou manual
     */
    private static void novoJogo(char decisaoJogador) {
        int cont = 0;
        Tabuleiro humano = new Tabuleiro(decisaoJogador);
        Tabuleiro maquina = new Tabuleiro('A');
        System.out.println("\n\n======================== PARTIDA INICIADA ========================");
        while (true) {
            //imprimir os dois tabuleiros (o máquina apenas para teste, depois será removido)
            humano.toString(Jogador.JOGADOR_HUMANO.getJogador());
            //maquina.toString(Jogador.JOGADOR_MAQUINA.getJogador());
            //Jogada humano
            System.out.println("Jogada de número: " + ++cont);
            JogadaHumano.jogadaHumano(humano, maquina);
            //verifica se o humano venceu, se sim retorna false, preencher o vencedor para preencher o placar e finaliza o loop
            if (ganhador(cont, humano, maquina)) {
                vencedor = Jogador.JOGADOR_HUMANO;
                break;
            }
            System.out.println("O computador fez a jogada de número: " + ++cont);
            JogadaMaquina.jogadaMaquina(maquina, humano);
            //verifica se a máquina venceu, se sim retorna false, preencher o vencedor para preencher o placar e finaliza o loop
            if (ganhador(cont, maquina, humano)) {
                vencedor = Jogador.JOGADOR_MAQUINA;
                break;
            }
        }
        System.out.println("\n======================= TABULEIROS FINAIS =======================");
        humano.toString(Jogador.JOGADOR_HUMANO.getJogador());
        maquina.toString(Jogador.JOGADOR_MAQUINA.getJogador());
        Placar.imprimePlacar(vencedor);
    }

    /**
     * Verifica se houve ganhador de acordo com parâmetro passado e retorna true caso sim e false caso não
     */
    private static boolean ganhador(int cont, Tabuleiro humano, Tabuleiro maquina) {
        if (humano.verificaGanhador(maquina)) {
            System.out.println("\nNessa partida houveram " + cont + " jogadas!");
            return true;
        }
        return false;
    }
}
