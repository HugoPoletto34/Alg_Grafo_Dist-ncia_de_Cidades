import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import models.Cidade;
import models.GrafoCidades;
import models.Vertice;
import utilLib.ArquivoTextoEscrita;

public class GrafoCidadesApplication {
	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
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
		
		Vertice<Cidade> verticeInicial = null;
		Vertice<Cidade> verticeIntermediario = null;

		int opcao = -1;
		while (opcao != 0) {
			System.out.println("\nEscolha uma opção:\n" +
					"1 - Busca em largura\n" +
					"2 - Avaliação de existencia de ciclo\n" +
					"3 - Avaliação de existencia de caminho mínimo\n" +
					"0 - Sair");
			try {
				opcao = Integer.parseInt(leitor.nextLine());
			} catch (NumberFormatException e) {
				opcao = -1;
			}

			switch (opcao) {
				case 1:
					System.out.println("\nBusca em largura:\n");
					while (verticeInicial == null) {
						System.out.println("Insira o nome da cidade de partida: ");
						String nomeVerticeInicial = leitor.nextLine();
						verticeInicial = grafoCidades.getCidadePeloNome(nomeVerticeInicial);
						if (nomeVerticeInicial == null)
							System.out.println("Nome da cidade inválida ou não está presente no grafo.");
					}
					int indice = 1;
					for (Vertice<Cidade> cidade : grafoCidades.buscaEmLargura(verticeInicial)) {
						System.out.println(indice + " - " + cidade.getItem().getNome());
						indice++;
					}
					break;
				case 2:
					System.out.println("\nDescubra se há ciclo a partir de uma cidade tendo outra cidade como intermediário.");
					while (verticeInicial == null) {
						System.out.println("Insira o nome da cidade de partida: ");
						String nomeVerticeInicial = leitor.nextLine();
						verticeInicial = grafoCidades.getCidadePeloNome(nomeVerticeInicial);
						if (nomeVerticeInicial == null)
							System.out.println("Nome da cidade inválida ou não está presente no grafo.");
					}
					while (verticeIntermediario == null) {
						System.out.println("Insira a cidade que deverá ser intermediária");
						String nomeVerticeIntermediario = leitor.nextLine();
						verticeIntermediario = grafoCidades.getCidadePeloNome(nomeVerticeIntermediario);
						if (verticeIntermediario == null)
							System.out.println("Nome da cidade inválida ou não está presente no grafo.");
					}
					System.out.println("Buscando ciclo...");
					boolean resposta = grafoCidades.temCicloComIntermediario(verticeInicial, verticeIntermediario);
					System.out.println("Finalizado!");
					System.out.println("Há ciclo possível iniciando em " + verticeInicial.getItem().getNome() + " e com intermediário " + verticeIntermediario.getItem().getNome() + "?");
					if (resposta)
						System.out.println("Sim!");
					else
						System.out.println("Não!");
					break;
				case 3:
					System.out.println("\nCaminho mínimo com Dijkstra:\n");


					System.out.println("\nDescubra o caminho mínimo de uma cidade a outra.");
					verticeInicial = null;
					verticeIntermediario = null;
					while (verticeInicial == null) {
						System.out.println("Insira o nome da cidade de partida: ");
						String nomeVerticeInicial = leitor.nextLine();
						verticeInicial = grafoCidades.getCidadePeloNome(nomeVerticeInicial);
						if (nomeVerticeInicial == null)
							System.out.println("Nome da cidade inválida ou não está presente no grafo.");
					}
					while (verticeIntermediario == null) {
						System.out.println("Insira a cidade final: ");
						String nomeVerticeIntermediario = leitor.nextLine();
						verticeIntermediario = grafoCidades.getCidadePeloNome(nomeVerticeIntermediario);
						if (verticeIntermediario == null)
							System.out.println("Nome da cidade inválida ou não está presente no grafo.");
					}

					System.out.println("Buscando caminho...\n");
					List<Vertice<Cidade>> caminhoDijkstra = grafoCidades.dijkstraCaminhoMinimo(verticeInicial, verticeIntermediario);
					if (caminhoDijkstra == null)
						System.out.println("Não há caminho possível.");
					else {
						int i = 1;
						for (Vertice<Cidade> vertice : caminhoDijkstra) {
							System.out.println(i + " - " + vertice.getItem().getNome());
							i++;
						}
					}

					break;
				case 0:
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Opção inválida.");
					break;

			}
		}



	}
	
	
}
