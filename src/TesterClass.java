import org.junit.jupiter.api.Test;

import org.junit.Assert;
public class TesterClass {
    
    @Test
    void unionResultsCombinedBag(){
        BagInterface<String> bagOne = new LinkedBag<>();
        BagInterface<String> bagTwo = new ResizableArrayBag<>();
        String[] bagOneContents = { "A", "A", "B", "A", "C", "A", "A", "B" };
        testAdd(bagOne, bagOneContents);
        String[] bagTwoContents = { "A", "B", "A", "C", "B", "C", "D" };
        testAdd(bagTwo, bagTwoContents);
        String[] expectedResult = new String[] {"A", "A", "B", "A", "C", "A", "A", "B", "D", "C", "B", "C", "A", "B", "A"};
        BagInterface<String> everything = bagOne.union(bagTwo);
        Assert.assertArrayEquals(expectedResult, everything.toArray());

    }   
    @Test
    void intersectionResultsCombinedBag(){
        BagInterface<String> bagOne = new LinkedBag<>();
        BagInterface<String> bagTwo = new ResizableArrayBag<>();
        String[] bagOneContents = { "A", "A", "B", "A", "C", "A", "A", "B" };
        testAdd(bagOne, bagOneContents);
        String[] bagTwoContents = { "A", "B", "A", "C", "B", "C", "D" };
        testAdd(bagTwo, bagTwoContents);
        String[] expectedResult = new String[] {"B", "C", "A", "A", "B"};
        BagInterface<String> intersect = bagOne.intersection(bagTwo);
        Assert.assertArrayEquals(expectedResult, intersect.toArray());
    }
    @Test
    void differenceOneResultsCombinedBag(){
        BagInterface<String> bagOne = new LinkedBag<>();
        BagInterface<String> bagTwo = new ResizableArrayBag<>();
        String[] bagOneContents = { "A", "A", "B", "A", "C", "A", "A", "B" };
        testAdd(bagOne, bagOneContents);
        String[] bagTwoContents = { "A", "B", "A", "C", "B", "C", "D" };
        testAdd(bagTwo, bagTwoContents);
        String[] expectedResult = new String[] {"A", "A", "A"};
        BagInterface<String> differ = bagOne.difference(bagTwo);
        Assert.assertArrayEquals(expectedResult, differ.toArray());
    }
    @Test
    void differenceTwoResultsCombinedBag(){
        BagInterface<String> bagOne = new LinkedBag<>();
        BagInterface<String> bagTwo = new ResizableArrayBag<>();
        String[] bagOneContents = { "A", "A", "B", "A", "C", "A", "A", "B" };
        testAdd(bagOne, bagOneContents);
        String[] bagTwoContents = { "A", "B", "A", "C", "B", "C", "D" };
        testAdd(bagTwo, bagTwoContents);
        String[] expectedResult = new String[] {"D", "C"};
        BagInterface<String> differ = bagTwo.difference(bagOne);
        Assert.assertArrayEquals(expectedResult, differ.toArray());
    }
    private static void testAdd(BagInterface<String> bagOne, String[] content) {
        for (int index = 0; index < content.length; index++) {
            bagOne.add(content[index]);
            System.out.print(content[index] + " ");
        }
    }
}
