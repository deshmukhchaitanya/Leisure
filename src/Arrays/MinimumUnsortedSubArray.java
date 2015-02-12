package Arrays;

/* Given an unsorted array, write a program to find the minimum length unsorted sub array 
 * such that sorting this sub array would sort the complete array. O(n)
 * Idea is to find the smallest out of place element and largest out of place element
 * then the correct indices of these would give us the minimum length sub array that would
 * sort the complete array. 
 */

public class MinimumUnsortedSubArray {

	public static void main(String[] args) {
		int arr[]={0, 1, 15, 25, 6, 7, 30, 40, 50};
		int len[]=findMinimumUnsortedSubArray(arr);
		if(len!=null)
			System.out.println("Minimum length sub array sorting which would sort whole array is: from "+len[0]+" to "+len[1]);
	}

	public static int[] findMinimumUnsortedSubArray(int[] arr) 
	{
		if(arr==null)
			return null;
		int min=Integer.MAX_VALUE,max=Integer.MIN_VALUE;
		// find the smallest out of place element
		for (int i = 0; i < arr.length-1; i++) {
			if(arr[i]>arr[i+1] && arr[i+1]<min)
			{
				min=arr[i+1];
			}
		}
		// find the largest out of place element
		for (int i = arr.length-1; i >0; i--) {
			if(arr[i]<arr[i-1] && arr[i-1]>max)
			{
				max=arr[i-1];
			}
		}
		// now we to find there correct index
		// correct index of min element is index where we find the first element bigger than min
		// All elements before this would be sorted/in proper place (as this is the smallest out of place element) 
		int i=0;
		while(arr[i]<min) {
			i++;
		}
		int j=arr.length-1;
		// same reasoning for max as above for min jut we start from right and find correct
		// position of our largest out of place element
		while(arr[j]>max) {
			j--;
		}
		int[]result={i,j};
		return result;
	}
}
