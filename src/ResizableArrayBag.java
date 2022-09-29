import java.util.Arrays;
public class ResizableArrayBag<T> implements BagInterface<T> {
    private T[] bag;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;
    private boolean integrityOK = false;
    private static final int MAX_CAPACITY = 10000;

    // Sets the capacity of the bag
    public ResizableArrayBag() {
        this(DEFAULT_CAPACITY);
    }// end ResizableArrayBag

    // Cchecks if the capacity of the bag is valid
    public ResizableArrayBag(int capacity) {
        numberOfEntries = 0;
        @SuppressWarnings("unchecked")
        T[] tempBag = (T[]) new Object[capacity];
        bag = tempBag;
        integrityOK = true;
    }// end ResizableArrayBag

    /**
     * Returns the combined contents of the two collections as one new bag.
     * Ex: bagOne contains a, b, c. bagTwo contains b, b, d, e.
     * bagOne.union(bagTwo) returns a, b, b, b, c, d, and e.
     * Duplicates: object x occurs 5 times and 2 times in the other, bag would
     * contain object x 7 times.
     */
    public BagInterface<T> union(BagInterface<T> bagEntry) // T(n)=0(f(n))
    // pretty much good to go, need to find a way to return a
    // combined BAG, not an array.
    {
        T[] bagArray = bagEntry.toArray();
        BagInterface<T> newBag = new ResizableArrayBag<T>(bag.length);
        // loop that adds current combined bag into a new bag object.
        for (int i = 0; i < bag.length; i++) {
            newBag.add(bag[i]);
        }
        // loop that adds second bag array to the bag.
        for (int index = 0; index < bagArray.length; index++) {
            newBag.add(bagArray[index]);
        }
        return newBag;
    }// end union

    /**
     * Returns a new bag of the overlapped entries of the two collections.
     * Ex: bagOne contains a, b, c. BagTwo contains b, b, d, e.
     * bag1.intersection(bagTwo) returns b.
     * Duplicates: If b had occurred in bagOne twice, the return result would have
     * been two b's.
     */
    public BagInterface<T> intersection(BagInterface<T> bagEntry) // T(n)=0(f(n^2))
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
     * Returns a new bag of the differences of the two collections.
     * Ex: bagOne contains a, b, c, bagTwo contains b, b, d, e.
     * bagOne.difference(bagTwo) returns a and c, bagTwo.difference(bagOne) returns
     * b, d, and e.
     * Duplicates: object x occurs 5 times and 2 times in the other, difference is
     * object x 3 times.
     */
    public BagInterface<T> difference(BagInterface<T> bagEntry) // T(n)=0(f(n^2))
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

    
    /** 
     * @param newEntry
     * @return int
     */
    // Count the number of times a certain object occurs in the bag.
    public int getFrequencyOf(T newEntry) {
        checkIntegrity();
        int counter = 0;
        for (int index = 0; index < numberOfEntries; index++) {
            if (newEntry.equals(bag[index])) {
                counter++;
            }
        }
        return counter;
    }// end getFrequnecyOf

    
    /** 
     * @return int
     */
    // Get the number of items currently in the bag.
    public int getCurrentSize() {
        return numberOfEntries;
    }// end getCurrentSize

    
    /** 
     * @return boolean
     */
    // See whether the bag is empty.
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }// end isEmpty

    
    /** 
     * @param anEntry
     * @return boolean
     */
    // Test whether the bag contains a particular object.
    public boolean contains(T anEntry) {
        checkIntegrity();
        return getIndexOf(anEntry) > -1;
    }// end contains

    
    /** 
     * @param newEntry
     * @return boolean
     */
    // Add a given object to the bag
    public boolean add(T newEntry) {
        checkIntegrity();
        boolean result = true;
        if (isFull()) {
            int newLength = 2 * bag.length;
            checkCapacity(newLength);
            bag = Arrays.copyOf(bag, bag.length * 2);
        } else if (newEntry != null) {
            bag[numberOfEntries] = newEntry;
            numberOfEntries++;
        }
        return result;
    }// end add

    
    /** 
     * @return T
     */
    // Removes a unspecified object from the bag.
    public T remove() {
        checkIntegrity();
        T result = removeEntry(numberOfEntries - 1);
        return result;
    }// end remove

    
    /** 
     * @param anEntry
     * @return boolean
     */
    // Remove a particular object from the bag
    public boolean remove(T anEntry) {
        checkIntegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    }// end remove

    // Remove all objects from the bag
    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    }// end clear

    
    /** 
     * @return T[]
     */
    // Look at all the objects that are in the bag.
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        for (int index = 0; index < numberOfEntries; index++) {
            result[index] = bag[index];
        }
        return result;
    }// end toArray

    
    /** 
     * @return boolean
     */
    // Checks whether or not the bag is full.
    public boolean isFull() {
        return numberOfEntries == bag.length;
    }// end isFull

    // Throws an exception if this object is not initialized
    private void checkIntegrity() {
        if (integrityOK == false) {
            throw new SecurityException("ArrayBag object is corrupt.");
        }
    }// end checkIntegrity

    
    /** 
     * @param capacity
     */
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a bag whose " +
                    "capacity exceeds allowed " +
                    "maximum of " + MAX_CAPACITY);
    }// end checkCapacity

    
    /** 
     * @param anEntry
     * @return int
     */
    // Gets the index of a given entry
    private int getIndexOf(T anEntry) {
        int where = -1;
        boolean found = false;
        int index = 0;

        while (!found && (index < numberOfEntries)) {
            if (anEntry.equals(bag[index])) {
                found = true;
                where = index;
            }
            index++;
        }
        return where;
    }// end getIndexOf

    
    /** 
     * @param givenIndex
     * @return T
     */
    // Removes the entry of a given index.
    private T removeEntry(int givenIndex) {
        T result = null;

        if (!isEmpty() && (givenIndex > 0)) {
            result = bag[givenIndex];
            bag[givenIndex] = bag[numberOfEntries - 1];
            bag[numberOfEntries - 1] = null;
            numberOfEntries--;
        }
        return result;
    }// end removeEntry
}
