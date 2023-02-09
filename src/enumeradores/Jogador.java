package enumeradores;

public enum Jogador {
    JOGADOR_HUMANO("HUMANO"), JOGADOR_MAQUINA("MÁQUINA");

    private String jogador;

    Jogador(String jogador) {
        this.jogador = jogador;
    }

    public String getJogador() {
        return jogador;
    }
}
