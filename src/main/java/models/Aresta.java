package models;

public class Aresta<T> implements Comparable<Aresta<T>> {

    private Vertice<T> origem;
    private double quilometros;
    private Vertice<T> destino;

    public Aresta(Vertice<T> origem, double peso, Vertice<T> dest){
        this.origem = origem;
        this.quilometros = peso;
        this.destino = dest;
    }

    public Vertice<T> getOrigem() {
        return origem;
    }


    public double getPeso() {
        return this.quilometros;
    }

    public Vertice<T> getDestino() {
        return destino;
    }

    @Override
    public String toString() {
        return "Aresta{" +
                "quilometros=" + quilometros +
                ", destino=" + destino +
                '}';
    }


    @Override
    public int compareTo(Aresta<T> o) {
        if(this.getPeso()> o.getPeso()) return 1;
        else if(this.getPeso() < o.getPeso()) return -1;
        return 0;
    }
}
