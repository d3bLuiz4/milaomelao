package bolonhaemilano;

public class Agricultura {
    private static final int HECTARES_POR_SERVO = 5;
    private static final int RENDIMENTO_POR_HECTARE = 2;
    private static final int SERVOS_POR_GRAO = 5;
    private static final int CRESCIMENTO_PERCENTUAL = 5;
    private static final int PERCENTUAL_MINIMO = 20;
    private static final int PERCENTUAL_MAXIMO = 80;

    /** Devolve os hectares cultivados, ou -1 se a cidade já colheu neste ano. */
    public int processarColheita(Cidade cidade) {
        if (cidade.isColheitaFeita()) {
            return -1;
        }
        int capacidade = cidade.getServos() * HECTARES_POR_SERVO;
        int hectares = Math.min(cidade.getTerras(), capacidade);
        int sementes = hectares / 2;
        if (cidade.getGraos() < sementes) {
            hectares = cidade.getGraos() * 2;
            sementes = hectares / 2;
        }
        cidade.removerGraos(sementes);
        cidade.adicionarGraos(hectares * RENDIMENTO_POR_HECTARE);
        cidade.marcarColheitaFeita();
        return hectares;
    }

    public boolean distribuirAlimentos(Cidade cidade, int percentual) {
        if (percentual < PERCENTUAL_MINIMO || percentual > PERCENTUAL_MAXIMO) {
            return false;
        }
        int distribuido = cidade.getGraos() * percentual / 100;
        cidade.removerGraos(distribuido);
        int necessidade = cidade.getServos() / SERVOS_POR_GRAO;
        if (distribuido >= necessidade) {
            cidade.adicionarServos(cidade.getServos() * CRESCIMENTO_PERCENTUAL / 100);
        } else {
            int deficit = necessidade - distribuido;
            cidade.removerServos(Math.min(deficit, cidade.getServos()));
        }
        return true;
    }
}
