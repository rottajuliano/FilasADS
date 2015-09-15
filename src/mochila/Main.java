package mochila;

import java.util.ArrayList;

public class Main
{
	
	public static int capacidadeMax = 5;
	public static int numServidores = 1;
	
	public static float tempoMinChegada = 2;
	public static float tempoMaxChegada = 3;
	
	public static float tempoMinAtendimento = 3;
	public static float tempoMaxAtendimento = 5;
	
	public static float tempoMaxSimulacao = 100;
	public static float tempoAtualSimulacao = 0;
	public static ArrayList<Integer> tempoNaFila = new ArrayList<Integer>(capacidadeMax+1);
	
	public static void main(String[] args)
	{
		//inicializa o array de pessoas na fila
		for (int i=0; i <= capacidadeMax; i++) {
			tempoNaFila.add(0);
		}
		
		Fila fila = new Fila(capacidadeMax, numServidores, tempoMinChegada,
				tempoMaxChegada, tempoMinAtendimento, tempoMaxAtendimento);
		Escalonador escalonador = new Escalonador(fila);
		
		String format = "%-8s %-12s %-10s\n";
		System.out.printf(format, "Evento", "Tempo Global", "Tempo com 0.." + fila.capacidadeMax + " pessoas na fila");
		while(tempoAtualSimulacao < tempoMaxSimulacao)
		{
			if(escalonador.queue.size() == 0)
			{
				System.out.println("Nao existem mais eventos a processar.");
				break;
			}
			
			Evento e = escalonador.queue.poll();
			tempoAtualSimulacao = e.tempo;
			System.out.printf(format, e.tipo, tempoAtualSimulacao, tempoNaFila);
			if(e.tipo == TipoEvento.CHEGADA)
			{
				if(fila.numClientes < fila.capacidadeMax)
				{
					fila.numClientes++;
					if(fila.numClientes <= fila.numServidores)
					{
						escalonador.agendaSaida(e.tempo);
					}
					escalonador.agendaChegada(e.tempo);
				}
			}
			else
			{
				fila.numClientes--;
				if(fila.numClientes >= fila.numServidores)
				{
					escalonador.agendaSaida(e.tempo);
				}
			}
		}
		System.out.println("Fim da simulacao.");
		
		
	}

}
