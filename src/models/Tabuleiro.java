package models;

import enumeradores.Jogador;
import enumeradores.Marcacoes;
import models.Jogador.JogadaHumano;

import java.util.Random;

public class Tabuleiro {
    private final String reset = "\u001B[0m";
    private final String cor_amarela = "\u001B[33m";
    private final String cor_azul = "\u001B[34m";
    private final String cor_roxa = "\u001B[35m";
    private final String cor_vermelho = "\u001B[31m";
    private final String cor_ciano = "\u001B[36m";
    private final String cor_verde = "\u001B[32m";
    private final String separador = "\n------------------------------------------------------------------";
    private final String titulo = "                         JOGADOR ";
    private int tamLinha = 10;
    private int tamColuna = 10;
    private int quantidadeNavios = 10;
    protected char[][] tabuleiro = new char[tamLinha][tamColuna];

    /**
     * Recebe um char contendo a descisão. Se "M" vai gerar de forma manual e se "A" ou qualquer outro caractere vai gerar automaticamente
     */
    public Tabuleiro(char decisao) {
        if (decisao == 'M') {
            preencherNaviosTabuleiroManualmente();
        } else {
            preencherNaviosTabuleiroRandomicamente();
        }
    }

    /**
     * Preenche os navios manualmente para iniciar a partida
     */
    private void preencherNaviosTabuleiroManualmente() {
        int linha, coluna;
        //imprimindo tabuleiro para usuário visualizar
        System.out.println("\n----- Informe as posições que gostaria de alocar seus navios -----");
        for (int i = 0; i < quantidadeNavios; i++) {
            toString(Jogador.JOGADOR_HUMANO.getJogador());
            //pegando a jogada pelo método e convertendo a posição da linha para um int e armazenando na linha.
            linha = converteCharLinhaInt(JogadaHumano.getLinha());
            coluna = JogadaHumano.getColuna();
            if (tabuleiro[linha][coluna] == Character.MIN_VALUE) {
                tabuleiro[linha][coluna] = Marcacoes.NAVIO_POSICIONADO.getMarcacao();
            } else {
                System.out.println("\nPosição já preenchida, favor, informar uma nova posição.");
                i--;
            }
        }
        System.out.println("\n----------------------- Tabuleiro definido -----------------------");
    }

    /**
     * Preenche os navios randomicamente para iniciar a partida
     */
    private void preencherNaviosTabuleiroRandomicamente() {
        Random random = new Random();
        int linhaRandom, colunaRandom;
        for (int i = 0; i < quantidadeNavios; i++) {
            //a cada passada gera uma posição aleatória para preencher uma coordenada na matriz
            linhaRandom = random.nextInt(9);
            colunaRandom = random.nextInt(9);
            if (tabuleiro[linhaRandom][colunaRandom] == Character.MIN_VALUE) {
                tabuleiro[linhaRandom][colunaRandom] = Marcacoes.NAVIO_POSICIONADO.getMarcacao();
            } else {
                i--;
            }
        }
    }

    /**
     * Método responsável por enviar a jogada convertendo o char enviado para int por conta da posição da matriz.
     * E validar as jogadas passando o tabuleiro do inimmigo.
     * Se tiver uma posição já jogada anteriormente vai retornar exceção
     */
    public void setJogada(char linha, int coluna, Tabuleiro inimigo) {
        //converte a coluna char para um INT para validar as posições da matriz
        linha = converteCharLinhaInt(linha);
        //valida se jogada já foi feita (se nessa posição não tiver uma valor vazio e não tiver um navio posicionado) e se tiver sido vai retornar uma exceção
        if (tabuleiro[linha][coluna] != Character.MIN_VALUE && tabuleiro[linha][coluna] != Marcacoes.NAVIO_POSICIONADO.getMarcacao()) {
            throw new RuntimeException();
        }
        //enviando linha primeiro e depois coluna, pois é assim que uma matriz funciona, primeiro coluna e depois linha.
        validaTiro(linha, coluna, inimigo);
    }

    /**
     * Atirar: (tiro certeiro, água, certeiro com navio posicionado e tiro na água com navio posicionado)
     */
    private void validaTiro(int posicaoLinha, int posicaoColuna, Tabuleiro inimigo) {
        //validações dos tiros e preenchimento do campo na posição
        if (inimigo.tabuleiro[posicaoLinha][posicaoColuna] == Marcacoes.NAVIO_POSICIONADO.getMarcacao() || inimigo.tabuleiro[posicaoLinha][posicaoColuna] == Marcacoes.TIRO_AGUA_NAVIO_POSICIONADO.getMarcacao() || inimigo.tabuleiro[posicaoLinha][posicaoColuna] == Marcacoes.TIRO_NAVIO_POSICIONADO.getMarcacao()) {
            //se no tiro tiver um navio posicionado ele verifica se tem um navio posicionado nesta posição no tabuleiro do inimigo com relação ao do atual
            if (tabuleiro[posicaoLinha][posicaoColuna] == Marcacoes.NAVIO_POSICIONADO.getMarcacao()) {
                //se tiver o mesmo preenchimento ele armazena marcação correspondente
                tabuleiro[posicaoLinha][posicaoColuna] = Marcacoes.TIRO_NAVIO_POSICIONADO.getMarcacao();
            } else {
                //se não tiver ele armazena o tiro
                tabuleiro[posicaoLinha][posicaoColuna] = Marcacoes.TIRO_CERTEIRO.getMarcacao();
            }
            //nesse caso se o jogador der um tiro ele verifica se no tabuleiro inimigo foi tiro na água
        } else if (inimigo.tabuleiro[posicaoLinha][posicaoColuna] == Character.MIN_VALUE || inimigo.tabuleiro[posicaoLinha][posicaoColuna] == Marcacoes.TIRO_CERTEIRO.getMarcacao() || inimigo.tabuleiro[posicaoLinha][posicaoColuna] == Marcacoes.TIRO_AGUA.getMarcacao()) {
            //se no tabuleiro do jogador atual tiver um navio posicionado ele coloca o n minúsculo
            if (tabuleiro[posicaoLinha][posicaoColuna] == Marcacoes.NAVIO_POSICIONADO.getMarcacao()) {
                tabuleiro[posicaoLinha][posicaoColuna] = Marcacoes.TIRO_AGUA_NAVIO_POSICIONADO.getMarcacao();
            } else {
                //se não tiver navio posicionado vira tiro na água
                tabuleiro[posicaoLinha][posicaoColuna] = Marcacoes.TIRO_AGUA.getMarcacao();
            }
        }
    }

    /**
     * Verifica ganhador. Se tiver um ganhador retornará true se não false
     */
    public boolean verificaGanhador(Tabuleiro inimigo) {
        int contJogadorUm = 0, contJogadorDois = 0;
        for (int linha = 0; linha < tabuleiro.length; linha++) {
            for (int coluna = 0; coluna < tabuleiro.length; coluna++) {
                if (tabuleiro[linha][coluna] == Marcacoes.TIRO_CERTEIRO.getMarcacao() || tabuleiro[linha][coluna] == Marcacoes.TIRO_NAVIO_POSICIONADO.getMarcacao()) {
                    contJogadorUm++;
                }
                if (inimigo.tabuleiro[linha][coluna] == Marcacoes.TIRO_CERTEIRO.getMarcacao() || tabuleiro[linha][coluna] == Marcacoes.TIRO_NAVIO_POSICIONADO.getMarcacao()) {
                    contJogadorDois++;
                }
            }
        }
        if (contJogadorUm == quantidadeNavios) {
            System.out.println("\nFim de jogo! \nO jogador " + cor_ciano + Jogador.JOGADOR_HUMANO.getJogador() + reset + " foi o vencedor!\nParabéns!");
            return true;
        } else if (contJogadorDois == quantidadeNavios) {
            System.out.println("\nFim de jogo! \nA " + cor_vermelho + Jogador.JOGADOR_MAQUINA.getJogador() + reset + " foi a vencedora!\nMais sorte da próxima vez!");
            return true;
        }
        return false;
    }

    /**
     * Método responsável por realizar a conversão da letra da linha informada para um número
     */
    private static char converteCharLinhaInt(char linha) {
        switch (linha) {
            case 'A':
                linha = (int) 0;
                break;
            case 'B':
                linha = (int) 1;
                break;
            case 'C':
                linha = (int) 2;
                break;
            case 'D':
                linha = (int) 3;
                break;
            case 'E':
                linha = (int) 4;
                break;
            case 'F':
                linha = (int) 5;
                break;
            case 'G':
                linha = (int) 6;
                break;
            case 'H':
                linha = (int) 7;
                break;
            case 'I':
                linha = (int) 8;
                break;
            case 'J':
                linha = (int) 9;
                break;
        }
        return linha;
    }

    /**
     * Imprime o tabuleiro em tela e deve ser enviado o enum do jogador correspondente ao objeto
     */
    public void toString(String jogador) {
        System.out.println(separador);
        System.out.print(cor_roxa + titulo + jogador + reset);
        System.out.println(separador);
        System.out.print(cor_azul + "     " + reset + "|" + cor_azul + "  0  " + reset + "|" + cor_azul + "  1  " + reset + "|" + cor_azul + "  2  " + reset + "|" + cor_azul + "  3  " + reset + "|" + cor_azul + "  4  " + reset + "|" + cor_azul + "  5  " + reset + "|" + cor_azul + "  6  " + reset + "|" + cor_azul + "  7  " + reset + "|" + cor_azul + "  8  " + reset + "|" + cor_azul + "  9  " + reset + "| ");
        System.out.println(separador);
        for (int linha = 0; linha < tabuleiro.length; linha++) {
            switch (linha) {
                case 0:
                    System.out.print(cor_azul + "  A  " + reset);
                    break;
                case 1:
                    System.out.print(cor_azul + "  B  " + reset);
                    break;
                case 2:
                    System.out.print(cor_azul + "  C  " + reset);
                    break;
                case 3:
                    System.out.print(cor_azul + "  D  " + reset);
                    break;
                case 4:
                    System.out.print(cor_azul + "  E  " + reset);
                    break;
                case 5:
                    System.out.print(cor_azul + "  F  " + reset);
                    break;
                case 6:
                    System.out.print(cor_azul + "  G  " + reset);
                    break;
                case 7:
                    System.out.print(cor_azul + "  H  " + reset);
                    break;
                case 8:
                    System.out.print(cor_azul + "  I  " + reset);
                    break;
                case 9:
                    System.out.print(cor_azul + "  J  " + reset);
                    break;
            }
            System.out.print("| ");
            for (int coluna = 0; coluna < tabuleiro.length; coluna++) {
                if (tabuleiro[linha][coluna] == Character.MIN_VALUE) {
                    System.out.print("   ");
                } else if (tabuleiro[linha][coluna] == Marcacoes.NAVIO_POSICIONADO.getMarcacao()) {
                    System.out.print(cor_amarela + " " + tabuleiro[linha][coluna] + " " + reset);
                } else if (tabuleiro[linha][coluna] == Marcacoes.TIRO_CERTEIRO.getMarcacao()) {
                    System.out.print(cor_vermelho + " " + tabuleiro[linha][coluna] + " " + reset);
                } else if (tabuleiro[linha][coluna] == Marcacoes.TIRO_AGUA.getMarcacao()) {
                    System.out.print(cor_ciano + " " + tabuleiro[linha][coluna] + " " + reset);
                } else if (tabuleiro[linha][coluna] == Marcacoes.TIRO_NAVIO_POSICIONADO.getMarcacao()) {
                    System.out.print(cor_vermelho + " " + tabuleiro[linha][coluna] + " " + reset);
                } else if (tabuleiro[linha][coluna] == Marcacoes.TIRO_AGUA_NAVIO_POSICIONADO.getMarcacao()) {
                    System.out.print(cor_verde + " " + tabuleiro[linha][coluna] + " " + reset);
                } else {
                    System.out.print(" " + tabuleiro[linha][coluna] + " ");
                }
                System.out.print(" | ");
            }
            System.out.println(separador);
        }
    }

}
