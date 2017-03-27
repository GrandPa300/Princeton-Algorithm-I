// import edu.princeton.cs.algs4.In;
// use In for local test only.
import edu.princeton.cs.algs4.StdIn; 
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation 
{
   public static void main(String[] args)
   {
       int k = Integer.parseInt(args[0]);
       int count = 0;
       // In in = new In(args[1]); 
       // local test only.
       String[] allString = StdIn.readAllStrings();
       StdRandom.shuffle(allString);
       
       Deque<String> deque = new Deque<String>();      
       while (count < k)
       {
           deque.addLast(allString[count]);
           count++;
        }
       
       for (String s : deque)
       StdOut.println(s);
       
       // System.out.println(s);
       // local test only.
    }
}
