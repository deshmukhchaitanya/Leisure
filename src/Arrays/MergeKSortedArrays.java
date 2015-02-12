package Arrays;

import java.util.PriorityQueue;

// Merging K sorted arrays. there could be duplicates, sizes of array could vary.
// The idea is to take a min heap of size k and fill it with values of the first 
// column (which contains the smallest value corresponding to each row). A pop from 
// the queue will give u the smallest among these. From the same row, put the next 
// element of that row in min heap. Repeat this process and keeping filling the 
// resulting 1D array.
public class MergeKSortedArrays 
{
	public static void main(String[] args) 
	{
		int arr[][] = { {1, 3, 5},
				{1, 4, 6, 8, 12, 15, 16},
				{9, 10, 11}};
		int result[]=mergreKSortedArrays(arr);
		for(int a:result)
			System.out.print(a+" ");
	}

	public static int[] mergreKSortedArrays(int[][] arr) 
	{
		if(arr==null)
			return null;
		if(arr.length==1) // single row
			return arr[0];
		// Local class to hold the value and indices of the array elements 
		class Node implements Comparable<Node>
		{
			private int value;
			private int row;
			private int col;
			public Node(int value,int row,int col) {
				this.setRow(row);
				this.setValue(value);
				this.setCol(col);
			}
			public int getValue() {
				return value;
			}
			public void setValue(int value) {
				this.value = value;
			}
			public int getRow() {
				return row;
			}
			public void setRow(int row) {
				this.row = row;
			}
			public int getCol() {
				return col;
			}
			public void setCol(int col) {
				this.col = col;
			}
			@Override
			public int compareTo(Node o) 
			{
				if(this.value>o.value)
					return 1;
				if(this.value<o.value)
					return -1;
				else
					return 0;
			}
		}
		PriorityQueue<Node> pq=new PriorityQueue<Node>(arr.length);
		int size=0; 
		// filling the queue with first column and calculating the size of result array
		for(int i=0;i<arr.length;i++)
		{
			size=size+arr[i].length;
			if(arr[i].length!=0) // prevent from inputs where one of the row is empty
			{
				Node n=new Node(arr[i][0], i,0);
				pq.offer(n);
			}
		}
		int [] result=new int[size];
		int i=0;
		// O(n*k*logk)
		while(!pq.isEmpty())
		{
			Node node=pq.poll(); // get the min element and put it in result array
			result[i]=node.getValue(); 
			i++;
			int k=node.getRow(); // get the indices
			int l=node.getCol();
			l++; // move to the next index in row where smallest element was found 
			// if not last index in row, put the value in min heap
			if(l!=arr[k].length)
			{
				Node nodeNext=new Node(arr[k][l], k,l );
				pq.offer(nodeNext);
			}
		}
		return result;
	}

}
