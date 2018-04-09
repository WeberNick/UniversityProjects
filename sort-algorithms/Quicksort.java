import java.util.Arrays;
public class Quicksort 
{
	public static void quicksort(int[] array, int p, int r)
	{
		if(p < r)
		{
			int q = Quicksort.partition(array, p, r);
			Quicksort.quicksort(array, p, q-1);
			Quicksort.quicksort(array, q+1, r);
		}
	}
	
	public static int partition(int[] array, int p, int r)
	{
		int x = array[r];
		int i = p - 1;
		for (int j = p; j < r; j++) 
		{
			if(array[j] <= x)
			{
				i++;
				int help = array[i];
				array[i] = array[j];
				array[j] = help;
			}
		}
		int help = array[i+1];
		array[i+1] = array[r];
		array[r] = help;
		System.out.println(Arrays.toString(array));
		return i+1;
	}
	
	
//	public static void quicksort2(int[] array, int p, int r)
//	{
//		if(p < r)
//		{
//			int[] q = Quicksort.partition2(array, p, r);
//			Quicksort.quicksort(array, p, q[0]-q[1]);
//			Quicksort.quicksort(array, q[0]+q[1], r);
//		}
//	}
//	
//	public static int[] partition2(int[] array, int p, int r)
//	{
//		int[] rueck = new int[2];
//		int x = array[r];
//		int i = p - 1;
//		int t = 0, c = 0, count = 1;
//		for (int j = p; j < r; j++) 
//		{
//			if(array[j] < x)
//			{
//				i++;
//				int help = array[i];
//				array[i] = array[j];
//				array[j] = help;
//			}
//			else if(array[j] == x)
//			{
//				t = j;
//				if(array[j+1] < x)
//				{
//					int help = array[j];
//					array[j] = array[j+1];
//					array[j+1] = help;
////					j--;
//				}
//				else if(array[j+1] > x)
//				{
//					int y = j+1;
//					while(array[y] > x)
//					{
//						y++;
//					}
//					int temp = array[y];
//					for (int k = y; k > j; k--) 
//					{
//						array[k] = array[k-1];
//					}
//					array[j+1] = temp;
//					j--;
//				}
//			}
//		}
//		int help = array[i+1];
//		array[i+1] = array[r];
//		array[r] = help;
//		System.out.println(Arrays.toString(array));
//		return rueck;
//	}
	
	
	public static void main(String[] args) 
	{
		int[] array = {10,4,8,6,12,26,16,18,12,22,14,12};
		System.out.println("Eingabe Array:");
		System.out.println(Arrays.toString(array) + "\n");
		Quicksort.quicksort(array, 0, array.length-1);
		
		System.out.println("\nSortiertes Array:");
		System.out.println(Arrays.toString(array));
	}
}
