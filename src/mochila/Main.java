package mochila;

public class Main
{	
	public static float lambdaMin = 2;
	public static float lambdaMax = 2;
	public static float lambdaAvg = (lambdaMin + lambdaMax)/2.0f;
	
	public static float muMin = 2;
	public static float muMax = 2;
	public static float muAvg = (muMin + muMax)/2.0f;
	
	public float tempoChegadaMin = 1.0f/lambdaMin;
	public float tempoChegadaMax = 1.0f/lambdaMax;
	public float tempoChegadaAvg = (tempoChegadaMin + tempoChegadaMax)/2.0f;
	
	public static float tempoAtendimentoMin = 1.0f/muMin;
	public static float tempoAtendimentoMax = 1.0f/muMax;
	public float tempoAtendimentoAvg = (tempoAtendimentoMin + tempoAtendimentoMax)/2.0f;
	
	public static int capacidade = 5;
	public static int numServidores = 2;
	
	public static float tempoSimulacao = 1000;
	public static long seed = 1000;
	
	public static Simulador simulador;
	public static NascimentoEMorte nm;
	
	
	public static void main(String[] args)
	{
		
		// Criar uma fila
		Fila fila = new Fila(lambdaMin, lambdaMax, muMin, muMax, capacidade, numServidores);
		
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
		Util.writeResultsToFile(simulador, nm, "results.txt");
		
	}

}