package models;

public class Node {
    public Vertice origem;
    public Double valor;
    public Vertice destino;

    public Node(Vertice origem, Double valor, Vertice destino) {
        this.origem = origem;
        this.valor = valor;
        this.destino = destino;
    }
    @Override
    public String toString() {
        return "origem: " + this.origem + " - destino: " + this.destino;
    }
}
