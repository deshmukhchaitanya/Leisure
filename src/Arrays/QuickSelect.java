package Arrays;

// Quick Select implementation with average complexity of O(n) but worst case is still O(n^2)

public class QuickSelect {

	public static void main(String[] args) 
	{
		int[] array={2,5,3,7,6,8,4,1,9,10};
		int num=quickSelect(array,array.length/2);
		System.out.println("Number is: "+num);
	}
	private static int quickSelect(int[] array, int k) 
	{
		return quickSelect(array,0,array.length-1,k);
	}
	public static int quickSelect(int[] array,int start ,int end, int kthIndex)
	{
		if(start>=end)
			return array[start];
		else
		{
			int absPartitionIndex=partition(array, start, end);
			int relativpartitionIndex=absPartitionIndex-start; 
			if(kthIndex==relativpartitionIndex)
				return array[absPartitionIndex];
			else if(kthIndex<relativpartitionIndex)              // then we search in left part of the array
				return quickSelect(array,start ,absPartitionIndex-1,kthIndex);
			else												// then we search in right part of the array
				return quickSelect(array,absPartitionIndex+1 ,end,kthIndex-relativpartitionIndex-1);
		}
		
	}
	private static int partition(int[] array, int start, int end) 
	{
		int pivot=array[end];
		int i=start-1;
		// start from start if num < pivot bring it ahead of index of pivot 
		for(int j=start;j<=end-1;j++)
		{
			if(array[j]<=pivot)
			{
				i=i+1;
				int temp=array[i];
				array[i]=array[j];
				array[j]=temp;
			}
		}
		int temp=array[i+1];
		array[i+1]=array[end];
		array[end]=temp;
		return i+1;
	}
}
