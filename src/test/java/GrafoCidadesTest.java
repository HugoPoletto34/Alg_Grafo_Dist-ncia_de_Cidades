
import models.GrafoCidades;
import models.Cidade;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GrafoCidadesTest {
    @Test
    public void testeKMCidade2() {
        Cidade v1, v2;
        v1 = new Cidade("RJ", -22.89225, -43.20651);
        v2 = new Cidade("São Paulo", -23.54276, -46.63646);

        assertEquals(357.88, GrafoCidades.calcularDistanciaCidades(v1, v2), 1.0);
    }

}
