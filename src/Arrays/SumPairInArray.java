package Arrays;
import java.util.HashSet;

/* find all pairs that exist in an array with sum as given number.
 * One O(n) time and O(n) space method would be using a hash map.
 * Another O(nlogn) method would be to sort and then find the 
 * (number-arr[i]) element in the array.
 */ 

public class SumPairInArray {

	public static void main(String[] args) {
		int arr[]={1, 4, 45, 6, 10, 8};
		findSumPairInArray(arr,16);

	}
	// O(n) time and O(n) space
	public static void findSumPairInArray(int[] arr,int sum) {
		if(arr==null)
			return;
		HashSet<Integer> set=new HashSet<Integer>();
		for (int i = 0; i < arr.length; i++) {
			set.add(arr[i]);
		}
		for (int i = 0; i < arr.length; i++) {
			int diff=Math.abs(sum-arr[i]);
			if(set.contains(diff))
				System.out.println("("+diff+","+arr[i]+")");
		}
	}

}
