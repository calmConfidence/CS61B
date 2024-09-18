package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    
    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        
        assertTrue("A newly initialized LLDeque should be empty", ad1.isEmpty());
        ad1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, ad1.size());
        assertFalse("ad1 should now contain 1 item", ad1.isEmpty());

        ad1.addLast("middle");
        assertEquals(2, ad1.size());

        ad1.addLast("back");
        assertEquals(3, ad1.size());

        System.out.println("Printing out deque: ");
        ad1.printDeque();
    }

    @Test
    /** test when the size of array is full what will happen. */
    public void addFullTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0;i < 8;i ++) {
            ad1.addFirst(i);
        }
        assertEquals(16, ad1.getLength());
    }

    @Test
    /** test when the size of array is less than the length of array's 25% what will happen. */
    public void removeMuchTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0;i < 15;i ++) {
            ad1.addFirst(i);
        }
        for (int i = 0;i < 12;i ++) {
            ad1.removeLast();
        }
        assertEquals(8, ad1.getLength());
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        // should be empty
        assertTrue("ad1 should be empty upon initialization", ad1.isEmpty());

        ad1.addFirst(10);
        // should not be empty
        assertFalse("ad1 should contain 1 item", ad1.isEmpty());

        ad1.removeFirst();
        // should be empty
        assertTrue("ad1 should be empty after removal", ad1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        ad1.addFirst(3);

        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeFirst();

        int size = ad1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {

        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, ad1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, ad1.removeLast());

    }

    @Test
    /* Test get method. */
    public void getTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 3; i++) {
            ad1.addFirst(i);
        }
        assertEquals(2, (int)ad1.get(0));
        assertEquals(1, (int)ad1.get(1));
        assertEquals(0, (int)ad1.get(2));
    }

    @Test
    /* test the iterator. */
    public void testIterator() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 3; i++) {
            ad1.addFirst(i);
        }
        for (int i : ad1) {
            System.out.print(i);
            System.out.print(' ');
        }
    }

    @Test
    /* test equals */
    public void testEquals() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        ArrayDeque<Integer> lld2 = new ArrayDeque<>();

        for (int i = 0; i < 3; i++) {
            lld1.addFirst(i);
            lld2.addFirst(i);
        }

        assertTrue("lld1 equals lld2", lld1.equals(lld2));
    }
    
    @Test
    /* random test all the methods. */
    public void randomizedTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        int N = 100000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 5);
            if (operationNumber == 0) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                ad.addFirst(randVal);
                System.out.println("addFirst(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = ad.size();
                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                ad.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 3 && ad.size() > 0) {
                // removeFirst
                int removeFirstVal = ad.removeFirst();
                System.out.println("Remove first value: " + removeFirstVal);
            } else if (operationNumber == 4 && ad.size() > 0) {
                // removeLast
                int removeLastVal = ad.removeLast();
                System.out.println("Remove last value: " + removeLastVal);
            }
        }
    }
}
