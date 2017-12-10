package net.jdkr.advent17.eight;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import net.jdkr.advent17.CommandLineReader;

public class DayEightA
{
    static private int findLargestRegisterValue(List<String> instructions)
    {
        Operation.executeInstructions(instructions);

        int max_value = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> register : Register.getInstance().iterator())
        {
            if (register.getValue() > max_value)
            {
                max_value = register.getValue();
            }
        }
        
        return max_value;
    }
    
    
    static public void main(String[] args) throws IOException
    {
        final CommandLineReader reader = new CommandLineReader();
        reader.processMultipleLinesUntilExit(DayEightA::findLargestRegisterValue);
    }
}
