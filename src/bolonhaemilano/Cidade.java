package bolonhaemilano;

public class Cidade {
    private String nome;
    private int anoAtual;
    private Jogador dono;
    private int florins;
    private int terras;
    private int graos;
    private int servos;
    private int taxaImposto;
    private String nivelJustica;
    private int soldados;
    private int construcoes;
    private boolean colheitaFeita;
    private boolean impostosArrecadados;

    public Cidade(String nome, Jogador dono) {
        this.nome = nome;
        this.dono = dono;
        this.anoAtual = 1400;
        this.florins = 1000;
        this.terras = 10000;
        this.graos = 500;
        this.servos = 200;
        this.taxaImposto = 10;
        this.nivelJustica = "Moderada";
        this.soldados = 0;
        this.construcoes = 0;
        this.colheitaFeita = false;
        this.impostosArrecadados = false;
    }

    // ---- Identificação e tempo ----
    public String getNome() { return nome; }
    public int getAnoAtual() { return anoAtual; }
    public Jogador getDono() { return dono; }

    public void avancarAno() {
        anoAtual++;
        colheitaFeita = false;
        impostosArrecadados = false;
    }

    // ---- Indicadores do ano ----
    public boolean isColheitaFeita() { return colheitaFeita; }
    public void marcarColheitaFeita() { colheitaFeita = true; }
    public boolean isImpostosArrecadados() { return impostosArrecadados; }
    public void marcarImpostosArrecadados() { impostosArrecadados = true; }

    // ---- Configurações ----
    public int getTaxaImposto() { return taxaImposto; }
    public void setTaxaImposto(int taxaImposto) { this.taxaImposto = taxaImposto; }
    public String getNivelJustica() { return nivelJustica; }
    public void setNivelJustica(String nivelJustica) { this.nivelJustica = nivelJustica; }

    // ---- Recursos: getters ----
    public int getFlorins() { return florins; }
    public int getTerras() { return terras; }
    public int getGraos() { return graos; }
    public int getServos() { return servos; }
    public int getSoldados() { return soldados; }
    public int getConstrucoes() { return construcoes; }

    // ---- Recursos: adicionar (sempre funciona) / remover (false se faltar) ----
    public void adicionarFlorins(int q) { if (q > 0) florins += q; }
    public boolean removerFlorins(int q) {
        if (q < 0 || q > florins) return false;
        florins -= q;
        return true;
    }

    public void adicionarTerras(int q) { if (q > 0) terras += q; }
    public boolean removerTerras(int q) {
        if (q < 0 || q > terras) return false;
        terras -= q;
        return true;
    }

    public void adicionarGraos(int q) { if (q > 0) graos += q; }
    public boolean removerGraos(int q) {
        if (q < 0 || q > graos) return false;
        graos -= q;
        return true;
    }

    public void adicionarServos(int q) { if (q > 0) servos += q; }
    public boolean removerServos(int q) {
        if (q < 0 || q > servos) return false;
        servos -= q;
        return true;
    }

    public void adicionarSoldados(int q) { if (q > 0) soldados += q; }
    public boolean removerSoldados(int q) {
        if (q < 0 || q > soldados) return false;
        soldados -= q;
        return true;
    }

    public void adicionarConstrucoes(int q) { if (q > 0) construcoes += q; }
    public boolean removerConstrucoes(int q) {
        if (q < 0 || q > construcoes) return false;
        construcoes -= q;
        return true;
    }

    public void exibirStatus() {
        System.out.println("\n=== " + nome.toUpperCase() + " - Ano " + anoAtual + " ===");
        System.out.println("Governante: " + dono.getNome() + " (" + dono.getTitulo() + ")");
        System.out.println("Florins: " + florins);
        System.out.println("Terras: " + terras + " hectares");
        System.out.println("Grãos: " + graos);
        System.out.println("Servos: " + servos);
        System.out.println("Soldados: " + soldados);
        System.out.println("Construções: " + construcoes);
        System.out.println("Taxa de imposto: " + taxaImposto + "%");
        System.out.println("Justiça: " + nivelJustica);
        System.out.println("Colheita deste ano: " + (colheitaFeita ? "já feita" : "pendente"));
        System.out.println("Impostos deste ano: " + (impostosArrecadados ? "já arrecadados" : "pendentes"));
    }
}
