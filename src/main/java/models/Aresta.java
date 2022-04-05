package models;

public class Aresta {

    private int peso;
    private Vertice destino;

    /**
     * Construtor para arestas com ou sem peso
     * @param peso Peso da aresta
     * @param dest Vértice de destino
     */
    public Aresta(int peso, Vertice dest){
        this.peso = peso;
        this.destino = dest;
    }

    /**
     * Método de acesso para o peso da aresta
     * @return the peso
     */
    public int getPeso() {
        return peso;
    }
   
    /**
     * Método de acesso para o destino da aresta
     * @return the destino
     */
    public Vertice getDestino() {
        return destino;
    }

    

}
