package net.jdkr.advent17.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DayOneB
{
    static private int toInt(final char ch, final int default_value)
    {
        switch (ch)
        {
            case '0' : return 0;
            case '1' : return 1;
            case '2' : return 2;
            case '3' : return 3;
            case '4' : return 4;
            case '5' : return 5;
            case '6' : return 6;
            case '7' : return 7;
            case '8' : return 8;
            case '9' : return 9;
        }
        
        return default_value;
    }
    
    static private int totalDuplicates(final String input)
    {
        if (null == input || input.isEmpty()) return 0;
        
        int sum = 0;
        
        final char[] input_chars = input.toCharArray();
        final int distance = input_chars.length / 2;
        
        for (int idx = 0; idx < input_chars.length; idx++)
        {
            final char ch = input_chars[idx];
            
            if (! Character.isDigit(ch)) continue;
            
            final int rot_idx = (idx + distance) % input_chars.length;
            final int rot_ch = input_chars[rot_idx];
            
            if (ch == rot_ch)
            {
                sum += toInt(ch, 0);
            }
        }

        return sum;
    }
    
    static public void main(String[] args) throws IOException
    {
        while (true)
        {
            System.out.println("Enter number to process:");
            String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
            
            if ("exit".equalsIgnoreCase(input)) return;
            if (0 != input.length() % 2)
            {
                System.err.println("Number must have an even number of digits! Try again.");
                continue;
            }
            
            int sum = totalDuplicates(input);
            System.out.println(sum);
            System.out.println();
        }
    }
}
