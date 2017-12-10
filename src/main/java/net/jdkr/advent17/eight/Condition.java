package net.jdkr.advent17.eight;

abstract public class Condition
{
    final private String register;
    final private int test_value;
    
    public Condition(final String register, final int test_value)
    {
        if (null == register) throw new NullPointerException();
        
        this.register = register;
        this.test_value = test_value;
    }
    
    abstract protected boolean compare(final int register_value, final int test_value);
    
    public boolean isTrue()
    {
        final int register_value = Register.getInstance().getValue(register);
        return compare(register_value, test_value);
    }
    
    
    static public Condition fromText(final String register, final String test, final int value)
    {
        switch (test)
        {
            case "<" : return new LessThan(register, value);
            case "<=" : return new LessThanEqual(register, value);
            case ">" : return new GreaterThan(register, value);
            case ">=" : return new GreaterThanEqual(register, value);
            case "==" : return new Equal(register, value);
            case "!=" : return new NotEqual(register, value);
        }
        
        return null;
    }
    
    
    static public class LessThan extends Condition
    {
        public LessThan(final String register, final int comparison)
        {
            super(register, comparison);
        }
        
        protected boolean compare(final int register_value, final int test_value)
        {
            return register_value < test_value;
        }
    }
    
    static public class LessThanEqual extends Condition
    {
        public LessThanEqual(final String register, final int comparison)
        {
            super(register, comparison);
        }
        
        protected boolean compare(final int register_value, final int test_value)
        {
            return register_value <= test_value;
        }
    }
    
    static public class GreaterThan extends Condition
    {
        public GreaterThan(final String register, final int comparison)
        {
            super(register, comparison);
        }
        
        protected boolean compare(final int register_value, final int test_value)
        {
            return register_value > test_value;
        }
    }
    
    static public class GreaterThanEqual extends Condition
    {
        public GreaterThanEqual(final String register, final int comparison)
        {
            super(register, comparison);
        }
        
        protected boolean compare(final int register_value, final int test_value)
        {
            return register_value >= test_value;
        }
    }
    
    static public class Equal extends Condition
    {
        public Equal(final String register, final int comparison)
        {
            super(register, comparison);
        }
        
        protected boolean compare(final int register_value, final int test_value)
        {
            return register_value == test_value;
        }
    }
    
    static public class NotEqual extends Condition
    {
        public NotEqual(final String register, final int comparison)
        {
            super(register, comparison);
        }
        
        protected boolean compare(final int register_value, final int test_value)
        {
            return register_value != test_value;
        }
    }
}
