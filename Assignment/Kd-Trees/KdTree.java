/**
 * Write a mutable data type that uses a 2d-tree to implement the same API.
 * A 2d-tree is a generalization of a BST to two-dimensional keys. 
 * The idea is to build a BST with points in the nodes, 
 * using the x- and y-coordinates of the points as keys in strictly alternating sequence.
 */
import java.util.ArrayList;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;

public class KdTree 
{
    private int size;
    private Node root; 
    private static final RectHV UNITSQUARE = new RectHV(0.0, 0.0, 1.0, 1.0);
    private static final boolean LEFTRIGHT = true;

    // construct an empty set of points 
    public KdTree() 
    {
        size = 0; 
        root = null;
    }
    
    // is the set empty?
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    // number of points in the set
    public int size()  
    {
        return size;
    } 

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) 
    {
        if (!this.contains(p))
            root = put(root, p);
    } 

    // does the set contain point p? 
    public boolean contains(Point2D p) 
    {
        Node searchNode = root; 
        
        while (searchNode != null)
        {
            if (searchNode.compare(p) < 0)
                searchNode = searchNode.lb; 
            else if (searchNode.compare(p) >= 0)
            {
                if (searchNode.point.equals(p))
                    return true;
                else
                    searchNode = searchNode.rt;
            }
        }
        return false;
    } 

    // draw all points to standard draw 
    public void draw() 
    {
        plot(root);    
    } 

    // all points that are inside the rectangle.
    public Iterable<Point2D> range(RectHV rect) 
    {
        ArrayList<Point2D> insideRec = new ArrayList<Point2D>();
        rangeSearch(root, rect, insideRec);
        return insideRec;
    }

    // a nearest neighbor in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p) 
    {
        if (isEmpty())
            return null;

        else
        {
            Point2D nearestPoint = pointSearch(root, p, root.point);
            return nearestPoint;
        }
    } 

    private static class Node
    {
        private Point2D point; // the point
        private RectHV rect; // the axis-aligned rectangle corresponding to this node
        private Node lb; // the left/bottom subtree        
        private Node rt; // the right/top subtree
        private boolean orientation; // true for left-right, false for up-down

        public Node(Point2D point)
        {
            this.point = point;
        }

        public int compare(Point2D p)
        {
            if (this.orientation) // compare left or right
                return Double.compare(p.x(), this.point.x());
        
            else  // compare up or down
                return Double.compare(p.y(), this.point.y());  
        }
    }
    
    private Node put(Node node, Point2D point)
    {
        if (node == null)
        {
            if (isEmpty()) // if tree i empty, insert the initial node
            {
                Node temp = new Node(point);
                temp.rect = UNITSQUARE;
                temp.orientation = LEFTRIGHT;
                size++;
                return temp;
            }
            
            else
            {
                size++;
                return new Node(point); 
            }
                
        }  

        if (node.compare(point) < 0) 
        {
            node.lb = put(node.lb, point);

            if (node.orientation && node.lb.rect == null) 
            {
                node.lb.rect = new RectHV(node.rect.xmin(), node.rect.ymin(), 
                                          node.point.x(), node.rect.ymax());
                node.lb.orientation = !node.orientation;
            }
            else if (!node.orientation && node.lb.rect == null)
            {
                node.lb.rect = new RectHV(node.rect.xmin(), node.rect.ymin(),
                                          node.rect.xmax(), node.point.y());
                node.lb.orientation = !node.orientation;
            }
        }   

        else
        {
            node.rt = put(node.rt, point); 

            if (node.orientation && node.rt.rect == null) 
            {
                node.rt.rect = new RectHV(node.point.x(), node.rect.ymin(), 
                                          node.rect.xmax(), node.rect.ymax());
                node.rt.orientation = !node.orientation;
            }
            else if (!node.orientation && node.rt.rect == null)
            {
                node.rt.rect = new RectHV(node.rect.xmin(), node.point.y(),
                                          node.rect.xmax(), node.rect.ymax());
                node.rt.orientation = !node.orientation;
            }
        }
        return node;
    } 

    private void plot(Node node)
    {
        if (node == null) 
            return;
        
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.point.draw();

        if (node.orientation)
        {
            StdDraw.setPenColor(StdDraw.RED); 
            StdDraw.setPenRadius();
            StdDraw.line(node.point.x(), node.rect.ymin(), 
                         node.point.x(), node.rect.ymax());
        }

        else
        {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius();
            StdDraw.line(node.rect.xmin(), node.point.y(), 
                         node.rect.xmax(), node.point.y());
        }

        plot(node.lb);
        plot(node.rt);
    }

    private void rangeSearch(Node node, RectHV rect, ArrayList<Point2D> list)
    {
        if (node == null)
            return; 

        if (node.rect.intersects(rect)) 
        {
            if (rect.contains(node.point))
                list.add(node.point);    
        }
        else
            return;

        rangeSearch(node.lb, rect, list);
        rangeSearch(node.rt, rect, list);
    } 

    private Point2D pointSearch(Node node, Point2D point, Point2D nearest)
    { 
        // succeed nearestPoint from upper level node
        Point2D nearestPoint = nearest;

        // return back to previous level if hit null node
        if (node == null)
            return nearestPoint;

        // if distance from point to node-rectangle is less 
        // then minimum distance so far, check distance to node-point.
        double minSqrDistance = point.distanceSquaredTo(nearestPoint);
        double sqrDistanceToRect = node.rect.distanceSquaredTo(point);

        if (sqrDistanceToRect < minSqrDistance)
        {
            double sqrDistance = point.distanceSquaredTo(node.point); 
            if (sqrDistance < minSqrDistance)
                nearestPoint = node.point;
        }

        // otherwise, don't bother. It's not possible to find such a point 
        // which has a closer distance. return exsisting nearestPoint to 
        // upper level node. 
        else 
            return nearestPoint;
   
        // search children node of current node
        // base on the orientation of a node, 
        // always search the child node which is on the same side of the point first. 
        if (node.orientation) 
        {
            if (point.x() <= node.point.x())
            {
                // update nearestpoint, if not found in near child node, 
                // nearestpoint remains the same.
                nearestPoint = pointSearch(node.lb, point, nearestPoint);
                // repeat the same process in far node. 
                nearestPoint = pointSearch(node.rt, point, nearestPoint);
            }
            else
            {
                nearestPoint = pointSearch(node.rt, point, nearestPoint);
                nearestPoint = pointSearch(node.lb, point, nearestPoint);
            }   
        }
        else
        {
            if (point.y() <= node.point.y()) 
            {
                nearestPoint = pointSearch(node.lb, point, nearestPoint);
                nearestPoint = pointSearch(node.rt, point, nearestPoint); 
            }
            else
            {
                nearestPoint = pointSearch(node.rt, point, nearestPoint);
                nearestPoint = pointSearch(node.lb, point, nearestPoint);
            }
        }

        // return nearestPoint back to upper level. 
        return nearestPoint;
    }
    
    // unit testing of the methods (optional) 
    public static void main(String[] args) 
    {
        String filename = args[0];
        In in = new In(filename);
        StdDraw.enableDoubleBuffering();

        // initialize the data structures with N points from standard input
        // PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) 
        {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            // brute.insert(p);
        }

        kdtree.draw();
        StdDraw.show();
    }  
                    
}
