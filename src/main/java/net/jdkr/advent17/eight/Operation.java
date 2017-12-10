package net.jdkr.advent17.eight;

import java.util.ArrayList;
import java.util.List;

public class Operation
{
    final private String register;
    final private boolean should_increment; // false means decrement
    final private int delta; // Can be positive or negative
    final private Condition condition;
    
    
    public Operation(final String register, final boolean should_increment, final int delta, final Condition condition)
    {
        if (null == register) throw new NullPointerException();
        if (null == condition) throw new NullPointerException();
        
        this.register = register;
        this.should_increment = should_increment;
        this.delta = delta;
        this.condition = condition;
    }
    
    synchronized public void execute()
    {
        if (! condition.isTrue()) return;
        
        Register.getInstance().increment(register, should_increment ? delta : -delta);
    }
    
    
    static public Operation fromString(final String input)
    {
        if (null == input) return null;
        
        String[] words = input.split("\\s+");
        
        final String register = words[0];
        final boolean should_increment = words[1].equalsIgnoreCase("inc");
        final int delta = Integer.parseInt(words[2]);
        // words[3] is always "if"
        final String condition_register = words[4];
        final String condition_test = words[5];
        final int condition_value = Integer.parseInt(words[6]);
        
        final Condition condition = Condition.fromText(condition_register, condition_test, condition_value);
        
        return new Operation(register, should_increment, delta, condition);
    }
    
    static public List<Operation> parseInstructions(final List<String> instructions)
    {
        final List<Operation> operations = new ArrayList<>();

        for (String instruction : instructions)
        {
            final Operation operation = Operation.fromString(instruction);
            if (null != operations)
            {
                operations.add(operation);
            }
        }
        
        return operations;
    }
    
    static public void executeOperations(final List<Operation> operations)
    {
        for (Operation operation : operations)
        {
            operation.execute();
        }
    }
    
    static public void executeInstructions(final List<String> instructions)
    {
        final List<Operation> operations = parseInstructions(instructions);
        executeOperations(operations);
    }
}
