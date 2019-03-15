import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public final class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;         // array of items
    private int n;

    public RandomizedQueue() {
        a = (Item[]) new Object[2];
        n = 0;
    }                 // construct an empty randomized queue

    public boolean isEmpty() {
        return n == 0;
    }                 // is the randomized queue empty?

    public int size() {
        return n;
    }            // return the number of items on the randomized queue

    private void resize(final int capacity) {
        assert capacity >= n;

        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void enqueue(final Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (n == a.length) {
            resize(2 * a.length);
        }    // double size of array if necessary
        a[n++] = item;
    }           // add the item

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int num = StdRandom.uniform(n);
        Item item = a[num];
        a[num] = null;                              // to avoid loitering
        n--;
        // shrink size of array if necessary
        if (n > 0 && n == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }                    // remove and return a random item

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = a[StdRandom.uniform(n)];
        return item;
    }                    // return a random item (but do not remove it)

    public Iterator<Item> iterator() {
        return new RandQueueIterator();
    }         // return an independent iterator over items in random order

    private class RandQueueIterator implements Iterator<Item> {
        private int i = n;

        RandQueueIterator() {
            StdRandom.shuffle(a);
        }

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            if (i == 0) {
                throw new NoSuchElementException();
            }
            --i;
            while (a[i] == null) {
                --i;
            }
            return a[i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

//    public static void main(String[] args) {
//        RandomizedQueue randomizedQueue = new RandomizedQueue();
//        randomizedQueue.enqueue("1");
//        randomizedQueue.enqueue("2");
//        randomizedQueue.enqueue("3");
//        randomizedQueue.enqueue("4");
//        for (Object s : randomizedQueue) {
//            for (Object m : randomizedQueue) {
//                System.out.println(s + " " + m);
//            }
//            System.out.println(s);
//        }
//    }   // unit testing (optional)
}
