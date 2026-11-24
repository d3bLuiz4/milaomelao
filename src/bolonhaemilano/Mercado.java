package bolonhaemilano;

public class Mercado {
    private static final int PRECO_GRAO = 2;
    private static final int PRECO_TERRA = 1;

    public boolean comprarGraos(Cidade cidade, int quantidade) {
        if (quantidade <= 0) return false;
        if (!cidade.removerFlorins(quantidade * PRECO_GRAO)) return false;
        cidade.adicionarGraos(quantidade);
        return true;
    }

    public boolean venderGraos(Cidade cidade, int quantidade) {
        if (quantidade <= 0) return false;
        if (!cidade.removerGraos(quantidade)) return false;
        cidade.adicionarFlorins(quantidade * PRECO_GRAO);
        return true;
    }

    public boolean comprarTerras(Cidade cidade, int quantidade) {
        if (quantidade <= 0) return false;
        if (!cidade.removerFlorins(quantidade * PRECO_TERRA)) return false;
        cidade.adicionarTerras(quantidade);
        return true;
    }

    public boolean venderTerras(Cidade cidade, int quantidade) {
        if (quantidade <= 0) return false;
        if (!cidade.removerTerras(quantidade)) return false;
        cidade.adicionarFlorins(quantidade * PRECO_TERRA);
        return true;
    }
}
