package Tree.BinaryTree;
import Tree.BST.TreeNode;
import Tree.BST.MyTree;
/*
 * Given a BST and a number k, remove all nodes that lie only on
 * root to leaf path(s) of length smaller than k. If a node X lies on
 * multiple root-to-leaf paths and if any of the paths has path-length >= k,
 * then X is not deleted from BST. In other words a node is deleted
 * if all paths going through it have lengths smaller than k  
 */

public class RmvNodesOnPathLessThanK {

	public static void main(String[] args) 
	{
		MyTree tree= new MyTree(20);
		tree.insert(tree.getRoot(), 21);
		tree.insert(tree.getRoot(), 23);
		tree.insert(tree.getRoot(), 22);
		tree.insert(tree.getRoot(), 18);
		tree.insert(tree.getRoot(), 19);
		tree.insert(tree.getRoot(), 17);
		tree.insert(tree.getRoot(), 16);
		TreeTraversals.inorder(tree.getRoot());
		System.out.println();
		boolean notShort=rmvNodesWithPathLTk(tree.getRoot(),4);
		if(!notShort)
		{
			System.out.println("whole tree has path length short than given path!!");
			tree.setRoot(null);
		}
		else
			TreeTraversals.inorder(tree.getRoot());
	}
	private static boolean rmvNodesWithPathLTk(TreeNode root, int k) 
	{
		if(root==null)
			return false;
		return rmvNodesWithPathLTkRecursive(root,k,0); // starting level with 0
	}
	// returns false if path is shorter than given path length otherwise return true
	// if any of the node on path is short, make its corresponding child null 
	private static boolean rmvNodesWithPathLTkRecursive(TreeNode node, int k, int level) 
	{
		if(node==null && level<=k-1)
			return false;
		if(node==null)      // reached a node below leaf level and path length >= k
			return true;
		if(level>=k) 		// all paths below this are greater than k length so no need to check further
			return true;
		boolean leftNotShort=rmvNodesWithPathLTkRecursive(node.getLeft(), k, level+1);
		boolean rightNotShort=rmvNodesWithPathLTkRecursive(node.getRight(), k, level+1);
		// when recursion rolls back just check if any of its left or right path are short 
		if(!leftNotShort)
			node.setLeft(null); // left path short
		if(!rightNotShort)
			node.setRight(null); // right path is short
		
		// return true even if at-least one of the path is not short
		return leftNotShort || rightNotShort;  
											  
	}

}
