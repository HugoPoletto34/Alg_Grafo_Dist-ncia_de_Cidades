package models;

public class Aresta {

    private int peso;
    private Vertice destino;

    public Aresta(int peso, Vertice dest){
        this.peso = peso;
        this.destino = dest;
    }

    public int getPeso() {
        return peso;
    }

    public Vertice getDestino() {
        return destino;
    }

    

}
