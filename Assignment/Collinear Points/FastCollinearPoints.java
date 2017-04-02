import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints 
{
   private ArrayList<LineSegment> segmentList = new ArrayList<LineSegment>();
    
   // finds all line segments containing 4 or more points
   public FastCollinearPoints(Point[] points)
   {
       int size = points.length;
       
       if (size == 0)
            throw new NullPointerException("Input is empty.");
       
       Point[] pointsSort = points.clone();
       Arrays.sort(pointsSort);
       
       if (checkDuplicate(pointsSort)) 
            throw new IllegalArgumentException("Input contains plicate points.");
       
       for (int i = 0; i < size; i++)
       {        
           Arrays.sort(pointsSort);
           
           if (pointsSort[i] == null)
               throw new NullPointerException("Point is empty.");
               
           Point p = pointsSort[i];
           Arrays.sort(pointsSort, p.slopeOrder());
           
           for (int first = 1, last = 2; last < size - 1; last++)
           {
               Point pFirst = pointsSort[first];
               Point pLast = pointsSort[last];
               double slope1 = p.slopeTo(pFirst);
               double slope2 = p.slopeTo(pLast);
                   
               while (last < size  && Double.compare(slope1, slope2) == 0)
               {
                   last++;
                   if (last >= size)
                   {
                       break;
                    }
                   pLast = pointsSort[last];
                   slope2 = p.slopeTo(pLast);
                }
                   
               if (last - first >= 3 && p.compareTo(pFirst) < 0)
               {
                   segmentList.add(new LineSegment(p, pointsSort[last - 1]));
                }
                    
               first = last;
            }
                
                
        }
    }

   // the number of line segments
   public int numberOfSegments()        
   {
       return segmentList.size();
    }
    
   // the line segments
   public LineSegment[] segments() 
   {
       int size = segmentList.size();
       return segmentList.toArray(new LineSegment[size]);
    }
    
   private boolean checkDuplicate(Point[] points)
   {
       for (int i = 0; i < points.length - 1; i++)
       {
           if (points[i].compareTo(points[i + 1]) == 0)
           {
               return true;
            }
       }
       return false;
    }
}