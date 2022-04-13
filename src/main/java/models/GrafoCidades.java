package models;

public class GrafoCidades extends Grafo {

    public GrafoCidades() {
        super();
    }

    // Método que lerá o arquivo de dados e criará todas as cidades.
    public void gerarGrafoComCidades() {
        System.out.println("Gerando Grafo");
    }

    public void criarLigacoesTresCidadesProximas() {

    }

    public static double calcularDistanciaCidades(VerticeCidade v1, VerticeCidade v2) {
        final double raioDaTerra = 6371;

        return raioDaTerra
                * Math.acos(
                        Math.cos((Math.toRadians(90 - v1.getLatitude()))
                                * Math.cos((Math.toRadians(90 - v2.getLatitude())) +
                                        Math.sin(Math.toRadians(90 - v1.getLatitude()))
                                                * Math.sin(90 - v2.getLatitude()))
                                * Math.cos(Math.toRadians(v1.getLongitude() - v2.getLongitude()))));
    }
}
