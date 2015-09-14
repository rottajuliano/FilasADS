package mochila;

import java.util.PriorityQueue;
import java.util.Random;

public class Escalonador {
	
	private Random rng;
	public PriorityQueue<Evento> queue;
	
	private Fila fila;
	private float tempoEventoAnterior;
	
	public Escalonador(Fila fila)
	{
		//this.rng = new Random(System.currentTimeMillis());
		this.rng = new Random(1000);
		this.queue = new PriorityQueue<Evento>(10, new TimeComparator());
		this.fila = fila;
		
		agendaChegada(0);
	}
	
	public void agendaChegada(float tempoEventoAnterior)
	{
		queue.add(new Evento(TipoEvento.CHEGADA, tempoEventoAnterior +
				getRandomFloat(fila.tempoMinChegada,fila.tempoMaxChegada)));
	}
	
	public void agendaSaida(float tempoEventoAnterior)
	{
		queue.add(new Evento(TipoEvento.SAIDA, tempoEventoAnterior + 
				getRandomFloat(fila.tempoMinAtendimento,fila.tempoMaxAtendimento)));
	}
	
	
	
	public float getRandomFloat(float min, float max)
	{
		return (max - min) * rng.nextFloat() + min;
	}

}
