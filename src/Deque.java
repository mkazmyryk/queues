import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @param <Item> - generic parameter for dequeue
 * @author Kazmyryk.M
 * @version 1.0
 */
public final class Deque<Item> implements Iterable<Item> {
    /**
     * Field with reference of first node.
     */
    private Node first;

    /**
     * Field with reference of last node.
     */
    private Node last;

    /**
     * Field with size of deque.
     */
    private int size = 0;

    /**
     *
     */
    public Deque() {
        this.first = null;
        this.last = null;
    }                           // construct an empty deque

    /**
     * @return size
     */
    public boolean isEmpty() {
        return size == 0;
    }                 // is the deque empty?

    /**
     * @return size of deque
     */
    public int size() {
        return size;
    }                        // return the number of items on the deque

    /**
     * @param item - item that will be added to deque
     */
    public void addFirst(final Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (first == null) {
            first = new Node();
            first.item = item;
            last = first;
            size++;
        } else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            oldFirst.previous = first;
            size++;
            if (oldFirst.next == null) {
                last = oldFirst;
            }
        }

    }          // add the item to the front

    /**
     * @param item - item that will be added to deque
     */
    public void addLast(final Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (first == null) {
            first = new Node();
            first.item = item;
            last = first;
            size++;
        } else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.previous = oldLast;
            oldLast.next = last;
            size++;
            if (oldLast.previous == null) {
                first = oldLast;
            }
        }
    }           // add the item to the end

    /**
     * @return item that will be removed
     */
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item removedItem = first.item;
        if (first.next != null) {
            first = first.next;
            first.previous = null;
            size--;
        } else {
            first = null;
            last = null;
            size--;
        }
        return removedItem;
    }               // remove and return the item from the front

    /**
     * @return item that will be removed
     */
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item removedItem = last.item;
        if (last.previous != null) {
            last = last.previous;
            last.next = null;
            size--;
        } else {
            last = null;
            first = null;
            size--;
        }
        return removedItem;
    }                 // remove and return the item from the end

    /**
     * @return iterator
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }        // return an iterator over items in order from front to end

    /**
     * Inner class. Represents node.
     *
     * @author Kazmyryk.M
     * @version 1.0
     */
    private class Node {
        /**
         * Field with item.
         */
        private Item item;
        /**
         * Field with reference of next node.
         */
        private Node next;
        /**
         * Field with reference of previous node.
         */
        private Node previous;

        /**
         * Default constructor.
         * Init all fields with null.
         */
        Node() {
            this.item = null;
            this.next = null;
            this.previous = null;
        }
    }

    /**
     * Simple class that represents deque iterator.
     */
    private class DequeIterator implements Iterator<Item> {
        /**
         * Field with current reference.
         */
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

//    public static void main(String[] args) {
//        Deque<String> deque = new Deque<>();
//        String s = "1";
//        String d = "2";
//        String c = "3";
//        String e = "4";
//        deque.addLast(s);
//        deque.addLast(d);
//        deque.addLast(c);
//        deque.removeLast();
//        for (String sr : deque) {
//            System.out.println(sr);
//        }
//    }   // unit testing (optional)
}
