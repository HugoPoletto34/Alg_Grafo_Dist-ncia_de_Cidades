import models.Grafo;
import models.GrafoCidades;
import models.Vertice;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class GrafoTest {
    @Test
    public void tresVerticesConexosTest() {
        Grafo grafo = new Grafo();
        grafo.addVertice();
        grafo.addVertice();
        grafo.addVertice();

        grafo.getVertice(0).addAresta(20, grafo.getVertice(1));
        grafo.getVertice(1).addAresta(20, grafo.getVertice(2));
        grafo.getVertice(2).addAresta(20, grafo.getVertice(0));

        assertEquals("Grafo: --- Vértice(0)=[1, 2] --- Vértice(1)=[0, 2] --- Vértice(2)=[1, 0]", grafo.toString());

    }

    @Test
    public void verificarCicloTest() {
//        GrafoCidades grafoCidades = new GrafoCidades();
//        System.out.println("Gerando Grafo...");
//        grafoCidades.gerarGrafoComCidades();
//        grafoCidades.criarLigacoesTresCidadesProximas();
        Grafo grafo = new Grafo();
        grafo.addVertice();
        grafo.addVertice();
        grafo.addVertice();
        grafo.addVertice();
        grafo.addVertice();
        grafo.addVertice();
        grafo.addVertice();

        grafo.getVertice(0).addAresta(20, grafo.getVertice(1));
        grafo.getVertice(1).addAresta(20, grafo.getVertice(2));
        grafo.getVertice(1).addAresta(20, grafo.getVertice(3));
        grafo.getVertice(3).addAresta(20, grafo.getVertice(2));
        grafo.getVertice(3).addAresta(20, grafo.getVertice(4));
        grafo.getVertice(4).addAresta(20, grafo.getVertice(0));
        grafo.getVertice(2).addAresta(20, grafo.getVertice(5));
        grafo.getVertice(2).addAresta(20, grafo.getVertice(6));

        grafo.getVertice(2).addAresta(2, grafo.getVertice(1));
        grafo.getVertice(1).addAresta(1, grafo.getVertice(3));
        grafo.getVertice(3).addAresta(3, grafo.getVertice(2));
        grafo.getVertice(1).addAresta(1, grafo.getVertice(0));
        grafo.getVertice(0).addAresta(0, grafo.getVertice(4));
        grafo.getVertice(4).addAresta(4, grafo.getVertice(3));

//        boolean sd = grafoCidades.temCicloComIntermediario(grafoCidades.getVertice(0), grafoCidades.getVertice(2));
        boolean sd = grafo.temCicloComIntermediario(grafo.getVertice(2), grafo.getVertice(5));
        System.out.println(sd);
//        grafo.caminhosFechados();
    }

    @Test
    public void testeKMCidade2() {
        Grafo<String> grafo = new Grafo<>();

        grafo.addVertice("a");
        grafo.addVertice("b");
        grafo.addVertice("c");
        grafo.addVertice("d");
        grafo.addVertice("e");
        grafo.addVertice("f");

        grafo.getVertice(0).addAresta(6, grafo.getVertice(1));
        grafo.getVertice(1).addAresta(1, grafo.getVertice(3));
        grafo.getVertice(2).addAresta(5, grafo.getVertice(0));
        grafo.getVertice(2).addAresta(1, grafo.getVertice(4));
        grafo.getVertice(3).addAresta(1, grafo.getVertice(5));
        grafo.getVertice(4).addAresta(8, grafo.getVertice(5));
        grafo.getVertice(4).addAresta(1, grafo.getVertice(1));
        grafo.getVertice(5).addAresta(2, grafo.getVertice(0));

        List<Vertice<String>> list = grafo.dijkstraCaminhoMinimo(grafo.getVertice(2), grafo.getVertice(0));
        System.out.println(list);

    }
}
