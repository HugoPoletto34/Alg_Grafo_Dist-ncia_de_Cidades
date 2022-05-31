package models;

public class Aresta<T> {

    private double quilometros;
    private Vertice<T> destino;

    public Aresta(double peso, Vertice dest){
        this.quilometros = peso;
        this.destino = dest;
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
}
