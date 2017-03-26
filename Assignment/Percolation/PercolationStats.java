import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats 
{
   private double[] testResult;
   private int trials;
    
   // perform trials independent experiments on an n-by-n grid
   public PercolationStats(int n, int trials)
   {
       if (n <= 0 || trials <= 0) 
       {
           throw new java.lang.IllegalArgumentException();
        }
       
       this.trials = trials;
       this.testResult = new double[trials];
       
       for (int i = 0; i < trials; i++)
       {
           Percolation testGrid = new Percolation(n);
    
           while (!testGrid.percolates())
           {
               int row = StdRandom.uniform(1, n + 1);
               int col = StdRandom.uniform(1, n + 1);
               testGrid.open(row, col);
            }
           
           testResult[i] = testGrid.numberOfOpenSites() / (double) (n * n);
        }
    }
   
   // sample mean of percolation threshold
   public double mean() 
   {
       return StdStats.mean(testResult);
    }
   
   // sample standard deviation of percolation threshold 
   public double stddev()
   {
       return StdStats.stddev(testResult);
    }
   
   // low  endpoint of 95% confidence interval 
   public double confidenceLo()
   {
       return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }
    
   // high endpoint of 95% confidence interval
   public double confidenceHi()   
   {
       return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }

   // test client (described below)   
   public static void main(String[] args)  
   {
       int i = Integer.parseInt(args[0]); // grid size
       int j = Integer.parseInt(args[1]); // trial numbers
       
       PercolationStats test = new PercolationStats(i, j);
       
       double mean = test.mean();
       double stddev = test.stddev();
       double cLw = test.confidenceLo();
       double cHi = test.confidenceHi();
       
       // use println to be same as example
       System.out.println("mean                    = " + mean);
       System.out.println("stddev                  = " + stddev);
       System.out.println("95% confidence interval = [" + cLw + "," + cHi +"]");
       
       /*
       // format print to show 8 digits only.
       System.out.printf("mean                    = %.8f%n", mean);
       System.out.printf("stddev                  = %.8f%n", stddev);
       System.out.printf("95%% confidence interval = [%.8f, %.16f]%n", cLw, cHi);
       */
    }
}