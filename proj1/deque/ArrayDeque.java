package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {

    private T[] items;
    private int front;
    private int rar;
    private int length; // The length of the array.

    private int mod(int x) {
        return (x + length) % length;
    }

    /** Create an empty array list deque. By default,
     * rar is the index of the next of the last elements */
    public ArrayDeque() {
        length = 8;
        items = (T[]) new Object[length];
        front = rar = 0;
    }


    /** Resize method. */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int j = 0;
        for (int i = front; i != rar; i = mod(i + 1)) {
            a[j++] = items[i];
        }
        rar = size();
        front = 0;
        items = a;
        length = capacity;
    }

    /** Check if the array has been full. */
    private boolean isFull() {
        return mod(rar + 1) == front;
    }

    /** Adds an item of type T to the front of the deque.
     * You can assume that item is never null. */
    @Override
    public void addFirst(T item) {
        if (isFull()) {
            resize(length * 2);
        }
        front = mod(front - 1);
        items[front] = item;
    }

    /** Adds an item of type T to the back of the deque.
     * You can assume that item is never null. */
    @Override
    public void addLast(T item) {
        if (isFull()) {
            resize(length * 2);
        }
        items[rar] = item;
        rar = mod(rar + 1);
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return mod(rar - front);
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line. */
    @Override
    public void printDeque() {
        for (int i = front; i != rar; i = mod(i + 1)) {
            System.out.print(items[i].toString() + ' ');
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (length > 8 && (size() - 1) < length * 0.25) {
            resize(length / 2);
        }
        T val = items[front];
        items[front] = null;
        front = mod(front + 1);
        return val;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null. */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (length > 8 && (size() - 1) < length * 0.25) {
            resize(length / 2);
        }
        rar = mod(rar - 1);
        T val = items[rar];
        items[rar] = null;
        return val;
    }

    /** Gets the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque! */
    @Override
    public T get(int index) {
        if (index >= size()) {
            return null;
        }
        return items[mod(front + index)];
    }

    /* get the front index. */
    private int getFront() {
        return this.front;
    }
    private int getRar() {
        return this.rar;
    }
    private int getLength() {
        return this.length;
    }

    /** The Deque objects we’ll make are iterable (i.e. Iterable<T>)
     * so we must provide this method to return an iterator. */
    public Iterator<T> iterator() {
        return new ArrayDeque.LLDIterator();
    }

    /** A Class for implement the details of iterator. */
    private class LLDIterator implements Iterator<T> {

        private int index;
        private int length;
        private int rar;

        LLDIterator() {
            index = getFront();
            length = getLength();
            rar = getRar();
        }

        public boolean hasNext() {
            return index != rar;
        }

        public T next() {
            T nextVal = items[index];
            index = mod(index + 1);
            return nextVal;
        }
    }

    /** Returns whether or not the parameter o is equal to the Deque.
     *  o is considered equal if it is a Deque and if it contains the same
     *  contents (as goverened by the generic T’s equals method) in the same order.*/
    public boolean equals(Object o) {
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        ArrayDeque<?> other = (ArrayDeque<?>) o;
        if (other.size() != size()) {
            return false;
        }
        Iterator<T> i1 = (Iterator<T>) this.iterator();
        Iterator<T> i2 = (Iterator<T>) other.iterator();
        while (i1.hasNext()) {
            if (!i1.next().equals(i2.next())) {
                return false;
            }
        }
        return true;
    }
}
