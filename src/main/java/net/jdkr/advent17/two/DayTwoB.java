package net.jdkr.advent17.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DayTwoB
{
    static private List<Integer> rowToInt(final String row)
    {
        final List<Integer> nums = new ArrayList<>();
        
        try (final Scanner scanner = new Scanner(row))
        {
            while (scanner.hasNext())
            {
                nums.add(scanner.nextInt());
            }
        }
        
        return Collections.unmodifiableList(nums);
    }
    
    static private int evenDivisorDifference(final String row)
    {
        final List<Integer> nums = rowToInt(row);
        
        for (final int num : nums)
        {
            for (final int compare : nums)
            {
                if (num == compare) continue;
                
                final int lo = Integer.min(num, compare);
                final int hi = Integer.max(num, compare);
                
                if (0 == hi % lo)
                {
//                    System.out.format("%d - %d = %d\n", hi, lo, hi/lo);
                    return hi/lo;
                }
            }
        }
        
        return 0;
    }
    
    static private int computeChecksum(List<String> rows)
    {
        int checksum = 0;
        
        for (String row : rows)
        {
            checksum += evenDivisorDifference(row);
        }
        
        return checksum;
    }
    
    static public void main(String[] args) throws IOException
    {
        table:
        while (true)
        {
            final List<String> rows = new ArrayList<String>();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("Enter table to process. Enter blank line to start processing.");
            
            while (true)
            {
                String row = reader.readLine();
                if ("exit".equalsIgnoreCase(row)) return;
                
                if (row.isEmpty())
                {
                    System.out.println(String.join("\n", rows.toArray(new String[0])));
                    
                    int checksum = computeChecksum(rows);
                    System.out.println(checksum);
                    System.out.println();
                    continue table;
                }
                else
                {
                    rows.add(row);
                }
            }
        }
    }
}
