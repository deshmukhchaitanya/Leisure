package Arrays;
// finding the index of the 0 replacing which with 1 would give us the longest
// series of contiguos ones. for ex. in {1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1}
// there are four candidate 0's to be replaces but out of these only the one at
// index 9 gives us max count of contiguous ones.
public class ZeroToBeReplacedByOne 
{
	public static void main(String[] args) {
		int [] arr={1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1};
		int indx=findIndexOfZeroToBeReplaced(arr);
		System.out.println(indx);
	}
	public static int findIndexOfZeroToBeReplaced(int[] arr) 
	{
		boolean zero=false; // keeps track if we are counting for a previous zero 
		int count=0; 		// counts number of ones 
		int maxCount=Integer.MIN_VALUE;
		int index=-1,prevZero=-1;
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i]==1) // increse the count
				count++;
			if(arr[i]==0)
			{
				if(!zero) // means encountered 0 at this index for the first time so keep counting 1's ahead
				{
					prevZero=i;
					count++;
					zero=true;
				}
				else
				{
					// means this is a second 0 has already been encountered
					// thus record the count(of 1's) of previous 0 and update max-count if 
					// count of 1's for previous 0 is more than max-count, also reinitialize 
					// count of 0 at this index as as zero, make boolean 'zero' condition 
					// false and start to count from the index next of the previous zero
					if(count>maxCount)
					{
						maxCount=count;
						index=prevZero;
					}
					i=prevZero;
					count=0;
					zero=false;
					i=prevZero; // starting the count of 1's form index next o previous 0 
				}
			}
		}
		if(count>maxCount)
		{
			maxCount=count;
			index=prevZero;
		}
		return index;
	}
}
