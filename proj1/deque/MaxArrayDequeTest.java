package deque;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MaxArrayDequeTest {
    @Test
    public void testMaxNoArgumentAllEquals() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntegerComparator());
        mad.addFirst(12);
        mad.addFirst(12);
        mad.addFirst(12);
        assertEquals(12, (int)mad.max());
    }

    @Test
    public void testMaxNoArgumentAllNoneEquals() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntegerComparator());
        mad.addFirst(19);
        mad.addFirst(28);
        mad.addFirst(40);
        assertEquals(40, (int)mad.max());
    }

    @Test
    public void testMaxNoArgumentEmpty() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntegerComparator());
        assertEquals(null, mad.max());
    }

    @Test
    public void testMaxWithArgumentAllEquals() {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(new StringComparator());
        mad.addFirst("123");
        mad.addFirst("12");
        mad.addFirst("1234");

        assertEquals("1234", mad.max(new StringComparatorUpdate()));
    }

    private class StringComparatorUpdate implements Comparator<String> {

        @Override
        public boolean compare(String a, String b) {
            return a.length() - b.length() >= 0;
        }
    }

    @Test
    public void testEquals() {
        MaxArrayDeque<String> mad1 = new MaxArrayDeque<>(new StringComparator());
        MaxArrayDeque<String> mad2 = new MaxArrayDeque<>(new StringComparator());
        for (int i = 0;i < 3;i += 1) {
            mad1.addFirst("str" + i);
            mad2.addFirst("str" + i);
        }
        assertTrue(mad1.equals(mad2));
    }
}
