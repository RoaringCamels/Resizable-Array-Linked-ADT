import java.util.Scanner;

public class bagDriver {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        BagInterface<String> bagOneDriver = null;
        BagInterface<String> bagTwoDriver = null;
        System.out.println(
                "This program will output a new collection consisting of the union, intersection and difference " +
                        "of two existing collections.");
        boolean loop1 = true;
        while (loop1) {
            System.out.println(
                    "Would you like your collection be inside a Resizable Array (1) or Linked ADT (2)? Quit (0).");
            int menu = s.nextInt();
            if (menu == 1) {
                System.out.println("You have chosen to use a Resizable Array.");
                System.out.println("How many items would you like to add to Bag 1(int)?");
                int itemsList1 = s.nextInt();
                System.out.println("\nCreating Bag One(Resizable Array)...");
                bagOneDriver = new ResizableArrayBag<>();
                System.out.println("Please input the items(String):");
                for (int i = 0; i < itemsList1; i++) {
                    bagOneDriver.add(s.next());
                }
                displayBag(bagOneDriver);
                loop1 = false;
            } else if (menu == 2) {
                System.out.println("You have chosen to use a Linked ADT.");
                System.out.println("How many items would you like to add to Bag 1(int)?");
                int itemsList1 = s.nextInt();
                System.out.println("\nCreating Bag One(Linked)...");
                bagOneDriver = new LinkedBag<>();
                System.out.println("Please input the items(String):");
                for (int i = 0; i < itemsList1; i++) {
                    bagOneDriver.add(s.next());
                }
                displayBag(bagOneDriver);
                loop1 = false;
            } else if (menu == 0) {
                break;
            }
        } // end loop 1
        boolean loop2 = true;
        while (loop2) {
            System.out.println(
                    "Would you like your secound collection be inside a Resizable Array (1) or Linked ADT (2)? Quit (0).");
            int menu2 = s.nextInt();
            if (menu2 == 1) {
                System.out.println("You have chosen to use a Resizable Array.");
                System.out.println("How many items would you like to add to Bag 2(int)?");
                int itemsList2 = s.nextInt();
                System.out.println("\nCreating Bag Two(Resizable Array)...");
                bagTwoDriver = new ResizableArrayBag<>();
                System.out.println("Please input the items(String):");
                for (int i = 0; i < itemsList2; i++) {
                    bagTwoDriver.add(s.next());
                }
                loop2 = false;
                s.close();
            } else if (menu2 == 2) {
                System.out.println("You have chosen to use a Linked ADT.");
                System.out.println("How many items would you like to add to Bag 2(int)?");
                int itemsList2 = s.nextInt();
                System.out.println("\nCreating Bag Two(Linked)...");
                bagTwoDriver = new LinkedBag<>();
                System.out.println("Please input the items(String):");
                for (int i = 0; i < itemsList2; i++) {
                    bagTwoDriver.add(s.next());
                }
                loop2 = false;
                s.close();
            } else if (menu2 == 0) {
                s.close();
                break;
            } else {
                System.out.println("Please choose 1 2 or 0.");
            }
        } // end loop 2
        s.close();
        System.out.println("Bag one: ");
        displayBag(bagOneDriver);
        System.out.println("Bag two: ");
        displayBag(bagTwoDriver);
        BagInterface<String> everything = bagOneDriver.union(bagTwoDriver);
        System.out.println('\n' + "Union: ");
        displayBag(everything);
        BagInterface<String> intersect = bagOneDriver.intersection(bagTwoDriver);
        System.out.println('\n' + "Intersection: ");
        displayBag(intersect);
        BagInterface<String> differ = bagOneDriver.difference(bagTwoDriver);
        System.out.println('\n' + "Difference of bagOne and bagTwo: ");
        displayBag(differ);
        BagInterface<String> differ2 = bagTwoDriver.difference(bagOneDriver);
        System.out.println('\n' + "Difference of bagTwo and bagOne: ");
        displayBag(differ2);
    }

    /**
     * @param bagEntry
     */
    private static void displayBag(BagInterface<String> bagEntry) {
        System.out.println("The bag contains " + bagEntry.getCurrentSize() + " string(s), as follows:");
        Object[] bagArray = bagEntry.toArray();
        for (int index = 0; index < bagArray.length; index++) {
            System.out.print(bagArray[index] + " ");
        }
        System.out.println("");
    }// end displayBag
}// end class bagDriver
