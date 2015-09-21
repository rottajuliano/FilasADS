package mochila;

import java.util.ArrayList;

public class Simulador {
	
	private Escalonador escalonador;
	
	public Fila fila;
	public ArrayList<Float> tempoNaFila;
	public ArrayList<Float> probabilidadesMarginais;
	
	private float tempoMaxSimulacao;
	private float tempoAtualSimulacao;
	
	public int clientesPerdidos;
	
	public String log;
	private String logFormat = "%-8s %-10s %-12s %-10s\n";
	
	
	public Simulador(Fila fila, float tempoSimulacao, long seed)
	{
		this.fila = fila;
		this.tempoMaxSimulacao = tempoSimulacao;
		this.escalonador = new Escalonador(this.fila, seed);
		
		this.log = "";
		
		// Inicializando o vetor de tempos
		tempoNaFila = new ArrayList<Float>(fila.capacidade+1);
		for (int i=0; i <= fila.capacidade; i++) tempoNaFila.add(0.0f);
		
		// Iniciando o vetor de probabilidades marginais
		probabilidadesMarginais = new ArrayList<Float>(fila.capacidade+1);
		for (int i=0; i <= fila.capacidade; i++) probabilidadesMarginais.add(0.0f);
	}
	
	public void simular()
	{
		// Cabecalho do log
		log += String.format(logFormat, "Evento", "# na fila",  "Tempo Global", "Tempo com 0.." + fila.capacidade + " pessoas na fila");
		
		// Enquanto nao atingirmos o tempo solicitado de simulacao
		while(tempoAtualSimulacao < tempoMaxSimulacao)
		{
			// Salvar o tempo atual da simulacao
			float tempoNoEventoAnterior = tempoAtualSimulacao;
			// Salvar o numero de clientes atual da fila
			int clientesNoEventoAnterior = fila.numClientes;
			
			// Avancar no tempo ate o evento mais proximo
			Evento e = escalonador.queue.poll();
			// Atualizar o tempo da simulacao
			tempoAtualSimulacao = e.tempo;
			// Atualizar o vetor de tempos
			tempoNaFila.set(clientesNoEventoAnterior, tempoNaFila.get(clientesNoEventoAnterior) + (tempoAtualSimulacao - tempoNoEventoAnterior));
			
			// Evento do tipo CHEGADA
			if(e.tipo == TipoEvento.CHEGADA)
			{
				// Caso a fila ainda nao esteja cheia
				if(fila.numClientes < fila.capacidade)
				{
					// Adicionar um cliente na fila
					fila.numClientes++;
					// Se ainda existirem servidores livres
					if(fila.numClientes <= fila.numServidores)
					{
						// Agendar uma saida
						// (ou seja, cliente vai ser atendido agora)
						escalonador.agendaSaida(e.tempo);
					}
				}
				// A fila esta cheia
				else
				{
					// Atualizar o numero de clientes perdidos
					clientesPerdidos++;
					log += "Chegou com fila cheia.\n";
				}
				// Agendar uma chegada nova
				escalonador.agendaChegada(e.tempo);
			}
			// Evento do tipo SAIDA
			else
			{
				// Retirar um cliente da fila
				fila.numClientes--;
				// Caso existam mais clientes do que servidores
				if(fila.numClientes >= fila.numServidores)
				{
					// Agendar uma nova saida
					escalonador.agendaSaida(e.tempo);
				}
			}
			// Adicionar o evento ao log
			log += String.format(logFormat, e.tipo, fila.numClientes, tempoAtualSimulacao, tempoNaFila);
		}
		// Atualizar o vetor de probabilidades marginais
		for (int i=0; i <= fila.capacidade; i++) {
			probabilidadesMarginais.set(i, tempoNaFila.get(i)/tempoAtualSimulacao);
		}
		
		// Fim da simulacao
		log += "Fim da simulacao.\n";
	}
	
	public String getResults()
	{
		String results = "====================\n";
		results += "RESULTADOS SIMULADOR:\n";
		results += "Clientes perdidos: " + clientesPerdidos + "\n";
		results += "Probabilidades marginais: " + probabilidadesMarginais + "\n";
		results += "Vazao media (cli/u.t): " + Util.getVazaoMedia(probabilidadesMarginais, fila) + "\n";
		results += "Utilizacao media (%): " + Util.getUtilizacaoMedia(probabilidadesMarginais, fila) + "\n";
		results += "Populacao media (cli): " + Util.getPopulacaoMedia(probabilidadesMarginais, fila) + "\n";
		results += "Tempo medio de resposta (u.t): " + Util.getTempoRespostaMedio(probabilidadesMarginais, fila) + "\n";
		results += "====================\n";
		return results;
	}
	
}
