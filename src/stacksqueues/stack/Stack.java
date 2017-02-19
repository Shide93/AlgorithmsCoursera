package stacksqueues.stack;

public interface Stack<T> {

    void push(T value);

    T pop();
    boolean isEmpty();
    int size();
}
