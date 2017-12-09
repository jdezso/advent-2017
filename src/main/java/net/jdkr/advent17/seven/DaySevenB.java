package net.jdkr.advent17.seven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaySevenB
{
    static private int findOddWeight(int[] weights)
    {
        Arrays.sort(weights);
        
        int lo = weights[0];
        int hi = weights[weights.length - 1];
        if (lo != hi)
        {
            // Someone is different...
            if (lo == weights[1]) // Compare to something in the middle since we know there's exactly one oddball
            {
                return hi;
            }
            else
            {
                return lo;
            }
        }
        
        return -1;
    }
    
    static private int computeCorrectedWeight(Program base, int target_weight)
    {
        int[] weights = new int[base.held.size()];
        
        for (int idx = 0; idx < base.held.size(); idx++)
        {
            weights[idx] = base.held.get(idx).computeTotalWeight();
        }
        
        final int odd_weight = findOddWeight(weights);
        
        int norm_weight = -1;
        for (int weight : weights)
        {
            if (weight != odd_weight)
            {
                norm_weight = weight;
                break;
            }
        }
        
        for (Program held : base.held)
        {
            if (held.computeTotalWeight() == odd_weight)
            {
                // Dig deeper
                return computeCorrectedWeight(held, norm_weight);
            }
        }
        
        // Base case - everything we hold is equal, but we're apparently not up to snuff
        int offset = target_weight - base.computeTotalWeight();
        return base.weight + offset;
    }
    
    static private int computeCorrectedWeight(List<String> input)
    {
        Program program = Program.buildTree(input);
        System.out.println(program);
        return computeCorrectedWeight(program, 0);
    }
    
    static public void main(String[] args) throws IOException
    {
        rows:
        while (true)
        {
            final List<String> programs = new ArrayList<String>();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("Enter program descriptions. Enter blank line to start processing.");
            
            while (true)
            {
                String program_desc = reader.readLine();
                if ("exit".equalsIgnoreCase(program_desc)) return;
                
                if (program_desc.isEmpty())
                {
                    int corrected_weight = computeCorrectedWeight(programs);
                    System.out.format("Out of balance program should weigh %d\n\n", corrected_weight);
                    continue rows;
                }
                else
                {
                    programs.add(program_desc);
                }
            }
        }
    }
}
