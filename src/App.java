public class App {

    static void grafoSimples(){
        Grafo grafo1 = new Grafo();
        int vertices = 10;

        for(int i=0; i<vertices; i++)
            grafo1.addVertice();

        for(int i=0; i<vertices-1; i+=2){
            grafo1.addAresta(grafo1.getVertices()[i], grafo1.getVertices()[i+1]);
        }

        grafo1.addAresta(grafo1.getVertices()[0], grafo1.getVertices()[1]);

        System.out.println(grafo1);
        System.out.println("Grafo completo? "+grafo1.completo());

    }
    
    public static void main(String[] args) throws Exception {
        int vertices = 10;
        Grafo grafo1 = new GrafoCompleto(vertices);
     
        System.out.println(grafo1);
        System.out.println("Grafo completo? "+grafo1.completo());

    }
}
