package mochila;

public class Fila
{
	
	public float lambdaMin;
	public float lambdaMax;
	public float lambdaAvg;
	
	public float muMin;
	public float muMax;
	public float muAvg;
	
	public float tempoChegadaMin;
	public float tempoChegadaMax;
	public float tempoChegadaAvg;
	
	public float tempoAtendimentoMin;
	public float tempoAtendimentoMax;
	public float tempoAtendimentoAvg;
	
	public int capacidade;
	public int numServidores;
	
	
	// Numero de clientes em um determinado momento
	public int numClientes;
	
	
	public Fila(float lambdaMin, float lambdaMax, float muMin, float muMax, int capacidade, int numServidores) {
		this.lambdaMin = lambdaMin;
		this.lambdaMax = lambdaMax;
		this.lambdaAvg = (lambdaMin + lambdaMax)/2.0f;
		
		this.muMin = muMin;
		this.muMax = muMax;
		this.muAvg = (muMin + muMax)/2.0f;
		
		this.tempoChegadaMin = 1/lambdaMin;
		this.tempoChegadaMax = 1/lambdaMax;
		this.tempoChegadaAvg = 1/lambdaAvg;
		
		this.tempoAtendimentoMin = 1/muMin;
		this.tempoAtendimentoMax = 1/muMax;
		this.tempoAtendimentoAvg = 1/muAvg;
		
		this.capacidade = capacidade;
		this.numServidores = numServidores;
		
		
		this.numClientes = 0;
	}
	
	public String toString()
	{
		String results = "====================\n";
		results += "PARAMETROS DA FILA:\n";
		results += "Lambda Mínimo: " + lambdaMin + "\n";
		results += "Lambda Máximo: " + lambdaMax + "\n";
		results += "Lambda Médio: " + lambdaAvg + "\n\n";
		
		results += "Mu Mínimo: " + muMin + "\n";
		results += "Mu Máximo: " + muMax + "\n";
		results += "Mu Médio: " + muAvg + "\n\n";
		
		results += "Tempo de Chegada Mínimo: " + tempoChegadaMin + "\n";
		results += "Tempo de Chegada Máximo: " + tempoChegadaMax + "\n";
		results += "Tempo de Chegada Médio: " + tempoChegadaAvg + "\n\n";
		
		results += "Tempo de Atendimento Mínimo: " + tempoAtendimentoMin + "\n";
		results += "Tempo de Atendimento Máximo: " + tempoAtendimentoMax + "\n";
		results += "Tempo de Atendimento Médio: " + tempoAtendimentoAvg + "\n\n";
		
		results += "Capacidade: " + capacidade + "\n";
		results += "Número de servidores: " + numServidores + "\n";
		results += "====================\n";
		return results;
	}

}
