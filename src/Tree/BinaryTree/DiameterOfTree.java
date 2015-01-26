package Tree.BinaryTree;

import Tree.BST.TreeNode;
// Maximum distance from one leaf to another (it might not necessarily pass through the root)
public class DiameterOfTree {
	
	// maintain a global max and at each node find the sum of max 
	// distance of a leaf node in left subtree + max distance to leaf node
	// in right subtree. Compare it with current max and update the current
	// max if sum is greater
	private static int maxDiameter=Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		
		TreeNode n1=new TreeNode(10);
		TreeNode n2=new TreeNode(20);
		TreeNode n3=new TreeNode(30);
		TreeNode n4=new TreeNode(40);
		TreeNode n5=new TreeNode(50);
		TreeNode n6=new TreeNode(60);
		
		n1.setLeft(n2);
		n2.setLeft(n3);
		n2.setRight(n4);
		n3.setLeft(n5);
		n4.setRight(n6);
		TreeTraversals.inorder(n1);
		findDiameter(n1);
		
		System.out.println("Diameter of the tree is: "+maxDiameter);
	}

	private static int findDiameter(TreeNode root) 
	{
		if(root==null)
			return 0;
		int left=-1,right=-1;
		left=findDiameter(root.getLeft());   // max distance to left leaf node
		right=findDiameter(root.getRight()); // max distance to right leaf node
		int dia=left+right;
		if(dia>maxDiameter)
			maxDiameter=dia;
		return Math.max(left, right)+1;
	}

}
