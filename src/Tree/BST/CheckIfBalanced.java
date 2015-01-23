package Tree.BST;

/*
 * simply check if each node in the tree is balanced. if it is, then continue to 
 * check for its children if not return false. balanced could be checked by 
 * comparing heights on left and on right of that node.
*/
public class CheckIfBalanced {

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
		//tree.insert(tree.getRoot(),30);
		if(isBalanced(tree.getRoot()))
			System.out.println("Given tree is balanced!");
		else
			System.out.println("Given tree is not balanced!");
	}
	public static boolean isBalanced(TreeNode node)
		{
			if(node==null)
				return true;
			else
			{
				int hleft=MyTree.getHeight(node.getLeft());
				int hright=MyTree.getHeight(node.getRight());
				if(Math.abs(hleft-hright)>1)
					return false;
				else
					return (isBalanced(node.getLeft()) && isBalanced(node.getRight()));
			}
		}
}
