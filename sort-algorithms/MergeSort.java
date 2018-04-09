import java.util.Arrays;
public class MergeSort 
{
	
	//Feherlhafter Code
	private static int count = 0;
	
	public static void mergeSort(int[] array, int p, int r)
	{
		if(p < r)
		{
			int q = (p+r)/2;
			mergeSort(array, p, q);
			mergeSort(array, (q+1), r);
			merge(array, p, q, r);
		}
	}
	
	public static void merge(int[] array, int p, int q, int r)
	{
		int[] left = new int[q+1-p];
		int[] right = new int[r-q];
		for (int i = p, j = 0; i <= q; i++, j++) 
		{
			left[j] = array[i]; 
		}
		
		for (int i = q+1, j = 0; i <= r; i++, j++) 
		{
			right[j] = array[i];
		}
		
		int i = 0, j = 0;
		while(count < 8)
		{
			if((i < left.length) && (j < right.length))
			{
				if(left[i] < right[j])
				{
					array[count] = left[i];
					i++;
					count++;
				}
				else
				{
					array[count] = right[j];
					j++;
					count++;
				}
			}
			else
			{
				if(i < left.length)
				{
					for (int k = j; k < right.length; k++) 
					{
						array[count] = right[k];
						count++;
					}
					break;
				}
				if(j < right.length)
				{
					for (int k = i; k < left.length; k++) 
					{
						array[count] = left[k];
						count++;
					}
					break;
				}
			}
		}
		count = 0;
	}
		
	public static void main(String[] args) 
	{
		int[] einArray = {5,2,4,7,1,3,2,6};
		MergeSort.mergeSort(einArray, 0, einArray.length-1);
		System.out.println(Arrays.toString(einArray));
	}
}
