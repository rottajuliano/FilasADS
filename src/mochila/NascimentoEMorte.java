package mochila;

import java.util.ArrayList;

public class NascimentoEMorte {
	
	private Fila fila;
	private ArrayList<Float> probabilidade;
	
	public NascimentoEMorte(Fila fila)
	{
		this.fila = fila;
		
		// Iniciando o vetor de probabilidades 
		probabilidade = new ArrayList<Float>(fila.capacidadeMax+1);
		for (int i=0; i <= fila.capacidadeMax; i++) probabilidade.add(0.0f);
	}
	
	public void calcular()
	{
		float lambda, mu, muI, p;
		float somaProbabilidades = 0;
		// Probabilidade do Estado 0 = 1
		probabilidade.set(0, 1.0f);
		for(int i=1; i<fila.capacidadeMax; i++)
		{
			lambda = (fila.tempoMinChegada + fila.tempoMaxChegada)/2.0f;
			mu = (fila.tempoMinAtendimento + fila.tempoMaxAtendimento)/2.0f;
			muI = mu * Math.min(i, fila.numServidores);
			p = probabilidade.get(i-1) * (lambda/muI);
			probabilidade.set(i, p);
		}
		
		// Soma das probabilidades
		for(int i=0; i<probabilidade.size(); i++)
		{
			somaProbabilidades += probabilidade.get(i);			
		}
		// Normalizacao
		for(int i=0; i<probabilidade.size(); i++)
		{
			probabilidade.set(i,probabilidade.get(i)/somaProbabilidades);			
		}
		
	}
	
	public void printProbabilidades()
	{
		for(int i=0; i<probabilidade.size(); i++)
		{
			System.out.println("P" + i + ": " + probabilidade.get(i));
		}
	}

}
