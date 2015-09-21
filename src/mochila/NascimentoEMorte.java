package mochila;

import java.util.ArrayList;

public class NascimentoEMorte {
	
	public Fila fila;
	public ArrayList<Float> probabilidades;
	
	public NascimentoEMorte(Fila fila)
	{
		this.fila = fila;
		
		// Iniciando o vetor de probabilidades 
		probabilidades = new ArrayList<Float>(fila.capacidade+1);
		for (int i=0; i <= fila.capacidade; i++) probabilidades.add(0.0f);
	}
	
	public void calcular()
	{
		float muI, p;
		float somaProbabilidades = 0;
		// Probabilidade do Estado 0 = 1
		probabilidades.set(0, 1.0f);
		for(int i=1; i<fila.capacidade; i++)
		{
			muI = fila.muAvg * Math.min(i, fila.numServidores);
			p = probabilidades.get(i-1) * (fila.lambdaAvg/muI);
			probabilidades.set(i, p);
		}
		
		// Soma das probabilidades
		for(int i=0; i<probabilidades.size(); i++)
		{
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
