package Tree.BinaryTree;

import Tree.BST.TreeNode;

// Find the max sum path from one leaf node to another

public class MaxSumPathBtwTwoLeaves 
{
	private static int maxSum=Integer.MIN_VALUE;
	public static void main(String[] args) 
	{
		
		
		TreeNode n1=new TreeNode(-15);
		TreeNode n2=new TreeNode(5);
		TreeNode n3=new TreeNode(-8);
		TreeNode n4=new TreeNode(2);
		TreeNode n5=new TreeNode(6);
		TreeNode n6=new TreeNode(1);
		TreeNode n7=new TreeNode(3);
		TreeNode n8=new TreeNode(6);
		TreeNode n9=new TreeNode(9);
		TreeNode n10=new TreeNode(0);
		TreeNode n11=new TreeNode(4);
		TreeNode n12=new TreeNode(-1);
		TreeNode n13=new TreeNode(10);
		n1.setLeft(n2);
		n2.setLeft(n3);
		n3.setLeft(n4);
		n3.setRight(n5);
		n2.setRight(n6);
		n1.setRight(n8);
		n8.setLeft(n7);
		n8.setRight(n9);
		n9.setRight(n10);
		n10.setLeft(n11);
		n10.setRight(n12);
		n12.setLeft(n13);
		//TreeTraversals.inorder(n1);
		findMaxSumPathBtwTwoLeaves(n1);
		System.out.println("Max sum path has sum= "+maxSum);
	}
	// idea is to check for each node if the sum of data at node + max sum 
	// of path from left child + max sum of path from right child is > current max sum
	// if it is them update the max 
	// return the sum of the path with larger sum(out of left or right child) and data part

	private static int findMaxSumPathBtwTwoLeaves(TreeNode node) {
		if(node==null)
			return -1;
		int left=0,right=0;
		if(node.getLeft()!=null)
			left=findMaxSumPathBtwTwoLeaves(node.getLeft());
		if(node.getRight()!=null)
			right=findMaxSumPathBtwTwoLeaves(node.getRight());
		if(left+node.getData()+right>maxSum)
			maxSum=left+right+node.getData();
		return Math.max(left, right)+node.getData();
	}

}
