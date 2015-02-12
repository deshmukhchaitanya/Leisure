package Tree.BinaryTree;

import Tree.BST.TreeNode;

public class LeastCommonAncestor {

	public static void main(String[] args) {
		
		TreeNode n1=new TreeNode(3);
		TreeNode n2=new TreeNode(5);
		TreeNode n3=new TreeNode(6);
		TreeNode n4=new TreeNode(2);
		TreeNode n5=new TreeNode(7);
		TreeNode n6=new TreeNode(4);
		TreeNode n7=new TreeNode(1);
		TreeNode n8=new TreeNode(0);
		TreeNode n9=new TreeNode(8);
		
		n1.setLeft(n2);
		n2.setLeft(n3);
		n2.setRight(n4);
		n4.setLeft(n5);
		n4.setRight(n6);
		n1.setRight(n7);
		n7.setLeft(n8);
		n7.setRight(n9);
		TreeTraversals.inorder(n1);
		
		
		TreeNode lca=findLCAInBinaryTree(n1,n2,n6);
		if(lca!=null)
			System.out.println("LCA of two given nodes: "+lca.getData());
	}

	private static TreeNode findLCAInBinaryTree(TreeNode n1, TreeNode n2, TreeNode n3) {
		
		if(n1==null||n2==null||n3==null)
			return  null;
		TreeNode[] lca=new TreeNode[1];
		findLCAInBinaryTree(n1,n2,n3,lca);
		return lca[0];
	}

	public static boolean findLCAInBinaryTree(TreeNode root,TreeNode n2, TreeNode n7, TreeNode[] lca) {
		if(root==null)
			return false;
		boolean found=false;
		if(root.getData()==n2.getData() || root.getData()==n7.getData())
			found=true;
		if(root.getData()==n2.getData() && root.getData()==n7.getData())
		{
			lca[0]=root;
			return true;
		}
		
		boolean left=findLCAInBinaryTree(root.getLeft(), n2, n7, lca);
		boolean right=findLCAInBinaryTree(root.getRight(), n2, n7, lca);
		
		if(found)
		{
			if(left||right)
				lca[0]=root;
			else
				return found;
		}
		else if(left && right)
			lca[0]=root;
		else if(left || right)
			return true;
		
		return false;
		
	}

}
