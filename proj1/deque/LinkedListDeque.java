package deque;

import java.util.Iterator;

/**
 * construct Deque by Link list.
 */
public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    /** construct the node of the list.*/
    public class Node {
        private T item;
        private Node next;
        private Node prev;

        public Node(T item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node sentinel;
    private int size;

    /** Create an empty linked list deque. */
    public LinkedListDeque() {
        this.sentinel = new Node(null, null, null);
        this.sentinel.next = sentinel;
        this.sentinel.prev = sentinel;
        this.size = 0;
    }

    /** Adds an item of type T to the front of the deque. You can assume that item is never null. */
    @Override
    public void addFirst(T item) {
        Node n = new Node(item, sentinel.next, sentinel);
        sentinel.next.prev = n;
        sentinel.next = n;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. You can assume that item is never null. */
    @Override
    public void addLast(T item) {
        Node n = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.next = n;
        sentinel.prev = n;
        size += 1;
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line. */
    @Override
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item.toString() + ' ');
            p = p.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    @Override
    public T removeFirst() {
        if (isEmpty()) return null;
        T value = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return value;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    @Override
    public T removeLast() {
        if (isEmpty()) return null;
        T value = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return value;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque! */
    @Override
    public T get(int index) {
        if (index >= size) return null;
        Node p = sentinel.next;
        int i = 0;
        while (i <= index) {
            if (i == index) break;
            i += 1;
            p = p.next;
        }
        return p.item;
    }

    public T getRecursiveHelper(Node p, int index, int i) {
        if (i == index) return p.item;
        return getRecursiveHelper(p.next, index, i + 1);
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index >= size) return null;
        return getRecursiveHelper(sentinel.next, index, 0);
    }

    /** The Deque objects we’ll make are iterable (i.e. Iterable<T>)
     * so we must provide this method to return an iterator. */
    public Iterator<T> iterator() {
        return new LLDIterator();
    }

    /** A Class for implement the details of iterator. */
    private class LLDIterator implements Iterator<T> {
        private Node p;

        public LLDIterator() {
            p = getSentinel();
        }

        public boolean hasNext() {
            if (p.next == getSentinel()) return false;
            return true;
        }

        public T next() {
            T nextVal = p.next.item;
            p = p.next;
            return nextVal;
        }
    }

    /** Help the Iterator get the sentinel. */
    public Node getSentinel() {
        return this.sentinel;
    }

    /** Returns whether or not the parameter o is equal to the Deque.
     *  o is considered equal if it is a Deque and if it contains the same
     *  contents (as goverened by the generic T’s equals method) in the same order.*/
    public boolean equals(Object o) {
        if (! (o instanceof LinkedListDeque)) return false;
        LinkedListDeque<?> other = (LinkedListDeque<?>) o;
        if (other.size() != size()) return false;
        Node p1 = sentinel.next;
        LinkedListDeque<?>.Node p2 = other.sentinel.next;
        while (p1 != sentinel && p2 != other.sentinel) {
            if (p1.item != p2.item) return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }
}
