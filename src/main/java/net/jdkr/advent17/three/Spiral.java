package net.jdkr.advent17.three;

public class Spiral
{
    private final Location loc;
    private final State state;
    
    public Location getLocation() { return loc; }
    public State getState() { return state; }
    
    
    public Spiral()
    {
        loc = new Location();
        state = new State();
    }
    
    public void step()
    {
        switch (state.getDirection())
        {
            case Expanding :
            {
                loc.moveRight();
                state.shift();
                break;
            }
            case Up :
            {
                loc.moveUp();
                if (loc.getY() == state.getRing())
                {
                    state.shift();
                }
                break;
            }
            case Left :
            {
                loc.moveLeft();
                if (loc.getX() == -state.getRing())
                {
                    state.shift();
                }
                break;
            }
            case Down :
            {
                loc.moveDown();
                if (loc.getY() == -state.getRing())
                {
                    state.shift();
                }
                break;
            }
            case Right :
            {
                loc.moveRight();
                if (loc.getX() == state.getRing())
                {
                    state.shift();
                }
                break;
            }
        }
    }
}
