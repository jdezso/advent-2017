package net.jdkr.advent17.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class DayFourA
{
    static public final Pattern PASSPHRASE_PATTERN = Pattern.compile("[a-z ]+");
    
    static private boolean isValid(String passphrase)
    {
        if (null == passphrase) return false;
        
        passphrase = passphrase.trim();
        if (passphrase.isEmpty()) return false;
        
        if (! PASSPHRASE_PATTERN.matcher(passphrase).matches())
        {
            return false;
        }
        
        Set<String> unique_words = new HashSet<>();
        
        String[] words = passphrase.split("[ ]+");
        for (String word : words)
        {
            if (! unique_words.add(word)) return false;
        }
        
        return true;
    }
    
    static private int countValidPassphrases(final List<String> passphrases)
    {
        int valid = 0;
        
        for (String passphrase : passphrases)
        {
            if (isValid(passphrase))
            {
                valid++;
            }
        }
        
        return valid;
    }
    
    static public void main(String[] args) throws IOException
    {
        table:
        while (true)
        {
            final List<String> passphrases = new ArrayList<String>();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("Enter passphrases to process. Enter blank line to start processing.");
            
            while (true)
            {
                String passphrase = reader.readLine();
                if ("exit".equalsIgnoreCase(passphrase)) return;
                
                if (passphrase.isEmpty())
                {
                    System.out.println(String.join("\n", passphrases.toArray(new String[0])));
                    
                    int valid = countValidPassphrases(passphrases);
                    System.out.format("%d/%d passphrases are valid\n\n", valid, passphrases.size());
                    continue table;
                }
                else
                {
                    passphrases.add(passphrase);
                }
            }
        }
    }
}
