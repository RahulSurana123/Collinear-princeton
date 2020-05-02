import edu.princeton.cs.algs4.StdDraw;
// import edu.princeton.cs.algs4.In;
import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x, y;
    public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}                         // constructs the point (x, y)

    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }                  
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }                           // string representation

	public int compareTo(Point that) {
	    if(this.y == that.y && this.x == that.x) {
            return 0;
        }
        
        // If this < that 
        if(this.y < that.y || (this.y == that.y && this.x < that.x)) {
            return -1;
        } 
        
        // if this > that
        return 1;
	   }     // compare points by y-coordinates, breaking ties by x-coordinates
	public double slopeTo(Point that) {
		
		if (that.y == this.y && that.x == this.x) {
			return Double.NEGATIVE_INFINITY;
		}
		if ((that.x == this.x)) {
			return +0.0;
		}
		if ((that.y == this.y)) {
			return Double.POSITIVE_INFINITY;
		}
		
		return (double) (that.y-this.y)/(that.x-this.x);
	}
	
	private class Slope implements Comparator<Point> {
		Point outPoint = Point.this;
		public int compare(Point p1, Point p2) {
			if (outPoint.slopeTo(p2) > outPoint.slopeTo(p1)) {
				return -1;
			}
			else if (outPoint.slopeTo(p1) > outPoint.slopeTo(p2)) {
				return 1;
			}
			return 0;
		}
	}
	public Comparator<Point> slopeOrder() {
		return new Slope();
	}


}
