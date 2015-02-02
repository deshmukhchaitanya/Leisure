package Arrays;

/* Given an unsorted array of positive integers. Find and display the number of triangles 
 * that can be formed with three different array elements as three sides of triangles.
 * For a triangle to be possible from 3 values, the sum of any two values (or sides) 
 * must be greater than the third value (or third side). Complexity is O(n^2)
 * 
 * http://www.geeksforgeeks.org/find-number-of-triangles-possible/
 */

import java.util.Arrays;

public class NumberOfTriangles 
{
	public static void main(String[] args) {
		int[] arr={10, 21, 22, 100, 101, 200, 300};
		Arrays.sort(arr);
		int num=findNumberOfTriangles(arr);
		System.out.println(num);
	}

	private static int findNumberOfTriangles(int[] arr) 
	{
		int number=0;
		int len=arr.length;
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
				number+=k-j-1;
			}
		}
		return number;
	}
}
