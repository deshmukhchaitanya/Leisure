package Arrays;

/* Given an array in which an element is at most k distance from its actual 
 * place(in sorted array). Write a program to sort the array. expected in O(nlogk)
 * Idea is to use a min heap of size k. we know that an element at index i in sorted
 * array would be the smallest element in the min heap considering we have sorted 
 * the i-1 elements which can be removed from heap easily in logk time. we repeat 
 * this process for n elements giving complexity O(nlogk).
 */ 

import java.util.PriorityQueue;

public class KSortedArray 
{
	public static void main(String[] args) 
	{
		int k = 3;
	    int arr[] = {2, 6, 3, 12, 56, 8};
	    sortKSortedArray(arr,k);
	    for(int i:arr)
	    	System.out.print(i+" ");
	}
	public static void sortKSortedArray(int[] arr,int k) 
	{
		PriorityQueue<Integer> minheap=new PriorityQueue<Integer>(k+1);
		int n=arr.length-1;
		// fill k elements
		for(int i=0;i<=k;i++)
			minheap.add(arr[i]);
		int i;
		// iterate over the array and keep removing from the heap  
		for(i=0;i<n-k;i++)
		{
			arr[i]=minheap.poll();
			minheap.offer(arr[i+k+1]);
		}
		// remove the remaining k elements
		while(minheap.peek()!=null)
		{
			arr[i]=minheap.poll();
			i++;
		}
	}

}
