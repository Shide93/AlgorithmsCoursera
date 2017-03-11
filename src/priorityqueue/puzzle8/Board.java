package priorityqueue.puzzle8;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int[][] blocks;

    public Board(int[][] blocks) {
        this.blocks = new int[blocks.length][];
        for (int i = 0; i < blocks.length; i++) {
            this.blocks[i] = new int[blocks[i].length];
            System.arraycopy(blocks[i], 0, this.blocks[i], 0, blocks[i].length);
        }
    }        // construct a board from an n-by-n array of blocks

    public static void main(String[] args) {

    } // unit tests (not graded)

    // (where blocks[i][j] = block in row i, column j)
    public int dimension() {
        return blocks.length;
    }      // board dimension n

    public int hamming() {
        int num = 1;
        int hamming = 0;
        for (int[] row : blocks) {
            for (int elem : row) {
                if (elem != 0 && elem != num) {
                    hamming++;
                }
                num++;
            }
        }
        return hamming;
    }    // number of blocks out of place

    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                if (!isSpace(i, j)) {
                    manhattan += countMDistance(i, j);
                }
            }
        }
        return manhattan;
    }  // sum of Manhattan distances between blocks and goal

    private int countMDistance(int i, int j) {
        int block = blocks[i][j];

        int row = (block - 1) / dimension();
        int col = (block - 1) % dimension();

        return Math.abs(i - row) + Math.abs(j - col);
    }

    public boolean isGoal() {
        int num = 1;
        for (int[] row : blocks) {
            for (int elem : row) {
                if (elem != 0 && elem != num) {
                    return false;
                }
                num++;
            }
        }
        return true;
    }   // is this board the goal board?

    public Board twin() {
        Board twin = new Board(blocks);
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length - 1; j++) {
                if (!isSpace(i, j) && !isSpace(i, j + 1)) {
                    twin.swap(i, j, i, j + 1);
                    return twin;
                }
            }
        }
        return null;
    } // a board that is obtained by exchanging any pair of blocks

    public boolean equals(Object y) {
        if (y == this) return true;

        if (y == null || !(y.getClass() == Board.class) || ((Board) y).blocks.length != this.blocks.length) {
            return false;
        }
        for (int i = 0; i < blocks.length; i++) {
            if (blocks[i].length != ((Board) y).blocks[i].length) return false;
            for (int j = 0; j < blocks[i].length; j++) {
                if (((Board) y).blocks[i][j] != blocks[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }  // does this board equal y?

    public Iterable<Board> neighbors() {
        List<Board> neighbours = new ArrayList<>();
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (isSpace(i, j)) {
                    // moveup
                    if (i - 1 >= 0) {
                        Board board = new Board(blocks);
                        board.swap(i, j, i - 1, j);
                        neighbours.add(board);
                    }
                    // movedown
                    if (i + 1 < dimension()) {
                        Board board = new Board(blocks);
                        board.swap(i, j, i + 1, j);
                        neighbours.add(board);
                    }
                    // moveright
                    if (j - 1 >= 0) {
                        Board board = new Board(blocks);
                        board.swap(i, j, i, j - 1);
                        neighbours.add(board);
                    }
                    // moveleft
                    if (j + 1 < dimension()) {
                        Board board = new Board(blocks);
                        board.swap(i, j, i, j + 1);
                        neighbours.add(board);
                    }
                    return neighbours;
                }
            }
        }


        return neighbours;
    }   // all neighboring boards

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(blocks.length).append("\n");
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }    // string representation of this board (in the output format specified below)

    private boolean isSpace(int block) {
        return block == 0;
    }

    private boolean isSpace(int i, int j) {
        return isSpace(blocks[i][j]);
    }

    private void swap(int row1, int col1, int row2, int col2) {
        int temp = blocks[row1][col1];
        blocks[row1][col1] = blocks[row2][col2];
        blocks[row2][col2] = temp;
    }
}