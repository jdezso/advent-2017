package net.jdkr.advent17.six;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DaySixA
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
    
    static private int stepsToRepeat(int[] values)
    {
        Set<Integer> seen_values = new HashSet<>();
        System.out.println(Arrays.toString(values));
        
        do
        {
            seen_values.add(Arrays.hashCode(values));
            int max_idx = findIndexOfMax(values);
            values = redistribute(values, max_idx);
            System.out.println(Arrays.toString(values));
        }
        while (! seen_values.contains(Arrays.hashCode(values)));
        
        return seen_values.size();
    }
    
    static private int stepsToRepeat(String input)
    {
        String[] str_values = input.split("\\s+");
        int[] values = new int[str_values.length];
        for (int idx = 0; idx < str_values.length; idx++)
        {
            values[idx] = Integer.parseInt(str_values[idx]);
        }
        
        return stepsToRepeat(values);
    }
    
    static public void main(String[] args) throws IOException
    {
        while (true)
        {
            System.out.println("Enter current values of memory banks:");
            String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
            
            if ("exit".equalsIgnoreCase(input)) return;
            
            int steps = stepsToRepeat(input);
            System.out.format("Steps before repeat: %d\n\n", steps);
        }
    }
}
