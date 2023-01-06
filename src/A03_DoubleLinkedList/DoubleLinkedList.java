package A03_DoubleLinkedList;

public class DoubleLinkedList<T>
{
    private Node<T> first;

    private Node<T> last;

    private Node<T> current;
    /**
     * Einfügen einer neuen <T>
     * @param a <T>
     */
    public void add(T a) {

        Node<T> newNode = new Node<>(a);

        if(last == null)
        {
            first = newNode;
            last = newNode;
        }
        else
        {
            last.setNext(newNode);
            newNode.setPrevious(last);
            last = newNode;
        }
    }

    /**
     * Internen Zeiger für next() zurücksetzen
     */
    public void reset()
    {
        current = first;
    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast()
    {
        current = last;
    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getFirst() {
        return first;
    }
    
    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getLast() {
    	
    	return last;
    }
    
    /**
     * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     * @return <T>|null
     */
    public T next() {

        if(current == null)
            return null;

        T returnData = current.getData();
        current = current.getNext();
        return returnData;
    }

    /**
     * analog zur Funktion next()
     * @return <T>|null
     */
    public T previous() {

    	if(current == null)
            return null;

        T returnedData = current.getData();
        current = current.getPrevious();
        return returnedData;

    }
    
    /**
     * Current-Pointer auf nächste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext()
    {
        if(current != null)
          current = current.getNext();
    }
    
    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious()
    {
        if(current != null)
            current = current.getPrevious();
    }
   
    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException {

        if(current != null)
            return current.getData();
        else
            throw new CurrentNotSetException();
    }

    /**
     * Gibt <T> an bestimmter Position zurück
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    public T get(int pos) {

        Node<T> node = first;
        int i = 1;
        while(i != pos && node != null)
        {
            i++;
            node = node.getNext();
        }
        return (node != null ? node.getData() : null);
    }

    /**
     * Entfernen des Elements an der angegebenen Position.
     * Falls das entfernte Element das aktuelle Element ist, wird current auf null gesetzt.
     * @param pos
     */
    public void remove(int pos)
    {
        Node<T> node = first;
        int i = 1;

        while(i != pos && node != null)
        {
            i++;
            node = node.getNext();
        }

        if(node == null)
            return;

        removeNode(node);

        if(node == current)
            current = null;
    }
    
    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element 
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException
    {
        if(current == null)
            throw new CurrentNotSetException();

        removeNode(current);

        if(current.getNext() != null)
            current = current.getNext();
        else
            current = current.getPrevious();
    }

    public void removeNode(Node n)
    {
        if(n == first)
        {
            first = n.getNext();
        if(first != null)
            first.setPrevious(null);
        }

        else if(n == last)
        {
            last = n.getPrevious();
            if(last != null)
                last.setNext(null);

        }
        else
        {
            n.getPrevious().setNext(n.getNext());
            n.getNext().setPrevious(n.getPrevious());
        }
    }
    
    /**
     * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
     * @throws CurrentNotSetException 
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException
    {
        Node<T> newNode = new Node<>(a);

        if(current == null)
            throw new CurrentNotSetException();

        else if(current == last)
        {
            last.setNext(newNode);
            newNode.setPrevious(last);
            last = newNode;
        }
        else
        {
        //Handling für neue Node
        newNode.setNext(current.getNext());
        newNode.setPrevious(current);

        //Handling für "alte" current
        current.getNext().setPrevious(newNode);
        current.setNext(newNode);
        }

        current = newNode;

    }
}
