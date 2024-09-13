package randomizedtest;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> al = new AListNoResizing<>();
        BuggyAList<Integer> bal = new BuggyAList<>();
        for (int i = 4; i <= 6;i += 1) {
          al.addLast(i);
          bal.addLast(i);
        }

        assertEquals(al.size(), bal.size());

        for (int i = 0;i < 3;i += 1) {
          int alResult = al.removeLast();
          int balResult = bal.removeLast();
          assertEquals(alResult, balResult);
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BL = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                BL.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int bsize = BL.size();
                assertEquals(size, bsize);
                System.out.println("size: " + size);
            } else if (operationNumber == 2 && L.size() > 0) {
                // getLast
                int lastVal = L.getLast();
                int blastVal = BL.getLast();
                assertEquals(lastVal, blastVal);
                System.out.println("Last value is: " + lastVal);
            } else if (operationNumber == 3 && L.size() > 0) {
                // removeLast
                int removeLastVal = L.removeLast();
                int bremoveLastVal = BL.removeLast();
                assertEquals(removeLastVal, bremoveLastVal);
                System.out.println("Remove last value: " + removeLastVal);
            }
        }
    }
}
