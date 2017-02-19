package stacksqueues.stack;

/**
 * Constant operations time.
 * Uses 40N bytes of memory.
 * @param <T>
 */
public class LinkedListStackImpl<T> implements Stack<T> {

    private Node stackHead;
    private int size;

    @Override
    public void push(T value) {
        stackHead = new Node(value, stackHead);
        size++;
    }

    @Override
    public T pop() {
        if (stackHead == null) {
            return null;
        }
        T result = stackHead.value;
        stackHead = stackHead.nextNode;
        size--;
        return result;
    }

    @Override
    public boolean isEmpty() {
        return stackHead == null;
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

