/** 
 * Classe básica para um Grafo simples
 */
public class Grafo {
    public static final int MAX_VERTICES = 100;
    private static int ultimoId = 0;
    protected int qtdVertices;

    /**
     * Um grafo tem um vetor de vértices, que conterão as arestas
     */
    protected Vertice[] vertices;

    /**
     * Construtor. Cria um grafo vazio com capacidade para MAX_VERTICES
     */
    public Grafo(){
        this.qtdVertices = 0;
        this.vertices = new Vertice[MAX_VERTICES];
    }

    /**
     * Método de acesso ao vetor de vértices
     * @return Vetor com vértices do grafo
     */
    public Vertice[] getVertices(){
            return this.vertices;
    }

    /**
     * Adiciona, se possível, um vértice ao grafo. O vértice é auto-nomeado com o próximo id disponível.
     */
    public void addVertice(){
        if(this.qtdVertices < MAX_VERTICES){
            Vertice novo = new Vertice(ultimoId);
            this.vertices[qtdVertices] = novo;
            this.qtdVertices++;
            ultimoId++;
        }
    }

    /**
     * Adiciona uma aresta entre dois vértices do grafo. 
     * Não verifica se os vértices pertencem ao grafo.
     * @param origem Vértice de origem
     * @param destino Vértice de destino
     */
    public void addAresta(Vertice origem, Vertice destino){
        origem.addAresta(1, destino);
    }


    /**
     * Verifica se este é um grafo completo. 
     * @return TRUE para grafo completo, FALSE caso contrário
     */
    public boolean completo(){
        boolean resposta = true;
        
        for(int i=0; i<this.qtdVertices; i++){
            if(this.vertices[i].grau()!= (this.qtdVertices-1)){
                resposta = false;
                break;
            }
        }
        return resposta;
    }

    /** 
     * Descrição do grafo em string (vértices e seus vizinhos) 
     * */
    @Override
    public String toString(){
        StringBuilder aux = new StringBuilder("DESCRIÇÃO DO GRAFO: "+"\n");

        for(int i=0; i<this.qtdVertices; i++){
            aux.append(this.vertices[i].toString()+"\n");
        }

        return aux.toString();
    }

}
