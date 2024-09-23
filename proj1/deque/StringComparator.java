package deque;

public class StringComparator implements Comparator<String> {
    @Override
    public boolean compare(String a, String b) {
        return a.compareTo(b) > 0;
    }
}
