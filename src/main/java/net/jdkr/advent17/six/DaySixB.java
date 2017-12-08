package net.jdkr.advent17.six;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DaySixB
{
    static private int findIndexOfMax(int[] values)
    {
        int max_idx = 0;
        for (int idx = 1; idx < values.length; idx++)
        {
            // Ties go to max_idx so that lowest idx wins
            if (values[idx] > values[max_idx])
            {
                max_idx = idx;
            }
        }
        
        return max_idx;
    }
    
    static private int[] redistribute(int[] values, int idx)
    {
        int to_distribute = values[idx];
        values[idx] = 0;
        
        for (; to_distribute > 0; to_distribute--)
        {
            idx = (idx + 1) % values.length;
            values[idx]++;
        }
        
        return values;
    }
    
    static private int sizeOfCycle(int[] values)
    {
        List<Integer> seen_values = new ArrayList<>();
        System.out.println(Arrays.toString(values));
        
        do
        {
            seen_values.add(Arrays.hashCode(values));
            int max_idx = findIndexOfMax(values);
            values = redistribute(values, max_idx);
            System.out.println(Arrays.toString(values));
        }
        while (seen_values.indexOf(Arrays.hashCode(values)) < 0);
        
        return seen_values.size() - seen_values.indexOf(Arrays.hashCode(values));
    }
    
    static private int sizeOfCycle(String input)
    {
        String[] str_values = input.split("\\s+");
        int[] values = new int[str_values.length];
        for (int idx = 0; idx < str_values.length; idx++)
        {
            values[idx] = Integer.parseInt(str_values[idx]);
        }
        
        return sizeOfCycle(values);
    }
    
    static public void main(String[] args) throws IOException
    {
        while (true)
        {
            System.out.println("Enter current values of memory banks:");
            String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
            
            if ("exit".equalsIgnoreCase(input)) return;
            
            int steps = sizeOfCycle(input);
            System.out.format("Size of inifinite loop: %d\n\n", steps);
        }
    }
}
