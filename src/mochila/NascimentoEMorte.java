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
	
	public ArrayList<Float> getProbabilidades() { return probabilidade; }
	
	public void printMetricas()
	{
		System.out.println("Probabilidades marginais: " + getProbabilidades());
		System.out.println("Vazao media: " + getVazaoMedia());
		System.out.println("Utilizacao media: " + getUtilizacaoMedia());
		System.out.println("Populacao media: " + getPopulacaoMedia());
		System.out.println("Tempo medio de resposta: " + getTempoRespostaMedio());
	}
	
	public float getVazaoMedia()
	{
		float vazaoMedia = 0;
		float tempoMedioAtendimento = (fila.tempoMaxAtendimento + fila.tempoMinAtendimento) / 2;
		for (int i=0; i <= fila.capacidadeMax; i++) {
			vazaoMedia += probabilidade.get(i) * (Math.min(probabilidade.get(i), fila.numServidores) / tempoMedioAtendimento);
		}
		return vazaoMedia;
	}
	
	public float getUtilizacaoMedia()
	{
		float utilizacaoMedia = 0;
		for (int i=0; i <= fila.capacidadeMax; i++) {
			utilizacaoMedia += probabilidade.get(i) * (Math.min(probabilidade.get(i), fila.numServidores) / fila.numServidores);
		}
		return utilizacaoMedia;
	}
	
	public float getPopulacaoMedia()
	{
		float populacaoMedia = 0;
		for (int i=0; i <= fila.capacidadeMax; i++) {
			populacaoMedia += probabilidade.get(i) * i;
		}
		return populacaoMedia;
	}
	
	public float getTempoRespostaMedio()
	{
		return getPopulacaoMedia() / getVazaoMedia();
	}

}
