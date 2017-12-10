package net.jdkr.advent17.seven;

import java.io.IOException;

import net.jdkr.advent17.CommandLineReader;

public class DaySevenA
{
    static public void main(String[] args) throws IOException
    {
        final CommandLineReader reader = new CommandLineReader();
        
        while (true)
        {
            Program program = reader.processMultipleLines(Program::buildTree, null);
            System.out.format("Base program: %s\n\n", program.name);
        }
    }
}
