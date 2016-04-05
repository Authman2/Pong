 
public class Point
{
    /**
     * Instance variables -- describe the position of the point.
     * Instance variables keep track of the state of the object.
     *
     * Scope: entire class, but not outside the class.
     * Lifetime: lasts as long as the object.
     */
    private double x, y;
    /**
     * Constructor: sets intial values of the instance variables.
     * Only called once -- when the object is first created.
     */
    public Point(double px, double py) {
        x = px;
        y = py; 
    }
    /**
     * Default constructor: if there are no input parameters, we initiali
     * ze the coordinates to (0, 0).
     */
    public Point() {
        x = 0.0;
        y = 0.0;
    }
    // The following methods are accessors -- return info about the objec
    //t, but do not modify the instance variables.
    /**
       * Returns the x coordinate.
         */
    public double getX() {
        return x;
    }
    /**
       * Returns the y coordinate.
      */
     public double getY() {
         return y; 
        }
    /**
     * Returns a string showing the point as an ordered pair.
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    /**
     * Returns the slope between this point and another point.
     */
    public double slope(Point other) {
        return (y - other.getY())/(x - other.getX());
    }
    /**
     * Returns the dot product of this point and another point -- like th
     * e dot product of two vectors.
       */
    public double dotProduct(Point other) {
        return x*other.getX() + y*other.getY();
    }
    /**
       * Returns the radius of this point (or in other words, the distance
       * from the origin).
     */
    public double getRadius() {
        return Math.sqrt(x*x + y*y);
    }
    /**
     * Returns the angle of the point in radians.
     * Angle is between the vector to the point, and the +x axis, as usua
     * l in trig.
     * Angle values are: [0, 2pi) -- including 0, not including 2pi.
     */
    public double getTheta() {
        if(x == 0) {
            if(y > 0) { // +y axis
                return Math.PI/2;
            }
            else if(y < 0) { // -y axis
                return -Math.PI/2;
            }
            else { // Origin
                return 0.0;
            }
        }
        else {
            double angle = Math.atan(y/x);
            if(x > 0 && y >= 0) { 
                // Quadrant 1
                return angle;
            }
            else if(x < 0) { // Quadrants 2 and 3
                return angle + Math.PI;
            }
            else { // Quadrant 4
                return angle + 2*Math.PI;
            }
        }
    }
    // The following methods are modifiers -- these modify the state of t
    //he object, or in other words
    // modify the values of the instance variables.
    // They may or may not return info about the object (most don't)
    /**
     * Sets the x coordinate to a new value.
     */
    public void setX(double newX) {
        x = newX;
    }
    /**
     * Sets the y coordinate to a new value.
     */
    public void setY(double newY) {
        y = newY;
    }
    /**
     * Adds another point to the current point -- like adding vectors.
     */
    public void translate(Point other) {
        x += other.getX();
        y += other.getY();
    }
    /**
       * Scales the point by factor s.
         */
    public void scale(double s) {
        x *= s;
        y *= s;
    }
    /**
     * Rotates the point by angle, in radians.
     * Positive: counterclockwise
     * Negative: clockwise.
     *
     * This uses the formula for rotation of a point by an angle theta:
     *
     * x -> xcos(theta) - ysin(theta)
     * y -> xsin(theta) + xcos(theta)
     */
    public void rotate(double angle) {
        double oldX = x; // Notice we need to store the old value, before changing x, so that y is calculated correctly.
        double oldY = y; // For symmetry, I store the old value of y also, although this is not strictly necessary.
        x = oldX*Math.cos(angle) - oldY*Math.sin(angle);
        y = oldX*Math.sin(angle) + oldY*Math.cos(angle);
    }
}

