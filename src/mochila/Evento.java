package mochila;

public class Evento
{
	public TipoEvento tipo;
	public float tempo;
	
	public Evento(TipoEvento tipo, float tempo)
	{
		this.tipo = tipo;
		this.tempo = tempo;
	}
	
	public String toString()
	{
		return "Evento: " + tipo + " - " + tempo;
	}
}
