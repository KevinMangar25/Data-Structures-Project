class ElementNotFoundException extends RuntimeException {
	public ElementNotFoundException(String msg) {
		super(msg);
	}
}

class EmptyListException extends RuntimeException {
	public EmptyListException(String msg) {
		super(msg);
	}
}

/**
 * ListADT.java       
 * Defines the interface to a general list collection. Specific
 * types of lists will extend this interface to complete the
 * set of necessary operations.
 * 
 */
interface ListADT<E>
{
	/**  Removes and returns the first item from this list. */
	public E removeFirst ();

	/**  Removes and returns the last item from this list. */
	public E removeLast ();

	/**  Removes and returns the specified item from this list. */
	public E remove (E item);

	/**  Returns a reference to the first item in this list. */
	public E first ();

	/**  Returns a reference to the last item in this list. */
	public E last ();

	/**  Returns true if this list contains the specified target element. */
	public boolean contains (E target);

	/**  Returns true if this list is empty. */
	public boolean isEmpty();

	/**  Returns the number of items in this list. */
	public int size();

	/**  Returns a string representation of this list. */
	public String toString();
}


/**
 * <p>Title: LinkedList.java</p>
 *
 * <p>Description: Represents a linked implementation of a list. The front of
 * the list is referenced by first and the back of the list is referenced by last. 
 * This class will be extended to create a specific kind of list.</p>
 *
 * @author <Kevin Mangar and Sahil Khan>
 */
public class LinkedList<E> implements ListADT<E>
{
	protected int count;
	protected Node<E> first, last;

	/**
	 * default constructor --
	 * Creates an empty list.
	 */
	public LinkedList()
	{
		count = 0;
		first = last = null;
	}
	
	/**
	 * size --
	 * returns a count of the number of items in the list.
	 * @return the number of items currently in the list
	 */
	public int size()
	{
		return count;
	}
	
	/**
	 * isEmpty --
	 * determines whether or not the list is empty.
	 * @return true if this list is empty; false otherwise
	 */
	public boolean isEmpty()
	{
		return count == 0;
	}
	
	/**
	 * first --
	 * returns a reference to the item at the front of the list. The item
	 * is not removed from the list.
	 * @return a reference to the first item in the list
	 * @throws EmptyListException if the list is empty
	 */
	public E first()
	{
		if (isEmpty())
			throw new EmptyListException("List is empty!");

		return first.getItem();
	}
	
	/**
	 * last -- 
	 * returns a reference to the item at the end of the list. The item
	 * is not removed from the list.
	 * @return a reference to the last item in the list
	 * @throws EmptyListException if the list is empty
	 */
	public E last()
	{
		if (isEmpty())
			throw new EmptyListException("List is empty!");

		return last.getItem();
	}
	
	/**
	 * contains --
	 * returns true if the list contains the specified target.
	 * @param target a reference to the item to be located
	 * @return true if the target is found; false otherwise
	 */
	public boolean contains (E target)
	{
		if (isEmpty())
			throw new EmptyListException("List is empty!");

		boolean found = false;

		Node<E> current = first;

		while (current != null && !found)
		{
			if (target.equals(current.getItem()))
				found = true;
			else
				current = current.getNext();
		}

		return found;
	}

	/**
	 * removeFirst -- 
	 * removes and returns the first item in the list.
	 * @return a reference to what was the first item in the list
	 * @throws EmptyListException if the list is empty
	 */
	public E removeFirst()
	{
		Node<E> current = first;
		if(isEmpty())
			throw new EmptyListException("List is empty!");
		
		if(first == last) 			
			first = last = null;
		
		first = first.getNext();
		count--;
		return current.getItem();
	}
	
	/**
	 * removeLast -- 
	 * removes and returns the last item in the list.
	 * @return the item removed from the end of the list
	 * @throws EmptyListException if the list is empty
	 */
	public E removeLast()
	{
		if (isEmpty())
			throw new EmptyListException("List is empty!");

		Node<E> temp = first;
		Node<E> current = first;  
		Node<E> prev = null;		

		while (current.getNext() != null) 
		{
			prev = current;
			current = current.getNext();
		} 
		prev.setNext(null); 
		last = prev;

		count--;
		return temp.getItem();
	}

	/**
	 * remove --
	 * locates and removes the target from the list.
	 * @param target a reference to an initialized item containing data 
	 * in its key-field
	 * @return a reference to the removed item
	 * @throws EmptyListException if the list is empty
	 * @throws ElementNotFoundException if the target is not found
	 * Fix: Added functionality to traverse the list to find the target item
	 */
	public E remove(E item)
	{
	    if (isEmpty())
	        throw new EmptyListException("List is empty!");

	    if (item.equals(first.getItem())) {
	        E removedItem = first.getItem();
	        first = first.getNext();
	        count--;
	        return removedItem;
	    }

	    Node<E> current = first;
	    Node<E> previous = null;
	    boolean found = false;
	    
	    
	    while (current != null && !found) {
	        if (item.equals(current.getItem()))
	            found = true;
	        else {
	            previous = current;
	            current = current.getNext();
	        }
	    }

	    if (!found)
	        throw new ElementNotFoundException("Item not found in the list!");

	    previous.setNext(current.getNext());
	    E removedItem = current.getItem();
	    count--;
	    return removedItem;
	}

	/*
	 * add -
	 * adds a node to a list, if there is no nodes in a list makes it the first node
	 * if there is a node changed and shifts the last node
	 * @param target a reference to an initialized item containing data 
	 */
	public void add(E item) {
        Node<E> newNode = new Node<>(item);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
        count++;
    }
	
	public void addAll(LinkedList<E> sourceList) {
	    // Start from the head of the source list
	    Node<E> current = sourceList.first;

	    // Traverse the source list and add each element to the destination list (this list)
	    while (current != null) {
	        this.add(current.getItem()); // Add each item from source to the end of this list
	        current = current.getNext(); // Move to the next node in the source list
	    }
	}

	
	
	 
	/**
	 * toString --
	 * returns a string representation of the list.
	 * @return a reference to a String containing the items in the list
	 */
	public String toString()
	{
		Node<E> current = first;
		String result = new String();

		while (current != null)
		{
			result = result + current.getItem().toString() + "\n";
			current = current.getNext();
		}

		return result;
	}


}