package app;

import models.*;

public class App {
	public static void main(String[] args) {
		
		int tam = 0;	
		
		//declaro novos objetos para poder ler o arquivo texto
		
		ArquivoTextoLeitura arquivoEntrada = new ArquivoTextoLeitura
				("./BaseDados/br.csv"); 
									//***USAR o arq certo!
		
		String leituraArquivo = arquivoEntrada.lerBuffer();
		
		//excluo a primera linha
		
		leituraArquivo = arquivoEntrada.lerBuffer();		
		while(leituraArquivo!=null) {
			tam++;
			
			//leio cada uma das linhas e vou contando o tamanho
			
			leituraArquivo = arquivoEntrada.lerBuffer();
		}
		
		arquivoEntrada.fecharArquivo();	
		arquivoEntrada = new ArquivoTextoLeitura
				("./BaseDados/br.csv"); 
								//***USAR o arq certo!
		
		
		//leio o cabeçalho para descartar
		
		String leitura = arquivoEntrada.lerBuffer();	
		
		//crio um vetor do tipo VerticeCidade
		
		VerticeCidade[] vetor = new VerticeCidade[tam];
		
		//vou percorrer todos os vetores		
		
		for (int i = 0; i < tam; i++) {		
			
			//crio uma variavel para me ajuar a percorrer os vetores do metodo SPLIT
			
			int j=0;
			
			//a cada iteraçao no FOR vai ser criado um novo objeto desse vetor de VerticeCidade
			//o "i" vai ser o ID
			
			vetor[i] = new VerticeCidade(i);
			
			//leio a linha e faço a separação da string em substrings
			
			leitura = arquivoEntrada.lerBuffer();
			String[] intermediario = leitura.split(",");
			
			//aqui eu defino os valores dos atributos do objeto criado com os SET's e os dados separados do SPLIT
			
			vetor[i].setNome(intermediario[j]);
			vetor[i].setLatitude(vetor[i].paraDouble(intermediario[++j]));
			vetor[i].setLongitude(vetor[i].paraDouble(intermediario[++j]));
			
			
		}
		arquivoEntrada.fecharArquivo();	
}
}
