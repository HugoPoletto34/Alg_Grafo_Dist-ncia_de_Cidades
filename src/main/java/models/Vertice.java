package models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Vertice {
    private int qtArestas;
    private int id;
    private List<Aresta> arestas;

    /**
     * Construtor para criação de vértice identificado
     * @param id Número do vértice a ser criado.
     */
    public Vertice(int id){
        this.id = id;
        this.qtArestas=0;
        this.arestas = new LinkedList<>();
    }

    public int getId() {
        return this.id;
    }

    private boolean existeAresta(Vertice dest){
        boolean resposta = false;
        for(int i=0; i<this.qtArestas; i++){
            if(this.arestas.get(i).getDestino() == dest){
                resposta = true;
                break;
            }
        }
        return resposta;

    }

    public void addAresta(int peso, Vertice dest){
        if(!this.existeAresta(dest)){
            Aresta nova = new Aresta(peso, dest);
            this.arestas.add(nova);
            this.qtArestas++;
            dest.addAresta(peso, this);
        }
    }

    public int grau(){
        return this.qtArestas;
    }

    public ArrayList<Vertice> getAllVerticesConectados(){
        ArrayList<Vertice> vertices = new ArrayList<>();

        for (int i = 0; i < qtArestas; i++) {
            vertices.add(arestas.get(i).getDestino());
        }

        return vertices;
    }

    @Override
    public String toString(){
        StringBuilder aux = new StringBuilder("Vértice("+this.id+")=[");
        for(int i = 0; i<this.qtArestas; i++) {
            aux.append(this.arestas.get(i).getDestino().id);
            if (i < this.qtArestas - 1)
                aux.append(", ");
        }

        aux.append("]");

        return aux.toString();
    }
}
