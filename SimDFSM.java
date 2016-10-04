
/* 
 * 
 * Name              : Prateek Dwivedi
 * CWID              : 11776118
 * Course            : CS5313 Formal Language Theory - Fall 2016
 * Assignment Number : 1
 * Program #         : 1
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SimDFSM {
 
	public static void main(String[] args) {
		String f = args[0];		
		String inputString = args[1];
		String[][] transitionTable = new String[50][50];

		// Handling Exception : Program says that we must take only two arguments
		if(args.length != 2)
		{
			System.out.println("Invalid No. of Arguments");
			System.exit(0);
		}
		
		String fState = null;
		File fileName = new File(f);
		ArrayList<String> transition = new ArrayList<String>();
        try{        	
        	Scanner sn = new Scanner(fileName);
        	String look = sn.nextLine();
        	
        	// To store input alphabets in an array
        	String[] inputAlphabet = look.split("\\s+");
        	
        	// Reading all the lines from Input file
            while(sn.hasNext())
            {
            	look = sn.nextLine();            	
            	if(sn.hasNext()== false)           // To identify the last line
            	{
            		fState = look;
            	}
            	else
            	{
            		transition.add(look);
            	}                
            }
            
            // Storing values in a 2D array
            for(int j=0;j<transition.size();j++)
            {            	
            	String inputValue = transition.get(j); 
            	String[] splitString = inputValue.split("\\s+");
            	for(int m=0;m<splitString.length;m++)
            	{
            		transitionTable[j+1][m] = splitString[m]; 
            	}
            }
            
            // Processing of the String
            char[] input = inputString.toCharArray();
            int index=0,currentState = 1;
            for(int i=0;i<input.length;i++)
            {
            	for(int j=0;j<inputAlphabet.length;j++)
            	{
            		if(input[i] == inputAlphabet[j].charAt(0))
            		{
            			index = j;
            			break;
            		}
            	}
            	currentState = Integer.parseInt(transitionTable[currentState][index]);
            }
 
         // Creating a flag
            boolean accept = false;
            String[] finalStates = fState.split("\\s+");
            for(int i=0;i<finalStates.length;i++)
            {
            	if(currentState == Integer.parseInt(finalStates[i]))
            	{
            		accept = true;
            		break;
            	}
            }
            
            // Result of the Simulation of the DFSM
            if(accept == true)
            {
            	System.out.println("yes");
            }
            else
            	System.out.println("no");
        }catch(FileNotFoundException e)
        {
        	System.err.println("Error(1) : File Not Found!!!");
        }
        catch(NumberFormatException e1)
        {
        	System.err.println("Error(2) : Invalid Value Encountered.");
        }
        
        
	}

}
