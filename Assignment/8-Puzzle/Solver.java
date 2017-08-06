import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayDeque;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class Solver
{
    private boolean solvable;
    private int moveCount;
    private ArrayDeque<Board> solutionDeque = new ArrayDeque<Board>();
     
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) // Solver with double minPQ.
    {
        MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>();
        MinPQ<SearchNode> minPQTwin = new MinPQ<SearchNode>();
        
        minPQ.insert(new SearchNode(initial));
        minPQTwin.insert(new SearchNode(initial.twin()));
        
        boolean running = true;
        while (running)
        {
            SearchNode current = minPQ.delMin();
            SearchNode currentTwin = minPQTwin.delMin();
            
            if (currentTwin.board.isGoal())
            {
                this.moveCount = -1;
                this.solvable = false;
                running = false;
            }
            
            if (current.board.isGoal())
            {
                this.moveCount = current.moves;
                this.solvable = true;
                
                // Fill up solutionDeque with all solution boards.
                while (current != null)
                {
                    solutionDeque.addFirst(current.board);
                    current = current.prevNode;
                }
                
                running = false;
            }
            
            else
            {
                for (Board neighborBoard : current.board.neighbors())
                {
                    if (current.prevNode == null || 
                        !neighborBoard.equals(current.prevNode.board))
                    {
                        SearchNode neighborNode = new SearchNode(neighborBoard);
                        neighborNode.moves = current.moves + 1;
                        neighborNode.prevNode = current;
                        minPQ.insert(neighborNode);
                    }
                }
                
                for (Board neighborBoardTwin : currentTwin.board.neighbors())
                {
                    if (currentTwin.prevNode == null ||
                        !neighborBoardTwin.equals(currentTwin.prevNode.board))
                    {
                        SearchNode neighborNodeTwin = new SearchNode(neighborBoardTwin);
                        neighborNodeTwin.moves = currentTwin.moves + 1;
                        neighborNodeTwin.prevNode = currentTwin;
                        minPQTwin.insert(neighborNodeTwin);
                    }
                }
            }
        }
    }
    
    // is the initial board solvable?
    public boolean isSolvable() 
    {
        return solvable;
    }
    
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() 
    {
        return moveCount;
    }
    
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() 
    {
        if (solvable)
        {
            return solutionDeque;
        }
        else
        {
            return null;
        }
    }
    
    private class SearchNode implements Comparable<SearchNode>
    {
        private Board board;
        private int moves;
        private SearchNode prevNode;
        
        public SearchNode(Board board)
        {
            this.board = board;
            this.moves = 0;
            this.prevNode = null;
        }
        
        public int compareTo(SearchNode that)
        {
            return Integer.compare(this.board.manhattan() + this.moves, 
                                   that.board.manhattan() + that.moves);
        }        
    }
    
    // solve a slider puzzle (given below)
    public static void main(String[] args) 
    {
        // create initial board from file
        In in = new In(args[0]);
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
        else 
        {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}