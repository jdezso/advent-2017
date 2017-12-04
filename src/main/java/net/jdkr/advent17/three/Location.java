package net.jdkr.advent17.three;

public class Location
{
    private int x;
    private int y;
    
    public int getX() { return x; }
    public int getY() { return y; }
    
    
    public Location()
    {
        this(0, 0);
    }
    
    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Location moveUp()
    {
        y++;
        return this;
    }
    
    public Location moveDown()
    {
        y--;
        return this;
    }
    
    public Location moveLeft()
    {
        x--;
        return this;
    }
    
    public Location moveRight()
    {
        x++;
        return this;
    }
    
    public int manhattanDistance()
    {
        return Math.abs(x) + Math.abs(y);
    }
    
    public String toString()
    {
        return String.format("(%d, %d)", x, y);
    }
}
