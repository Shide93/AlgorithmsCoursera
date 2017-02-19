package sorting.collinear;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> segments;
    private Point[] slopeOrdered;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }
        segments = new ArrayList<>();
        slopeOrdered = new Point[points.length - 1];

        Arrays.sort(points);

        for (int p = 0; p < points.length; p++) {
            if (points[p] == null) {
                throw new NullPointerException();
            }
            if (p < points.length - 1 && points[p].compareTo(points[p + 1]) == 0) {
                throw new IllegalArgumentException();
            }

            slopeOrdered = Arrays.copyOfRange(points, p + 1, points.length);

            Arrays.sort(slopeOrdered, points[p].slopeOrder());


            int start = 0;
            int end = 0;
            Point endPoint = points[p];
            for (int q = 1; q < slopeOrdered.length; q++) {
                if (points[p].slopeTo(slopeOrdered[q]) != points[p].slopeTo(slopeOrdered[q - 1])) {
                    start = q;
                    endPoint = slopeOrdered[q];
                } else if (endPoint.compareTo(slopeOrdered[q]) < 0) {
                    endPoint = slopeOrdered[q];
                }
                end = q;

                if (end - start >= 2) {

                    segments.add(new LineSegment(points[p], endPoint));
                }
            }
        }

    }

    // finds all line segments containing 4 points

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In("rs1423.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    private void copyToSlopedArray(Point[] points, Point point) {
        int i = 0;
        for (Point p : points) {
            if (point.compareTo(p) == 0) {
                continue;
            }
            slopeOrdered[i++] = p;
        }
    }

    public int numberOfSegments() {
        return segments.size();
    } // the number of line segments

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }   // the line segments
}
