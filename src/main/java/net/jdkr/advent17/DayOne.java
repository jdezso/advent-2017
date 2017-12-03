package net.jdkr.advent17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class DayOne
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
        
        final char[] full_input_chars = Arrays.copyOf(input_chars, input_chars.length + 1);
        full_input_chars[full_input_chars.length - 1] = full_input_chars[0]; // Copy wrap around character
        
        char last_ch = '\0';
        for (final char ch : full_input_chars)
        {
            if (! Character.isDigit(ch)) continue;
            
            if (ch == last_ch)
            {
                sum += toInt(ch, 0);
            }
            
            last_ch = ch;
        }

        return sum;
    }
    
    static public void main(String[] args) throws IOException
    {
        while (true)
        {
            System.out.println("Enter number string to compute:");
            String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
            
            if ("exit".equalsIgnoreCase(input)) return;
            
            int sum = totalDuplicates(input);
            System.out.println(sum);
            System.out.println();
        }
    }
}
