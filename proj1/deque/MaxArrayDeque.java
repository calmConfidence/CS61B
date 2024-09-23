package deque;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> comparator;

    /** creates a MaxArrayDeque with the given Comparator */
    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    /** returns the maximum element in the deque as governed
     *  by the previously given Comparator.
     *  If the MaxArrayDeque is empty, simply return null. */
    public T max() {
        if (isEmpty()) return null;
        T tempMax = getFirst();
        for (T item : this) {
            if (!comparator.compare(tempMax, item)) {
                tempMax = item;
            }
        }
        return tempMax;
    }

    /** returns the maximum element in the deque as governed by the parameter Comparator c.
     *  If the MaxArrayDeque is empty, simply return null. */
    public T max(Comparator<T> c) {
        if (isEmpty()) return null;
        T tempMax = getFirst();
        for (T item : this) {
            if (!c.compare(tempMax, item)) {
                tempMax = item;
            }
        }
        return tempMax;
    }

    /** override the equals method. */
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        MaxArrayDeque other = (MaxArrayDeque) o;
        return other.max().equals(max());
    }
}
