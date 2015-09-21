package mochila;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Util {
	
	// Escreve em um arquivo uma string
	public static void writeStringToFile(String s, String fileName)
	{
		try {
			PrintWriter out = new PrintWriter(fileName);
			out.println(s);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// Escreve em um arquivo as estatisticas obtidas
	// atraves de um simulador e um processo de nascimento e morte	
	public static void writeResultsToFile(Simulador simulador, NascimentoEMorte nm, String fileName)
	{
		String results = simulador.getResults();
		results += "\n";
		results += nm.getResults();
		
		writeStringToFile(results, fileName);
	}
	
	public static float getVazaoMedia(ArrayList<Float> probabilidades, Fila fila)
	{
		float vazaoMedia = 0;
		for (int i=0; i <= fila.capacidade; i++) {
			vazaoMedia += probabilidades.get(i) * (Math.min(probabilidades.get(i), fila.numServidores) / fila.tempoAtendimentoAvg);
		}
		return vazaoMedia;
	}
	
	public static float getUtilizacaoMedia(ArrayList<Float> probabilidades, Fila fila)
	{
		float utilizacaoMedia = 0;
		for (int i=0; i <= fila.capacidade; i++) {
			utilizacaoMedia += probabilidades.get(i) * (Math.min(probabilidades.get(i), fila.numServidores) / fila.numServidores);
		}
		return utilizacaoMedia;
	}
	
	public static float getPopulacaoMedia(ArrayList<Float> probabilidades, Fila fila)
	{
		float populacaoMedia = 0;
		for (int i=0; i <= fila.capacidade; i++) {
			populacaoMedia += probabilidades.get(i) * i;
		}
		return populacaoMedia;
	}
	
	public static float getTempoRespostaMedio(ArrayList<Float> probabilidades, Fila fila)
	{
		return getPopulacaoMedia(probabilidades, fila) / getVazaoMedia(probabilidades, fila);
	}

}
