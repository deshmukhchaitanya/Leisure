package Tree.BST;

public class MyTree
{
	private TreeNode root;

	public MyTree() 
	{
		root =null;
	}
	public MyTree(TreeNode n) 
	{
		root =n;
	}
	public MyTree(int val) 
	{
		root =new TreeNode(val);
	}
	public void insert(TreeNode node,int val)
	{	
		if(root==null)
			root =new TreeNode(val);
		if(node==null)
		{
			node=new TreeNode(val);
		}
		else if (val>=node.getData())
		{
			if(node.getRight()!=null)
				insert(node.getRight(), val);
			else
				node.setRight(new TreeNode(val));
		}		
		else
		{
			if(node.getLeft()!=null)
				insert(node.getLeft(), val);
			else
				node.setLeft(new TreeNode(val));
		}
	}

	public TreeNode getRoot() {
		return root;
	}
	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	public static TreeNode search(TreeNode root,int val)
	{
		if(root==null)
			return null;
		TreeNode current=root;
		while(current!=null && current.getData()!=val)
		{
			if(current.getData()>=val)
				current=current.getLeft();
			else
				current=current.getRight();
		}
		return current;

	}

	// this function gets the max height for a given node (from that node to leaf)
	public static int getHeight(TreeNode node)
	{
		if( node==null)
			return 0;
		else
			return Math.max(getHeight(node.getLeft()),getHeight(node.getRight()))+1;
	}
	
}