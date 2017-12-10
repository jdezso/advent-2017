package net.jdkr.advent17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CommandLineReader
{
    public <T> T processSingleLine(Function<String, T> processor, T default_value)
    {
        try
        {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("Enter program input:");
            String input = reader.readLine();
            
            if ("exit".equalsIgnoreCase(input)) return default_value;
            
            T result = processor.apply(input);
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return default_value;
        }
    }
    
    public <T> void processSingleLinesUntilExit(Function<String, T> processor)
    {
        while (true)
        {
            T result = processSingleLine(processor, null);
            if (null == result) return;
            
            System.out.format("Result: %s\n\n", result);
        }
    }
    
    public <T> T processMultipleLines(Function<List<String>, T> processor, T default_value)
    {
        try
        {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            final List<String> rows = new ArrayList<String>();
            
            System.out.println("Enter program input. Enter blank line to start processing:");
            
            while (true)
            {
                String row = reader.readLine();
                if ("exit".equalsIgnoreCase(row)) return default_value;
                
                if (row.isEmpty())
                {
                    T result = processor.apply(rows);
                    return result;
                }
                else
                {
                    rows.add(row);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return default_value;
        }
    }
    
    public <T> void processMultipleLinesUntilExit(Function<List<String>, T> processor)
    {
        while (true)
        {
            T result = processMultipleLines(processor, null);
            if (null == result) return;
            
            System.out.format("Result: %s\n\n", result);
        }
    }
}
