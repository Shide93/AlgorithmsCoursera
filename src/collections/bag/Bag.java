package collections.bag;

import java.util.Iterator;

/**
 * Created by Shide on 04.02.2017.
 */
public interface Bag<T> extends Iterator<T> {
    void add(T value);
    int size();
    
}
