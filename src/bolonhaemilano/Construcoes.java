package bolonhaemilano;

public class Construcoes {
    private static final int PRECO_SOLDADO = 5;
    private static final int SERVOS_POR_SOLDADO = 1;
    private static final int PRECO_CONSTRUCAO = 20;

    public boolean recrutarSoldados(Cidade cidade, int quantidade) {
        if (quantidade <= 0) return false;
        int custoFlorins = quantidade * PRECO_SOLDADO;
        int custoServos = quantidade * SERVOS_POR_SOLDADO;
        // valida os DOIS recursos antes de debitar qualquer um
        if (cidade.getFlorins() < custoFlorins || cidade.getServos() < custoServos) {
            return false;
        }
        cidade.removerFlorins(custoFlorins);
        cidade.removerServos(custoServos);
        cidade.adicionarSoldados(quantidade);
        return true;
    }

    public boolean construir(Cidade cidade, int quantidade) {
        if (quantidade <= 0) return false;
        if (!cidade.removerFlorins(quantidade * PRECO_CONSTRUCAO)) return false;
        cidade.adicionarConstrucoes(quantidade);
        return true;
    }
}
