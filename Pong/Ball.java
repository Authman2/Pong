import java.awt.*;

public class Ball
{
    private int centerX, centerY, radius;
    private Color color;
    private int speed, direction;
    
    public Ball(int x, int y, int r, Color c)
    {
        centerX = x;
        centerY = y;
        radius = r;
        color = c;
    }
    
    public int getX() {
        return centerX;
    }
    public int getY() {
        return centerY;
    }
    public int getRadius() {
        return radius;
    }

    public void draw(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(color);
        
        g.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
        g.setColor(oldColor);
    }
    
    public void fill(Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(color);
        
        g.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
        g.setColor(oldColor);
    }
    
    public boolean containsPoint(int x, int y) {
        int xSquared = (x - centerX) * (x - centerX);
        int ySquared = (y - centerY) * (y - centerY);
        int radiusSquared = radius * radius;
        return xSquared + ySquared - radiusSquared <= 0;
    }
    
    public void setPosition(int x, int y){
        centerX = x;
        centerY = y;
    }
    
    public void setSpeed(int s) {
        speed = s;
    }
    
    public void move(int xAmount, int yAmount) {
        centerX = centerX + xAmount;
        centerY = centerY + yAmount;
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
