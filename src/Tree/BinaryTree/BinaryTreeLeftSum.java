package Tree.BinaryTree;

import Tree.BST.TreeNode;
import Tree.BinaryTree.TreeTraversals;

// find the sum of all left leaves in a binary tree

public class BinaryTreeLeftSum {

	public static void main(String[] args) 
	{
		TreeNode n1=new TreeNode(20);
		TreeNode n2=new TreeNode(9);
		TreeNode n3=new TreeNode(5);
		TreeNode n4=new TreeNode(12);
		TreeNode n5=new TreeNode(15);
		TreeNode n6=new TreeNode(49);
		TreeNode n7=new TreeNode(23);
		TreeNode n8=new TreeNode(52);
		TreeNode n9=new TreeNode(50);
		
		n1.setLeft(n2);
		n2.setLeft(n3);
		n2.setRight(n4);
		n4.setRight(n5);
		n1.setRight(n6);
		n6.setLeft(n7);
		n6.setRight(n8);
		n8.setLeft(n9);
		TreeTraversals.inorder(n1);
		
		int sum=findLeftSum(n1,false);
		System.out.println("Left Sum= "+ sum);
	}

	private static int findLeftSum(TreeNode node,boolean isLeft) 
	{
		if(node==null)
			return 0;
		if(node.getLeft()==null && node.getRight()==null && isLeft)
			return node.getData();
		int leftSum=findLeftSum(node.getLeft(), true);
		int rightSum=findLeftSum(node.getRight(), false);
		
		return leftSum + rightSum;
	}

}
