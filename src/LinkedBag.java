public class LinkedBag<T> implements BagInterface<T> {
    /** Linked Implementation has two data fields */
    private Node firstNode; // reference to first node
    private int numberOfEntries;

    public LinkedBag() // default constructor
    {
        firstNode = null;
        numberOfEntries = 0;
    } // end of default constructor

    private class Node {
        private T data;
        private Node next;

        private Node(T dataPortion) {
            this(dataPortion, null);
        }// end constructor

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }// end constructor

        private T getData() {
            return data;
        }// end getData

        private void setData(T newData) {
            data = newData;
        } // end setData

        private Node getNextNode() {
            return next;
        }// end getNextNode

        private void setNextNode(Node nextNode) {
            next = nextNode;
        }// end setNextNode
    }// end class Node

    
    /** 
     * @param anEntry
     * @return Node
     */
    // private methods required for LinkedBag
    private Node getReferenceTo(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNextNode();
        } // end while
        return currentNode;
    }// end getReferenceTo

    /** All of the BagInterface methods */
    public int getCurrentSize() {
        return numberOfEntries;
    }// end getCurretnSize

    
    /** 
     * @return boolean
     */
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }// end isEmpty

    
    /** 
     * @param newEntry
     * @return boolean
     */
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);
        newNode.next = firstNode; // Makes a new node reference
        firstNode = newNode;
        numberOfEntries++;
        return true;
    }// end add

    
    /** 
     * @return T
     */
    public T remove() {
        T result = null;
        if (firstNode != null) {
            result = firstNode.getData();
            firstNode = firstNode.getNextNode(); // Removes first node from chain
            numberOfEntries--;
        } // end if
        return result;
    }// end remove

    
    /** 
     * @param anEntry
     * @return boolean
     */
    public boolean remove(T anEntry) {
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);
        if (nodeN != null) {
            nodeN.setData(firstNode.getData());// replaces located entry with entry in first node
            firstNode = firstNode.getNextNode();// removes the first node
        } // end if
        return result;
    }// end remove

    public void clear() {
        while (!isEmpty())
            remove();
    }// end clear

    
    /** 
     * @param anEntry
     * @return int
     */
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;
        int counter = 0;
        Node currentNode = firstNode;
        while ((counter < numberOfEntries) && (currentNode != null)) {
            if (anEntry.equals(currentNode.getData())) {
                frequency++;
            } // end if
            counter++;
            currentNode = currentNode.getNextNode();
        } // end while
        return frequency;
    }// end getFrequencyOf

    
    /** 
     * @param anEntry
     * @return boolean
     */
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNextNode();
        } // end while
        return found;
    }// end contains

    
    /** 
     * @return T[]
     */
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null)) {
            result[index] = currentNode.getData();
            index++;
            currentNode = currentNode.getNextNode();
        } // end while
        return result;
    }// end toArray

    
    /** 
     * @return boolean
     */
    public boolean isFull() {
        return numberOfEntries > 0;
    }// end isFull

    
    /** 
     * @param bagEntry
     * @return BagInterface<T>
     */
    public BagInterface<T> union(BagInterface<T> bagEntry) // n^2
    {
        BagInterface<T> result = new LinkedBag<>(); // making result bag
        T[] entryBag = bagEntry.toArray(); // bag2 to array
        T[] ourBag = this.toArray();// bag1 to array
        for (int i = 0; i < entryBag.length; i++) {
            result.add(entryBag[i]); // adding bag1 to result
        }
        for (int i = 0; i < ourBag.length; i++) {
            result.add(ourBag[i]);// adding bag2 to result
        }
        return result;
    }// end union

    
    /** 
     * @param bagEntry
     * @return BagInterface<T>
     */
    public BagInterface<T> intersection(BagInterface<T> bagEntry)// n^2
    {
        BagInterface<T> result = new LinkedBag<>(); // creating result bag
        BagInterface<T> tempBag = new LinkedBag<>();// creating temperary bag
        T[] entryBag = bagEntry.toArray();// bag2 to array
        T[] ourBag = this.toArray();// bag1 to array
        for (int i = 0; i < entryBag.length; i++) {
            tempBag.add(entryBag[i]);// adding bag1 to temperary bag
        }

        for (int i = 0; i < ourBag.length; i++) {
            if (tempBag.contains(ourBag[i])) {
                result.add(ourBag[i]); // adding to result if temp bag contains object in bag1
                tempBag.remove(ourBag[i]); // removing to not read twice
            }
        }
        return result;
    }// end intersection

    
    /** 
     * @param bagEntry
     * @return BagInterface<T>
     */
    public BagInterface<T> difference(BagInterface<T> bagEntry) // n^2
    {
        BagInterface<T> result = new LinkedBag<>(); // creating result bag
        BagInterface<T> tempBag = new LinkedBag<>();// creating temperary bag
        T[] entryBag = bagEntry.toArray(); // making the entered bag into an array
        for (int i = 0; i < entryBag.length; i++) {
            tempBag.add(entryBag[i]); // adding array into temperary bag
        }
        T[] ourBag = this.toArray(); // turning bag to array
        for (int i = 0; i < ourBag.length; i++) {
            if (tempBag.contains(ourBag[i])) {
                tempBag.remove(ourBag[i]); // removing from bag so we dont read twice
            } else {
                result.add(ourBag[i]);// adding to result bag
            }
        }
        return result;
    }// end difference
}// End of BagInterface methods
