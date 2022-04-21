import models.*;
import utilLib.ArquivoTextoEscrita;

import java.time.LocalDate;

public class GrafoCidadesApplication {
	public static void main(String[] args) {
		GrafoCidades grafoCidades = new GrafoCidades();
		System.out.println("Gerando Grafo...");
		grafoCidades.gerarGrafoComCidades();
		grafoCidades.criarLigacoesTresCidadesProximas();
		System.out.println("Grafo gerado com sucesso!");

		ArquivoTextoEscrita arq = new ArquivoTextoEscrita();
		System.out.println("Gerando Relatório...");
		arq.abrirArquivo("./Relatórios/Grafo_Cidades_" + LocalDate.now() + ".md");
		arq.escrever("# Relatório de Grafo de Cidades - v1");
		arq.escrever(grafoCidades.toString());
		arq.fecharArquivo();

		System.out.println("Relatório gerado com sucesso!");
	}
}
