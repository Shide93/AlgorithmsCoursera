package collections.stack;

import java.util.Iterator;

public class ArrayStackImpl<T> implements Stack<T> {
    T[] arr = (T[]) new Object[10];
    int head;

    @Override
    public void push(T value) {
        if (head == arr.length) {
            resizeArray(arr.length * 2);
        }
        arr[head++] = value;
    }

    @Override
    public T pop() {
        if (head == 0) {
            return null;
        }
        T result = arr[--head];
        arr[head] = null;
        if (head == arr.length / 4) {
            resizeArray(arr.length / 2);
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return head == 0;
    }

    @Override
    public int size() {
        return arr.length;
    }

    private void resizeArray(int capacity) {
        T[] biggerArr = (T[]) new Object[capacity];
        for (int i = 0; i < arr.length; i++) {
            biggerArr[i] = arr[i];
        }
        arr = biggerArr;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T> {

        private int current = arr.length - 1;

        @Override
        public boolean hasNext() {
            return current >= 0;
        }

        @Override
        public T next() {
            return arr[--current];
        }
    }
}
