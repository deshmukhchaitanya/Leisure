package Arrays;

// finds if an array contains a sub array with a given sum.
// Idea is to think of a window with first element of array as start and end.
// Then extending the end to the right by adding next elements in array. Sum of
// the window constitutes the current sum. Check if current sum is greater 
// then given sum, if so then remove the elements from the start of the
// window until current sum is not > given sum and until you reach the 
// end-1 of window. (Special case: We do not go till end as then current sum
// would become 0 and it will be misinterpreted and a positive case when given
// sum is itself 0. Each time check if the current sum == given sum.

public class SubArrayWithSum {

	public static void main(String[] args) {
		int arr[] = {15, 2, 4, 8, 9, 5, 10, 23};
		printSubArraysWithSum(arr,23);
	}

	private static void printSubArraysWithSum(int[] arr, int sum) 
	{
		int start=0,currSum=0;
		for (int end = 0; end <arr.length; end++)
		{
			currSum = currSum + arr[end];
			while (currSum > sum && start <end)
			{
				currSum = currSum - arr[start];
				start++;
			}
			if (currSum == sum)
				System.out.println("Sum found between indexes: "+ start+" and " + (end));
		}
	}
}
