public class LinkedBagTest {
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Creating an empty bag.");
        BagInterface<String> bagOne = new LinkedBag<>();
        testIsEmpty(bagOne, true);
        BagInterface<String> bagTwo = new LinkedBag<>();
        testIsEmpty(bagTwo, true);

        String[] bagOneContents = { "A", "A", "B", "A", "C", "A" };
        String[] bagTwoContents = { "A", "B", "A", "C", "B", "C", "D" };

        testAdd(bagOne, bagOneContents);
        testIsEmpty(bagOne, false);
        testAdd(bagTwo, bagTwoContents);
        testIsEmpty(bagTwo, false);

        BagInterface<String> everything = bagOne.union(bagTwo);
        System.out.println('\n' + "Union: ");
        displayBag(everything);
        BagInterface<String> intersect = bagOne.intersection(bagTwo);
        System.out.println('\n' + "Intersection: ");
        displayBag(intersect);
        BagInterface<String> differ = bagOne.difference(bagTwo);
        System.out.println('\n' + "Difference of bagOne and bagTwo: ");
        displayBag(differ);
        BagInterface<String> differ2 = bagTwo.difference(bagOne);
        System.out.println('\n' + "Difference of bagTwo and bagOne: ");
        displayBag(differ2);
    }

    /**
     * Adds the variables from contetnt into bagOne
     * 
     * @param bagOne  either a LinkedBag<>() or a ResizableArrayBag<>() in which
     *                content will be added to
     * @param content the variables in which what will be added
     */
    private static void testAdd(BagInterface<String> bagOne, String[] content) {
        System.out.println('\n' + "Adding to the bag: ");
        for (int index = 0; index < content.length; index++) {
            bagOne.add(content[index]);
            System.out.print(content[index] + " ");
        }
        System.out.println();
        displayBag(bagOne);
    }// end testAdd

    /**
     * Takes an object and makes it into an array to be able to be printed
     * 
     * @param bagOn either a LinkedBag<>() or a ResizableArrayBag<>()
     */
    private static void displayBag(BagInterface<String> bagOne) {
        System.out.println("The bag now contains the following string(s): ");
        Object[] bagArray = bagOne.toArray();
        for (int index = 0; index < bagArray.length; index++) {
            System.out.print(bagArray[index] + " ");
        } // end for
        System.out.println(); // makes an extra line
    } // end displayBag

    
    /** 
     * @param bag
     * @param empty
     */
    private static void testIsEmpty(BagInterface<String> bag, boolean empty) {
        System.out.print("\nTesting isEmpty with ");
        if (empty)
            System.out.println("an empty bag:");
        else
            System.out.println("a bag that is not empty:");

        System.out.print("isEmpty finds the bag ");
        if (empty && bag.isEmpty())
            System.out.println("empty: OK.");
        else if (empty)
            System.out.println("not empty, but it is: ERROR.");
        else if (!empty && bag.isEmpty())
            System.out.println("empty, but it is not empty: ERROR.");
        else
            System.out.println("not empty: OK.");
    } // end testIsEmpty
}// end of LinkedBagTest
