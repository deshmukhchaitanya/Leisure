package Tree.BST;

/* finding the lca in a BST, the idea is that the current node should lie 
 * between the two given nodes to be the lowest common parent 
 */

public class LeastCommonAncestor {

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
		TreeNode LCAnode=lca(tree.getRoot(), 14, 18);
		if(LCAnode!=null)
			System.out.println("LCA: "+LCAnode.getData());
		else
			System.out.println("LCA could not be found!!");
		

	}
	public static TreeNode lca(TreeNode root,int val1, int val2)
	{
		if( root == null ) 
			return null;
		TreeNode node1= MyTree.search(root,val1);
		TreeNode node2= MyTree.search(root,val2);
		if(node1==null || node2==null)
			return null;
		if(root.getData()<node1.getData() && root.getData()<node2.getData())
			return lca(root.getRight(),val1,val2);
		else if(root.getData()>node1.getData() && root.getData()>node2.getData())
			return lca(root.getLeft(),val1,val2);
		else
			return root;

	}
}
