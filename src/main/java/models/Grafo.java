package models;

import java.util.LinkedList;
import java.util.List;

public class Grafo<T> {
    private static int ultimoId = 0;

    protected List<Vertice<T>> vertices;

    public Grafo(){
        this.vertices = new LinkedList<>();
    }

    public List<Vertice<T>> getVertices(){
            return this.vertices;
    }

    public Vertice<T> getVertice(int indice) {
        return this.vertices.get(indice);
    }

    public void addVertice(){
        Vertice<T> novo = new Vertice<>(ultimoId);
        this.vertices.add(novo);
        ultimoId++;
    }

    public void addAresta(Vertice<T> origem, Vertice<T> destino){
        origem.addAresta(1, destino);
    }

    public boolean completo(){
        boolean resposta = true;
        
        for(int i=0; i<this.vertices.size(); i++){
            if(this.vertices.get(i).grau()!= (this.vertices.size() - 1)){
                resposta = false;
                break;
            }
        }
        return resposta;
    }

    @Override
    public String toString(){
        StringBuilder aux = new StringBuilder("Grafo:");

        for (Vertice<T> vertex : this.vertices) {
            aux.append(" --- " + vertex.toString());
        }

        return aux.toString();
    }

}
