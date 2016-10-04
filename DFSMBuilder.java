/* 
 * 
 * Name              : Prateek Dwivedi
 * CWID              : 11776118
 * Course            : CS5313 Formal Language Theory - Fall 2016
 * Assignment Number : 1
 * Program #         : 2
 * 
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class DFSMBuilder {

	// Function to find a temporary Array Using KMP Algorithm
    private int[] computeTemporaryArray(char pattern[]){
        int [] lps = new int[pattern.length];
        int index =0;
        for(int i=1; i < pattern.length;){
            if(pattern[i] == pattern[index]){
                lps[i] = index + 1;
                index++;
                i++;
            }else{
                if(index != 0){
                    index = lps[index-1];
                }else{
                    lps[i] =0;
                    i++;
                }
            }
        }
        return lps;
    }
    
	public static void main(String[] args) {
		
		// Creating a file object
		File f1 = new File(args[0]);
		DFSMBuilder db = new DFSMBuilder();

		// Handling Exception : Program says that we must take only two arguments
						if(args.length != 2)
						{
							System.out.println("Invalid No. of Arguments");
							System.exit(0);
						} 

		// Breaking the Pattern String and converting it into an array of Characters
		char pattern[] = args[1].toCharArray();
		
		// Creating an array for Temporary use, it follows KMP Algorithm
		int lds[] = db.computeTemporaryArray(args[1].toCharArray());
                 		

		// Creating TreeSet to find the Unique alphabets and arranging them in ascending order
		TreeSet<Character> ts = new TreeSet<Character>();
		for(int i=0;i<pattern.length;i++)
			ts.add(pattern[i]);
		
		// Write a loop for transition function
		
		
		// Creating a List of HashSet elements
	        List<Character> list = new ArrayList<Character>(ts);
		
		
				try{
				     PrintWriter writer;
				     writer = new PrintWriter(f1, "UTF-8");					
				     
				     // Writing first line in the output file
				     for(Character c:list)
				     {
				    	 // To covert ASCII to String
				    	 writer.print(Character.toString(c)+' ');
				     }
				     int startIndex = -1;
				     int index = list.indexOf(pattern[0]);
				     
			 	     writer.print("\n");
				     
				     // For first State
				     for(int r=0;r<list.size();r++)
				        {
				    	  if(r==index)
				    		  writer.print(2 + " ");
				    	  else
				    		  writer.print(1 + " ");
				        }
				     
				     boolean exit;
				     writer.print("\n");

				     // For States between first & last
				     for(int d=1;d< (pattern.length);d++)
				     {
				    	 for(int e=0;e<list.size();e++)
				    	 {
				    		 exit = false;
				    		 if(list.get(e)==pattern[d]){
				    			 writer.print((d + 2)+" ");
				    		 }

				    		 else{
				    			 index = lds[d-1];                              // Using KMP Algorithm
				    			 while(exit !=true)
				    			 {
				    				 if(pattern[index] == list.get(e))
				    				 {
				    					 writer.print((index + 2)+" ");
				    					 exit = true;
				    					 break;
				    				 }
				    				 else
				    				 {
				    					 if(index== 0)
				    					 {
				    						 writer.print(1 +" "); 
				    						 exit = true;
				    						 break;
				    					 }
				    					 else {
                                                                                if(index != lds[index])
				    					           index = lds[index];
                                                                                else
                                                                                   index=0;
                                                                                
                                                                              }
				    				 }
				    					
				    			 }
				    		 }  
				    	 }
				    	 writer.print("\n");
				     }
				   
				     
				  // After reaching final state, all input values will result into the same state(i.e., final state) 
				     for(int r=0;r<list.size();r++)
				    	 writer.print((pattern.length + 1)+" ");
				     writer.print("\n"+ (pattern.length + 1));				     
				     writer.close();
				}
				catch(NumberFormatException e)                          // This will check the invalid Entries
				{
					 System.out.println("Error (2): Invalid Value Encountered.");
				}
				catch(FileNotFoundException e1){                        // This will check the File not found exception
					 System.out.println("Error (1): File Not Found");
				}
				catch (UnsupportedEncodingException e) {
					 e.printStackTrace();
				}

	}

}
