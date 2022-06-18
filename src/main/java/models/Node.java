package models;

public class Node<T> implements Comparable<Node> {
    public Vertice<T> origem;
    public Double valor;
    public Vertice<T> destino;

    public Node(Vertice<T> origem, Double valor, Vertice<T> destino) {
        this.origem = origem;
        this.valor = valor;
        this.destino = destino;
    }
    @Override
    public String toString() {
        return "origem: " + this.origem + " - destino: " + this.destino;
    }

    @Override
    public int compareTo(Node o) {
        if(this.valor> o.valor) return 1;
        else if(this.valor < o.valor) return -1;
        return 0;
    }
}
