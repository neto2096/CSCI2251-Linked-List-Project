// Name: Ernesto Morales Carrasco
// Email: emoralescarras@cnm.edu
// Assignment: Linked List
/**
 * Purpose:
 * Implements a doubly-linked, self-sorting list for storing HurricaneRowData
 * objects
 * sorted by ACE index in descending order during insertion.
 */

public class DoublyLinkedSortedList implements DoublyLinkedSortedListInterface {
    private HurricaneRowData value;
    private DoublyLinkedSortedList next;
    private DoublyLinkedSortedList previous;

    // Node Constructor
    public DoublyLinkedSortedList() {
        this.value = null;
        this.next = null;
        this.previous = null;
    }

    // Value Node Constructor
    public DoublyLinkedSortedList(HurricaneRowData value) {
        this.value = value;
        this.next = null;
        this.previous = null;
    }

    // Returns the HurricaneRowData value of this node
    @Override
    public HurricaneRowData getValue() {
        return value;
    }

    // Returns true if this node has a next node
    @Override
    public boolean hasNext() {
        return next != null;
    }

    // Sets the next node to the given node
    @Override
    public void setNext(DoublyLinkedSortedList next) {
        this.next = next;
    }

    // Returns the next node
    @Override
    public DoublyLinkedSortedList getNext() {
        return next;
    }

    // Returns true if this node has a previous node
    @Override
    public boolean hasPrevious() {
        return previous != null;
    }

    // Sets the previous node to the given node
    @Override
    public void setPrevious(DoublyLinkedSortedList previous) {
        this.previous = previous;
    }

    // Returns the previous node
    @Override
    public DoublyLinkedSortedList getPrevious() {
        return previous;
    }

    // Returns the first node with data in the list
    @Override
    public DoublyLinkedSortedList getFirst() {
        DoublyLinkedSortedList current = this;
        while (current.hasPrevious()) {
            current = current.getPrevious();
        }

        if (current.getValue() == null && current.hasNext()) {
            return current.getNext();
        }
        return current;
    }

    // Returns the last node in the list
    @Override
    public DoublyLinkedSortedList getLast() {
        DoublyLinkedSortedList current = this;
        while (current.hasNext()) {
            current = current.getNext();
        }
        return current;
    }

    // Removes the node with the specified value and returns it
    @Override
    public DoublyLinkedSortedList remove(HurricaneRowData toRemove) {
        DoublyLinkedSortedList current = getFirst();
        while (current != null && current.getValue() != null) {
            if (current.getValue().equals(toRemove)) {
                // Connect previous and next nodes
                if (current.hasPrevious()) {
                    current.getPrevious().setNext(current.getNext());
                }
                if (current.hasNext()) {
                    current.getNext().setPrevious(current.getPrevious());
                }
                return current; // Return the removed node
            }
            current = current.getNext();
        }
        return null; // Not found
    }

    // Inserts a new node with the given value in sorted order (descending by ACE index)
    @Override
    public void insert(HurricaneRowData newValue) {
        DoublyLinkedSortedList newNode = new DoublyLinkedSortedList(newValue);

        // If the list is entirely empty (only the dummy head exists)
        if (this.value == null && this.next == null && this.previous == null) {
            // This is the dummy head. Insert the first data node after it.
            this.next = newNode;
            newNode.setPrevious(this);
            return;
        }

        DoublyLinkedSortedList current = getFirst(); // Get the first non-dummy node

        if (newValue.getAceIndex() > current.getValue().getAceIndex()) {
            DoublyLinkedSortedList dummyHead = current.getPrevious(); // Get the dummy head
            newNode.setNext(current);
            current.setPrevious(newNode);
            dummyHead.setNext(newNode); // The dummy head now points to the new first node
            newNode.setPrevious(dummyHead);
            return;
        }

        // Find the correct position to insert (descending ACE order)
        while (current.hasNext() && current.getNext().getValue() != null &&
                newValue.getAceIndex() <= current.getNext().getValue().getAceIndex()) {
            current = current.getNext();
        }

        // Insert the new node
        newNode.setNext(current.getNext());
        newNode.setPrevious(current);
        current.setNext(newNode);
        if (newNode.hasNext()) {
            newNode.getNext().setPrevious(newNode);
        }
    }

    // Returns a multi-line string of the list, sorted by ACE index
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DoublyLinkedSortedList current = getFirst();
        while (current != null && current.getValue() != null) {
            HurricaneRowData data = current.getValue();
            sb.append(String.format("%10d %10d %10d %10d %10d%n",
                    data.getYear(), data.getAceIndex(), data.getTropicalStorms(),
                    data.getHurricanes(), data.getMajorHurricanes()));
            current = current.getNext();
        }
        return sb.toString();
    }

    // Returns true if the list contains the given value
    public boolean contains(HurricaneRowData value) {
        DoublyLinkedSortedList current = getFirst();
        while (current != null && current.getValue() != null) {
            if (current.getValue().equals(value)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    // Returns the node containing the given value, or null if not found
    public DoublyLinkedSortedList getByValue(HurricaneRowData value) {
        DoublyLinkedSortedList current = getFirst();
        while (current != null && current.getValue() != null) {
            if (current.getValue().equals(value)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
}
