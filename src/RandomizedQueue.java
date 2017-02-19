import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.*;

public class RandomizedQueue<Item> implements Iterable<Item> {


    private Item[] arr = (Item[]) new Object[10];
    private int first;
    private int last;
    private int size;

    public RandomizedQueue() {
    }               // construct an empty randomized queue

    public boolean isEmpty() {
        return size == 0;
    }               // is the queue empty?

    public int size() {
        return size;
    }                // return the number of items on the queue

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (size == arr.length) {
            resizeArray(arr.length * 2);
        }
        arr[last++] = item;
        size++;
        if (last == arr.length) {
            last = 0;
        }
    }       // add the item

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int randIndex = getRandomIndex();
        Item result = arr[randIndex];
        arr[randIndex] = arr[first];

        arr[first] = null;
        first++;
        size--;
        if (first == arr.length) {
            first = 0;
        }
        if (size > 0 && size == arr.length / 4) {
            resizeArray(arr.length / 2);
        }
        return result;
    }            // remove and return a random item

    private void resizeArray(int capacity) {
        Item[] biggerArr = (Item[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            biggerArr[i] = arr[(i + first) % arr.length];
        }
        arr = biggerArr;
        first = 0;
        last = size;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return arr[getRandomIndex()];
    }             // return (but do not remove) a random item

    private int getRandomIndex() {
        return (StdRandom.uniform(size) + first) % arr.length;
    }

    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }       // return an independent iterator over items in random order

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < 101; i++) {
            queue.enqueue(i);
        }
        System.out.println("size:" + queue.size());
        for (Integer i : queue) {
            System.out.println(i);
        }
        System.out.println("size:" + queue.size());
        for (int i = 0; i < 101; i++) {
            System.out.println(queue.dequeue());
        }
        System.out.println("size:" + queue.size());


    }  // unit testing (optional)

    private class RandomQueueIterator implements Iterator<Item> {

        private int next = 0;
        private Item[] iterArr;

        public RandomQueueIterator() {
            iterArr = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                iterArr[i] = arr[(i + first) % arr.length];
            }
            StdRandom.shuffle(iterArr);
        }

        @Override
        public boolean hasNext() {
            return next < iterArr.length;
        }

        @Override
        public Item next() {
            if (next >= iterArr.length) {
                throw new NoSuchElementException();
            }
            return iterArr[next++];
        }
    }

}
