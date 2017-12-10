package net.jdkr.advent17.eight;

import java.util.HashMap;
import java.util.Map;

public class Register
{
    static private final Register instance = new Register();
    
    static public Register getInstance() { return instance; }
    
    
    final private Map<String, Integer> values = new HashMap<>();
    
    synchronized public int getValue(String register)
    {
        return values.getOrDefault(register, 0);
    }
    
    /**
     * @param register The register to modify
     * @param delta Can be positive <b>or</b> negative
     */
    synchronized public void increment(String register, int delta)
    {
        int cur_value = getValue(register);
        values.put(register, cur_value + delta);
    }
    
    synchronized Iterable<Map.Entry<String, Integer>> iterator()
    {
        return values.entrySet();
    }
}
