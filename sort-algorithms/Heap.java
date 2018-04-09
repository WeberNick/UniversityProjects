import java.util.Arrays;
public class Heap 
{
	public static void buildMaxHeap(int[] array)
	{
		for(int i = ((array.length -1) / 2); i >= 0; i--) 
		{
			Heap.maxHeapify(array, i);
		}
	}
	
	public static void maxHeapify(int[] array, int i)
	{
		int left, right, largest;
		left = (i+i+1);
		right = (i+i+2);
		
		if((left <= (array.length-1)) && (array[left] > array[i]))
		{
			largest = left;
		}
		else
		{
			largest = i;
		}
		if((right <= (array.length-1)) && (array[right] > array[largest]))
		{
			largest = right;
		}
		if(largest != i)
		{
			int help = array[i];
			array[i] = array[largest];
			array[largest] = help;
			Heap.maxHeapify(array, largest);
		}
	}
	
	public static void heapsort(int[] array)
	{
		int[] temp = new int[array.length];
		Heap.buildMaxHeap(array);
		System.out.println("Array nach Build-Max-Heap:");
		System.out.println(Arrays.toString(array));
		for (int i = array.length - 1; i >= 2; i--)
		{
			temp[i] = array[0];
			array[0] = array[i];
			
			int[] helpArray = new int[array.length-1];
			
			for (int j = 0; j < helpArray.length; j++) 
			{
				helpArray[j] = array[j];
			}
			array = helpArray;
			Heap.maxHeapify(array, 0);
		}
		temp[1] = array[0];
		temp[0] = array[1];
		array = temp;
		System.out.println("Sortiertes Array:");
		System.out.println(Arrays.toString(array));
	}
	
	public static void main(String[] args) 
	{
		int[] array = {10,4,8,6,26,16,18,22,14,12};
		System.out.println("Eingabe Array:");
		System.out.println(Arrays.toString(array));
		Heap.heapsort(array);
	}
}
