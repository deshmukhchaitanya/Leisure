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
		int res[]=getDiameter(n1);
		System.out.println(res[0]);
		System.out.println(res[1]);
		
	}

	public static int findDiameter(TreeNode root) 
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
	// below method does same thing but without using a global variable to keep track 
	// of max diameter. Instead, have an array of length 2 which stores diameter as 
	// height as 2nd and return this array at each recursion. To decide whether 
	// the diameter at this node is largest we just need to check if sum of max
	// height of left subtree of that node + the max height of right subtree 
	// is > current max diameter which is max of diameter from left and right.
	// This is available in result[0] from returned from the below children. 
	// we initialize result[0] of this node with max diameter up till this level
	// result[1] with max height.
	public static int[] getDiameter(TreeNode root) 
	{
	    int[] result = new int[]{0,0};        
	    if (root == null)  return result;
	    int[] leftResult = getDiameter(root.getLeft());
	    int[] rightResult = getDiameter(root.getRight());
	    int height = Math.max(leftResult[1], rightResult[1]) + 1;
	    int rootDiameter = leftResult[1] + rightResult[1];
	    int leftDiameter = leftResult[0];
	    int rightDiameter = rightResult[0];
	    result[0] = Math.max(rootDiameter, Math.max(leftDiameter, rightDiameter));
	    result[1] = height;

	    return result;
	}

}
