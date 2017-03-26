import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
    private boolean[][] grid; // grid[row][col], true for open, false for block
    private int gridSize;
    private int countOpen;
    
    private WeightedQuickUnionUF node; // union-find data structure
    private int nodeSize;
    
    private boolean[] top; // top[index], true for connected to top
    private boolean[] btm; // btm[index], true for connected to bottom
    private boolean touchDown;
    
    // Initialize a percolation
    public Percolation(int gridSize)
    {
        checkGrid(gridSize);
        
        // initial grid and fill in each node of grid as "blocked"
        this.gridSize = gridSize;
        this.countOpen = 0;
        grid = new boolean[gridSize][gridSize];
        
        for (int i = 0; i < gridSize; i++)
        {   
            for (int j = 0; j < gridSize; j++)
            {
                grid[i][j] = false; 
            }
        }
        
        this.touchDown = false;
        this.nodeSize = gridSize * gridSize;
        node = new WeightedQuickUnionUF(nodeSize);
        
        // initial top and btm, fill all as "false"
        top = new boolean[nodeSize];
        btm = new boolean[nodeSize];
        for (int i = 0; i < nodeSize; i++)
        {
           top[i] = false;
           btm[i] = false;
        }
    }
    
    // open a node, if it's not opened yet.
    public void open(int row, int col)
    {
        checkNode(row, col);
        
        if (!isOpen(row, col))
        {
           int index = getIndex(row, col);
           countOpen += 1; 
           // open a node and assume it's not connect to top or bottom.
           boolean topStat = false;
           boolean btmStat = false;
            
           // then judge status based on its row. 
           if (row == 1)
           {
               topStat = true;
           }
            
           if (row == gridSize)
           {
               btmStat = true;
           }
            
           grid[row - 1][col - 1] = true; // open it!
            
           if (col > 1 && isOpen(row, col - 1)) 
            {
                // check  if root of the neighbor is connect to top.
                // check self.
                if ((top[node.find(index - 1)]) || (top[node.find(index)]))
                {
                    // if any condition is ture, union status is also true
                    topStat = true;
                }
                if ((btm[node.find(index - 1)]) || (btm[node.find(index)]))
                {
                    btmStat = true;
                }
                node.union(index - 1, index);
            }
            
           if (col < gridSize && isOpen(row, col + 1))
            {
                if ((top[node.find(index + 1)]) || (top[node.find(index)]))
                {
                    topStat = true;
                }
                if ((btm[node.find(index + 1)]) || (btm[node.find(index)]))
                {
                    btmStat = true;
                }
                node.union(index + 1, index);
            }
            
           if (row > 1 && isOpen(row - 1, col))
            {
                if ((top[node.find(index - gridSize)]) || (top[node.find(index)]))
                {
                    topStat = true;
                }
                if ((btm[node.find(index - gridSize)]) || (btm[node.find(index)]))
                {
                    btmStat = true;
                }
                node.union(index - gridSize, index);
            }
            
           if (row < gridSize && isOpen(row + 1, col))
            {
                if ((top[node.find(index + gridSize)]) || (top[node.find(index)]))
                {
                    topStat = true;
                }
                if ((btm[node.find(index + gridSize)]) || (btm[node.find(index)]))
                {
                    btmStat = true;
                }
                node.union(index + gridSize, index);
            }
            
            // only update status for root node after union to be efficient. 
            top[node.find(index)] = topStat;
            btm[node.find(index)] = btmStat;
            // check percolation node just opened.
            // percolated if root of this node is connected to both top and bottom.
            if (top[node.find(index)] && btm[node.find(index)])
            {
                touchDown = true;
            }
        }
    }
    
    // Check if a site is open
    public boolean isOpen(int row, int col)
    {
        checkNode(row, col);
        
        return grid[row - 1][col - 1];
    }
    
    // Check if a site is full
    public boolean isFull(int row, int col)
    {
        checkNode(row, col);
        
        int index = getIndex(row, col);
        // check root node since only status of root node is updated. 
        // top[] of a node is always false unless its opened.
        return top[node.find(index)]; 
    }
    
    // Get the number of open sites
    public int numberOfOpenSites()
    {
        return countOpen;
    }
    
    // Check if the system is percolate
    public boolean percolates()
    {
        return touchDown;
    }

    // Helper method to get node index of a grid
    private int getIndex(int row, int col)
    {
        return (row - 1) * gridSize + col - 1;
    }
    
    // Helper method to check grid size
    private void checkGrid(int n)
    {
        if (n <= 0)
        {
            throw new java.lang.IllegalArgumentException();
        }
    }
    
    // Helper method to check the node param
    private void checkNode(int row, int col)
    {
        if (row < 1 || row > gridSize || 
            col < 1 || col > gridSize)
        {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }
}