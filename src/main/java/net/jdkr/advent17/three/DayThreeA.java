package net.jdkr.advent17.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class DayThreeA
{
    static private int computeDistance(int destination)
    {
        Spiral spiral = new Spiral();
        
        for (int position = 1; position < destination; position++)
        {
            spiral.step();
        }
        
        System.out.println("Final Locaton: " + spiral.getLocation());
        return spiral.getLocation().manhattanDistance();
    }
    
    static public void main(String[] args) throws IOException
    {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        while (true)
        {
            System.out.println("Enter the memory location to compute the distance for:");
            String input = reader.readLine();
            
            if ("exit".equalsIgnoreCase(input)) return;
            
            int location = Integer.parseInt(input);
            int distance = computeDistance(location);
            System.out.format("Manhattan Distance: %d\n\n", distance);
        }
    }
}
