import java.util.Arrays;
import java.util.ArrayList;

public class Board 
{
    private final int SIZE;
    private final int N;
    private char[] blocks;
        
    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    // convert into char[] instead of 2D-array for optimiaztion.
    public Board(int[][] blocks)
    {
        this.N = blocks[0].length;
        this.SIZE = N * N;
        this.blocks = new char[SIZE];
        
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                this.blocks[i * N + j] = (char) blocks[i][j];
            }
        }
    }
    
    // board dimension n
    public int dimension()
    {
        return this.N;
    }
    
    // number of blocks out of place
    public int hamming() 
    {
        int hamming = 0; 
        for (int i = 0; i < SIZE; i++)
        {
            if (i + 1 != (int) (blocks[i]) && (int) blocks[i] != 0) 
            { 
                hamming++;
            }
         }
        return hamming;
    }
    
    // sum of Manhattan distances between blocks and goal
    public int manhattan()   
    {
        int manhattan = 0; 
        
        for (int i = 0; i < SIZE; i++)
        {
            if ((int) blocks[i] != 0)
            {
                int target = (int) blocks[i] - 1;
                manhattan += (Math.abs(target % N - i % N) + 
                              Math.abs(target / N - i / N));
            }
        }
        
        return manhattan;        
    }
    
    // is this board the goal board?
    public boolean isGoal()         
    {
        return this.manhattan() == 0;
        // return this.manhattan() == 0 || this.checkTwinSolution();
    }
    
    // a board that is obtained by exchanging any pair of blocks
    public Board twin()
    {
        // int[][] twinBlocks = new int[N][N];??
        int temp;
        int[][] twinBlocks = convertTo2D(blocks);
        
        if (twinBlocks[0][0] != 0 && twinBlocks[0][1] != 0)
        {
            temp = twinBlocks[0][0];
            twinBlocks[0][0] = twinBlocks[0][1];
            twinBlocks[0][1] = temp;
        }
        
        else
        {
            temp = twinBlocks[1][0];
            twinBlocks[1][0] = twinBlocks[1][1];
            twinBlocks[1][1] = temp;
        }
        
       return new Board(twinBlocks);
    }
    
    // does this board equal y?
    public boolean equals(Object y) 
    {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        
        Board that = (Board) y;
        if (this.SIZE != that.SIZE) return false;
        return Arrays.equals(this.blocks, that.blocks);
        /*
        for (int i = 0; i < SIZE; i++)
        {
           if (this.blocks[i] != that.blocks[i])
               return false;
         }
        return true;*/
    }
    
    // all neighboring boards
    public Iterable<Board> neighbors()  
    {
        ArrayList<Board> neighborsList = new ArrayList<Board> ();
        int current = 0; 
        
        for (int i = 0; i < SIZE; i++)
        {
            if ((int) blocks[i] == 0)
            {
                current = i;
                break;
            }
        }
        
        if (current % N > 0)            
            neighborsList.add(newNeighbor(current, current - 1));
            
        if (current % N < N - 1)            
            neighborsList.add(newNeighbor(current, current + 1));
        
        if (current / N > 0)            
            neighborsList.add(newNeighbor(current, current - N));
        
        if (current / N < N - 1)            
            neighborsList.add(newNeighbor(current, current + N));
          
        return neighborsList;
    }
    
     // string representation of this board (in the output format specified below)
    public String toString()   
    {
        StringBuilder str = new StringBuilder();
        str.append(N + "\n");
        
        for (int i = 0; i < SIZE; i++)
        {
                str.append(String.format("%2d ", (int) blocks[i]));
                if ((i + 1) % N == 0) { str.append("\n"); } 
         }
         
        return str.toString();    
    }

    
    // helper method to convert 1D char[] into 2D int[][]
    private int[][] convertTo2D(char[] array1D)
    {
        int n = (int) Math.sqrt(array1D.length);
        int[][] array2D = new int[n][n];
        
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                array2D[i][j] = (int) (array1D[i * n + j]);
            }
        }
        
        return array2D;
    }
    
    // helper method to swap to elemnt in the array char[]
    private Board newNeighbor(int searchNode, int target)
    {
        char[] temp = blocks.clone();
        temp[searchNode] = temp[target];
        temp[target] = (char) 0;
        return new Board(convertTo2D(temp));
    }
    
    /* 
    // helpeer to check if board qualifies a solution of non-solvable twin.
    private boolean checkTwinSolution()
    {
        for (int i = 0; i < SIZE; i++)
        {
            if (i == 0 && (int) blocks[i] != 2) return false;
            if (i == 1 && (int) blocks[i] != 1) return false;
            if (i > 1 && (int) blocks[i] != i + 1 && (int) blocks[i] != 0) return false;
        }
        return true;
    } */
    
    // unit tests (not graded)
    public static void main(String[] args) 
    {
        
        // input as  input2 as Twin Solution as
        // 1 2 3     1 2 3     2 1 3
        // 4   6     5 4 6     4 5 6
        // 5 8 7     0 8 7     7 8  
        
        // int[][] input = {{1, 2, 3}, {4, 0, 6}, {5, 8, 7}};
        int[][] input = {{1, 2, 3}, {5, 4, 6}, {0, 8, 7}};
        int[][] twinSolution = {{2, 1, 3}, {4, 5, 6}, {7, 8, 0}};
        Board iniBoard = new Board(input);
        System.out.println("hamming = " + iniBoard.hamming());
        System.out.println("manhattan = " + iniBoard.manhattan());
        System.out.println();
        
        // test toString()
        System.out.println("Initial Board: ");
        System.out.println(iniBoard);
        
        // test twin()
        System.out.println("Twin Board: ");
        System.out.println(iniBoard.twin());
        
        // test neighbors()
        System.out.println("Current Neighbors: ");
        for (Board board: iniBoard.neighbors())
            System.out.println(board);
        
        Board board = new Board(input);
        
        // int[][] input4x4 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
        // Board board = new Board(input4x4);
        // String board = "input"; 
        
        Board twinBoard = new Board(twinSolution);
        System.out.println(twinBoard.isGoal());
        System.out.println(iniBoard.equals(board));
        System.out.println(iniBoard.equals(twinSolution));
     }
}