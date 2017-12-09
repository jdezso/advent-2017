package net.jdkr.advent17.seven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program
{
    public final String name;
    public final int weight;
    
    public Program base = null; // Optional, may stay null (root)
    public List<Program> held = new ArrayList<>();
    
    public Program(String name, int weight)
    {
        this.name = name;
        this.weight = weight;
    }
    
    public int computeTotalWeight()
    {
        int total = weight;
        for (Program prog : held)
        {
            total += prog.computeTotalWeight();
        }
        
        return total;
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
        
    
    static private Program parseRow(String input, Map<String, List<String>> all_held)
    {
        String[] words = input.split(" ");
        
        String name = words[0].trim();
        
        String weight_str = words[1].trim();
        weight_str = weight_str.replace("(", "").replace(")", "");
        int weight = Integer.parseInt(weight_str);
        
        List<String> held = new ArrayList<>();
        for (int idx = 3; idx < words.length; idx++)
        {
            String word = words[idx];
            word = word.replace(",", "").trim();
            held.add(word);
        }
        
        all_held.put(name, held);
        
        Program program = new Program(name, weight);
        
        System.out.println(program);
        
        return program;
    }
    
    static public Program buildTree(List<String> input)
    {
        // 1) Parse all rows
        Map<String, List<String>> all_held = new HashMap<>();
        Map<String, Program> all_programs = new HashMap<>();
        
        for (String row : input)
        {
            Program program = parseRow(row, all_held);
            all_programs.put(program.name, program);
        }
        
        for (Program program : all_programs.values())
        {
            List<String> held_names = all_held.get(program.name);
            if (held_names.isEmpty()) continue;
            
            for (String held_name : held_names)
            {
                Program held = all_programs.get(held_name);
                program.held.add(held);
                held.base = program;
            }
        }
        
        for (Program program : all_programs.values())
        {
            if (null == program.base)
            {
                return program;
            }
        }
        
        return null;
    }
}
