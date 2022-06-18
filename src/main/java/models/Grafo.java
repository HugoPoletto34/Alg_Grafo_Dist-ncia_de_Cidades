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

    public boolean temCicloComIntermediario(Vertice<T> verticeInicial, Vertice<T> verticeIntermediaria) {
        Vertice<T> verticeAtual = verticeInicial.getArestas().get(0).getDestino();
        boolean resposta = temCicloComIntermediario(verticeInicial, verticeInicial, verticeAtual, verticeIntermediaria);
        this.vertices = this.vertices.stream().peek(e -> e.setVisitado(false)).collect(Collectors.toList());
        return resposta;
    }

    public List<Vertice<T>> dijkstraCaminhoMinimo(Vertice<T> verticeInicial, Vertice<T> verticeFinal) {
        HashMap<Integer, Vertice<T>> conjuntosExplorados = new HashMap<>();

        for (Vertice<T> vertice : this.vertices) {
            vertice.distancia = Integer.MAX_VALUE;
            vertice.predecessor = null;
        }

        conjuntosExplorados.put(verticeInicial.getId(), verticeInicial);
        verticeInicial.distancia = 0;

        for (int i = 0; i < vertices.size() - 1; i++) {
            Aresta<T> proxArestaCorte = arestasCorte(conjuntosExplorados);
            if (proxArestaCorte == null) {
                break;
            }
            proxArestaCorte.getDestino().predecessor = proxArestaCorte.getOrigem();
            proxArestaCorte.getDestino().distancia = proxArestaCorte.getPeso() + proxArestaCorte.getOrigem().distancia;
            conjuntosExplorados.put(proxArestaCorte.getDestino().getId(), proxArestaCorte.getDestino());
        }

        Stack<Vertice<T>> pilha = new Stack<>();
        List<Vertice<T>> caminho = new LinkedList<>();
        Vertice<T> verticeAtual = conjuntosExplorados.get(verticeFinal.getId());
        if (verticeAtual == null) {
            return Collections.emptyList();
        }
        while (verticeAtual.getId() != verticeInicial.getId()) {
            pilha.push(verticeAtual);
            verticeAtual = verticeAtual.predecessor;
        }
        pilha.add(verticeAtual);

        while (!pilha.empty()) {
            caminho.add(pilha.pop());
        }
//        for (Vertice<T> vertice : conjuntosExplorados) {
//            caminho.add(vertice);
//            if (vertice.getArestas().stream().anyMatch(e -> e.getDestino().getId() == verticeFinal.getId())) {
//                caminho.add(verticeFinal);
//                break;
//            }
//
//        }

        
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

//    public List<Vertice<T>> algoritmoDijkstra(Vertice<T> verticeInicial, Vertice<T> verticeFinal){
//        int[] vectEtiquetas = new int[getV()];//Verifica se o vertice ja esta "checado"
//        int[] vectCustos = new int[getV()];//custo acumulado
//        int[] percursoMinimo = new int[getV()]; //vai ter o percurso minimo de uma cidade a outra
//        int w, w0 = 0;//w0 -> posicao do vector onde esta o percurso minimo.
//        boolean stop;
//
////-----------------------------------------------------
//
//        for (w = 0; w < getV(); w++){//inicializa todas a vareaveis
//            vectEtiquetas[w] = -1;
//            vectCustos[w] = custo(s_inicial, w);//coloca no vector qual o custo de ir de s_inicial para w
//            percursoMinimo[w] = s_inicial;
//        }
//
//        vectEtiquetas[s_inicial] = s_inicial;//posicao onde comeco a procura
//        vectCustos[s_inicial] = 0;//custo inicial e 0
//        stop = false;
//
//        while (!stop){
//            int minimoCusto = INFINITO;
//            for (w = 0; w < getV(); w++) {
//                if (vectEtiquetas[w] == -1 && (vectCustos[w] < minimoCusto)){//Determina o minimo de todos os vertices k inda nao foram "checados"
//                    w0 = w;	//diz a posicao onde esta o minimo												//
//                    minimoCusto = vectCustos[w0];
//                }
//            }
//            if ((minimoCusto == INFINITO) || (s_final == w0)) // Cheguei a sulução!!!
//                stop = true;
//
//            else{
//                vectEtiquetas[w0] = percursoMinimo[w0];
//                for (w = 0; w < getV(); w++){
//                    if (vectCustos[w] > (vectCustos[w0] + custo(w0, w))){ //minimiza o custo
//                        vectCustos[w] = vectCustos[w0] + custo(w0, w);
//                        percursoMinimo[w] = w0;
//                    }
//                }
//            }
//        }
//        System.out.println("Para ir de "+cidA+" ate "+cidB+" tenho que passar em:");
//        int anterior = s_final;
//        //apresenta o percurso do fim para o inicio
//        ArrayList<Cidade> listAux = new ArrayList<Cidade>();//representara as cidades por onde passo
//        listAux.add(CFinal);
//        while(anterior != s_inicial){
//            anterior = percursoMinimo[anterior];
//            listAux.add(listC.get(anterior));
//        }
//        mostrarListaCidadesPercorridas(listAux);
//        ArrayList<Estrada> LE_ondePasso = constroiPercurso(listAux, listE); //Devolve estradas por onde passo
//
//        return LE_ondePasso;
//    }

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
