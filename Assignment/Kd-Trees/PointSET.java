/**
 * Brute-force implementation. 
 * Write a mutable data type that represents a set of points in the unit square. 
 * Implement the following API by using a red-black BST 
 * (using either SET from algs4.jar or java.util.TreeSet).
 */
import java.util.ArrayList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET
{
    private final SET<Point2D> pointTree; 

    // construct an empty set of points 
    public PointSET() 
    {
        pointTree = new SET<Point2D>();
    }
    
    // is the set empty?
    public boolean isEmpty()
    {
        return pointTree.isEmpty();
    }
    
    // number of points in the set
    public int size()  
    {
        return pointTree.size();
    } 

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) 
    {
            pointTree.add(p);
    } 

    // does the set contain point p? 
    public boolean contains(Point2D p) 
    {
        return pointTree.contains(p);
    } 

    // draw all points to standard draw 
    public void draw() 
    {
        for (Point2D point: pointTree)
        {
            point.draw();
        }
    } 

    // all points that are inside the rectangle.
    public Iterable<Point2D> range(RectHV rect) 
    {
        ArrayList<Point2D> insideRec = new ArrayList<Point2D>();

        for (Point2D point : pointTree)
        {
            if (rect.contains(point))
            {
                insideRec.add(point);
            }
        }
        return insideRec;
    }

    // a nearest neighbor in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p) 
    {
        double minSqrDistance = Double.MAX_VALUE; 
        Point2D nearestPoint = null;

        if (pointTree.isEmpty())
        {
            return null;
        }

        else
        {
            for (Point2D point : pointTree) 
            {
                double sqrDistance = point.distanceSquaredTo(p); 
                if (sqrDistance < minSqrDistance)
                {
                    nearestPoint = point;
                    minSqrDistance = sqrDistance;
                }
            }
            return nearestPoint;
        }
    } 
    
    // unit testing of the methods (optional) 
    /*
    public static void main(String[] args) 
    {
       
    } */               
}