import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class ListSort 
{
	static ArrayList[] myArrayList = {createArrayList(100), createArrayList(10000), createArrayList(1000000)};
	static LinkedList[] myLinkedList = {createLinkedList(100), createLinkedList(10000), createLinkedList(1000000)};
	
	static ArrayList[] myArrayListSorted = {createArrayListSorted(100), createArrayListSorted(10000), createArrayListSorted(1000000)};
	static LinkedList[] myLinkedListSorted = {createLinkedListSorted(100), createLinkedListSorted(10000), createLinkedListSorted(1000000)};
	
	public static LinkedList createLinkedList(int size)
	{
		LinkedList<Integer> myList = new LinkedList();
		for(int i = 0; i < size; i++)
		{
			myList.add((int)(Math.random() * (Integer.MAX_VALUE - 0) + 0));
		}
		Collections.shuffle(myList);
		return myList;
	}
	
	public static ArrayList createArrayList(int size)
	{
		ArrayList<Integer> myList = new ArrayList();
		for(int i = 0; i < size; i++)
		{
			myList.add((int)(Math.random() * (Integer.MAX_VALUE - 0) + 0));
		}
		Collections.shuffle(myList);
		return myList;
	}
	
	public static LinkedList createLinkedListSorted(int size)
	{
		LinkedList<Integer> myList = new LinkedList();
		for(int i = 0; i < size; i++)
		{
			myList.add((int)(Math.random() * (Integer.MAX_VALUE - 0) + 0));
		}
		Collections.sort(myList);
		return myList;
	}
	
	public static ArrayList createArrayListSorted(int size)
	{
		ArrayList<Integer> myList = new ArrayList();
		for(int i = 0; i < size; i++)
		{
			myList.add((int)(Math.random() * (Integer.MAX_VALUE - 0) + 0));
		}
		Collections.sort(myList);
		return myList;
	}
	
	public static void main(String[] args) 
	{
		//Aufgabe 1.3 a)
		long start, end;
		
		System.out.println("				<<Unsortierte LinkedList>>");
		
		System.out.print("Sortiere unsortierte LinkedList mit 100 Eintraegen. ");
		start = System.nanoTime();
		Collections.sort(myLinkedList[0]);
		end = System.nanoTime();
		System.out.println("Dauer: " + ((end - start)) + " Millisekunden.");
		
		System.out.print("Sortiere unsortierte LinkedList mit 10000 Eintraegen. ");
		start = System.nanoTime();
		Collections.sort(myLinkedList[1]);
		end = System.nanoTime();
		System.out.println("Dauer: " + ((double)(end - start)/1000000) + " Millisekunden.");
		
		System.out.print("Sortiere unsortierte LinkedList mit 1000000 Eintraegen. ");
		start = System.nanoTime();
		Collections.sort(myLinkedList[2]);
		end = System.nanoTime();
		System.out.println("Dauer: " + ((double)(end - start)/1000000) + " Millisekunden.");
		
		System.out.println("				<<Sortierte LinkedList>>");
		
		System.out.print("Sortiere sortierte LinkedList mit 100 Eintraegen. ");
		start = System.nanoTime();
		Collections.sort(myLinkedListSorted[0]);
		end = System.nanoTime();
		System.out.println("Dauer: " + ((double)(end - start)/1000000) + " Millisekunden.");
		
		System.out.print("Sortiere sortierte LinkedList mit 10000 Eintraegen. ");
		start = System.nanoTime();
		Collections.sort(myLinkedListSorted[1]);
		end = System.nanoTime();
		System.out.println("Dauer: " + ((double)(end - start)/1000000) + " Millisekunden.");
		
		System.out.print("Sortiere sortierte LinkedList mit 1000000 Eintraegen. ");
		start = System.nanoTime();
		Collections.sort(myLinkedListSorted[2]);
		end = System.nanoTime();
		System.out.println("Dauer: " + ((double)(end - start)/1000000) + " Millisekunden.");
		
		System.out.println("				<<Unsortierte ArrayList>>");
		
		System.out.print("Sortiere unsortierte ArrayList mit 100 Eintraegen. ");
		start = System.nanoTime();
		Collections.sort(myArrayList[0]);
		end = System.nanoTime();
		System.out.println("Dauer: " + ((double)(end - start)/1000000) + " Millisekunden.");
		
		System.out.print("Sortiere unsortierte ArrayList mit 10000 Eintraegen. ");
		start = System.nanoTime();
		Collections.sort(myArrayList[1]);
		end = System.nanoTime();
		System.out.println("Dauer: " + ((double)(end - start)/1000000) + " Millisekunden.");
		
		System.out.print("Sortiere unsortierte ArrayList mit 1000000 Eintraegen. ");
		start = System.nanoTime();
		Collections.sort(myArrayList[2]);
		end = System.nanoTime();
		System.out.println("Dauer: " + ((double)(end - start)/1000000) + " Millisekunden.");
		
		System.out.println("				<<Sortierte ArrayList>>");
		
		System.out.print("Sortiere sortierte ArrayList mit 100 Eintraegen. ");
		start = System.nanoTime();
		Collections.sort(myArrayListSorted[0]);
		end = System.nanoTime();
		System.out.println("Dauer: " + ((double)(end - start)/1000000) + " Millisekunden.");
		
		System.out.print("Sortiere sortierte ArrayList mit 10000 Eintraegen. ");
		start = System.nanoTime();
		Collections.sort(myArrayListSorted[1]);
		end = System.nanoTime();
		System.out.println("Dauer: " + ((double)(end - start)/1000000) + " Millisekunden.");
		
		System.out.print("Sortiere sortierte ArrayList mit 1000000 Eintraegen. ");
		start = System.nanoTime();
		Collections.sort(myArrayListSorted[2]);
		end = System.nanoTime();
		System.out.println("Dauer: " + ((double)(end - start)/1000000) + " Millisekunden.");
		
		//Aufgabe 1.3 d)
		
		ArrayList<Integer> mySortedArrayList = createArrayListSorted(10000);
		Collections.shuffle(mySortedArrayList);
		System.out.println();
		System.out.println("Sortiere unsortierte ArrayList mit 10000 Eintraegen.");
		for (int i = 0; i < 15; i++) 
		{
			start = System.nanoTime();
			Collections.sort(mySortedArrayList);
			end = System.nanoTime();
			System.out.println((i+1) + ". Dauer: " + ((double)(end - start)/1000000) + " Millisekunden.");
			Collections.shuffle(mySortedArrayList);
		}
		
	}	
}
