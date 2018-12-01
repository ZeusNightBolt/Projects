/*
 * This code is for CSC172 Lab3 purpose. This code has been worked upon individually by Kathan Thakkar. 
 * I have not received any outside or unauthorized help. Some help was taken from StackOverflow for syntax purposes. 
 * Lab section : MW 3:25-4:40 Harkness 114
 * Question 2 src file
 */
import java.util.Arrays;
import java.util.ArrayList;
public class Lab3Task2 {

	   /*
	    * Running Sum of a 2D array. 4 different loops for 4 different ways. left,right,up,down.
	    */
      public static void runningSum2DArray(int[][] arr, int dir)
	{
		
		
		int[][] arra= new int[4][4];
		if(dir==1)
		{
			for(int i=0;i<4;i++) {
				
				
					for(int j=3; j>=0; j--)
					{
						if(j==3)
						{
							continue;
						}
						arr[i][j]=arr[i][j+1] + arr[i][j];
						
					}
					
				}
			
		}
		if(dir==2)
		{
			
			for (int row = 0; row < 4; row++) {
			    for (int col = 0; col < 4; col++) {
			    	    if(col==0)
			    	    {
			    	    	continue;
			    	    }
			    	     
			        arr[row][col] = arr[row][(col - 1)] + arr[row][col];
			    }
			}
			
		}
		
		if(dir==3) {
			
			for (int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					
					if(j==0) {
						continue;
					}
					arr[j][i]=arr[j-1][i]+arr[j][i];
					
				}
				
			    }
			}
			
             if(dir==4) {
			
			for (int i=0;i<4;i++) {
				for(int j=3;j>=0;j--) {
					
					if(j==3) {
						continue;
					}
					arr[j][i]=arr[j+1][i]+arr[j][i];
					
				}
				
			    }
			}
		
		
		
	}
	
	
	public static void runningSum2DList(ArrayList< ArrayList< Integer > > arr, int dir)
	{
		
		
		int[][] arra= new int[4][4];
		if(dir==1)
		{
			for(int i=0;i<4;i++) {
				
				    int temp = 0;
					for(int j=3; j>=0; j--)
					{
						if(j==3)
						{
							continue;
						}
						temp =arr.get(i).get(j+1) + arr.get(i).get(j);
						arr.get(i).set(j,Integer.valueOf(temp)); //get i row element and set the jth column element as temp. 
						
					}
					
				}
			
		}
		
		if(dir==2)
		{
			
			for (int row = 0; row < 4; row++) {
				int temp=0;
			    for (int col = 0; col < 4; col++) {
			    	    if(col==0)
			    	    {
			    	    	continue;
			    	    }
			    	     
			        temp = arr.get(row).get(col - 1) + arr.get(row).get(col);
			        arr.get(row).set(col, temp);
			        
			    }
			}
			
		}
		
		if(dir==3) {
			
			for (int i=0;i<4;i++) {
				int temp=0;
				for(int j=0;j<4;j++) {
					
					if(j==0) {
						continue;
					}
					temp=arr.get(j-1).get(i)+arr.get(j).get(i);
					arr.get(j).set(i, temp);
					
				}
				
			    }
			}
			
             if(dir==4) {
			
			for (int i=0;i<4;i++) {
				int temp=0;
				for(int j=3;j>=0;j--) {
					
					if(j==3) {
						continue;
					}
					temp=arr.get(j+1).get(i)+arr.get(j).get(i);
					arr.get(j).set(i, temp); 
					
				}
				
			    }
			}
		
		
	}
	
public static void print2DArray(int[][] array)
	
	{
		

		for(int i = 0; i<4; i++) {
			
		{
		    for(int j = 0; j<4; j++)
		    {
		        System.out.print(array[i][j]+ "     ");
		        
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
		  
		  int arr[][] = {{10, 15, 30, 40},{15, 5, 8, 2}, {20, 2, 4, 2},{1, 4, 5, 0}};
		  runningSum2DArray(arr, 4);
		  print2DArray(arr);
		  
		  
		  
		  ArrayList< ArrayList< Integer > > table = new ArrayList<ArrayList<Integer>>();
			table.add(new ArrayList<Integer>(Arrays.asList(10,15,30,40)));
			table.add(new ArrayList<Integer>(Arrays.asList(15,5,8,2)));
			table.add(new ArrayList<Integer>(Arrays.asList(20,2,4,2)));
			table.add(new ArrayList<Integer>(Arrays.asList(1,4,5,0)));
			runningSum2DList(table,4);
			print2DList(table);
			
	  }
	
}
