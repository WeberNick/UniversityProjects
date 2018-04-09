import java.util.Arrays;


public class CountingSort 
{
	public static void countingSort(int[] a, int[] b, int k)
	{
		k = k+1;
		int[] c = new int[k];
		
		for (int i = 0; i < k; i++) 
		{
			c[i] = 0;
		}
		
		for (int i = 0; i < a.length; i++) 
		{
			c[a[i]] = c[a[i]] + 1;
		}
		
		for (int i = 1; i < k; i++) 
		{
			if(i < 2)
				c[0] = c[0] - 1;
			c[i] = c[i] + c[i-1];
		}
		
		for (int i = a.length - 1; i >= 0; i--) 
		{
			b[c[a[i]]] = a[i];
			c[a[i]] = c[a[i]] - 1;
		}
	}
	
	
	
	public static int maxElement(int[] array)
	{
		int temp = 0;
		for (int i = 0; i < array.length; i++) 
		{
			if(array[i] > temp)
			{
				temp = array[i];
			}
		}
		return temp;
	}
	
	public static int[] fillArray(int n, int k)
	{
		int[] temp = new int[n];
		for (int i = 0; i < temp.length; i++) 
		{
			temp[i] = (int)(Math.random() * (k+1));
		}
		return temp;
	}
	
	public static void main(String[] args) 
	{
//		int[] array = {5,4,2,2,3,5,6,1,0,3,5,4,2,1,6,5,5,0};
//		int[] sortiert = new int[array.length];
//		int max = CountingSort.maxElement(array);
//		System.out.println("Eingabe Array:\n" + Arrays.toString(array));
//		CountingSort.countingSort(array, sortiert, max);
		
		int[] neu_10 = CountingSort.fillArray(10, 15);
		int[] neu_10_copy = neu_10;
		int[] neu_10_empty = new int[neu_10.length];
		
		int[] neu_1000 = CountingSort.fillArray(1000, 15);
		int[] neu_1000_copy = neu_1000;
		int[] neu_1000_empty = new int[neu_1000.length];
		
		int[] neu_10000 = CountingSort.fillArray(10000, 15);
		int[] neu_10000_copy = neu_10000;
		int[] neu_10000_empty = new int[neu_10000.length];

		int[][][] allArrays = {{neu_10, neu_10_copy, neu_10_empty},{neu_1000,neu_1000_copy,neu_1000_empty},{neu_10000, neu_10000_copy, neu_10000_empty}};
		for (int i = 0; i < allArrays.length; i++) 
		{
			for (int j = 0; j < allArrays[i].length-1; j++) 
			{
				long start = 0, diff = 0;
				String anzElem = "";
				if(i == 0)
					anzElem = "10 Eintraegen";
				else if(i == 1)
					anzElem = "1.000 Eintraegen";
				else if(i == 2)
					anzElem = "10.000 Eintraegen";
				
				if(j == 0)
				{
					System.out.print("Sortieren durch Array Sort mit " + anzElem + " benoetigte ");
					start = System.nanoTime();
					Arrays.sort(allArrays[i][j]);
					diff = System.nanoTime() - start;
					System.out.print(diff + " Nanosekunden.\n\n");
				}
				else if(j == 1)
				{
					System.out.print("Sortieren durch Counting Sort mit " + anzElem + " benoetigte ");
					int k_max = CountingSort.maxElement(allArrays[i][j]);
					start = System.nanoTime();
					CountingSort.countingSort(allArrays[i][j], allArrays[i][2], k_max);
					diff = System.nanoTime() - start;
					System.out.print(diff + " Nanosekunden.\n\n");
				}
			}
		}
	}
}
