import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;
import java.util.LinkedList;

public class FastCollinearPoints { 
    private final Point[] points;
    private LineSegment[] segments;
    private int segmentCount;
    
    /**   
     * @description finds all line segments containing 4 or more points
     * @param points      
     */   
    public FastCollinearPoints(Point[] pointi) { 
    	
        // check to see if argument matches constraints
        checksPoints(pointi);
        
        this.points = pointi.clone();
        this.segments = new LineSegment[2];
        this.segmentCount = 0;
        LinkedList<Point> collinearPoints = new LinkedList<Point>();   
        
        // Arrays.sort(this.points);
        // check to see if argument matches constraints
        for (Point point : this.points) {
            Arrays.sort(this.points, point.slopeOrder());          
            double prevSlope = 0.0;
            
            for (int j = 0; j < this.points.length; j++) {
                double currentSlope = point.slopeTo(this.points[j]);
                if (j == 0 || currentSlope != prevSlope) {
                    
                    if (collinearPoints.size() >= 3) {
                        // Collections.sort(collinearPoints);
                        this.enqueue(new LineSegment(collinearPoints.getFirst(), collinearPoints.getLast()));                
                        collinearPoints.getFirst().drawTo(collinearPoints.getLast());    
                        StdDraw.show();   
                    }
                    
                    collinearPoints.clear();
                } 
                
                collinearPoints.add(this.points[j]);
                prevSlope = currentSlope; 
            }
        }
        
    }   
    
    /**
     * @description the number of line segments
     * @return 
     */ 
    public int numberOfSegments() {
        return this.segmentCount;
    } 
   
    /**
     * @description the line segments
     * @return 
     */
    public LineSegment[] segments() {
        return Arrays.copyOf(this.segments, this.segmentCount);
    }
    
    /**
     * @description resize the underlying array holding the elements
     * @param capacity 
     */
    private void resize(int capacity) {
        assert capacity >= this.segmentCount;

        // textbook implementation
        LineSegment[] temp = new LineSegment[capacity];
        System.arraycopy(this.segments, 0, temp, 0, this.segmentCount);
        this.segments = temp;

       // alternative implementation
       // a = java.util.Arrays.copyOf(a, capacity);
    }
    
    /**
     * @description add the item    
     */
    private void enqueue(LineSegment item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        
        if (this.segmentCount == this.segments.length) {
            resize(2 * this.segments.length);
        }
        
        this.segments[this.segmentCount++] = item;
    }    
    
    /**
     * @description do check on argument
     */
    private void checksPoints(Point[] point) {
        if (point == null) {
            throw new IllegalArgumentException();
        }
        
        for (int i = 0; i < point.length; i++) {
            for (int j = 0; j < point.length; j++) {
                
                if (point[i] == null || point[j] == null) {
                    throw new IllegalArgumentException();
                }
                
                if (i != j && point[i].compareTo(point[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        In in = new In("files\\grid4x4.txt");      // input file
        int n = in.readInt();   
        
        // padding for drawing
        int padding = 1000;
        
        // set draw scale
        StdDraw.setXscale(-padding, Short.MAX_VALUE + padding);
        StdDraw.setYscale(-padding, Short.MAX_VALUE + padding);
               
        // Index of array
        int index = 0;
        
        // turn on animation mode
        StdDraw.enableDoubleBuffering();
 
        // Create array
        Point[] points = new Point[n];
            
        points[index] = new Point(in.readInt(), in.readInt());
        points[index].draw();
        StdDraw.show();
        
        index++;
        
        while (!in.isEmpty()) {        
            points[index] = new Point(in.readInt(), in.readInt());
            points[index].draw();
            StdDraw.show();
                       
            index++;
        }        
       
        new FastCollinearPoints(points);
//        int inde = fclp.numberOfSegments();
        // LineSegment[] segments = fclp.segments();
    }
}
