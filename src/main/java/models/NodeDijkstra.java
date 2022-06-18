package models;

public class NodeDijkstra<T> {
    NodeDijkstra<T> antecessor;
    double distancia;
    double peso;
    NodeDijkstra<T> predecessor;

    public NodeDijkstra(NodeDijkstra<T> predecessor, int distancia, double peso, NodeDijkstra<T> antecessor) {
        this.antecessor = antecessor;
        this.distancia = distancia;
        this.peso = peso;
        this.predecessor = predecessor;
    }



//    public NodeDijkstra(Aresta<T> tAresta) {
//        this.distancia = tAresta.getOrigem().distancia;
//        this.peso = tAresta.getPeso();
//        this.antecessor = new NodeDijkstra<>(tAresta.getOrigem());
//        this.predecessor = new NodeDijkstra<>(tAresta.getDestino(), this.distancia + this.peso);
//    }
//
//    public NodeDijkstra(Vertice<T> vertice, double distancia) {
//        this.distancia = distancia;
//        this.peso = 0;
//        this.antecessor = new NodeDijkstra<>(vertice.predecessor);
//    }

//    public Vertice<T> getPredecessor() {
//        return predecessor;
//    }

    public double getDistancia() {
        return distancia;
    }

    public double estimativaDistancia() {
        return distancia + peso;
    }

//    public Vertice<T> getAntecessor() {
//        return antecessor;
//    }


    public void setDistancia(int i) {
        this.distancia = i;
    }
}
