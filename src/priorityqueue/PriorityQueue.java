package priorityqueue;

import java.util.ArrayList;

public class PriorityQueue<T extends Comparable<T>> {

    private T[] heap;
    private int N;

    public PriorityQueue() {
        heap = (T[]) new Object[100];
    }

    public void insert(T key) {
        heap[N++] = key;
        swim(N);
    }

    public T delMax() {
        T max = heap[1];
        swap(1, N--);
        sink(1);
        heap[N + 1] = null;
        return max;
    }

    public boolean isEmpty() {
        return heap[1] == null;
    }

    private void swim(int i) {
        while (i > 1 && less(i / 2, i)) {
            swap(i, i / 2);
            i = i / 2;
        }
    }

    private void sink(int i) {
        while (2 * i <= N) {
            int j = 2 * i;

            if (j < N && less(j, j + 1)) j++;
            if (!less(i, j)) break;
            swap(i, i / 2);
            i = j;

        }
    }

    private boolean less(int v, int w) {
        return heap[v].compareTo(heap[w]) < 0;
    }

    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
