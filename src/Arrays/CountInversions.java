package Arrays;

import java.util.Arrays;
import java.util.HashMap;

public class CountInversions {

	public static void main(String[] args) {
		
		int [] arrCopy1={2,6,3,5,8,9,1};
	
		int inversionCount=countInversions(arrCopy1);  // O(nlogn)
		System.out.println("Number of inversions: "+inversionCount);
		int [] arrCopy2={2,6,3,5,8,9,1};
		inversionCount=countInversionsBrute(arrCopy2);  // O(n^2)
		System.out.println("Number of inversions: "+inversionCount);
	}

	private static int countInversionsBrute(int[] arr) {
		int count=0;
		for(int i=0;i<arr.length;i++)
		{
			for (int j = i+1; j < arr.length; j++) {
				if(arr[i]>arr[j])
					count++;
			}
		}
		return count;
	}

	private static int countInversions(int[] arr) 
	{
		return 0;
	}

}
