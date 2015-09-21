package mochila;

public class Main
{	
	public static float tempoChegadaMin = 2;
	public static float tempoChegadaMax = 2;
	public static float tempoChegadaAvg = (tempoChegadaMin + tempoChegadaMax)/2.0f;
	
	public static float tempoAtendimentoMin = 2;
	public static float tempoAtendimentoMax = 2;
	public static float tempoAtendimentoAvg = (tempoAtendimentoMin + tempoAtendimentoMax)/2.0f;
	
	public static float lambdaMin = 1/tempoChegadaMin;
	public static float lambdaMax = 1/tempoChegadaMax;
	public static float lambdaAvg = (lambdaMin + lambdaMax)/2.0f;
	
	public static float muMin = 1/tempoAtendimentoMin;
	public static float muMax = 1/tempoAtendimentoMax;
	public static float muAvg = (muMin + muMax)/2.0f;
	
	
	public static int capacidade = 5;
	public static int numServidores = 1;
	
	public static float tempoSimulacao = 10000;
	public static long seed = 1000;
	
	public static Simulador simulador;
	public static NascimentoEMorte nm;
	
	
	public static void main(String[] args)
	{
		
		// Criar uma fila
		Fila fila = new Fila(lambdaMin, lambdaMax, muMin, muMax, capacidade, numServidores);
		// Imprimir parametros da fila
		System.out.println(fila);
		
		// Criar um simulador
		simulador = new Simulador(fila, tempoSimulacao, seed);
		// Simular
		simulador.simular();
		// Imprimir os resultados
		System.out.println(simulador.getResults());
		
		// Criar um processo de nascimento e morte
		nm = new NascimentoEMorte(fila);
		// Calcular por processo de nascimento e morte
		nm.calcular();
		// Imprimir os resultados
		System.out.println(nm.getResults());
		
		// Escrever em um arquivo a tabela de eventos do simulador
		Util.writeStringToFile(simulador.log, "log.txt");
		// Escrever em um arquivo os resultados obtidos na simulacao e no processo de nascimento e morte
		Util.writeResultsToFile(fila, simulador, nm, "results.txt");
		
	}

}