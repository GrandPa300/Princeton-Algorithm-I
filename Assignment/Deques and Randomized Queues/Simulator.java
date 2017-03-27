public class Simulator
{  
   // unit testing (optional)
   public static void main(String[] args)  
   { 
      Deque<Integer> tester = new Deque<Integer>();
       
      tester.addFirst(3);
      tester.addFirst(2);
      tester.addFirst(1);
      tester.addLast(4);
      tester.addLast(5);
      tester.addLast(6);
        
      System.out.println("------- Deque Test -------");
      System.out.println("Test Here: Size = " + tester.size());
      System.out.println("Supposed result: 1 6, 2 5, 3 4,");
      System.out.print("Test Result:     ");
        
      while (tester.size() != 0)
      {
         int temp1 = tester.removeFirst();
         int temp2 = tester.removeLast();
         System.out.print(temp1 + " ");
         System.out.print(temp2 + ", ");
       }
      System.out.println("\n");
        
      tester.addFirst(9);
      tester.addLast(10);
      tester.addLast(11);
     
      System.out.println("Test Here: Size = " + tester.size());
      System.out.println("Supposed result: 9 10 11");
      System.out.print("Test Result:     ");
        
      while (tester.size() != 0)
      {
         int temp = tester.removeFirst();
         System.out.print(temp + " ");
       }
      System.out.println("\n");
        
      // refill linked-list "tester".
      tester.addFirst(3);
      tester.addFirst(2);
      tester.addFirst(1);
      System.out.println("Test Here: Size = " + tester.size());
      System.out.println("Supposed result: 1 2 3");
      System.out.print("Test Result:     ");
        
      for (int n : tester)
      System.out.print(n + " ");
      System.out.println("\n");
        
      tester.addLast(4);
      tester.addLast(5);
      tester.addLast(6);        
      System.out.println("Test Here: Size = " + tester.size());
      System.out.println("Supposed result: 1 2 3 4 5 6");
      System.out.print("Test Result:     ");
        
      for (int n : tester)
      System.out.print(n + " ");
      System.out.println("\n" + "------- Deque Test End -------");
      
      RandomizedQueue<Integer> tester2 = new RandomizedQueue<Integer>();
      
      System.out.println();
      System.out.println("------- RandomizedQueue Test -------");
      
      tester2.enqueue(3);
      System.out.println(tester2.size());
      tester2.dequeue();
      System.out.println(tester2.size());
      tester2.enqueue(3);
      tester2.enqueue(5);
      System.out.println(tester2.size());
      tester2.dequeue();
      System.out.println(tester2.size());
      tester2.dequeue();
      System.out.println(tester2.size());
      
      System.out.println("Initial Size: " + tester2.size());
      for (int i = 0; i < 10; i++)
      {
          tester2.enqueue(i);
        }
      System.out.println("Enqueued 0 to 9.");
      System.out.println("Size after enqueue: " + tester2.size());
      System.out.println("Sample: " + tester2.sample());
      System.out.println("Size after sample:  " + tester2.size());
      
      int temp = tester2.dequeue();
      System.out.println("After dequeue: \"" + temp + "\", Size: " + tester2.size());
      
      /*
      while ( tester2.size() != 0)
      {
          int temp = tester2.dequeue();
          System.out.println("After dequeue: \"" + temp + "\", Size: " + tester2.size());
        }
      */
      
      for (int n : tester2)
      System.out.print(n + " ");
      System.out.println("\n");
      System.out.println("Size after iterator:  " + tester2.size());
      System.out.println("------- RandomizedQueue Test End -------");
    }
}