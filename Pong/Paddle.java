
import javax.swing.*;
import java.awt.*;

public class Paddle
{
    public Point origin;
    public int width, height;
    public Color color;
    
    int speed, direction;
    
    
    //CONSTRUCTOR
    public Paddle(int oX, int oY, int w, int h, Color c) {
        origin = new Point(oX, oY);
        width = w;
        height = h;
        color = c;
    }
    
    //GET THE VERTICIES OF THE RECTANGLE
    public Point getOrigin() { return origin; }
    public Point getTopRight() { 
        Point newP = new Point(origin.getX() + width, origin.getY());
        return newP; 
    }
    public Point getBottomRight() { 
        Point newP = new Point(origin.getX() + width, origin.getY() + height);
        return newP;
    }
    public Point getBottomLeft() {
        Point newP = new Point(origin.getX(), origin.getY() + height);
        return newP; 
    }
    
    //GET THE WIDTH AND HEIGHT
    public int Width() {
        return width;
    }
    public int Height() {
        return height;
    }
    
    //DRAW AND FILL
    public void draw(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(color);
        
        g.drawRect((int)origin.getX(),(int)origin.getY(), width, height);
        g.setColor(oldColor);
    }
    
    public void fill(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(color);
        
        g.fillRect((int)origin.getX(),(int)origin.getY(), width, height);
        g.setColor(oldColor);
    }
    
    public void setColor(Color cx) {
        color = cx;
    }
    
    public void setPosition(Point p) {
        origin = p;
    }
    
    //CHECKS IF THE POINT LIES IN THE RECTANGLE
    public boolean containsPoint(int x, int y) {
        boolean cont;
        if(x > origin.getX() && x < (origin.getX() + width)) {
            if(y > origin.getY() && y < (origin.getY() + height)) {
                cont = true;
            } else {
                cont = false;
            }
        } else {
            cont = false;
        }
        
        return cont;
    }
    
    //MOVE THE RECTANGLE
    public void move(int xAmount, int yAmount) {
        origin.setX(origin.getX() + xAmount);
        origin.setY(origin.getY() + yAmount);
    }
    
    //CHECK IF ANOTHER RECTANGLE INTERSECTS THIS ONE
    public boolean intersects(Paddle r) {
        boolean inOrigin = false;
        boolean inTopRight = false;
        boolean inBottomRight = false;
        boolean inBottomLeft = false;
        boolean inOverall = false;
        
        if(this.containsPoint((int)r.getOrigin().getX(), (int)r.getOrigin().getY())) {
            inOrigin = true;
        }
        if(this.containsPoint((int)r.getTopRight().getX(), (int)r.getTopRight().getY())) {
            inTopRight = true;
        }
        if(this.containsPoint((int)r.getBottomRight().getX(), (int)r.getBottomRight().getY())) {
            inBottomRight = true;
        }
        if(this.containsPoint((int)r.getBottomLeft().getX(), (int)r.getBottomLeft().getY())) {
            inBottomLeft = true;
        }
        
        if(inOrigin || inTopRight || inBottomRight || inBottomLeft) {
            inOverall = true;
        } else if (!inOrigin && !inTopRight && !inBottomRight && !inBottomLeft) {
            inOverall = false;
        }
        
        return inOverall;
    }
    
    public boolean hits(Ball b) {
        boolean hits = false;
        
        if(b.getX() >= this.getOrigin().getX() && b.getX() <= this.getTopRight().getX()) {
            if(b.getY() >= this.getOrigin().getY() && b.getY() <= this.getBottomRight().getY()) {
                hits = true;
            } else {
                hits = false;
            }
        } else {
            hits = false;
        }

        return hits;
    }
    
    
    public void setSpeed(int s) {
        speed = s;
    }
    
    public void move() {
        move((int)(speed * Math.cos(Math.toRadians(direction))), (int)(speed * Math.sin(Math.toRadians(direction))));
    }
    
    public void setDirection(int degrees) {
        direction = degrees % 360;
    }
    
    public void turn(int degrees) {
        direction = (direction + degrees) % 360;
    }
    
    public int getDirection() {
        return direction;
    }
    
    
    
}
