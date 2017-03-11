package balancedsearchtrees.symboltables;


import edu.princeton.cs.algs4.Queue;

public class SymbolTableBST<K extends Comparable<K>, V> implements SymbolTable<K, V> {
    private Node root;

    public SymbolTableBST() {
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        }
        int cmpResult = key.compareTo(node.key);
        if (cmpResult < 0) {
            node.left = put(node.left, key, value);
        } else if (cmpResult > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public V get(K key) {
        Node nodePointer = root;
        while (nodePointer != null) {
            int cmpResult = key.compareTo(nodePointer.key);
            if (cmpResult > 0) {
                nodePointer = nodePointer.right;
            } else if (cmpResult < 0) {
                nodePointer = nodePointer.left;
            } else {
                return nodePointer.value;
            }
        }
        return null;
    }

    @Override
    public void delete(K key) {
        root = delete(root, key);
    }

    public Node delete(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }

            Node tmp = node;
            node = min(tmp.right);
            node.right = deleteMin(tmp.right);
            node.left = tmp.left;
        }
        node.count = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.count;
    }

    @Override
    public Iterable<K> keys() {
        Queue<K> q = new Queue<>();
        inorderTraversal(root, q);
        return q;
    }

    private void inorderTraversal(Node node, Queue<K> q) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, q);
        q.enqueue(node.key);
        inorderTraversal(node.right, q);
    }

    private void postorderTraversal(Node node, Queue<K> q) {
        if (node == null) {
            return;
        }
        postorderTraversal(node.left, q);
        postorderTraversal(node.right, q);
        q.enqueue(node.key);
    }

    private void preorderTraversal(Node node, Queue<K> q) {
        if (node == null) {
            return;
        }
        q.enqueue(node.key);
        preorderTraversal(node.left, q);
        preorderTraversal(node.right, q);
    }

    private void breadthFirstTraversal(Node root, Queue<K> q) {
        if (root == null) {
            return;
        }
        Queue<Node> nextNodes = new Queue<>();
        nextNodes.enqueue(root);
        while (!nextNodes.isEmpty()) {
            Node current = nextNodes.dequeue();
            q.enqueue(current.key);

            if (current.left != null) {
                nextNodes.enqueue(current.left);
            }
            if (current.right != null) {
                nextNodes.enqueue(current.right);
            }

        }

    }

    @Override
    public K floor(K key) {
        Node node = floor(root, key);
        return node == null ? null : node.key;
    }

    private Node floor(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);

        if (cmp == 0) {
            return node;
        }
        if (cmp < 0) return floor(node.left, key);

        Node t = floor(node.right, key);

        return t == null ? node : t;
    }

    @Override
    public K celling(K key) {
        Node node = celling(root, key);
        return node == null ? null : node.key;
    }

    private Node celling(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);

        if (cmp == 0) {
            return node;
        }
        if (cmp > 0) return celling(node.right, key);

        Node t = celling(node.left, key);

        return t == null ? node : t;
    }

    @Override
    public K max() {
        if (isEmpty()) return null;

        Node max = root;
        while (max.right != null) {
            max = max.right;
        }
        return max.key;
    }

    @Override
    public K min() {
        if (isEmpty()) return null;

        return min(root).key;
    }

    private Node min(Node node) {
        Node min = node;
        while (min.left != null) {
            min = min.left;
        }
        return min;
    }

    @Override
    public int rank(K key) {
        return rank(key, root);
    }

    private int rank(K key, Node node) {
        if (node == null) {
            return 0;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return rank(key, node.left);
        } else if (cmp > 0) {
            return 1 + size(node.left) + rank(key, node.right);
        } else {
            return size(node.left);
        }
    }

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int count;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}
