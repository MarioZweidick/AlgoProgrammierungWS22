package A06_Tiefensuche;

import java.util.ArrayList;
import java.util.List;

import A05_Breitensuche.BaseTree;
import A05_Breitensuche.Node;

public class Tiefensuche extends BaseTree<Film> {

	@Override
	/**
	 * Sortierkriterium im Baum: L�nge des Films
	 */
	protected int compare(Film a, Film b) {

		if(a.getL�nge() == b.getL�nge())
		{
			return a.getTitel().compareTo(b.getTitel());
		}
		else
			return Double.compare(a.getL�nge(), b.getL�nge());
	}

	/**
	 * Retourniert die Titelliste der Film-Knoten des Teilbaums in symmetrischer Folge (engl. in-order, d.h. links-Knoten-rechts)
	 * @param node Wurzelknoten des Teilbaums
	 * @return Liste der Titel in symmetrischer Reihenfolge
	 */
	public List<String> getNodesInOrder(Node<Film> node)
	{
		List<String> inOrder = new ArrayList<>();

		if(node == null)
			return inOrder;

		//Alle Linken
		inOrder.addAll(getNodesInOrder(node.getLeft()));

		//Node selbst
		inOrder.add(node.getValue().getTitel());

		//Alle rechten
		inOrder.addAll(getNodesInOrder(node.getRight()));

		return inOrder;

	}
	
	/**
	 * Retourniert Titelliste jener Filme, deren L�nge zwischen min und max liegt, in Hauptreihenfolge (engl. pre-order, d.h. Knoten-links-rechts)
	 * @param min Minimale L�nge des Spielfilms
	 * @param max Maximale L�nge des Spielfilms
	 * @return Liste der Filmtitel in Hauptreihenfolge
	 */
	public List<String> getMinMaxPreOrder(double min, double max) {

		return getMinMaxPreOrderInternal(getRoot(), min, max);
	}

	public List<String> getMinMaxPreOrderInternal(Node<Film> node,  double min, double max) {

		List<String> results = new ArrayList<>();

		if(node == null)
			return results;

		if(node.getValue().getL�nge() >= min && node.getValue().getL�nge() <= max)
			results.add(node.getValue().getTitel());

		//Links
		//OPTIMIERUNG VON SKOCHAUF: Ich schau nur links weiter, wenn l�nge noch gr��er als Min ist...dann kann links noch was sein
		if(node.getValue().getL�nge() >= min)
		   results.addAll(getMinMaxPreOrderInternal(node.getLeft(),min, max));


		//Rechts
		//OPTIMIERUNG VON SKOCHAUF: Ich schau nur rechts weiter, wenn l�nge noch kleiner als Max ist...dann kann rechts noch was sein
		if(node.getValue().getL�nge() <= max)
			results.addAll(getMinMaxPreOrderInternal(node.getRight(),min, max));

		return results;
	}

}
