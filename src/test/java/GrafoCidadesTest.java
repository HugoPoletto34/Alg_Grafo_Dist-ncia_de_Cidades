
import models.Grafo;
import models.GrafoCidades;
import models.VerticeCidade;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;

public class GrafoCidadesTest {
    @Test
    public void testeKMCidade2() {
        VerticeCidade v1, v2;
        v1 = new VerticeCidade(1, "RJ", -22.89225, -43.20651);
        v2 = new VerticeCidade(2, "São Paulo", -23.54276, -46.63646);

        assertEquals(356.26, GrafoCidades.calcularDistanciaCidades(v1, v2), 1.0);
    }

}
