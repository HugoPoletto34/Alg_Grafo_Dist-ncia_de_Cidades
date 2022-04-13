package models;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe básica para um models.Grafo simples
 */
public class Grafo {
    private static int ultimoId = 0;

    /**
     * Um grafo tem um vetor de vértices, que conterão as arestas
     */
    protected List<Vertice> vertices;

    /**
     * Construtor. Cria um grafo vazio com capacidade para MAX_VERTICES
     */
    public Grafo(){
        this.vertices = new LinkedList<>();
    }

    /**
     * Método de acesso ao vetor de vértices
     * @return Vetor com vértices do grafo
     */
    public List<Vertice> getVertices(){
            return this.vertices;
    }

    public Vertice getVertice(int indice) {
        return this.vertices.get(indice);
    }

    /**
     * Adiciona, se possível, um vértice ao grafo. O vértice é auto-nomeado com o próximo id disponível.
     */
    public void addVertice(){
        Vertice novo = new Vertice(ultimoId);
        this.vertices.add(novo);
        ultimoId++;
    }

    public void addAresta(Vertice origem, Vertice destino){
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

        for(int i=0; i<this.vertices.size(); i++){
            aux.append(" --- " + this.vertices.get(i).toString());
        }

        return aux.toString();
    }

}
