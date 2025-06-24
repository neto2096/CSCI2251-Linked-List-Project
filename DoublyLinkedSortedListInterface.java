// Name: Ernesto Morales Carrasco
// Email: emoralescarras@cnm.edu
// Assignment: Linked List
/** Purpose:
 * Defines the interface for a doubly-linked, self-sorting list that stores HurricaneRowData objects,
 * maintaining sort order by ACE index (descending) during insertion.
 */

public interface DoublyLinkedSortedListInterface
{
	//Get the value of the current DoublyLinkedSortedList
	public HurricaneRowData getValue();
	
	//Return true if next is not null
	public boolean hasNext();
	
	//Set next to be the given DoublyLinkedSortedList
	public void setNext(DoublyLinkedSortedList next);
	
	//Return a reference to the next DoublyLinkedSortedList
	public DoublyLinkedSortedList getNext();
	
	//Return true if previous is not null
	public boolean hasPrevious();
	
	//Set previous to be the given DoublyLinkedSortedList
	public void setPrevious(DoublyLinkedSortedList previous);
	
	//Return a reference to the previous DoublyLinkedSortedList
	public DoublyLinkedSortedList getPrevious();
	
	//Return a reference to the first DoublyLinkedSortedList element in the list
	public DoublyLinkedSortedList getFirst();
	
	//Return a reference to the last DoublyLinkedSortedList element in the list
	public DoublyLinkedSortedList getLast();
	
	//Remove the DoublyLinkedSortedList element that has toRemove as its value
	public DoublyLinkedSortedList remove(HurricaneRowData toRemove);
	
	//Insert a new DoublyLinkedSortedList element that has the given newValue in order in the list.
	public void insert(HurricaneRowData newValue);
	
	//Return the entire list as a multi-line String
	public String toString();
}