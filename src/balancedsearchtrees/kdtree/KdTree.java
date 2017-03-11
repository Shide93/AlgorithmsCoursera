package balancedsearchtrees.kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.List;

public class KdTree {

    private Node root;

    public KdTree() {

    }                      // construct an empty set of points

    public static void main(String[] args) {

    }  // unit testing of the methods (optional)

    public boolean isEmpty() {

        return root == null;
    }                     // is the set empty?

    public int size() {

        return 0;
    }                    // number of points in the set

    public void insert(Point2D p) {

    }// add the point to the set (if it is not already in the set)

    public boolean contains(Point2D p) {

        return false;
    }      // does the set contain point p?

    public void draw() {

    }     // draw all points to standard draw

    public Iterable<Point2D> range(RectHV rect) {

        List<Point2D> inRect = new ArrayList<>();


        return inRect;

    }    // all points that are inside the rectangle

    private void range(Node root, List<Point2D> inRect, RectHV rect) {

    }


    public Point2D nearest(Point2D p) {

        return p;
    }   // a nearest neighbor in the set to point p; null if the set is empty


    private class Node {
        private int key;
        private Point2D value;
        private boolean coordType;
        private Node left;
        private Node right;
        private int count;

        public Node(int key, Point2D value, boolean coordType) {
            this.key = key;
            this.value = value;
            this.coordType = coordType;
        }
    }
}
