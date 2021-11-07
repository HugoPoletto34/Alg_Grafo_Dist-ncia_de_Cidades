/**
 * Classe filha de grafo para criação de um grafo completo
 */
public class GrafoCompleto extends Grafo{
    
    private int tamanho; 

    /**
     * Construtor para criar um grafo completo Kn
     * @param tamanho Tamanho n do grafo completo
     */
    public GrafoCompleto(int tamanho){
        super();
        this.tamanho = tamanho;
        
        for(int i=0; i<this.tamanho; i++){
            this.addVertice();
        }

        for(int i=0; i<this.tamanho; i++){
            for(int j=i+1; j<tamanho; j++){
                this.addAresta(this.getVertices()[i], this.getVertices()[j]);
            }
        }
    }
}
