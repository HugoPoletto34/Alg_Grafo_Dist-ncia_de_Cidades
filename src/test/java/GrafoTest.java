import models.Cidade;
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
        GrafoCidades grafoCidades = new GrafoCidades();
        System.out.println("Gerando Grafo...");
        grafoCidades.gerarGrafoComCidades();
        grafoCidades.criarLigacoesTresCidadesProximas();
//        grafo.addVertice();
//        grafo.addVertice();
//        grafo.addVertice();
//        grafo.addVertice();
//        grafo.addVertice();
//        grafo.addVertice();
//        grafo.addVertice();
//
//        grafo.getVertice(0).addAresta(20, grafo.getVertice(1));
//        grafo.getVertice(1).addAresta(20, grafo.getVertice(2));
//        grafo.getVertice(1).addAresta(20, grafo.getVertice(3));
//        grafo.getVertice(3).addAresta(20, grafo.getVertice(2));
//        grafo.getVertice(3).addAresta(20, grafo.getVertice(4));
//        grafo.getVertice(4).addAresta(20, grafo.getVertice(0));
//        grafo.getVertice(2).addAresta(20, grafo.getVertice(5));
//        grafo.getVertice(2).addAresta(20, grafo.getVertice(6));

        boolean sd = grafoCidades.verificarCiclo(grafoCidades.getVertice(0), grafoCidades.getVertice(3));
        System.out.println(sd);
//        grafo.caminhosFechados();
    }
}
