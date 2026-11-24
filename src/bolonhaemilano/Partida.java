package bolonhaemilano;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    public static final int MAXIMO_JOGADORES = 4;
    private static final int ANO_INICIAL = 1400;
    private static final int ANO_FINAL = 1410;

    private List<Cidade> cidades;
    private int indiceAtual;

    public Partida() {
        this.cidades = new ArrayList<>();
        this.indiceAtual = 0;
    }

    public boolean adicionarCidade(Cidade cidade) {
        if (cidade == null || cidades.size() >= MAXIMO_JOGADORES) {
            return false;
        }
        cidades.add(cidade);
        return true;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public Cidade getCidadeAtual() {
        if (cidades.isEmpty()) {
            return null;
        }
        return cidades.get(indiceAtual);
    }

    public int getAnoAtual() {
        return cidades.isEmpty() ? ANO_INICIAL : cidades.get(0).getAnoAtual();
    }

    public int getAnoFinal() {
        return ANO_FINAL;
    }

    /** Devolve true se o ano acabou (o último jogador encerrou o turno). */
    public boolean passarTurno() {
        indiceAtual++;
        if (indiceAtual >= cidades.size()) {
            indiceAtual = 0;
            return true;
        }
        return false;
    }

    /** Avança o ano de todas as cidades e devolve as que foram promovidas. */
    public List<Cidade> encerrarAno(Nobreza nobreza) {
        List<Cidade> promovidas = new ArrayList<>();
        for (Cidade cidade : cidades) {
            cidade.avancarAno();
            if (nobreza.verificarPromocao(cidade)) {
                promovidas.add(cidade);
            }
        }
        return promovidas;
    }

    public boolean terminou() {
        for (Cidade cidade : cidades) {
            if (cidade.getDono().ehMonarca()) {
                return true;
            }
        }
        return getAnoAtual() > ANO_FINAL;
    }

    /** Título mais alto vence; empate, maior prestígio. */
    public Cidade determinarVencedor(Nobreza nobreza) {
        Cidade vencedor = null;
        for (Cidade cidade : cidades) {
            if (vencedor == null) {
                vencedor = cidade;
                continue;
            }
            int nivelA = cidade.getDono().getNivelTitulo();
            int nivelB = vencedor.getDono().getNivelTitulo();
            if (nivelA > nivelB
                || (nivelA == nivelB
                    && nobreza.calcularPrestigio(cidade) > nobreza.calcularPrestigio(vencedor))) {
                vencedor = cidade;
            }
        }
        return vencedor;
    }
}
