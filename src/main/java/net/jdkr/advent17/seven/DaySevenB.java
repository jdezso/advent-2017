package net.jdkr.advent17.seven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DaySevenB
{
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
                    Program program = Program.buildTree(programs);
                    System.out.format("Base program: %s\n\n", program.name);
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
