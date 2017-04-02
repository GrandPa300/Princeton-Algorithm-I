import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> 
{
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point
    
    // Initializes a new point.
    public Point(int x, int y) 
    {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // Draws this point to standard draw.
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }
    
    // Draws the line segment between this point and the specified point
    public void drawTo(Point that) 
    {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope calculation. Take care of corner cases.
    public double slopeTo(Point that) 
    {   
        if (this.x == that.x)
        {
            if (this.y == that.y) return Double.NEGATIVE_INFINITY;
            else return Double.POSITIVE_INFINITY;
        }
        else
        {
            if (this.y == that.y) return +0.0;
            else return (double) (that.y - this.y) / (that.x - this.x);
        }
    }

    // Compares two points by y-coordinate, breaking ties by x-coordinate.
    // Formally, the invoking point (x0, y0) is less than the argument point
    // (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.     
    public int compareTo(Point that) 
    {
        if (that == null)
            throw new NullPointerException("Empty input.");
        
        if (this.y < that.y)  return -1;
        if (this.y > that.y)  return +1;
        if (this.x < that.x)  return -1;
        if (this.x > that.x)  return +1;
        else return 0;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder()
    {
        return new SComparator();
    }
    
    private class SComparator implements Comparator<Point>
    {
        public int compare(Point q1, Point q2)
        {
            double slope1 = Point.this.slopeTo(q1);
            double slope2 = Point.this.slopeTo(q2);
            
            if (slope1 < slope2) return -1;
            if (slope1 > slope2) return +1;
            return 0;
        }
    }

    // Returns a string representation of this point.
    // This method is provide for debugging;
    // your program should not rely on the format of the string representation.
    public String toString() 
    {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
}