# Mochila

Primeiro trabalho da disciplina de Análise de Desempenho de Sistemas, cujo objetivo é comparar as métricas obtidas através da técnica de simulação de filas e a técnica de utilização de métodos analíticos. O método analítico escolhido foi o processo de nascimento e morte.

## Simulador M/M/C/K

Um simples simulador baseado em eventos para uma fila M/M/C/K. Produz um relatório com as seguintes métricas (para simulador e processo de nascimento e morte):

* Número de clientes perdidos (somente para simulador)
* Probabilidade marginal de cada estado
* Vazão média
* Utilização média
* População média
* Tempo médio de resposta

O simulador **não** utiliza uma unidade de tempo específica.

Também é possível gerar um arquivo com a tabela de eventos da simulação.

O programa solicita ao usuário a **taxa** de chegada (lambda) e a **taxa** de saída (mu). Como o simulador necessita do **tempo** de chegada e o **tempo** de saída, é realizada uma pequena conversão de frequência para período utilizando uma unidade de tempo arbitrária (1/lambda e 1/mu, respectivamente).

### Parâmetros de utilização

* **Taxa de chegada (lambda):** tempo mínimo e máximo para a chegada de um novo cliente.
* **Taxa de saída (mu):** tempo mínimo e máximo para o atendimento de um cliente.
* **Capacidade:** tamanho da fila.
* **Número de servidores:** número de servidores.
* **Tempo de simulação:** tempo total de simulação. Lembrando que o simulador não utiliza uma unidade de tempo específica.
* **Semente:** semente para o gerador de números pseudoaleatórios.
* **Gerar logs:** se marcada, gera um arquivo *"log.txt"* com a tabela de eventos da simulação.

## Processo de Nascimento e Morte

O programa também realiza o cálculo das métricas da fila através do processo de nascimento e morte. Os resultados através desta técnica estarão presentes no arquivo de relatório.