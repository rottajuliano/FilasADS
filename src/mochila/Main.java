package mochila;

public class Main
{	
	public static int capacidadeMax = 5;
	public static int numServidores = 2;
	
	public static float tempoMinChegada = 2;
	public static float tempoMaxChegada = 3;
	
	public static float tempoMinAtendimento = 3;
	public static float tempoMaxAtendimento = 5;
	
	private static float tempoSimulacao = 1000;
	private static long seed = 1000;
	
	public static void main(String[] args)
	{
		Fila fila = new Fila(capacidadeMax, numServidores, tempoMinChegada,
				tempoMaxChegada, tempoMinAtendimento, tempoMaxAtendimento);
		
		Simulador simulador = new Simulador(fila, tempoSimulacao, seed);
		simulador.simular();
		
		System.out.println("Clientes perdidos: " + simulador.getNumClientesPerdidos());
		System.out.println("Probabilidades marginais: " + simulador.getProbabilidadesMarginais());
		System.out.println("Vazao media: " + simulador.getVazaoMedia());
		System.out.println("Utilizacao media: " + simulador.getUtilizacaoMedia());
		System.out.println("Populacao media: " + simulador.getPopulacaoMedia());
		System.out.println("Tempo medio de resposta: " + simulador.getTempoRespostaMedio());
		
	}

}