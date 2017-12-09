package net.jdkr.advent17.seven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaySevenA
{
    static private class Program
    {
        public final String name;
        public final int weight;
        
        public Program base = null; // Optional, may stay null (root)
        public List<String> held = new ArrayList<>();
        
        public Program(String name, int weight)
        {
            this.name = name;
            this.weight = weight;
        }
        
        public String toString()
        {
            if (held.isEmpty())
            {
                return String.format("%s (%d)", name, weight);
            }
            else
            {
                return String.format("%s (%d) -> %s", name, weight, held);
            }
        }
    }
    
    static private Program parseRow(String input)
    {
        String[] words = input.split(" ");
        
        String name = words[0].trim();
        
        String weight_str = words[1].trim();
        weight_str = weight_str.replace("(", "").replace(")", "");
        int weight = Integer.parseInt(weight_str);
        
        Program program = new Program(name, weight);
        
        for (int idx = 3; idx < words.length; idx++)
        {
            String word = words[idx];
            word = word.replace(",", "").trim();
            program.held.add(word);
        }
        
        System.out.println(program);
        
        return program;
    }
    
    static private Program buildTree(List<String> input)
    {
        Map<String, Program> programs = new HashMap<>();
        
        for (String row : input)
        {
            Program program = parseRow(row);
            programs.put(program.name, program);
        }
        
        for (Program program : programs.values())
        {
            if (program.held.isEmpty()) continue;
            
            for (String held : program.held)
            {
                programs.get(held).base = program;
            }
        }
        
        for (Program program : programs.values())
        {
            if (null == program.base)
            {
                return program;
            }
        }
        
        return null;
    }
    
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
                    Program program = buildTree(programs);
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
