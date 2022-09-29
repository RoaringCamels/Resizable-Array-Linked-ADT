
public class ResizableArrayBagTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // Creating bagOne and adding its contents
        BagInterface<String> bagOne = new LinkedBag<>();
        testIsFull(bagOne, false);
        System.out.println("Adding to bag1 more strings than its initial capacity.");
        String[] bagOneContents = { "A", "A", "B", "B", "C", "C" };
        testAdd(bagOne, bagOneContents);

        // Creating bagTwo and adding its contents
        BagInterface<String> bagTwo = new ResizableArrayBag<>();
        testIsFull(bagTwo, false);
        System.out.println("Adding to bag2 more strings than its initial capacity.");
        String[] bagTwoContents = { "A", "A", "B", "G" };
        testAdd(bagTwo, bagTwoContents);

        // Getting the Union/Intersection/and two forms of Difference
        BagInterface<String> everything = bagOne.union(bagTwo);
        System.out.println('\n' + "Union:");
        displayBag(everything);
        BagInterface<String> intersect = bagOne.intersection(bagTwo);
        System.out.println('\n' + "Intersection:");
        displayBag(intersect);
        BagInterface<String> differ = bagOne.difference(bagTwo);
        System.out.println('\n' + "Difference of bagOne and bagTwo:");
        displayBag(differ);
        BagInterface<String> differ2 = bagTwo.difference(bagOne);
        System.out.println('\n' + "Difference of bagTwo and bagOne:");
        displayBag(differ2);

        // Testing the contents of the bag with testFrequency and testContains
        String[] testString = { "", "A", "B" };
        testFrequency(bagOne, testString);
        testContains(bagOne, testString);

        // Testing testRemove
        testRemove(bagOne, testString);
    }// end main

    /**
     * @param bagOne
     * @param content
     */
    // tests the method add
    private static void testAdd(BagInterface<String> bagOne, String[] content) {
        System.out.println("Adding to the bag: ");
        for (int index = 0; index < content.length; index++) {
            bagOne.add(content[index]);
            System.out.print(content[index] + " ");
        }
        System.out.println();
        displayBag(bagOne);
    }// end testAdd

    /**
     * @param aBag
     * @param tests
     */
    // Tests the two remove methods.
    private static void testRemove(BagInterface<String> aBag, String[] tests) {
        for (int index = 0; index < tests.length; index++) {
            String aString = tests[index];
            if (aString.equals("") || (aString == null)) {
                // Test remove()
                System.out.println("\nRemoving a string from the bag:");
                String removedString = aBag.remove();
                System.out.println("remove() returns " + removedString);
            } else {
                // Test remove(aString)
                System.out.println("\nRemoving \"" + aString + "\" from the bag:");
                boolean result = aBag.remove(aString);
                System.out.println("remove(\"" + aString + "\") returns " + result);
            } // end if
            displayBag(aBag);
        } // end for
    } // end testRemove

    /**
     * @param bagOne
     * @param correctResult
     */
    // Tests if the bag is full
    private static void testIsFull(BagInterface<String> bagOne, boolean correctResult) {
        System.out.print("\nTesting the method isFull with ");
        if (correctResult)
            System.out.println("a full bag:");
        else
            System.out.println("a bag that is not full:");

        System.out.print("isFull finds the bag ");
        if (correctResult && bagOne.isFull())
            System.out.println("full: OK");
        else if (correctResult)
            System.out.println("not full, but it is full: ERROR");
        else if (!correctResult && bagOne.isFull())
            System.out.println("full, but it is not full: ERROR");
        else
            System.out.println("not full: OK");
    }// end testIsFull

    /**
     * @param aBag
     * @param tests
     */
    // Tests the method getFrequencyOf.
    private static void testFrequency(BagInterface<String> aBag, String[] tests) {
        System.out.println("\nTesting the method getFrequencyOf:");
        for (int index = 0; index < tests.length; index++)
            System.out.println("In this bag, the count of " + tests[index] +
                    " is " + aBag.getFrequencyOf(tests[index]));
    } // end testFrequency

    /**
     * @param aBag
     * @param tests
     */
    // Tests the method contains.
    private static void testContains(BagInterface<String> aBag, String[] tests) {
        System.out.println("\nTesting the method contains:");
        for (int index = 0; index < tests.length; index++)
            System.out.println("Does this bag contain " + tests[index] +
                    "? " + aBag.contains(tests[index]));
    } // end testContains

    /**
     * @param bagOne
     */
    // Displays the content in the bag
    private static void displayBag(BagInterface<String> bagOne) {
        System.out.println("The bag contains " + bagOne.getCurrentSize() + " string(s), as follows:");
        Object[] bagArray = bagOne.toArray();
        for (int index = 0; index < bagArray.length; index++) {
            System.out.print(bagArray[index] + " ");
        } // end for

        System.out.println();
    } // end displayBag
}// end ResiableArrayDemo