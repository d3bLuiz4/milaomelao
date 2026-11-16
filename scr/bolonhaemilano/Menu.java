package bolonhaemilano;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private boolean emExecucao;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.emExecucao = true;
    }

    public void executar() {
        while (emExecucao) {
            exibirOpcoes();
            int opcao = lerOpcao();
            processarOpcao(opcao);
        }
        scanner.close();
    }

    public void exibirOpcoes() {
        System.out.println("\nBOLONHA E MILANO");
        System.out.println("Um jogo de estratégia económica medieval");
        System.out.println("1 - Novo jogo");
        System.out.println("2 - Sobre o jogo");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    public int lerOpcao() {
        try {
            String entrada = scanner.nextLine();
            return Integer.parseInt(entrada.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                System.out.println("\n[Aviso] Novo jogo selecionado. (Funcionalidade completa nas próximas etapas)");
                break;
            case 2:
                exibirSobre();
                break;
            case 0:
                emExecucao = false;
                System.out.println("\nA sair do jogo... Até breve!");
                break;
            default:
                System.out.println("\n[Erro] Opção inválida! Por favor, introduza um número válido.");
                break;
        }
    }

    public void exibirSobre() {
        System.out.println("\n--- SOBRE O JOGO ---");
        System.out.println("Bolonha e Milano é um jogo de estratégia económica medieval.");
        System.out.println("O objetivo académico é gerir recursos, terras, grãos, impostos e população de forma incremental.");
    }
}