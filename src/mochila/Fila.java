package mochila;

public class Fila
{
	
	public int capacidadeMax;
	public int numServidores;
	
	public float tempoMinChegada;
	public float tempoMaxChegada;
	
	public float tempoMinAtendimento;
	public float tempoMaxAtendimento;
	
	public int numClientes;
	
	public Fila(int capacidadeMax, int numServidores, float tempoMinChegada,
			float tempoMaxChegada, float tempoMinAtendimento,
			float tempoMaxAtendimento) {
		this.capacidadeMax = capacidadeMax;
		this.numServidores = numServidores;
		this.tempoMinChegada = tempoMinChegada;
		this.tempoMaxChegada = tempoMaxChegada;
		this.tempoMinAtendimento = tempoMinAtendimento;
		this.tempoMaxAtendimento = tempoMaxAtendimento;
		
		this.numClientes = 0;
	}

}
