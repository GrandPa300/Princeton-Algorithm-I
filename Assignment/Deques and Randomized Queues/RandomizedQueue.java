import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> 
{
   private Item[] s;
   private int current;
    
   // construct an empty randomized queue
   public RandomizedQueue()
   {
       this.current = 0;
       s = (Item[]) new Object[1];
    }
   
   // is the queue empty?
   public boolean isEmpty()
   {
       return current == 0;
    }
    
   // return the number of items on the queue
   public int size()
   {
       return current;
    }
    
   // add the item
   public void enqueue(Item item)
   {
        if (item == null)
       {
           throw new java.lang.NullPointerException("Null Item.");
        }
       
       if (current == s.length)
       {
           resize(s.length * 2);
        }
        
       s[current++] = item;
    }
    
   // remove and return a random item
   public Item dequeue() 
   {
       if (isEmpty()) 
       {
            throw new NoSuchElementException("Empty"); 
        }
       
       int pick = StdRandom.uniform(current);
       Item item = s[pick];
       s[pick] = s[--current];
       s[current] = null; // garbage collection
       
       if (current == s.length / 4 && current >= 8) 
       // avoid shirnk array whose size is less than 8
       {
           resize(s.length / 2);
        }
       return item;
    }
    
   // return (but do not remove) a random item
   public Item sample() 
   {
       if (isEmpty()) 
       {
            throw new NoSuchElementException("Empty"); 
        }
       
       int pick = StdRandom.uniform(current);
       return s[pick];
    }
    
   // return an independent iterator over items in random order
   public Iterator<Item> iterator() 
   {
       return new RandomIterator();
    }
   
   private void resize(int size)
   {
       Item[] temp = (Item[]) new Object[size];
       for (int i = 0; i < current; i++)
       {
           temp[i] = s[i];
        }
        s = temp;
    }
    
   private class RandomIterator implements Iterator<Item>
   {
       private int i;
       private int[] randomIdx;
       
       public RandomIterator()
       {
           i = 0;
           randomIdx = new int[current];
           for (int j = 0; j < current; j++)
           {
               randomIdx[j] = j;
            }
           StdRandom.shuffle(randomIdx);
        }
        
       public boolean hasNext()
       {
           return i < current; 
        }
        
       public void remove()
       {
           throw new java.lang.UnsupportedOperationException("Not Supported!"); 
        }
        
       public Item next()
       {
           if (!hasNext())
           {
               throw new java.util.NoSuchElementException("End of the List!");
            }
           
           
           return s[randomIdx[i++]];
        }
    }
}
