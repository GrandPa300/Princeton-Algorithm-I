import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints 
{
   private ArrayList<LineSegment> segmentList = new ArrayList<LineSegment>();
    
   // finds all line segments containing 4 points
   public BruteCollinearPoints(Point[] points) 
   {
       int size = points.length;
       
       if (size == 0)
            throw new NullPointerException("Input is empty.");
       
       Point[] pointsSort = points.clone();
       Arrays.sort(pointsSort);
       
       if (checkDuplicate(pointsSort)) 
            throw new IllegalArgumentException("Input contains plicate points.");
       
       for (int o = 0; o < size - 3; o++)
       {
           for (int p = o + 1; p < size - 2; p++)
           {
               double slopeOP = pointsSort[o].slopeTo(pointsSort[p]);
               for (int q = p + 1; q < size - 1; q++)
               {
                   double slopePQ = pointsSort[p].slopeTo(pointsSort[q]);                   
                   if (slopeOP == slopePQ)
                   {
                       for (int r = q + 1; r < size; r++)
                       {
                           double slopeQR = pointsSort[q].slopeTo(pointsSort[r]);
                           if (slopePQ == slopeQR)
                           {
                               segmentList.add(new LineSegment(pointsSort[o], pointsSort[r]));
                            }
                        }
                    }
                }
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
