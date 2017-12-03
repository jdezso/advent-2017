package net.jdkr.advent17.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayTwoA
{
    static private int minMaxDifference(String row)
    {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        try (Scanner scanner = new Scanner(row))
        {
            while (scanner.hasNext())
            {
                final int num = scanner.nextInt();
                min = Integer.min(min, num);
                max = Integer.max(max, num);
            }
        }
        
        return max - min;
    }
    
    static private int computeChecksum(List<String> rows)
    {
        int checksum = 0;
        
        for (String row : rows)
        {
            checksum += minMaxDifference(row);
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
