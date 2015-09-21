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
	public static void writeResultsToFile(Fila fila, Simulador simulador, NascimentoEMorte nm, String fileName)
	{
		String results = "";
		results += fila;
		results += "\n";
		results += simulador.getResults();
		results += "\n";
		results += nm.getResults();
		
		writeStringToFile(results, fileName);
	}
	
	public static float getVazaoMedia(ArrayList<Float> probabilidades, Fila fila)
	{
		float res = 0;
		for(Float p : probabilidades)
		{
			res += p * (Math.min(p, fila.numServidores)/fila.tempoAtendimentoAvg);
		}
		return res;
	}
	
	public static float getUtilizacaoMedia(ArrayList<Float> probabilidades, Fila fila)
	{
		float res = 0;
		for(Float p : probabilidades)
		{
			res += p * (Math.min(p, fila.numServidores)/fila.numServidores);
		}
		return res;
	}
	
	public static float getPopulacaoMedia(ArrayList<Float> probabilidades, Fila fila)
	{
		float res = 0;
		for (int i=0; i < probabilidades.size(); i++) {
			res += probabilidades.get(i) * i;
		}
		return res;
	}
	
	public static float getTempoRespostaMedio(ArrayList<Float> probabilidades, Fila fila)
	{
		return getPopulacaoMedia(probabilidades,fila)/getVazaoMedia(probabilidades,fila);
	}

}
