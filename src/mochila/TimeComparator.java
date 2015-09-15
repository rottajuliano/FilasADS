package mochila;

import java.util.Comparator;

public class TimeComparator implements Comparator<Evento>
{
	public int compare(Evento e1, Evento e2)
	{
		return Float.compare(e1.tempo, e2.tempo);
	}

}
