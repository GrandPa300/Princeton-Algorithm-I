import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> 
{
   private Node sentinel;
   private int size;
    
    private class Node
   {
       private Item item; 
       private Node next;
       private Node prev;      
    }
       
   
    
   // construct an empty deque
   public Deque()
   {      
       sentinel = new Node();
       sentinel.next = sentinel;
       sentinel.prev = sentinel;
       size = 0;
    }
   
   // is the deque empty?
   public boolean isEmpty() 
   {
       return size == 0; 
    }
   
   // return the number of items on the deque
   public int size()
   {
       return size;
    }
   
   // add the item to the front
   public void addFirst(Item item)   
   {
       if (item == null)
       {
           throw new java.lang.NullPointerException("Null Item.");
        }
      // save original first as temp.  
      Node temp =  sentinel.next;
      sentinel.next = new Node();
      sentinel.next.item = item;
      sentinel.next.prev = sentinel;
      sentinel.next.next = temp;
      temp.prev = sentinel.next;
      size++;
    }

    // add the item to the end
   public void addLast(Item item)
   {
       if (item == null)
       {
           throw new java.lang.NullPointerException("Null Item.");
        }
        
      // save original last as temp.  
      Node temp = sentinel.prev; 
      sentinel.prev = new Node();
      sentinel.prev.item = item;
      sentinel.prev.next = sentinel;
      sentinel.prev.prev = temp;
      temp.next = sentinel.prev;
      size++;
    }
   
   // remove and return the item from the front

   public Item removeFirst() 
   {
       if (isEmpty()) 
       {
            throw new NoSuchElementException("Empty"); 
        }
       
       Item item = sentinel.next.item;
       sentinel.next = sentinel.next.next;
       sentinel.next.prev = sentinel;
       size--;
       return item;
    }
   
   // remove and return the item from the end
   public Item removeLast() 
   {
       if (isEmpty()) 
       {
            throw new NoSuchElementException("Empty"); 
        }
       
       Item item = sentinel.prev.item;
       sentinel.prev = sentinel.prev.prev;
       sentinel.prev.next = sentinel;
       size--;
       return item;
    }
   
   // return an iterator over items in order from front to end
   public Iterator<Item> iterator() 
   {
       return new ListIterator();
    }
    
   private class ListIterator implements Iterator<Item>
   {
       private Node current = sentinel.next;
       
       public boolean hasNext()
       {
           return current != sentinel;
        }
       
       public void remove()
       {
           throw new java.lang.UnsupportedOperationException("Not Supported!"); 
        }
       
       public  Item next()
       {
           if (!hasNext())
           {
               throw new java.util.NoSuchElementException("End of the List!");
            }
           
           Item item = current.item;
           current = current.next;
           return item;
        }
    }
}