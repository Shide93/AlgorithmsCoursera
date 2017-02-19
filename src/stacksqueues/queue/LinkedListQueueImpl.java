package stacksqueues.queue;

public class LinkedListQueueImpl<T> implements Queue<T> {

    int size;
    private Node first;
    private Node last;

    @Override
    public void enqueue(T item) {
        Node oldLast = last;
        last = new Node(item, null);
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.nextNode = last;
        }
        size++;
    }

    @Override
    public T dequeue() {
        T result = first.value;
        first = first.nextNode;
        if (isEmpty()) {
            last = null;
        }
        size--;
        return result;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return size;
    }

    private class Node {
        T value;
        Node nextNode;

        public Node(T value, Node nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }


    }
}
