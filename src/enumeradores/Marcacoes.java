package enumeradores;

public enum Marcacoes {
    /**
     * Enumeradores de tiros
     */
    NAVIO_POSICIONADO('N'),
    TIRO_CERTEIRO('*'),
    TIRO_AGUA('-'),
    TIRO_NAVIO_POSICIONADO('X'),
    TIRO_AGUA_NAVIO_POSICIONADO('n');

    private char valor;

    Marcacoes(char valor) {
        this.valor = valor;
    }

    public char getMarcacao() {
        return valor;
    }
}
