package bolonhaemilano;

public class Governo {
    private static final int TAXA_MAXIMA = 50;
    private static final double MULTIPLICADOR_JUSTA = 0.8;
    private static final double MULTIPLICADOR_MODERADA = 1.0;
    private static final double MULTIPLICADOR_SEVERA = 1.3;

    /** Devolve o valor arrecadado, ou -1 se a cidade já arrecadou neste ano. */
    public int arrecadarImpostos(Cidade cidade) {
        if (cidade.isImpostosArrecadados()) {
            return -1;
        }
        double base = cidade.getServos() * cidade.getTaxaImposto() / 100.0;
        int arrecadado = (int) Math.round(base * multiplicadorPara(cidade.getNivelJustica()));
        cidade.adicionarFlorins(arrecadado);
        cidade.marcarImpostosArrecadados();
        return arrecadado;
    }

    public boolean definirTaxaImposto(Cidade cidade, int taxa) {
        if (taxa < 0 || taxa > TAXA_MAXIMA) {
            return false;
        }
        cidade.setTaxaImposto(taxa);
        return true;
    }

    public boolean definirJustica(Cidade cidade, String nivel) {
        if ("Justa".equals(nivel) || "Moderada".equals(nivel) || "Severa".equals(nivel)) {
            cidade.setNivelJustica(nivel);
            return true;
        }
        return false;
    }

    private double multiplicadorPara(String nivel) {
        if ("Justa".equals(nivel)) return MULTIPLICADOR_JUSTA;
        if ("Severa".equals(nivel)) return MULTIPLICADOR_SEVERA;
        return MULTIPLICADOR_MODERADA;
    }
}
