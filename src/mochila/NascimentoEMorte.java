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
		// Ou seja, o estado vazio + o numero de servidores + o tamanho da fila
		int numStates = 1 + fila.numServidores + fila.capacidade;
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
		
		System.out.println(fila.lambdaAvg + " * " + probabilidades.get(probabilidades.size()-1));
		System.out.println("Clientes perdidos = " + fila.lambdaAvg * probabilidades.get(probabilidades.size()-1));
		
	}
	
	public String getResults()
	{
		String results = "====================\n";
		results += "RESULTADOS NASCIMENTO E MORTE:\n";
		results += "Clientes perdidos: " + fila.lambdaAvg * probabilidades.get(probabilidades.size()-1) + "por u.t\n";
		results += "Probabilidades marginais: " + probabilidades + "\n";
		results += "Vazao media: " + Util.getVazaoMedia(probabilidades, fila) + "\n";
		results += "Utilizacao media: " + Util.getUtilizacaoMedia(probabilidades, fila) + "\n";
		results += "Populacao media: " + Util.getPopulacaoMedia(probabilidades, fila) + "\n";
		results += "Tempo medio de resposta: " + Util.getTempoRespostaMedio(probabilidades, fila) + "\n";
		results += "====================\n";
		return results;
	}
	
}
