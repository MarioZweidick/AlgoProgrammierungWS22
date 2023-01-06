package A01_Stack;


public class Stack<T>
{
	 private Node<T> first;
    /**
     * Oberstes Element entfernen und zur�ckliefern.
     * Existiert kein Element, wird eine Exception ausgel�st.
     * @throws StackEmptyException 
     */
    public T pop() throws StackEmptyException
    {
        if(first == null)
            throw new StackEmptyException();

        T removedItem = first.getData();
        first = first.getNext();

        return removedItem;
    }
    
    /**
     * �bergebenen T auf Stack (als oberstes Element) speichern.
     * @param i data
     */
    public void push(T i)
    {
       Node<T> newNode = new Node<>(i);
       newNode.setNext(first);
       first = newNode;

    }
    
    /**
     * Liefert die Anzahl der Elemente im Stack
     * @return
     */
    public int getCount()
    {
        Node<T> temp = first;
        int count = 0;

        while(temp != null)
        {
            count ++;
            temp = temp.getNext();
        }

        return count;

    }
}
