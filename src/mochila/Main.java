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
	public static ArrayList<Integer> tempoNaFila = new ArrayList<Integer>();
	
	public static void main(String[] args)
	{
		Fila fila = new Fila(capacidadeMax, numServidores, tempoMinChegada,
				tempoMaxChegada, tempoMinAtendimento, tempoMaxAtendimento);
		Escalonador escalonador = new Escalonador(fila);
		
		while(tempoAtualSimulacao < tempoMaxSimulacao)
		{
			if(escalonador.queue.size() == 0)
			{
				System.out.println("Nao existem mais eventos a processar.");
				break;
			}
			
			Evento e = escalonador.queue.poll();
			tempoAtualSimulacao = e.tempo;
			System.out.println(e + " - tempo atual: " + tempoAtualSimulacao);
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
