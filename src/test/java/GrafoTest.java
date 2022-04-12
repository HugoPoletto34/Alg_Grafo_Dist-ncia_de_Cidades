import models.Grafo;
import org.junit.Test;

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
}
