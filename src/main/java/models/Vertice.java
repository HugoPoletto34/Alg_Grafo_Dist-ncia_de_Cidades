package models;

import java.util.ArrayList;

public class Vertice {
    private static final int MAX_ARESTAS = 100;
    private int qtArestas;    
    private int id;

    /**
     * Um vértice tem um vetor de arestas para outros vértices
     */
    private Aresta[] arestas;

    /**
     * Construtor para criação de vértice identificado
     * @param id Número do vértice a ser criado.
     */
    public Vertice(int id){
        this.id = id;
        this.qtArestas=0;
        this.arestas = new Aresta[MAX_ARESTAS];
    }

    public int getId() {
        return this.id;
    }

    /**
     * Verifica se já existe aresta entre este vértice e um destino. Método privado
     * @param dest Vértice de destino
     * @return TRUE se existe aresta, FALSE se não
     */
    private boolean existeAresta(Vertice dest){
        boolean resposta = false;
        for(int i=0; i<this.qtArestas; i++){
            if(this.arestas[i].getDestino() == dest){
                resposta = true;
                break;
            }
        }
        return resposta;

    }
    
    /**
     * Adiciona uma aresta neste vértice para um destino
     * @param peso Peso da aresta (1 para grafos não ponderados)
     * @param dest Vértice de destino
     */
    public void addAresta(int peso, Vertice dest){
        if(this.qtArestas < MAX_ARESTAS && !this.existeAresta(dest)){
            Aresta nova = new Aresta(peso, dest);
            this.arestas[this.qtArestas] = nova;
            this.qtArestas++;
            dest.addAresta(peso, this);
        }
    }

    /**
     * Retorna o grau do vértice
     * @return Grau (inteiro não negativo)
     */
    public int grau(){
        return this.qtArestas;
    }

    public ArrayList<Vertice> getAllVerticesConectados(){
        ArrayList<Vertice> vertices = new ArrayList<>();

        for (int i = 0; i < qtArestas; i++) {
            vertices.add(arestas[i].getDestino());
        }

        return vertices;
    }

    
    /**
     * Retorna a descrição do vértice (id + vizinhos) em String
     * @return Descrição do vértice
     */
    @Override
    public String toString(){
        StringBuilder aux = new StringBuilder("Vértice "+this.id+":");

        for(int i = 0; i<this.qtArestas; i++)
            aux.append(this.arestas[i].getDestino().id+ " ");

        return aux.toString();
    }
}
