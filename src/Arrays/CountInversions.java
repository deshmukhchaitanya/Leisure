package Arrays;

/* Program to count the number of inversions in an array.
 * We could do this in brute force manner using O(n^2) time but 
 * the better way is to use the merge sort algo with small change.
 * Idea is that if we have two sorted arrays say left[] and right[] 
 * then an element smaller than ith element in left will be smaller
 * than all the elements right to i in left[] as the arrays
 * are sorted.This will produce (length(left)-i) number of inversions.
 * Thus use merge phase of merge sort to find the number of inversion.
 * We find inversions corresponding to all the indices.The total number
 * of inversion for a index would be inversions at a index(mid) + inversions
 * in left sub array + inversions in right sub array. In merge phase
 * when an index is mid point, the sub arrays to left and right of
 * mid act as above left and right arrays and we can calculate the
 * number of inversions=mid-i where i is index in left array for which
 * array value is greater than value in right array.
 */
public class CountInversions 
{
	public static void main(String[] args) 
	{
		int arr[]={2,6,3,5,8,9,1};
		int inversions=mergeSort(arr,0,arr.length-1);
		System.out.println("Nomber of inversions: "+ inversions);
	}

	public static int mergeSort(int[] arr, int start, int end) {
		if(start>=end)
			return 0;
		int mid=start+((end-start)/2);
		int leftInversion=mergeSort(arr, start, mid);      // inversions from left
		int rightInversion=mergeSort(arr, mid+1, end);     // inversions from right
		int thisInversion=merge(arr,start,mid,end);        // inversions at this mid index
		return leftInversion+rightInversion+thisInversion; // total inversion at this mid index
	}
	// merge routine
	public static int merge(int[] arr,int start,int mid, int end) {
		int inversions=0;
		int [] left=new int[mid-start+1];
		int [] right=new int[end-mid];
		// create left sub array
		for(int i=start;i<=mid;i++)
		{
			left[i-start]=arr[i];
		}
		// create right sub array
		for(int i=mid+1;i<=end;i++)
		{
			right[i-mid-1]=arr[i];
		}
		int i = 0;
		int j = 0;
		int k = start;
		// merge them and count the inversion while doing so
		while (i < left.length && j < right.length)
		{
			if (left[i] <= right[j]) // left is smaller than right means no inversion
			{
				arr[k] = left[i];
				i++;
			}
			else					 // right is smaller than left thus there is inversion
			{
				arr[k] = right[j];
				j++;
				inversions+=mid-i;  // number of inversions for this mid index
			}
			k++;
		}
		while (i < left.length)   // copy any remaining elements in left
		{
			arr[k] = left[i];
			i++;
			k++;
			
		}
		while (j < right.length) // copy any remaining elements in right array 
		{
			arr[k] = right[j];
			j++;
			k++;
		}
		return inversions;
	}
}
