package models;

import java.util.*;
import java.util.stream.Collectors;

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

    public void addVertice(T item) {
        Vertice<T> novo = new Vertice<>(ultimoId);
        novo.setItem(item);
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

    public List<Vertice<T>> getVerticesPercorridos() {
        return this.vertices.stream().filter(Vertice::isVisitado).collect(Collectors.toList());
    }

    public List<Vertice<T>> dijkstraCaminhoMinimo(Vertice<T> verticeInicial, Vertice<T> verticeFinal) {
        List<Vertice<T>> caminho = null;

        if (!this.buscaEmLargura(verticeInicial).contains(verticeFinal))
            return caminho;
        HashMap<Integer, Vertice<T>> conjuntosExplorados = new HashMap<>();

        for (Vertice<T> vertice : this.vertices) {
            vertice.distancia = Integer.MAX_VALUE;
            vertice.predecessor = null;
        }

        conjuntosExplorados.put(verticeInicial.getId(), verticeInicial);
        verticeInicial.distancia = 0;

        for (int i = 0; i < vertices.size() - 1; i++) {
            Aresta<T> proxArestaCorte = arestasCorte(conjuntosExplorados);
            if (proxArestaCorte == null)
                break;
            proxArestaCorte.getDestino().predecessor = proxArestaCorte.getOrigem();
            proxArestaCorte.getDestino().distancia = proxArestaCorte.getPeso() + proxArestaCorte.getOrigem().distancia;
            conjuntosExplorados.put(proxArestaCorte.getDestino().getId(), proxArestaCorte.getDestino());
        }

        Stack<Vertice<T>> pilha = new Stack<>();
        Vertice<T> verticeAtual = conjuntosExplorados.get(verticeFinal.getId());
        if (verticeAtual != null) {
            caminho = new LinkedList<>();
            while (verticeAtual.getId() != verticeInicial.getId()) {
                pilha.push(verticeAtual);
                verticeAtual = verticeAtual.predecessor;
            }
            pilha.add(verticeAtual);

            while (!pilha.empty()) {
                caminho.add(pilha.pop());
            }
        }

        this.vertices.stream().map(v -> {
            v.distancia = Integer.MAX_VALUE;
            v.predecessor = null;
            return v;
        });

        return caminho;
    }

    private Aresta<T> arestasCorte(HashMap<Integer, Vertice<T>> listaVertices) {
        return listaVertices.values()
                .stream()
                .flatMap(e -> e.getArestas().stream())
                .filter(e -> !listaVertices.values().contains(e.getDestino()))
                .min((e1, e2) -> {
                    if ((e1.getPeso() + e1.getOrigem().distancia) < (e2.getPeso() + e2.getOrigem().distancia)) return -1;
                    else if ((e1.getPeso() + e1.getOrigem().distancia) > (e2.getPeso() + e2.getOrigem().distancia)) return 1;
                    else return 0;

                })
                .orElse(null);
    }

    public boolean temCicloComIntermediario(Vertice<T> verticeInicial, Vertice<T> verticeIntermediaria) {
        if (!this.buscaEmLargura(verticeInicial).contains(verticeIntermediaria))
            return false;
        Vertice<T> verticeAtual = verticeInicial.getArestas().get(0).getDestino();
        boolean resposta = temCicloComIntermediario(verticeInicial, verticeInicial, verticeAtual, verticeIntermediaria);
        this.vertices = this.vertices.stream().peek(e -> e.setVisitado(false)).collect(Collectors.toList());
        return resposta;
    }

    private boolean temCicloComIntermediario(Vertice<T> verticeInicial, Vertice<T> verticeAnterior, Vertice<T> verticeAtual, Vertice<T> verticeIntermediaria) {
        boolean resposta = false;
        verticeAtual.setVisitado(true);

        if (verticeAtual.getId() == verticeInicial.getId() && getVerticesPercorridos().contains(verticeIntermediaria)) {
            resposta = true;
        } else {
            for (Aresta<T> aresta : verticeAtual.getArestasDesconsidera(verticeAnterior)) {
                if (!resposta && !getVerticesPercorridos().contains(aresta.getDestino()) && !(aresta.getDestino().getId() == verticeInicial.getId() && !(getVerticesPercorridos().contains(verticeIntermediaria)))) {
                    resposta = temCicloComIntermediario(verticeInicial, verticeAtual, aresta.getDestino(), verticeIntermediaria);

                }
            }
            if (!resposta)
                verticeAtual.setVisitado(false);
        }


        return resposta;

    }

    public List<Vertice<T>> buscaEmLargura(Vertice<T> verticeInicial){
        ArrayList<Vertice<T>> marcados = new ArrayList<>();
        ArrayList<Vertice<T>> fila = new ArrayList<>();
        List<Vertice<T>> finalizados = new LinkedList<>();

        marcados.add(verticeInicial);
        fila.add(verticeInicial);
        while(!fila.isEmpty()){
            Vertice<T> visitado = fila.get(0);
            for(int i=0; i < visitado.getArestas().size(); i++){
                Vertice<T> proximo = visitado.getArestas().get(i).getDestino();
                if (!marcados.contains(proximo)){
                    marcados.add(proximo);
                    finalizados.add(proximo);
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
        return finalizados;
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
