package Tree.BST;

import Tree.BinaryTree.TreeTraversals;

// construct a minimum binary search tree from a given sorted array
// and return the root node. We get a minimum tree because we start 
// with the mid of array and by this method we will get a tree of minimum height.  
public class MinBSTFromSortedArray {

	public static void main(String[] args) {
		int arr[]= {0,1,2,3,4,5,6};
		TreeNode root=sortedArrayToBST(arr);
		TreeTraversals.inorder(root);
		
	}
	public static TreeNode sortedArrayToBST(int arr[], int start, int end) 
	{
		if (start > end) 
			return null;
		int mid = start + (end - start) / 2;
		TreeNode node = new TreeNode(arr[mid]);
		node.setLeft(sortedArrayToBST(arr, start, mid-1));
		node.setRight(sortedArrayToBST(arr, mid+1, end));
		return node;
	}
	public static TreeNode sortedArrayToBST(int arr[]) 
	{
		if(arr==null)
			return null;
		int n=arr.length;
		return sortedArrayToBST(arr, 0, n-1);
	}
}
