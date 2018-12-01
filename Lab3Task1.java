/*
 * This code is for CSC172 Lab3 purpose. This code has been worked upon individually by Kathan Thakkar. 

 * I have not received any outside or unauthorized help. Some help was taken from StackOverflow for syntax purposes. 
 * Lab section : MW 3:25-4:40 Harkness 114
 * Question 1 src file
 */

import java.util.ArrayList;
import java.util.Arrays;
public class Lab3Task1 {

	
	public static void print2DArray(int[][] array)
	
	{
		

		for(int i = 0; i<4; i++) {
			
		{
		    for(int j = 0; j<4; j++)
		    {
		        System.out.print(array[i][j]+ "     "); //print i row j column element every time loop runs
		        
		    }
		  
		    System.out.println();
		    
		}
		}
		
	}
	
	
	public static void print2DList(ArrayList< ArrayList< Integer > > table) {
		
		
		for(int i = 0; i<4; i++) {
			
			{
			    for(int j = 0; j<4; j++)
			    {
			        System.out.print(table.get(i).get(j) + "     ");
			        
			    }
			  
			    System.out.println();
			    
			}
			}
		
		
	}
	
	
	
	public static void main(String[] args) {
		
		//printing 2d array
		int arr[][] = {{10, 15, 30, 40},{15, 5, 8, 2}, {20, 2, 4, 2},{1, 4, 5, 0}};
		
		print2DArray(arr);
		System.out.println();
		
		// printing 2d list
		ArrayList< ArrayList< Integer > > table = new ArrayList<ArrayList<Integer>>();
		table.add(new ArrayList<Integer>(Arrays.asList(10,15,30,40)));
		table.add(new ArrayList<Integer>(Arrays.asList(15,5,8,2)));
		table.add(new ArrayList<Integer>(Arrays.asList(20,2,4,2)));
		table.add(new ArrayList<Integer>(Arrays.asList(1,4,5,0)));
		
		
		print2DList(table);
		
		
	}
	
	
}
