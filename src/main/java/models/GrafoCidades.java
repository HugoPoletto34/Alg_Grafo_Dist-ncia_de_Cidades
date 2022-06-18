package models;

import utilLib.ArquivoTextoLeitura;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GrafoCidades extends Grafo<Cidade> {
    public final static String NOME_ARQUIVO = "./BaseDados/br.csv";
    private final List<Node<Cidade>> listaKmCidades = new LinkedList<>();
    static final int MAX_LIGACOES = 3;


    public GrafoCidades() {
        super();
    }

    public Vertice<Cidade> getCidadePeloNome(String cidade) {
        return this.vertices.stream().filter(e -> e.getItem().getNome().equals(cidade)).findFirst().orElse(null);
    }

    // Método que lerá o arquivo de dados e criará todas as cidades.
    public void gerarGrafoComCidades() {
        ArquivoTextoLeitura arquivoEntrada = new ArquivoTextoLeitura(NOME_ARQUIVO);
        String leituraArquivo = arquivoEntrada.lerBuffer();
        int id = 1;
        leituraArquivo = arquivoEntrada.lerBuffer();
        while(leituraArquivo!=null) {

            String[] camposCidade = leituraArquivo.split(",");

            Cidade cidade = new Cidade(
                    camposCidade[0],
                    Double.parseDouble(camposCidade[1]),
                    Double.parseDouble(camposCidade[2])
            );
            Vertice<Cidade> vertice = new Vertice<>(id);
            vertice.setItem(cidade);

            this.vertices.add(vertice);
            id++;
            leituraArquivo = arquivoEntrada.lerBuffer();
        }
    }

    public void criarLigacoesTresCidadesProximas() {
        Map<Integer, Vertice<Cidade>> cidades = this.listaKmCidades
                .stream().map(e -> e.destino)
                .collect(Collectors.toMap(Vertice<Cidade>::getId, Function.identity()));
        cidades.put(this.vertices.get(0).getId(), this.vertices.get(0));
        for (Vertice<Cidade> cidadeAtual : this.vertices) {
            for (Vertice<Cidade> proxCidade : this.vertices) {
                if (cidadeAtual.getId() != proxCidade.getId() && !cidades.containsKey(proxCidade.getId())) {
                    Node<Cidade> node = new Node<>(cidadeAtual, calcularDistanciaCidades(cidadeAtual.getItem(), proxCidade.getItem()), proxCidade);
                    this.listaKmCidades.add(node);
                    cidades.put(proxCidade.getId(), proxCidade);
                }
            }
            cidades = new HashMap<>();
            cidades.put(cidadeAtual.getId(), cidadeAtual);
        }
        this.listaKmCidades.sort(Node::compareTo);

        for (Vertice<Cidade> vertice : this.vertices) {
            List<Node<Cidade>> listFiltered = get3proximas(vertice.getId());

            for (Node<Cidade> node : listFiltered) {
                vertice.addAresta(node.valor, node.destino);
            }
        }
    }

    private List<Node<Cidade>> get3proximas(int idVertice) {
        return this.listaKmCidades.stream().filter
                ((e) -> e.destino.getId() != idVertice && e.origem.getId() == idVertice)
                .collect(Collectors.toList())
                .subList(0, MAX_LIGACOES);
    }

    // https://stackoverflow.com/questions/27928/calculate-distance-between-two-latitude-longitude-points-haversine-formula
    public static double calcularDistanciaCidades(Cidade v1, Cidade v2) {
        // =Raio*ACOS(COS(RADIANOS(90-Latitude_1))*COS(RADIANOS(90-Latitude_2))+SEN(RADIANOS(90-Latitude_1))*SEN(RADIANOS(90-Latitude_2))*COS(RADIANOS(Longitude_1-Longitude_2))*Fator_Ajuste
        final double RAIO_DA_TERRA = 6371;
//        final double FATOR_AJUSTE = 1.15;
//        return RAIO_DA_TERRA*Math.acos(grausParaRadianos(90-v1.getLatitude()))*Math.cos(grausParaRadianos(90- v2.getLatitude()))+Math.sin(grausParaRadianos(90-v1.getLatitude()))*Math.sin(grausParaRadianos(90-v2.getLatitude()))*Math.cos(grausParaRadianos(v1.getLongitude()-v2.getLongitude()))*FATOR_AJUSTE;
        double dLat = grausParaRadianos(v2.getLatitude() - v1.getLatitude());
        double dLng = grausParaRadianos(v2.getLongitude() - v1.getLongitude());

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(grausParaRadianos(v1.getLatitude()))
                * Math.cos(grausParaRadianos(v2.getLatitude())) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return RAIO_DA_TERRA * c;
    }

    private static double grausParaRadianos(double deg) {
        return deg * (Math.PI / 180);
    }

    @Override
    public String toString(){
        StringBuilder aux = new StringBuilder("Cidades:  \n");

        for (Vertice<Cidade> vertex : this.vertices) {
            aux.append(vertex.toString());
        }

        return aux.toString();
    }
    
    public void buscaEmLargura(){
        ArrayList<Vertice<Cidade>> marcados = new ArrayList<Vertice<Cidade>>();
        ArrayList<Vertice<Cidade>> fila = new ArrayList<Vertice<Cidade>>();
        Vertice<Cidade> atual = this.vertices.get(0);
        marcados.add(atual);
        System.out.println(atual.getItem());
        fila.add(atual);
        while(fila.size() > 0){
            Vertice<Cidade> visitado = fila.get(0);
            for(int i=0; i < visitado.getArestas().size(); i++){
                Vertice<Cidade> proximo = visitado.getArestas().get(i).getDestino();
                if (!marcados.contains(proximo)){ 
                    marcados.add(proximo);
                    System.out.println(proximo.getItem());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
    }
}
