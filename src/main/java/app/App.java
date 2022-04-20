package app;

import models.*;
import utilLib.ArquivoTextoEscrita;

import java.time.LocalDate;

public class App {
	public static void main(String[] args) {
		GrafoCidades grafoCidades = new GrafoCidades();

		grafoCidades.gerarGrafoComCidades();
		grafoCidades.criarLigacoesTresCidadesProximas();

		ArquivoTextoEscrita arq = new ArquivoTextoEscrita();

		arq.abrirArquivo("./Relatórios/Grafo_Cidades_" + LocalDate.now() + ".md");
		arq.escrever("# Relatório de Grafo de Cidades - v1");
		arq.escrever(grafoCidades.toString());
		arq.fecharArquivo();

		System.out.println("Relatório gerado com sucesso!");
	}
}
