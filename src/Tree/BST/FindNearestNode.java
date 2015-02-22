package Tree.BST;

/*given a number and a binary search tree as input find the nearest node in a given tree */

public class FindNearestNode 
{
	public static void main(String[] args) 
	{
		MyTree tree= new MyTree(20);
		tree.insert(tree.getRoot(),23);
		tree.insert(tree.getRoot(),16);
		tree.insert(tree.getRoot(),14);
		tree.insert(tree.getRoot(),18);
		tree.insert(tree.getRoot(),22);
		tree.insert(tree.getRoot(),25);
		tree.insert(tree.getRoot(),28);
		
		TreeNode node=nearestNode(tree.getRoot(), 15, Integer.MAX_VALUE, null);
		if(node!=null)
			System.out.println("Nearest node is: "+node.getData());
		else
			System.out.println("Nearest node could not found!!");
		

	}
	public static TreeNode nearestNode(TreeNode node,int num,int minDiff,TreeNode minNode)
	{
		if(node==null)
			return minNode;
		else if(node.getData()==num)
			return node;
		
		int diff=Math.abs(node.getData()-num);
		
		if(diff<minDiff && node.getData()>num)
			return nearestNode(node.getLeft(), num, diff, node);
		else if(diff<minDiff && node.getData()<num)
			return nearestNode(node.getLeft(), num, diff, node);
		return minNode;
	}
}
