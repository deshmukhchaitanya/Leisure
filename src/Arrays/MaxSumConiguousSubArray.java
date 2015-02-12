package Arrays;

import java.util.Arrays;

public class MaxSumConiguousSubArray {

	public static void main(String[] args) 
	{
		int[] arr={1, -3,-2, 4, -1, -2, 1, 5, -3,-7, 1,2,3,4};
		int[] sol=findContiguousSubArrayWithMaxSum(arr);
		for(int a: sol)
			System.out.print(a+" ");
	}
	// modification of kadane's algorithm
	private static int[] findContiguousSubArrayWithMaxSum(int[] arr) 
	{
		boolean allNegative=true;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]>0)
				allNegative=false;
		}
		if(allNegative)
		{
			Arrays.sort(arr); // or just find max element, put it array and return 
			int [] newArr=new int[1];
			newArr[0]=arr[arr.length-1];
			return newArr;
		}	
		int maxSoFar=0;
		int maxEndingHere=0;
		int start=-1; // keeps track of start current positive sum sub array
		int maxStart=-1,maxEnd=-1; // keep track of max sum array
		boolean continuing=false; // indicates if new sub array or continuation of sum from a previous positive element
		for(int i=0;i<arr.length;i++)
		{
			maxEndingHere=maxEndingHere+arr[i];
			if(maxEndingHere<0)
			{
				maxEndingHere=0; // sum becomes < 0 and thus continuation of sum sub array is marked false 
				continuing=false;
			}
			// goes in only when a new sum sub array is started
			if(maxEndingHere>0  && !continuing)
			{
				start=i;
				continuing=true;
			}
			// compare and update maxSoFar and corresponding indices
			if(maxEndingHere>maxSoFar)
			{
				maxSoFar=maxEndingHere;
				maxStart=start;
				maxEnd=i;
			}
		}
		System.out.println(maxStart+" "+maxEnd);
		System.out.println(maxEnd-maxStart+1);
		System.out.println(maxSoFar);
		int [] newArr=new int[maxEnd-maxStart+1];
		for(int i=maxStart;i<=maxEnd;i++)
			newArr[i-maxStart]=arr[i];
		return newArr;
	}

}
