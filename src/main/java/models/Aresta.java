package models;

public class Aresta {

    private double quilometros;
    private Vertice destino;

    public Aresta(double peso, Vertice dest){
        this.quilometros = peso;
        this.destino = dest;
    }

    public double getPeso() {
        return this.quilometros;
    }

    public Vertice getDestino() {
        return destino;
    }

    

}
