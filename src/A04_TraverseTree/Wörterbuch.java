package A04_TraverseTree;

import A01_Stack.Stack;
import A01_Stack.StackEmptyException;

import java.util.HashSet;
import java.util.Set;


public class W�rterbuch {

	/**
	 * Wurzel des Baums (Startknoten)
	 */
	private Wort root;
	
	public Wort getRoot() {
		return root;
	}

	/**
	 * Z�hlt alle W�rter des Teilbaums ab einem bestimmten Wort
	 * @param w Wort
	 * @return Zahl der W�rter (=Anzahl der Elemente)
	 */
	public int countWordsInSubTree(Wort w) {

		if(w == null)
			return 0;

		int countLeft = w.getLeft() != null ? countWordsInSubTree(w.getLeft()) : 0;
		int countRight = w.getRight() != null ? countWordsInSubTree(w.getRight()) : 0;

		return countLeft + countRight +1; // +1, weil ich selbst auch dabei bin
	}

	/**
	 * Liefert die Menge aller W�rter retour, die ein spezifisches Pr�fix haben.
	 * @param prefix W�rter m�ssen diesen Pr�fix haben
	 * @return Menge aller zutreffenden W�rter
	 */
	public Set<String> getWordsWithPrefix(String prefix)
	{
		try
		{
			return getWordWithPrefixInterative(prefix);
		}
		catch (StackEmptyException e)
		{
			throw new RuntimeException();
		}
		//return getWordsWithPrefixInternal(getRoot(), prefix);

	}

	public Set<String> getWordWithPrefixInterative(String prefix) throws StackEmptyException
	{
		Stack<Wort> stack = new Stack<>();
		Set<String> result = new HashSet<>();

		stack.push(root);

		while(stack.getCount() > 0)
		{
			Wort w = stack.pop();

			//Pre-Order Implementierung, weil wir zuerst alle linken Knoten abarbeiten!
			if(w.getRight() != null)
				stack.push(w.getRight());
			if(w.getLeft() != null)
				stack.push(w.getLeft());

			//Post-Order Implementierung, da STack --> Zuerst schauen wir uns dann den rechten an...erst dann den linken, weil der rechte oberhalb am Stack liegt.
			/*
			if(w.getLeft() != null)
				stack.push(w.getLeft());
			if(w.getRight() != null)
				stack.push(w.getRight()); */
			if(w.getWort().startsWith(prefix))
				result.add(w.getWort());
		}

		return result;
	}

	private Set<String> getWordsWithPrefixInternal(Wort w, String prefix)
	{
		Set<String> result = new HashSet<>();

		if(w == null)
			return result;

		if(w.getWort().startsWith(prefix))
			result.add(w.getWort());

		//links
		result.addAll(getWordsWithPrefixInternal(w.getLeft(), prefix));

		//rechts
		result.addAll(getWordsWithPrefixInternal(w.getRight(), prefix));

		return result;
	}

	/**
	 * Neues Wort hinzuf�gen
	 * @param wort Hinzuzuf�gendes Wort
	 */
	public void add(String wort) {
		Wort neu = new Wort(wort);
		if (root == null) {			// Fall 1: Baum ist leer
			root = neu;
			return;
		}
		Wort w = root;				// Fall 2: Baum ist nicht leer
		while (true) {
			int vgl = wort.compareTo(w.getWort());
			if (vgl < 0) {			// Neues Wort ist lexikographisch kleiner
				if (w.getLeft() == null) {
					w.setLeft(neu);
					neu.setParent(w);
					return;
				}
				w = w.getLeft();
			}
			else if (vgl > 0) {		// Neues Wort ist lexikographisch gr��er
				if (w.getRight() == null) {
					w.setRight(neu);
					neu.setParent(w);
					return;
				}
				w = w.getRight();
			}
			else {					// Neues Wort ist lexikographisch gleich
				return;
			}
		}
	}

	public Wort find(String s) {
		return find(root, s);
	}
	
	private Wort find(Wort current, String s) {
		if (current == null) {
			return null;
		}
		int vgl = s.compareTo(current.getWort());
		if (vgl == 0) {		// Gefunden
			return current;
		}
		else if (vgl < 0) {	// Links
			return find(current.getLeft(), s);
		}
		else {				// Rechts
			return find(current.getRight(), s);
		}
	}
	
}
