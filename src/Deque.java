import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.*;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node first;
    private Node last;

    public Deque() {
    }                         // construct an empty deque

    public boolean isEmpty() {
        return first == null && last == null;
    }              // is the deque empty?

    public int size() {
        return size;
    }                 // return the number of items on the deque

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node oldFirst = first;
        first = new Node(item, oldFirst, null);
        if (last == null) {
            last = first;
        } else {
            oldFirst.prevNode = first;
        }
        size++;
    }       // add the item to the front

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node oldLast = last;
        last = new Node(item, null, oldLast);
        if (first == null) {
            first = last;
        } else {
            oldLast.nextNode = last;
        }
        size++;
    }         // add the item to the end

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item result = first.value;
        first = first.nextNode;
        if (first == null) {
            last = null;
        } else {
            first.prevNode = null;
        }
        size--;
        return result;
    }         // remove and return the item from the front

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item result = last.value;
        last = last.prevNode;
        if (last == null) {
            first = null;
        } else {
            last.nextNode = null;
        }
        size--;
        return result;
    }          // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }       // return an iterator over items in order from front to end

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(1);
        deque.addLast(1);
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());


        deque.addFirst(1);
        deque.addFirst(1);
        deque.addFirst(1);
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());

        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        for (Integer integer : deque) {
            System.out.println(integer);
        }
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());


    }   // unit testing (optional)

    private class DequeIterator implements Iterator<Item> {
        Node next = first;

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Item next() {
            if (next == null) {
                throw new NoSuchElementException();
            }
            Item result = next.value;
            next = next.nextNode;
            return result;
        }
    }

    private class Node {
        Item value;
        Node nextNode;
        Node prevNode;

        public Node(Item value, Node nextNode, Node prevNode) {
            this.value = value;
            this.nextNode = nextNode;
            this.prevNode = prevNode;
        }
    }
}