package collections.queue;

public class ArrayQueueImpl<T> implements Queue<T> {

    private T[] arr = (T[]) new Object[10];
    private int first;
    private int last;
    private int size;

    @Override
    public void enqueue(T item) {
        if (size == arr.length) {
            resizeArray(arr.length * 2);
        }
        arr[last++] = item;
        size++;
        if (last == arr.length) {
            last = 0;
        }
    }


    @Override
    public T dequeue() {

        T result = arr[first];
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
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void resizeArray(int capacity) {
        T[] biggerArr = (T[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            biggerArr[i] = arr[(i + first) % arr.length];
        }
        arr = biggerArr;
        first = 0;
        last = size;
    }
}
