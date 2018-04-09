import java.util.Arrays;

public class InsertionSort 
{
	public static void insertionSort(int[] array)
	{
		System.out.println("Unsortiert: ");
		System.out.print(Arrays.toString(array) + "\n");
		
		for (int j = 1; j < array.length; j++) 
		{
			int key = array[j];
			int i = j - 1;
			while(i >= 0 && array[i] > key)
			{
				array[i+1] = array[i];
				i--;
			}
			array[i+1] = key;
		}
		
		System.out.println("Sortiert: ");
		System.out.print(Arrays.toString(array) + "\n");
	}
	
	public static void main(String[] args) 
	{
		int[] einArray = {5,2,4,6,1,3};
		InsertionSort.insertionSort(einArray);
	}
}
