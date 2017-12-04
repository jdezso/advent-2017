package net.jdkr.advent17.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DayThreeB
{
    static private int sumSurrounding(final Grid grid, final Location loc)
    {
        int sum = 0;
        
        for (int x = loc.getX() - 1; x <= loc.getX() + 1; x++)
        {
            for (int y = loc.getY() - 1; y <= loc.getY() + 1; y++)
            {
                sum += grid.getValue(new Location(x, y), 0);
            }
        }
        
        return sum;
    }
    
    static private int getNextValueGreaterThan(int target)
    {
        int grid_size = 25;
        
        Grid grid = new Grid(grid_size);
        Spiral spiral = new Spiral();
        
        grid.setValue(spiral.getLocation(), 1);
        spiral.step();
        
        for (int count = 2; count <= grid_size*grid_size; count++)
        {
            int value = sumSurrounding(grid, spiral.getLocation());
            if (value > target) return value;
            
            grid.setValue(spiral.getLocation(), value);
            spiral.step();
        }
        
        System.out.println(grid);
        return -1;
    }
    
    static public void main(String[] args) throws IOException
    {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        while (true)
        {
            System.out.println("Enter the memory location to compute the distance for:");
            String input = reader.readLine();
            
            if ("exit".equalsIgnoreCase(input)) return;
            
            int target = Integer.parseInt(input);
            int value = getNextValueGreaterThan(target);
            System.out.format("Next Largest Value: %d\n\n", value);
        }
    }
}
