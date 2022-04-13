
import models.Grafo;
import models.GrafoCidades;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GrafoCidadesTest {
    @Test
    public void tresVerticesConexosTest() {
        GrafoCidades grafo = new GrafoCidades();
        grafo.gerarGrafoComCidades();
        grafo.criarLigacoesTresCidadesProximas();

        ArquivoTextoLeitura arquivoTextoLeitura = new ArquivoTextoLeitura();

        arquivoTextoLeitura.abrirArquivo("ads");
        String valor = arquivoTextoLeitura.ler();
        arquivoTextoLeitura.

        assertEquals("Grafo: --- Vértice(0)=[1, 2] --- Vértice(1)=[0, 2] --- Vértice(2)=[1, 0]", grafo.toString());

    }
}
