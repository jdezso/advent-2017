package net.jdkr.advent17.five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DayFiveA
{
    static private int step(final List<Integer> instructions, int idx)
    {
        int instruction = instructions.get(idx);
        instructions.set(idx, instruction + 1); // Increment the "used" instruction
        
        int new_idx = idx + instruction;
        if (new_idx < 0 || new_idx >= instructions.size())
        {
            return -1;
        }
        
        return new_idx;
    }
    
    static private int computeStepsToExit(final List<Integer> instructions)
    {
        int idx = 0;
        int steps = 0;
        
        while (idx >= 0)
        {
            idx = step(instructions, idx);
            steps++;
        }
        
        return steps;
    }
    
    static public void main(String[] args) throws IOException
    {
        rows:
        while (true)
        {
            final List<Integer> instructions = new ArrayList<Integer>();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("Enter jump instructions. Enter blank line to start processing.");
            
            while (true)
            {
                String instruction = reader.readLine();
                if ("exit".equalsIgnoreCase(instruction)) return;
                
                if (instruction.isEmpty())
                {
                    int steps = computeStepsToExit(instructions);
                    System.out.format("%d steps to exit maze\n\n", steps);
                    continue rows;
                }
                else
                {
                    instructions.add(Integer.parseInt(instruction));
                }
            }
        }
    }
}
