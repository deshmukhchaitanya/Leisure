package Tree.BinaryTree;

import Tree.BST.LeastCommonAncestor;
import Tree.BST.MyTree;
import Tree.BST.TreeNode;

/* given two numbers find the distance between corresponding nodes
 * them in a tree for this we use findLevel method which gives 
 * us the level of the node with given data or number as input.
 * */ 

public class DistanceBetweenTwoNodes 
{
	public static void main(String[] args) {
		
		MyTree tree= new MyTree(20);
		tree.insert(tree.getRoot(),23);
		tree.insert(tree.getRoot(),16);
		tree.insert(tree.getRoot(),14);
		tree.insert(tree.getRoot(),18);
		tree.insert(tree.getRoot(),22);
		tree.insert(tree.getRoot(),25);
		tree.insert(tree.getRoot(),28);
		
		int level=findLevel(tree.getRoot(), 14, 1);
		if(level==-1)
			System.out.println("Given node doesn't exist!");
		else
			System.out.println("Level of given node is: "+level);
		int dist=distanceBtwNodes(tree.getRoot(),28,50);
		if(dist==-1)
			System.out.println("One or both of the given node don't exist!");
		else
			System.out.println("Distance between given nodes: "+dist);
		
	}
	public static int findLevel(TreeNode node,int data,int level)
	{
		if(node==null)
			return -1;
		if (node.getData() == data)
			return level;
		int downlevel = findLevel(node.getLeft(), data, level+1);
		if (downlevel != -1)
			return downlevel;
		downlevel = findLevel(node.getRight(), data, level+1);
		return downlevel;
	}
	public static int distanceBtwNodes(TreeNode root,int val1,int val2)
	{
		int lvl1=findLevel(root, val1, 1);
		int lvl2=findLevel(root, val2, 1);
		if(lvl1==-1 || lvl2==-1)
			return -1;
		else
		{
			TreeNode node=LeastCommonAncestor.lca(root, val1, val2);
			int lvl3=findLevel(root, node.getData(), 1);
			int dist=lvl1+lvl2-2*lvl3;
			return dist;
		}
	}

}
