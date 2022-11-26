package A10_DijkstraPQShortestPath;


import java.util.ArrayList;
import java.util.List;

public abstract class FindWay {
	protected Graph graph;
	protected int[] pred;
	
	public FindWay(Graph graph) {
		this.graph = graph;
		this.pred = new int[graph.numVertices()];
	}

	/**
	 * Liefert den Weg von (from) nach (to) als Liste zurück
	 * @param from Startknoten
	 * @param to Zielknoten
	 * @return Weg von Start nach Ziel oder null
	 */
	public List<Integer> findWay(int from, int to) {
		initPathSearch();
		if (!calculatePath(from, to)) {
			return null;
		}
		return createWay(from, to);
	}

	/**
	 * Initialisierungsfunktion
	 */
	abstract protected void initPathSearch();

	/**
	 * Berechnungsfunktion für Weg von (from) nach (to)
	 */
	abstract protected boolean calculatePath(int from, int to);
	
	/**
	 * Weg von (to) nach (from) aus Vorgängerknoten rekonstruieren
	 * @param from Startknoten
	 * @param to Zielknoten
	 * @return Weg als Liste
	 */

	//am einfachsten mit dem pred (Predecessor) variable
	protected ArrayList<Integer> createWay(int from, int to)
	{
		ArrayList<Integer> way = new ArrayList<Integer>();

		//Ausgangsknoten (ganz hinten)
		int currentVertex = to;

		//Solange nicht am Anfang...
		while(from != currentVertex)
		{
			//Dem Weg hinzufügen...der Vorgänger ist dann gleich der neue currentVertex...
			// Index = 0...das heißt immer am Anfang...damit ist der erste Knoten am Ende ist dann  0
			way.add(0 ,currentVertex);
			currentVertex = pred[currentVertex];
		}

		//Da wir das nicht mehr in der While-Schleife machen...müssen wir so das FROM element hinzufügen.
		way.add(0, from);


		return way;
	}
}
