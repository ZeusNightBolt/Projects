/*
 * This code is for CSC172 Lab3 purpose. This code has been worked upon individually by Kathan Thakkar. 
 * I have not received any outside or unauthorized help. Some help was taken from StackOverflow for syntax purposes. 
 * Lab section : MW 3:25-4:40 Harkness 114
 * Question 3 src file
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

public class Lab3Task3 {
    /*
     * Different ways of iteration. Basic looping which prints out elements in Arraylist everytime in a new line one by one. 
     */
	public static void printArrayListBasicLoop(ArrayList<Integer> arr) {
		
		for(int i=0;i<arr.size();i++) {
			
			System.out.println(arr.get(i));
		}
	}
	
	public static void printArrayListEnhancedLoop(ArrayList<Integer> arr){
	
		
		for(Integer i : arr) {
			
			
			System.out.println(i);
		}
		
	}
	
	public static <E> void printArrayListForLoopListIterator(ArrayList<Integer> arr) {
		
		
		for (ListIterator<E> iter = (ListIterator<E>) arr.listIterator(); iter.hasNext(); ) {
		    E element = iter.next();
		    System.out.println(element);
		}
		
		
	}
	
	public static <E> void printArrayListWhileLoopListIterator(ArrayList<Integer> arr) {
		
		ListIterator<E> iter = (ListIterator<E>) arr.listIterator();
		while(iter.hasNext()) {
			
			E element= iter.next();
			System.out.println(element);
			
		}
		
		
		
				
		
	}
	
   public static <E> void printArrayListLambda(ArrayList<Integer> arr) {
	   
	   ListIterator<E> iter = (ListIterator<E>) arr.listIterator();
	   arr.forEach(System.out::println);
	   
	   
	   
	   
   }
	
	
	
	
	public static void main(String[] args) {
		
		
		ArrayList<Integer> table = new ArrayList<Integer>();
		table.addAll(new ArrayList<Integer>(Arrays.asList(10,15,30,40)));
		printArrayListWhileLoopListIterator(table);
		printArrayListLambda(table);
		
		
	}
	
	
	
	
}
