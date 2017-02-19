package stacksqueues.queue;

public class ArrayQueueImpl<T> implements Queue<T> {

    T[] arr = (T[]) new Object[10];
    int first;
    int last;

    @Override
    public void enqueue(T item) {
        arr[last++] = item;

    }

    @Override
    public T dequeue() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    private void resizeArray(int capacity) {
        T[] biggerArr = (T[]) new Object[capacity];

//todo: toWhile
        for (int i = 0; i < arr.length; i++) {
            biggerArr[i] = arr[i];
        }


        arr = biggerArr;
    }
}
