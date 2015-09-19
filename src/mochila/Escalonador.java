package mochila;

import java.util.PriorityQueue;
import java.util.Random;

public class Escalonador {
	
	// Gerador pseudoaleatorio
	private Random rng;
	
	// Fila de eventos
	public PriorityQueue<Evento> queue;
	
	// Informacao sobre a fila
	private Fila fila;
	
	public Escalonador(Fila fila) { this(fila, (long)1000); }
	public Escalonador(Fila fila, long seed)
	{
		this.rng = new Random(seed);
		this.queue = new PriorityQueue<Evento>(10, new TimeComparator());
		this.fila = fila;
		
		agendaChegada(0);
	}
	
	public void agendaChegada(float tempoEventoAnterior)
	{
		queue.add(new Evento(TipoEvento.CHEGADA, tempoEventoAnterior +
				getFloatRange(fila.tempoMinChegada,fila.tempoMaxChegada)));
	}
	
	public void agendaSaida(float tempoEventoAnterior)
	{
		queue.add(new Evento(TipoEvento.SAIDA, tempoEventoAnterior + 
				getFloatRange(fila.tempoMinAtendimento,fila.tempoMaxAtendimento)));
	}
	
	public float getFloatRange(float min, float max)
	{
		return (max - min) * rng.nextFloat() + min;
	}

}
