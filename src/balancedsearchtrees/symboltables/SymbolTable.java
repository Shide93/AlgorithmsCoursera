package balancedsearchtrees.symboltables;

public interface SymbolTable<K extends Comparable<K>, V> {

    void put(K key, V value);

    Object get(K key);

    void delete(K key);

    boolean contains(K key);

    boolean isEmpty();

    int size();

    Iterable<K> keys();

    /**
     * Previous closest elem
     */
    K floor(K key);

    /**
     * Next closest elem
     */
    K celling(K key);

    K max();

    K min();

    /**
     * How many keys <k?
     */
    int rank(K key);
}
