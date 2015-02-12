package Arrays;

/* Given an unsorted array of positive integers. Find and display the number of triangles 
 * that can be formed with three different array elements as three sides of triangles.
 * For a triangle to be possible from 3 values, the sum of any two values (or sides) 
 * must be greater than the third value (or third side). Complexity is O(n^2)
 * 
 * http://www.geeksforgeeks.org/find-number-of-triangles-possible/
 * 
 * Idea is to sort the array first first then  fix first number arr[i] at i and 
 * second number arr[j] at j  and then find the largest number arr[k] at 
 * index k such that arr[i] + arr[j] > arr[k]. Such a triplet satisfies the
 * given condition for forming a triangle. Also all the k's > j but < largest k 
 * would also satisfy the condition as array is sorted. Thus the number of 
 * triangles that can be formed with arr[i] and arr[j] as two sides is k – j.
 * Thus add k – j to count of triangles.A naive solution could be O(n^3). 
 * Also we can pick first two numbers using two for loop and then find 
 * third using binary search giving O(n^2logn)
 */

import java.util.Arrays;

public class NumberOfTriangles 
{
	public static void main(String[] args) {
		int[] arr={10, 21, 22, 100, 101, 200, 300};
		int num=findNumberOfTriangles(arr);
		System.out.println("Number of different triangles: "+num);
	}

	public static int findNumberOfTriangles(int[] arr) 
	{
		if(arr==null)
			return 0;
		int triangleCount=0;
		int len=arr.length;
		Arrays.sort(arr);
		for(int i=0;i<len-2;i++) 
		{
			int k = i + 2;
			for(int j=i+1;j<len-1;j++) 
			{
				while (k<arr.length && arr[i]+arr[j]>arr[k]){
					if(j!=k)
						System.out.println("("+arr[i]+","+arr[j]+","+arr[k]+")");
					k++;
				}
				triangleCount+=k-j-1;
			}
		}
		return triangleCount;
	}
}
