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

    @Override
    public HurricaneRowData getValue() {
        return value;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public void setNext(DoublyLinkedSortedList next) {
        this.next = next;
    }

    @Override
    public DoublyLinkedSortedList getNext() {
        return next;
    }

    @Override
    public boolean hasPrevious() {
        return previous != null;
    }

    @Override
    public void setPrevious(DoublyLinkedSortedList previous) {
        this.previous = previous;
    }

    @Override
    public DoublyLinkedSortedList getPrevious() {
        return previous;
    }

    @Override
    public DoublyLinkedSortedList getFirst() {
        DoublyLinkedSortedList current = this;
        while (current.hasPrevious()) {
            current = current.getPrevious();
        }
        return current;
    }

    @Override
    public DoublyLinkedSortedList getLast() {
        DoublyLinkedSortedList current = this;
        while (current.hasNext()) {
            current = current.getNext();
        }
        return current;
    }

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

    @Override
    public void insert(HurricaneRowData newValue) {
        DoublyLinkedSortedList newNode = new DoublyLinkedSortedList(newValue);

        // If list is empty or this is a dummy node
        if (this.value == null && !hasNext() && !hasPrevious()) {
            this.value = newValue;
            return;
        }

        DoublyLinkedSortedList current = getFirst();
        DoublyLinkedSortedList first = current;

        // If inserting at the beginning (newValue has higher ACE)
        if (current.getValue() == null || newValue.getAceIndex() > current.getValue().getAceIndex()) {
            newNode.setNext(current);
            current.setPrevious(newNode);
            if (this == first) {
                this.value = newNode.getValue();
                this.next = newNode.getNext();
                this.previous = null;
            }
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
