
import models.Grafo;
import models.GrafoCidades;
import models.VerticeCidade;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;

public class GrafoCidadesTest {
    @Test
    public void testeKMCidade() {
        VerticeCidade v1, v2;
        v1 = new VerticeCidade(1, "BH", -19.9281, -43.9419);
        v2 = new VerticeCidade(2, "SP", -23.5504, -46.6339);

        System.out.println(GrafoCidades.calcularDistanciaCidades(v1, v2));
        // assertEquals(490.69, GrafoCidades.calcularDistanciaCidades(v1, v2));
    }

}
