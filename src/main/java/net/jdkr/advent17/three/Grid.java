package net.jdkr.advent17.three;

import java.util.Arrays;

public class Grid
{
    /**
     * (0, 0) is always at the center of the grid
     */
    private int[][] grid;
    
    private final int size;
    private final int offset;
    
    public Grid(int size)
    {
        this.size = size;
        
        if (0 == size % 2)
        {
            throw new RuntimeException("Size must be odd");
        }
        
        grid = new int[size][];
        for (int row = 0; row < size; row++)
        {
            grid[row] = new int[size];
        }
        
        for (int[] row : grid)
        {
            Arrays.fill(row, -1);
        }
        
        offset = size / 2;
    }
    
    
    public int getValue(Location loc, int default_value)
    {
        int x = fixX(loc.getX());
        int y = fixY(loc.getY());
        
        if (x < 0 || x >= size) return default_value;
        if (y < 0 || y >= size) return default_value;
        
        int value = grid[y][x];
        if (value < 0) return default_value;
        
        return value;
    }
    
    public void setValue(Location loc, int value)
    {
        int x = fixX(loc.getX());
        int y = fixY(loc.getY());
        
        grid[y][x] = value;
    }
    
    private int fixX(final int x)
    {
        return x + offset;
    }
    
    private int fixY(final int y)
    {
        return size - 1 - (y + offset);
    }
    
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        
        for (int row = 0; row < size; row++)
        {
            str.append(Arrays.toString(grid[row]));
            str.append('\n');
        }
        
        return str.toString();
    }
}
