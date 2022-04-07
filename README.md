# Aplicação de Grafos em um Contexto Real

## Descrição da Proposta do Trabalho

Neste trabalho prático, vamos colocar em prática os conhecimentos sobre grafos aprendidos
durante a disciplina. O projeto pode ser feito nas linguagens: c, c++ ou Java (qualquer outra
linguagem desejada, deve ser conversada com o professor). Tanto a escolha da linguagem quanto
as escolhas referentes ao código são de responsabilidades do grupo.
 - Etapa 1 - (3 pontos) O trabalho consiste em ler o arquivo disponibilizado no Canvas que
contém dados referentes às latitudes e longitudes de algumas cidades brasileiras (você pode
escolher ler: CSV ou JSON).
Após a leitura desses dados, o programa deve ser capaz de gerar um grafo não direcionado e
ponderado que represente esse contexto, considerando os vértices como as cidades brasileiras
(presentes no arquivo) e as arestas como as rotas entre essas cidades, cujo peso é a distância da
rota.
Informação Importante: para cada cidade criada (vértice), considere que ela possua
ligações (arestas) somente com as três cidades mais próximas a ela. Essa característica irá deixar o
nosso grafo um pouco fora do contexto real, porém será importante para testarmos diferentes
alternativas nos algoritmos que serão criados nas próximas etapas.
Como o arquivo contém latitudes e longitudes, fica como responsabilidade do grupo procurar
como encontrar distância entre diferentes latitudes e longitudes, bem como desenvolver o programa
responsável por encontrar essa distância e definir quais arestas serão criadas.
    - Entregáveis etapa 1: código para leitura do arquivo e criação do grafo.
 - Etapa 2 - (3 pontos) Para percorrer esse grafo e escrever os dados referentes a ele,
implemente os métodos de busca em largura ou busca em profundidade (escolha entre uma das
duas buscas).
Posteriormente, deve-se implementar métodos capazes de receber como entrada um vértice
de origem e um vértice intermediário e, a partir desses, determinar um ciclo (se possível) que
comece e termine no vértice de origem e passe pelo vértice intermediário. Caso não seja possível, o
programa deve informar que não existe ciclo conforme solicitado.
Pontifícia Universidade Católica de Minas Gerais
Instituto de Ciências Exatas e Informática
Teoria dos Grafos | Prof. Felipe Soares
   - Entregáveis etapa 2: Etapa 1 + código para busca em largura ou busca em
profundidade e definição do ciclo.
 - Etapa 3 - (9 pontos) Por fim, nessa última etapa, o grupo deve implementar um método que
recebe como entrada um vértice de origem e um vértice de destino e, a partir desses dados,
encontre o caminho mínimo para sair da origem e chegar ao destino.
Parte escrita - Deve ser entregue um artigo (usando LaTeX - utilizar “SBC Conferences
Template”) como resultado da realização deste trabalho prático (máximo 10 páginas). Anexo a esse
artigo deve ser entregue o projeto (códigos) do sistema desenvolvido (formato .zip). Esse artigo deve
conter a seguinte estrutura:
   - Título
   - Resumo
   - Introdução
   - Desenvolvimento
   - Testes e Resultados
   - Conclusão
   - Referências
  
O artigo deverá conter as principais decisões adotadas sobre a estrutura de dados, os
algoritmos utilizados para resolver o problema e exemplos de resultados obtidos. Caso se aproveite
ou se adapte algoritmos disponíveis gratuitamente, a fonte deverá ser citada nas referências
bibliográficas e o texto deverá explicitar as mudanças que foram necessárias.
   - Entregáveis etapa 3: Etapa 1 + Etapa 2 + código para caminho mínimo + artigo
documentando todo o trabalho realizado