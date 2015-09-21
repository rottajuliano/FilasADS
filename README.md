# Mochila

Primeiro trabalho da disciplina de An�lise de Desempenho de Sistemas, cujo objetivo � comparar as m�tricas obtidas atrav�s da t�cnica de simula��o de filas e a t�cnica de utiliza��o de m�todos anal�ticos. O m�todo anal�tico escolhido foi o processo de nascimento e morte.

## Simulador M/M/C/K

Um simples simulador baseado em eventos para uma fila M/M/C/K. Produz um relat�rio com as seguintes m�tricas (para simulador e processo de nascimento e morte):

* N�mero de clientes perdidos (somente para simulador)
* Probabilidade marginal de cada estado
* Vaz�o m�dia
* Utiliza��o m�dia
* Popula��o m�dia
* Tempo m�dio de resposta

O simulador **n�o** utiliza uma unidade de tempo espec�fica.

Tamb�m � poss�vel gerar um arquivo com a tabela de eventos da simula��o.

### Par�metros de utiliza��o

* **Tempo de chegada:** tempo m�nimo e m�ximo para a chegada de um novo cliente.
* **Tempo de sa�da:** tempo m�nimo e m�ximo para o atendimento de um cliente.
* **Capacidade:** tamanho da fila.
* **N�mero de servidores:** n�mero de servidores.
* **Tempo de simula��o:** tempo total de simula��o. Lembrando que o simulador n�o utiliza uma unidade de tempo espec�fica.
* **Semente:** semente para o gerador de n�meros pseudoaleat�rios.
* **Gerar logs:** se marcada, gera um arquivo *"log.txt"* com a tabela de eventos da simula��o.

## Processo de Nascimento e Morte

O programa tamb�m realiza o c�lculo das m�tricas da fila atrav�s do processo de nascimento e morte. Como o processo de nascimento e morte requer o uso de **taxas** e n�o **tempos**, fazemos uma simples convers�o entre os dois, baseados em uma unidade de tempo arbitr�ria (1/chegadaMedia e 1/atendimendoMedio).

Os resultados atrav�s desta t�cnica estar�o presentes no arquivo de relat�rio.