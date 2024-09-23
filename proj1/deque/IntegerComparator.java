package deque;

public class IntegerComparator implements Comparator<Integer> {
    @Override
    public boolean compare(Integer a, Integer b) {
        return a - b >= 0;
    }
}
