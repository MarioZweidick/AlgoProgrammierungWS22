package A10_DijkstraPQShortestPath;

import java.util.List;

public class DijkstraPQShortestPath extends FindWay {
	private int[] dist;

	public DijkstraPQShortestPath(Graph graph) {
		super(graph);
	}

	/**
	 * Startentfernung initialisieren
	 *
	 */
	protected void initPathSearch() {
		int numv = graph.numVertices();
		dist = new int[numv];
		for (int i = 0; i < numv; i++) {
			dist[i] = 9999; // Summen im Graph dürfen nie mehr ergeben
		}
	}

	/**
	 * Berechnet *alle* kürzesten Wege ausgehend vom Startknoten Setzt dist[]-
	 * und pred[]-Arrays, kein Rückgabewert
	 * 
	 * @param from
	 *            Startknoten
	 */
	protected boolean calculatePath(int from, int to) {

		// 1.Schritt --> eigene Distanz festlegen = 0!!
		dist[from] = 0;

		// 2.Schritt --> Heap aufbauen
		//Graph beinhaltet numVertices....diese Variable gibt mir die Anzahl der Elemente
		VertexHeap vertexHeap = new VertexHeap(graph.numVertices());
		for (int i = 0; i < graph.numVertices(); i++)
		{
			vertexHeap.insert(new Vertex(i, dist[i]));
		}

		while(!vertexHeap.isEmpty()) //Schleife ist dazu da, damit wir das innere so lange wiederholen bis der Heap wieder leer ist
			                         // ...wir nehmen ja immer Schritt für Schritt alles runter
		{
			//Hole mir den aktuellen Knoten. Im ersten Schritt = "FROM", da geringste dist.
			int currentVertex = vertexHeap.remove().vertex;

			//Alle Edges zum Knoten holen und in einer Liste von Edges speichern
			List<WeightedEdge> edgesFromCurrent = graph.getEdges(currentVertex);

			//Edges durchgehen
			for (WeightedEdge we : edgesFromCurrent)
			{
				//Kosten von aktuellem Knoten + den betrachteten Knoten. nextVertex gibt mir den nächsten Knoten
				int newCosts = dist[currentVertex] + we.weight;
				int nextVertex = we.to_vertex;

				//Wenn die Kosten geringer sind als die bisher eingetragenen, dann übeschreiben wir die.
				// Am Beginn wären das ja bei allen 9999...daher im ersten Durchlauf immer überschrieben
				if(newCosts < dist[nextVertex])
				{
					dist[nextVertex] = newCosts;
					pred[nextVertex] = currentVertex; //Vorgänger von "nextVertex" muss "currentVertex" sein...z.B. Vorgänger von Leibnitz = Graz
					vertexHeap.setCost(nextVertex, newCosts); //Im Heap auch die Distanz setzen
				}
			}
		}

		// Wenn wir nach dem Durchgehen des Heaps immer noch bei der Distanz von "TO" den Wert 9999 haben, dann gibt es keinen Weg!!!!
		// False wird retourniert.
		if(dist[to] == 9999)
			return false;

		return true;
	}
}
