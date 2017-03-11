package priorityqueue.puzzle8;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


public class Solver {
    private Move currentMove;
    private Move currentTwinMove;

    public Solver(Board initial) {
        MinPQ<Move> openList = new MinPQ<>();
        currentMove = new Move(initial);

        MinPQ<Move> openTwinList = new MinPQ<>();
        currentTwinMove = new Move(initial.twin());

        while (!currentMove.board.isGoal() && !currentTwinMove.board.isGoal()) {
            for (Board neighbour : currentMove.board.neighbors()) {
                // critical optimization
                if (currentMove.previous != null && neighbour.equals(currentMove.previous.board)) continue;
                // if not in open
                openList.insert(new Move(neighbour, currentMove));
            }
            currentMove = openList.delMin();

            for (Board neighbour : currentTwinMove.board.neighbors()) {
                // critical optimization
                if (currentTwinMove.previous != null && neighbour.equals(currentTwinMove.previous.board)) continue;
                // if not in open
                openTwinList.insert(new Move(neighbour, currentTwinMove));
            }
            currentTwinMove = openTwinList.delMin();
        }
    }        // find a solution to the initial board (using the A* algorithm)

    public static void main(String[] args) {

        // create initial board from file
        In in = new In("puzzle3x3-31.txt");
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    } // solve a slider puzzle (given below)

    public boolean isSolvable() {
        return currentMove.board.isGoal();
    }      // is the initial board solvable?

    public int moves() {
        return isSolvable() ? currentMove.numMoves : -1;
    }    // min number of moves to solve initial board; -1 if unsolvable

    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }

        Stack<Board> solution = new Stack<>();
        Move iter = currentMove;
        while (iter != null) {
            solution.push(iter.board);
            iter = iter.previous;
        }
        return solution;
    }  // sequence of boards in a shortest solution; null if unsolvable

    private class Move implements Comparable<Move> {

        private Move previous;
        private Board board;
        private int numMoves;

        public Move(Board board) {
            this.board = board;
        }

        public Move(Board board, Move previous) {
            this.board = board;
            this.previous = previous;
            this.numMoves = previous.numMoves + 1;
        }

        public int compareTo(Move move) {
            return (this.board.manhattan() - move.board.manhattan()) + (this.numMoves - move.numMoves);
        }

    }
}