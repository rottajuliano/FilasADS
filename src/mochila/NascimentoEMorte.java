package mochila;

import java.util.ArrayList;

public class NascimentoEMorte {
	
	public Fila fila;
	public ArrayList<Float> probabilidades;
	
	public NascimentoEMorte(Fila fila)
	{
		this.fila = fila;
		
		// O numero de estados no processo de nascimento e morte
		// é igual ao numero de clientes no sistema, incluindo os em serviço
		// Ou seja, o estado vazio + o tamanho da fila + o numero de servidores
		int numStates = 1 + fila.capacidade + fila.numServidores;
		System.out.println(numStates);
		
		// Iniciando o vetor de probabilidades
		probabilidades = new ArrayList<Float>();
		for (int i=0; i < numStates; i++) probabilidades.add(0.0f);
		// Peso do Estado 0 => 1
		probabilidades.set(0, 1.0f);
	}
	
	public void calcular()
	{
		float muI, p;
		float somaProbabilidades = probabilidades.get(0);
		for(int i=1; i<probabilidades.size(); i++)
		{
			muI = fila.muAvg * Math.min(i, fila.numServidores);
			p = probabilidades.get(i-1) * (fila.lambdaAvg/muI);
			probabilidades.set(i, p);
			
			// Soma das probabilidades
			somaProbabilidades += probabilidades.get(i);
			
		}
		// Normalizacao
		for(int i=0; i<probabilidades.size(); i++)
		{
			probabilidades.set(i,probabilidades.get(i)/somaProbabilidades);
		}
		
	}
	
	public String getResults()
	{
		String results = "====================\n";
		results += "RESULTADOS NASCIMENTO E MORTE:\n";
		results += "Probabilidades marginais: " + probabilidades + "\n";
		results += "Vazao media: " + Util.getVazaoMedia(probabilidades, fila) + "\n";
		results += "Utilizacao media: " + Util.getUtilizacaoMedia(probabilidades, fila) + "\n";
		results += "Populacao media: " + Util.getPopulacaoMedia(probabilidades, fila) + "\n";
		results += "Tempo medio de resposta: " + Util.getTempoRespostaMedio(probabilidades, fila) + "\n";
		results += "====================\n";
		return results;
	}
	
}
