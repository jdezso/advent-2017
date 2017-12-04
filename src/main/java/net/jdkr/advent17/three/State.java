package net.jdkr.advent17.three;

public class State
{
    private int ring = 0;
    private Direction direction = Direction.Expanding;
    
    public int getRing() { return ring; }
    public Direction getDirection() { return direction; }
    
    public void shift()
    {
        switch (direction)
        {
            case Up :
            {
                direction = Direction.Left;
                break;
            }
            case Left :
            {
                direction = Direction.Down;
                break;
            }
            case Down :
            {
                direction = Direction.Right;
                break;
            }
            case Right :
            {
                direction = Direction.Expanding;
                break;
            }
            case Expanding :
            {
                ring++;
                direction = Direction.Up;
                break;
            }
        }
    }
    
    
    static public enum Direction
    {
        Up,
        Left,
        Down,
        Right,
        Expanding,
    }
}
