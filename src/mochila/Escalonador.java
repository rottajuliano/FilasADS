package mochila;

import java.util.PriorityQueue;
import java.util.Random;

public class Escalonador {
	
	private Random rng;
	public PriorityQueue<Evento> queue;
	
	private Fila fila;
	
	public Escalonador(Fila fila, long seed)
	{
		this.rng = new Random(seed);
		this.queue = new PriorityQueue<Evento>(10, new TimeComparator());
		this.fila = fila;
		
		agendaChegada(0);
	}
	
	public Escalonador(Fila fila)
	{
		this(fila, (long)1000);
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
