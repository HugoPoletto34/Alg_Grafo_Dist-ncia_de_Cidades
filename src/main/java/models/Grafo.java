package models;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    // método verifica se há ciclo no grafo

    //Inicialização / Chamada inicial
    //1. t ← 0;                 // Inicializar tempo global
    //2. para todo vértice v ∈ V(G) faça
    //a. TD[v]  ← 0;          // Inicializar tempo de descoberta
    //b. TT[v]  ← 0;         // Inicializar tempo de término
    //c. pai[v] ← null; // Inicializar predecessor ou pai
    //3. enquanto existir algum vértice v tal que TD[v] = 0 efetuar
    //a. Executar Busca_Profundidade(v); // Executar busca para raiz v

    // Busca_Profundidade(v)
    //1. t ← t + 1;  TD[v] ← t; // Definir tempo de descoberta
    //2. para todo vértice w ∈ Γ(v) faça  // Para toda a vizinhança de v
    //a. se TD[w] = 0 então // Se w é visitado pela 1ª vez
    //Visitar aresta {v, w};            // {v, w} é aresta de árvore
    //pai[w] ← v;
    //Executar Busca_Profundidade(w);
    //b. senão se TT[w] = 0 e w ≠ pai[v] então  // Se w for ancestral mas não pai
    //Visitar aresta {v, w}; // {v, w} é aresta de retorno
    //3. t ← t + 1;  TT[v] ← t; // Definir tempo de término

    public List<List<Vertice<T>>> caminhosFechados(Vertice<T> vertice){
        List<List<Vertice<T>>> resposta = new LinkedList<>();
        List<Vertice<T>> lista = new LinkedList<>();
        Queue<Vertice<T>> fila = new ArrayDeque<>();
        fila.add(vertice);
        lista.add(vertice);
        int count = 0;

        while(!fila.isEmpty()){
            Vertice<T> atual = fila.remove();
            for(int i=0; i<atual.getArestas().size(); i++){
                if(!lista.contains(atual.getArestas().get(i).getDestino())){
                    fila.add(atual.getArestas().get(i).getDestino());
                    lista.add(atual.getArestas().get(i).getDestino());
                }
            }

            if(fila.size() == this.vertices.size()){
                resposta.add(lista);
                lista = new LinkedList<>();
                fila = new ArrayDeque<>();
                count = 0;
            }
        }

        return resposta;
    }

    public List<Vertice<T>> caminhoFechado(Vertice<T> origem, Vertice<T> destino){
        List<Vertice<T>> resposta = new LinkedList<>();
        Queue<Vertice<T>> fila = new ArrayDeque<>();
        Vertice<T> atual = origem;
        Vertice<T> proximo;
        atual.setVisitado(true);
        fila.add(atual);
        while(!fila.isEmpty()){
            atual = fila.remove();
            if(atual.equals(destino)){
                break;
            }
            for(Aresta a : atual.getArestas()){
                proximo = a.getDestino();
                if(!proximo.isVisitado()){
                    proximo.setVisitado(true);
                    fila.add(proximo);
                }
            }
        }
        atual = destino;
        while(!atual.equals(origem)){
            resposta.add(atual);
            for(Aresta a : atual.getArestas()){
                if(a.getDestino().equals(atual)){
                    atual = a.getDestino();
                    break;
                }
            }
        }
        resposta.add(origem);
        return resposta;
    }

    public boolean verificarCiclo(Vertice<T> verticeInicial, Vertice<T> verticeIntermediaria) {
        List<Vertice<T>> verticesPercorridos = new LinkedList<>();
        return verificarCiclo(verticeInicial, verticeInicial.getArestas().get(0).getDestino(), verticeIntermediaria, 0, new LinkedList<>(new LinkedList<>()));
    }

    private boolean verificarCiclo(Vertice<T> verticeInicial, Vertice<T> verticeAtual, Vertice<T> verticeIntermediaria, int qtdPercorridos, List<Vertice<T>> ciclos) {
        boolean resposta = false;
        ciclos.add(verticeAtual);
        verticeAtual.setVisitado(true);
        verticeAtual.setMarcado(true);

        List<Aresta> arestas = verticeAtual.getArestasDesconsideraVisitado(verticeInicial, qtdPercorridos);
        if (qtdPercorridos > 0 && verticeAtual.getId() == verticeInicial.getId()) {
            resposta = true;
        } else if (!arestas.isEmpty()){
            for (Aresta aresta : arestas) {
                resposta = verificarCiclo(verticeInicial, aresta.getDestino(), verticeIntermediaria, qtdPercorridos + 1, ciclos);
            }
            if (!resposta && verticeAtual.getArestasDesconsideraVisitado(verticeInicial, qtdPercorridos).size() == 0) {
                ciclos.remove(verticeAtual);
                verticeAtual.setMarcado(false);
            }

        } else {
            ciclos.remove(verticeAtual);
            verticeAtual.setMarcado(false);
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
