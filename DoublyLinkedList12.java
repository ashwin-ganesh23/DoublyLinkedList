
/*
 * Name: <Ashwin Ganesh>
 * ID: <A10210060>
 * Login: <asganesh@ucsd.edu>
 */
package hw2;


/**
 *
 * A class that extends AbstractList to exhibit the functionality
 * of a doubly linked list.
 * @since 2015-10-08
 * @version 1.0
 * @author Ashwin Ganesh
 */

import java.util.*;

public class DoublyLinkedList12<E> extends AbstractList<E>
{

	private int nelems;
	private Node head;
	private Node tail;
	private Node temp;
	private E elem;
	private int placeholder;


	protected class Node
	{
    	E data;
    	Node next;
    	Node prev;

    	/**
     	* Constructor initializes the value in data.
     	* @param element
     	*/
    	public Node (E element)
    	{
    		data = element;
    	}
    	/**
     	* Constructor to create singleton link it between previous and next
     	*   @param element Element to add, can be null
     	*   @param prevNode predecessor Node, can be null
     	*   @param nextNode successor Node, can be null
     	*/
    	public Node(E element, Node prevNode, Node nextNode)
    	{
    		data = element;
    		next = nextNode;
    		prev = prevNode;
    	}
    	/**
     	*
     	* Remove this node from the list. Update previous and next nodes
     	* @param element
     	*/
    	public void remove(E element)
    	{
    		data = null;
    		next = null;
    		prev = null;
    	}
    	/**
     	* Set the previous node in the list
     	* @param p new previous node
     	*/
    	public void setPrev(Node p)
    	{
    		prev = p;
    	}
    	/**
     	* Set the next node in the list
     	* @param n new next node
     	*/
    	public void setNext(Node n)
    	{
    		next = n;
    	}

    	/**
     	* Set the element
     	* @param e new element
     	*/
    	public void setElement(E o)
    	{
    		data = o;
    	}
    	/**
     	*
     	* Accessor to get the next Node in the list
     	*/
    	public Node getNext()
    	{
      		return next;
    	}
    	/**
     	* Accessor to get the prev Node in the list
     	*/
    	public Node getPrev()
    	{
      		return prev;
    	}
    	/**
     	* Accessor to get the Nodes Element
     	*/
    	public E getElement()
    	{
      		return data;
    	}
  	}

  /** ListIterator implementation */

	protected class MyListIterator implements ListIterator<E>
	{

    	private boolean forward;
    	private boolean canRemove;
    	private Node left;		// Cursor sits between these two nodes
    	private Node right; 	// Cursor sits between these two nodes
    	private int idx;        // Tracks current position

    	/*
     	* Constructor that initializes values
     	*/
    	public MyListIterator()
    	{
    		left = head;
    		right = head.getNext();
    		idx = 0;
    		forward = false;
    		canRemove = false;
    		placeholder = 0;
    	}

    	/**
     	* Adds element to linked list and creates new pointers to left and right
     	* @param o contains element value to add
     	* @throws NullPointerException
     	*/
    	@Override
    	public void add(E o) throws  NullPointerException
    	{
    		try
			{
    			left.setNext(new Node(o, left ,right));
    			left = left.getNext();
  		  		right.setPrev(left);
  		  		nelems++;
  		  		idx++;
    		}
			catch(NullPointerException e)
			{
    			System.out.println(e.getMessage());
    		}
    	}

		/**
     	* Sees if there is a value in the next space
     	* @return boolean true is list has valid next value
     	*/
    	@Override
    	public boolean hasNext()
    	{
      		if (idx + 1 < nelems)
			{
				return true;
			}
			else
			{
				return false;
			}
    	}

    /**
     * Sees if there is a valid element previous to idx
     * @return boolean true if valid element previous
     */
    @Override
    public boolean hasPrevious()
    {
    	if(idx - 1 >= 0){
    		return true;
    	}else{
      return false;
    	}
    }

    /**
     * moves index over to the right if there is valid node
     * @return E element returned that has been changed from right to left
     */
    @SuppressWarnings("unchecked")
    public E next() throws NoSuchElementException
    {
      if(getNth(idx + 1) != null){
			placeholder++;
			left = getNth(idx);
  		  	right = getNth(idx + 1);
  		  	forward = true;
  		  	canRemove = true;
  		  	idx++;
  		  	return (E) left;
		}else{
			throw new NoSuchElementException();
		}
    }
    /**
     * returns next index
     * @return int next index value
     */
    @Override
    public int nextIndex()
    {
      return idx + 1;
    }
    /**
     * moves index back one place
     * @return E previous element returned
     */
	@SuppressWarnings("unchecked")
	@Override
    public E previous() throws NoSuchElementException
    {
    	if(getNth(idx - 1) != null){
			left = getNth(idx - 1);
  		  	right = getNth(idx);
  		  	forward = false;
  		  	canRemove = true;
  		  	idx--;
  		  	return (E) right;
		}else{
			throw new NoSuchElementException();
		}
    }

    /**
     * returns previous index
     * @return int previous index value
     */
    @Override
    public int previousIndex()
    {
      return idx - 1;
    }
    /**
     * removes current element and selects surrounding nodes as left and right
     */
    @Override
    public void remove() throws IllegalStateException
    {
      try{
    	  if(canRemove){
    		  if(forward){
    			  left = left.getPrev();
    			  left.setNext(right);
    			  right.setPrev(left);
    			  --idx;
    			  --nelems;
        		  canRemove = false;
        	  }else{
        		  right = right.getNext();
        		  right.setPrev(left);
        		  left.setNext(right);
        		  --nelems;
        		  canRemove = false;
        	  }
    	  }

      }catch(IllegalStateException e){
    	  System.out.println(e.getMessage());
      }
    }

    /**
     * sets element of the node visited by next or previous
     * @param o value to be set in current element
     */
	@Override
    public void set(E o)
      throws NullPointerException,IllegalStateException
    {
      try{
    	  if(forward){
    	      left.data = o;
    	  }else{
    		  right.data = o;
    	  }

      }catch(NullPointerException e){
    	  System.out.println(e.getMessage());
      }catch(IllegalStateException e){
    	  System.out.println(e.getMessage());
      }
    }

  }


  //  Implementation of the DoublyLinkedList12 Class


  /**
   * initializes values for doublylinkedlist12
   */
  public DoublyLinkedList12()
  {
	  nelems = 0;
	  head = new Node(null);
	  tail = new Node(null);
	  head.setNext(tail);
	  tail.setPrev(head);
	  placeholder = 0;
  }
  /**
   * returns size of list (number of elements)
   * @return int size of list
   */
  @Override
  public int size()
  {
    return nelems;
  }

 /**
  * Gets value of element at index given
  * @return E value of element at index
  * @param index index at which we want to get element
  * @throws IndexOutOfBoundsException
  */
  @Override
  public E get(int index) throws IndexOutOfBoundsException
  {
	if(index < 0 || index >= nelems){
	  throw new IndexOutOfBoundsException();
	}else{
	  return getNth(index).data;
	}
  }
@Override
  /** Add an element to the list
    * @param index  where in the list to add
    * @param data data to add
    * @throws IndexOutOfBoundsException
    * @throws NullPointerException
    */
    public void add(int index, E data)
    throws IndexOutOfBoundsException,NullPointerException
    {
	 try{
		 getNth(index - 1).setNext(new Node(data, getNth(index - 1) ,getNth(index)));
		 getNth(index + 1).setPrev(getNth(index));
		 nelems++;

	 }catch(IndexOutOfBoundsException e){
		System.out.println(e.getMessage());
	 }catch(NullPointerException e){
		System.out.println(e.getMessage());
	}


  }
/** Add an element to the end of the list
    * @param data data to add
    * @throws NullPointerException
    */
  public boolean add(E data) throws NullPointerException
  {
	try{
		tail.setPrev(new Node(data, tail.getPrev(), tail));
		tail.getPrev().getPrev().setNext(tail.getPrev());
		nelems++;
		return true;
	}catch(NullPointerException e){
		System.out.println(e.getMessage());
	}
    return false;
  }

  /** Set the element at an index in the list
    * @param index  where in the list to add
    * @param data data to add
    * @return element that was previously at this index.
    * @throws IndexOutOfBoundsException
    * @throws NullPointerException
    * @throws IllegalStateExceptoin
    */
  public E set(int index, E data)
    throws IndexOutOfBoundsException,NullPointerException
  {
	  try{
		  elem = getNth(index).getElement();
		  getNth(index).setElement(data);
	  }catch(IndexOutOfBoundsException e){
			System.out.println(e.getMessage());
		}catch(NullPointerException e){
			System.out.println(e.getMessage());
		}
    return elem;
  }

  /** Remove the element at an index in the list
    * @param index  where in the list to add
    * @return element the data found
    * @throws IndexOutOfBoundsException
    * @throws IllegalStateException
    */
  public E remove(int index) throws IndexOutOfBoundsException, IllegalStateException
  {
	  try{
		  elem = getNth(index).getElement();
		  getNth(index+1).setPrev(getNth(index -1));
		  getNth(index-1).setNext(getNth(index + 1));
		  nelems--;
	  }catch(IndexOutOfBoundsException e){
			System.out.println(e.getMessage());
		}catch(IllegalStateException e){
			System.out.println(e.getMessage());
		}
    return elem;
  }

  /**
   * Clear the linked list
   */
  public void clear()
  {
	  nelems = 0;
	  head.setNext(tail);
	  tail.setPrev(head);
  }

  /** Determine if the list empty
    * @return boolean true if empty, false otherwise
    */
  public boolean isEmpty()
  {
	if (nelems == 0){
    return true;
	} else{
		return false;
	}
  }



  /**
   *  Helper method to get the Node at the Nth index
   * @param index index to get
   * @return Node element we want to get
   * @throws IndexOutOfBoundsException
   */
  private Node getNth(int index) throws IndexOutOfBoundsException
  {
	  temp = head;
	  placeholder = -1;
	try{
		while(temp != null ){

			if(placeholder == index){
				return temp;
			}
				temp = temp.getNext();
				placeholder++;
			}

	}catch(IndexOutOfBoundsException e){
		System.out.println(e.getMessage());
	}

    return temp;
  }


public Iterator<E>  iterator()
{
	return new MyListIterator();
}

public ListIterator<E>  listIterator()
{
	return new MyListIterator();
}

}
