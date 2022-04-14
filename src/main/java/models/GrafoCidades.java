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

    // https://stackoverflow.com/questions/27928/calculate-distance-between-two-latitude-longitude-points-haversine-formula
    public static double calcularDistanciaCidades(VerticeCidade v1, VerticeCidade v2) {
        final double RAIO_DA_TERRA = 6371;
        double dLat = grausParaRadianos(v2.getLatitude() - v1.getLatitude());
        double dLng = grausParaRadianos(v2.getLongitude() - v1.getLongitude());

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(grausParaRadianos(v1.getLatitude()))
                * Math.cos(grausParaRadianos(v2.getLatitude())) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return RAIO_DA_TERRA * c;
    }

    private static double grausParaRadianos(double deg) {
        return deg * (Math.PI / 180);
    }
}
