package balancedsearchtrees.kdtree;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

public class KdTree {

    private Node root;
    private int size;

    public KdTree() {

    }                      // construct an empty set of points

    public static void main(String[] args) {

    }  // unit testing of the methods (optional)

    public boolean isEmpty() {

        return root == null;
    }                     // is the set empty?

    public int size() {

        return size;
    }                    // number of points in the set

    public void insert(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        root = insert(root, p, true);
    }// add the point to the set (if it is not already in the set)

    private Node insert(Node root, Point2D p, boolean coordType) {
        if (root == null) {
            double key = coordType ? p.x() : p.y();
            size++;
            return new Node(key, p, coordType);
        }
        double coord = root.coordType ? p.x() : p.y();
        if (coord < root.key) {
            root.left = insert(root.left, p, !root.coordType);
        } else if (coord > root.key) {
            root.right = insert(root.right, p, !root.coordType);
        } else if (!root.value.equals(p)) {
            root.right = insert(root.right, p, !root.coordType);

        }
        return root;
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        return contains(root, p);
    }      // does the set contain point p?

    private boolean contains(Node root, Point2D p) {
        if (root == null) return false;
        if (root.value.equals(p)) return true;

        double coord = root.coordType ? p.x() : p.y();
        if (coord < root.key) {
            return contains(root.left, p);
        } else if (coord >= root.key) {
            return contains(root.right, p);
        } else {
            return true;
        }
    }

    public void draw() {
        draw(root, 0, 1, 1);
    }     // draw all points to standard draw

    private void draw(Node root, double from, double prevX, double prevY) {
        if (root == null) return;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        root.value.draw();
//        StdDraw.text(root.value.x(), root.value.y(), root.value.toString());

        StdDraw.setPenRadius();
        if (root.coordType) {
            StdDraw.setPenColor(StdDraw.RED);
            if (root.value.y() >= from) {
                if (prevY > from) {
                    StdDraw.line(root.key, from, root.key, prevY);
                } else {
                    StdDraw.line(root.key, from, root.key, 1);
                }
            } else {
                if (prevY < from) {
                    StdDraw.line(root.key, from, root.key, prevY);
                } else {
                    StdDraw.line(root.key, from, root.key, 0);
                }
            }
            prevY = from;
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            if (root.value.x() >= from) {
                if (prevX > from) {
                    StdDraw.line(from, root.key, prevX, root.key);
                } else {
                    StdDraw.line(from, root.key, 1, root.key);
                }
            } else {
                if (prevX < from) {
                    StdDraw.line(from, root.key, prevX, root.key);
                } else {
                    StdDraw.line(from, root.key, 0, root.key);
                }

            }
            prevX = from;       //TODO: check corectness
        }
        draw(root.left, root.key, prevX, prevY);
        draw(root.right, root.key, prevX, prevY);

    }
    private double findMinBorder(Node node, Node targetNode, double from) {
        if (targetNode.coordType != node.coordType) {

        }//TODO: SCAN FROM ROOT AND FIND BORDER COORDINATES
        return 0;
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new NullPointerException();
        }
        List<Point2D> inRect = new ArrayList<>();

        range(root, inRect, rect);
        return inRect;

    }    // all points that are inside the rectangle

    private void range(Node root, List<Point2D> inRect, RectHV rect) {
        if (root == null) {
            return;
        }
        if (rect.contains(root.value)) {
            inRect.add(root.value);
        }
        double maxCoord = root.coordType ? rect.xmax() : rect.ymax();
        double minCoord = root.coordType ? rect.xmin() : rect.ymin();
        if (maxCoord < root.key || minCoord < root.key) {
            range(root.left, inRect, rect);
        }
        if (minCoord >= root.key || maxCoord >= root.key) {
            range(root.right, inRect, rect);
        }
    }


    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new NullPointerException();
        }
        if (isEmpty()) {
            return null;
        }
        return nearest(root, p, root.value);
    }   // a nearest neighbor in the set to point p; null if the set is empty

    public Point2D nearest(Node root, Point2D query, Point2D nearestPoint) {
        if (root == null) {
            return nearestPoint;
        }
        if (nearestPoint == null ||
                query.distanceSquaredTo(root.value) < query.distanceSquaredTo(nearestPoint)) {
            nearestPoint = root.value;
        }
        double queryCoord = root.coordType ? query.x() : query.y();
        double distanceToLine = (queryCoord - root.key) * (queryCoord - root.key);

        if (queryCoord < root.key) {
            nearestPoint = nearest(root.left, query, nearestPoint);
            if (query.distanceSquaredTo(nearestPoint) > distanceToLine) {
                nearestPoint = nearest(root.right, query, nearestPoint);
            }

        } else {
            nearestPoint = nearest(root.right, query, nearestPoint);
            if (query.distanceSquaredTo(nearestPoint) > distanceToLine) {
                nearestPoint = nearest(root.left, query, nearestPoint);
            }
        }
        return nearestPoint;
    }

    private class Node {
        private double key;
        private Point2D value;
        private boolean coordType;      //true is x coord, false is y coord
        private Node left;
        private Node right;

        public Node(double key, Point2D value, boolean coordType) {
            this.key = key;
            this.value = value;
            this.coordType = coordType;
        }
    }
}
