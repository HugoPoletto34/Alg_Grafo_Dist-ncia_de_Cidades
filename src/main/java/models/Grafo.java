package models;

import java.util.LinkedList;
import java.util.List;
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


    public boolean temCicloComIntermediario(Vertice<T> verticeInicial, Vertice<T> verticeIntermediaria) {
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

    @Override
    public String toString(){
        StringBuilder aux = new StringBuilder("Grafo:");

        for (Vertice<T> vertex : this.vertices) {
            aux.append(" --- " + vertex.toString());
        }

        return aux.toString();
    }
    
    

}
