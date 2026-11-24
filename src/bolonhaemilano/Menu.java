package bolonhaemilano;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private boolean emExecucao;
    private Partida partida;
    private Cidade cidade;
    private Mercado mercado;
    private Agricultura agricultura;
    private Governo governo;
    private Construcoes construcoes;
    private Nobreza nobreza;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.emExecucao = true;
        this.mercado = new Mercado();
        this.agricultura = new Agricultura();
        this.governo = new Governo();
        this.construcoes = new Construcoes();
        this.nobreza = new Nobreza();
    }

    public void executar() {
        while (emExecucao) {
            exibirOpcoes();
            int opcao = lerOpcao();
            processarOpcao(opcao);
        }
        scanner.close();
    }

    private int lerInteiro() {
        try {
            String entrada = scanner.nextLine();
            return Integer.parseInt(entrada.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private int lerQuantidade(String mensagem) {
        System.out.print(mensagem);
        return lerInteiro();
    }

    private boolean exigirJogoEmAndamento() {
        if (partida == null || cidade == null) {
            System.out.println("\n[Aviso] Nenhum jogo em andamento. Escolha '1 - Novo jogo' primeiro.");
            return false;
        }
        return true;
    }

    public void exibirOpcoes() {
        System.out.println("\n=== BOLONHA E MILANO ===");
        if (partida != null && cidade != null) {
            System.out.println("Ano: " + partida.getAnoAtual() + "/" + partida.getAnoFinal()
                    + " | Vez de: " + cidade.getNome()
                    + " (" + cidade.getDono().getNome() + " - " + cidade.getDono().getTitulo() + ")");
        }
        System.out.println("1 - Novo jogo");
        System.out.println("2 - Ver status da cidade");
        System.out.println("3 - Mercado");
        System.out.println("4 - Agricultura");
        System.out.println("5 - Governo");
        System.out.println("6 - Construções");
        System.out.println("7 - Encerrar turno");
        System.out.println("8 - Ver classificação");
        System.out.println("9 - Sobre o jogo");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    public int lerOpcao() {
        return lerInteiro();
    }

    public void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                iniciarNovoJogo();
                break;
            case 2:
                exibirStatus();
                break;
            case 3:
                abrirMercado();
                break;
            case 4:
                abrirAgricultura();
                break;
            case 5:
                abrirGoverno();
                break;
            case 6:
                abrirConstrucoes();
                break;
            case 7:
                encerrarTurno();
                break;
            case 8:
                exibirClassificacao();
                break;
            case 9:
                exibirSobre();
                break;
            case 0:
                emExecucao = false;
                System.out.println("\nSaindo do jogo... Até breve!");
                break;
            default:
                System.out.println("\n[Erro] Opção inválida!");
                break;
        }
    }

    private void iniciarNovoJogo() {
        System.out.print("\nQuantos jogadores (1 a 4)? ");
        int qtd = lerInteiro();
        if (qtd < 1 || qtd > Partida.MAXIMO_JOGADORES) {
            System.out.println("Quantidade inválida. Usando 2 jogadores por padrão.");
            qtd = 2;
        }

        partida = new Partida();
        for (int i = 1; i <= qtd; i++) {
            System.out.print("Nome do jogador " + i + " (padrão: Jogador " + i + "): ");
            String nomeJogador = scanner.nextLine().trim();
            if (nomeJogador.isEmpty()) {
                nomeJogador = "Jogador " + i;
            }

            System.out.print("Nome da cidade " + i + " (padrão: Bolonha): ");
            String nomeCidade = scanner.nextLine().trim();
            if (nomeCidade.isEmpty()) {
                nomeCidade = "Bolonha";
            }

            Cidade nova = new Cidade(nomeCidade, new Jogador(nomeJogador));
            partida.adicionarCidade(nova);
        }

        cidade = partida.getCidadeAtual();
        System.out.println("\nPartida iniciada! Começa com " + cidade.getNome()
                + " (" + cidade.getDono().getNome() + ").");
        exibirStatus();
    }

    private void exibirStatus() {
        if (!exigirJogoEmAndamento()) {
            return;
        }
        cidade.exibirStatus();
    }

    private void abrirMercado() {
        if (!exigirJogoEmAndamento()) {
            return;
        }
        System.out.println("\n--- MERCADO ---");
        System.out.println("1 - Comprar grãos (2 florins/un)");
        System.out.println("2 - Vender grãos (2 florins/un)");
        System.out.println("3 - Comprar terras (1 florim/ha)");
        System.out.println("4 - Vender terras (1 florim/ha)");
        System.out.print("Escolha: ");
        int op = lerInteiro();
        int qtd = lerQuantidade("Quantidade: ");

        boolean ok = false;
        switch (op) {
            case 1:
                ok = mercado.comprarGraos(cidade, qtd);
                break;
            case 2:
                ok = mercado.venderGraos(cidade, qtd);
                break;
            case 3:
                ok = mercado.comprarTerras(cidade, qtd);
                break;
            case 4:
                ok = mercado.venderTerras(cidade, qtd);
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }
        System.out.println(ok ? "Operação realizada com sucesso!" : "Operação recusada.");
    }

    private void abrirAgricultura() {
        if (!exigirJogoEmAndamento()) {
            return;
        }
        System.out.println("\n--- AGRICULTURA ---");
        System.out.println("1 - Colher (processar safra)");
        System.out.println("2 - Distribuir alimentos (20% a 80%)");
        System.out.print("Escolha: ");
        int op = lerInteiro();

        if (op == 1) {
            int hectares = agricultura.processarColheita(cidade);
            if (hectares == -1) {
                System.out.println("A colheita deste ano já foi realizada.");
            } else {
                System.out.println("Colheita concluída! Hectares cultivados: " + hectares);
            }
        } else if (op == 2) {
            int pct = lerQuantidade("Percentual de grãos a distribuir (20 a 80): ");
            boolean ok = agricultura.distribuirAlimentos(cidade, pct);
            System.out.println(ok ? "Alimentos distribuídos!" : "Percentual inválido.");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private void abrirGoverno() {
        if (!exigirJogoEmAndamento()) {
            return;
        }
        System.out.println("\n--- GOVERNO ---");
        System.out.println("1 - Arrecadar impostos");
        System.out.println("2 - Definir taxa de imposto (0 a 50)");
        System.out.println("3 - Definir nível de justiça (1=Justa, 2=Moderada, 3=Severa)");
        System.out.print("Escolha: ");
        int op = lerInteiro();

        if (op == 1) {
            int valor = governo.arrecadarImpostos(cidade);
            if (valor == -1) {
                System.out.println("Os impostos deste ano já foram arrecadados.");
            } else {
                System.out.println("Impostos arrecadados: " + valor + " florins.");
            }
        } else if (op == 2) {
            int taxa = lerQuantidade("Nova taxa (0 a 50): ");
            boolean ok = governo.definirTaxaImposto(cidade, taxa);
            System.out.println(ok ? "Taxa atualizada!" : "Taxa inválida.");
        } else if (op == 3) {
            System.out.print("Justiça (1=Justa, 2=Moderada, 3=Severa): ");
            int j = lerInteiro();
            String nivel = (j == 1) ? "Justa" : (j == 2) ? "Moderada" : (j == 3) ? "Severa" : "";
            boolean ok = governo.definirJustica(cidade, nivel);
            System.out.println(ok ? "Justiça atualizada para " + nivel + "." : "Nível inválido.");
        } else {
            System.out.println("Opção inválida.");
        }
    }

    private void abrirConstrucoes() {
        if (!exigirJogoEmAndamento()) {
            return;
        }
        System.out.println("\n--- CONSTRUÇÕES ---");
        System.out.println("1 - Recrutar soldados (5 florins e 1 servo cada)");
        System.out.println("2 - Construir (20 florins cada)");
        System.out.print("Escolha: ");
        int op = lerInteiro();
        int qtd = lerQuantidade("Quantidade: ");

        boolean ok = false;
        if (op == 1) {
            ok = construcoes.recrutarSoldados(cidade, qtd);
        } else if (op == 2) {
            ok = construcoes.construir(cidade, qtd);
        } else {
            System.out.println("Opção inválida.");
            return;
        }
        System.out.println(ok ? "Ação concluída!" : "Recursos insuficientes ou quantidade inválida.");
    }

    private void encerrarTurno() {
        if (!exigirJogoEmAndamento()) {
            return;
        }
        boolean fimDoAno = partida.passarTurno();
        if (fimDoAno) {
            List<Cidade> promovidas = partida.encerrarAno(nobreza);
            System.out.println("\n=== FIM DO ANO " + (partida.getAnoAtual() - 1) + " ===");
            for (Cidade c : promovidas) {
                System.out.println("Promoção! " + c.getDono().getNome()
                        + " (" + c.getNome() + ") agora é " + c.getDono().getTitulo() + "!");
            }
            exibirClassificacao();

            if (partida.terminou()) {
                encerrarPartida();
                return;
            }
        }

        cidade = partida.getCidadeAtual();
        System.out.println("\nAgora é a vez de " + cidade.getNome()
                + " (" + cidade.getDono().getNome() + ").");
    }

    private void exibirClassificacao() {
        if (!exigirJogoEmAndamento()) {
            return;
        }
        System.out.println("\n--- CLASSIFICAÇÃO ---");
        for (Cidade c : partida.getCidades()) {
            Jogador d = c.getDono();
            int prestigio = nobreza.calcularPrestigio(c);
            int prox = nobreza.prestigioParaProximoTitulo(d);
            String infoProx = (prox == -1) ? "Título máximo" : "Próx. título: " + prox + " pts";
            System.out.println(c.getNome() + " | " + d.getNome() + " (" + d.getTitulo() + ")"
                    + " | Prestígio: " + prestigio + " | " + infoProx);
        }
    }

    private void encerrarPartida() {
        Cidade vencedor = partida.determinarVencedor(nobreza);
        System.out.println("\n=== FIM DE JOGO ===");
        if (vencedor != null) {
            System.out.println("Vencedor: " + vencedor.getDono().getNome()
                    + " (" + vencedor.getDono().getTitulo() + ") governando "
                    + vencedor.getNome() + " com " + nobreza.calcularPrestigio(vencedor) + " de prestígio!");
        }
        partida = null;
        cidade = null;
    }

    public void exibirSobre() {
        System.out.println("\n--- SOBRE O JOGO ---");
        System.out.println("Bolonha e Milano é um jogo de estratégia econômica medieval.");
        System.out.println("Gerencie recursos, agricultura, impostos e construções para alcançar a nobreza.");
    }
}