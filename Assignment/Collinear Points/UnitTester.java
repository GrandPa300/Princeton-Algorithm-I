import java.util.*;

public class UnitTester
{   
    // Unit tests the Point data type.
    public static void main(String[] args) 
    {
        //private final oint ORIGIN = new Point(0, 0);
        final Point ORIGIN= new Point(0, 0);
        Point[] points = new Point[6];
        
        for (int i = 0; i < 4; i++)
        {
            points[i] = new Point(i, i);
        }
        
        points[4] = new Point(1, 2);
        points[5] = new Point(2, 1);
        
        System.out.println("All the test points lasted as below: ");
        System.out.println();
        System.out.println(ORIGIN + "---> Origin Point");
        for (int i = 0; i < 6; i++)
        {
            System.out.println(points[i]);
        }
        /*
        Arrays.sort(points);
        for (int i = 0; i < 6; i++)
        {
            System.out.println(points[i]);
        }
        
        System.out.println();
        for (int i = 0; i < 6; i++)
        {
            double slope = ORIGIN.slopeTo(points[i]);
            System.out.println("slope from origin to point " + i + " is: " + slope);
        }
        */
        
        System.out.println();
        for (int i = 0; i < 6; i++)
        {            
            Point p = new Point(1, 2);
            int c = p.compareTo(points[i]);
            
            String result = "equal to";
            if (c < 0) result = "less than";
            if (c > 0) result = "larger than";
            
            System.out.println("Point (1, 2) is " + result +" Point #" + i);
        }
        
        Point p = new Point(1, 2);
        Point q = new Point(2, 1);
        
        System.out.println();
        for (int i = 0; i < 6; i++)
        {   
            int c = checkSlope(p, q, points[i].slopeOrder());
            double slope1 = points[i].slopeTo(p);
            double slope2 = points[i].slopeTo(q);
            
            String check = "equal to";
            if (c < 0) check = "less than";
            if (c > 0) check = "larger than";
            
            System.out.println("for "+ points[i] 
                                     + ": slope to (1, 2) (" 
                                     + slope1 +") is " 
                                     + check +" slope to (2, 1) (" 
                                     + slope2 +")");
        }
    }
    
    public static int checkSlope(Point p, Point q, Comparator<Point> comparator)
    {
        return comparator.compare(p, q);
    }
}
