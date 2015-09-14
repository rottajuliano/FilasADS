package mochila;

import java.util.Comparator;

public class TimeComparator implements Comparator<Evento>
{

	public int compare(Evento e1, Evento e2)
	{
		if ((e2.tempo - e1.tempo) > 0) return -1;
		if ((e2.tempo - e1.tempo) == 0) return 0;
		return 1;
	}

}
