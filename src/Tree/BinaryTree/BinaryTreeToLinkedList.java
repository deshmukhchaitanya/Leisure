package Tree.BinaryTree;

// Write a program to convert a binary tree to doubly linked list O(n) time and O(1) space

// The idea is to use the tree node's left and right pointers as previous and next
// pointers of the linked list and modify the nodes as we traverse it in in-order manner.


import Tree.BST.TreeNode;

public class BinaryTreeToLinkedList {
	
	public static void main(String[] args) 
	{
		TreeNode n1=new TreeNode(1);
		TreeNode n2=new TreeNode(2);
		TreeNode n3=new TreeNode(5);
		TreeNode n4=new TreeNode(0);
		TreeNode n5=new TreeNode(7);
		TreeNode n6=new TreeNode(6);
		
		n1.setLeft(n2);
		n2.setLeft(n3);
		n2.setRight(n4);
		n1.setRight(n5);
		n5.setLeft(n6);
		TreeTraversals.inorder(n1);
		System.out.println();
		TreeNode head=BinaryTreeToLL(n1);
		if(head!=null)
			printList(head);

	}

	private static void printList(TreeNode node) {
		while(node!=null)
		{
			System.out.print(node.getData()+"->");
			node=node.getRight();
		}
		System.out.println("null");
		
	}
	private static TreeNode BinaryTreeToLL(TreeNode node)
	{
		TreeNode[] head=new TreeNode[1];
		if(node!=null)
		{
			TreeNode[] previous=new TreeNode[1];
			BinaryTreeToLL(node,previous,head);
		}
		return head[0];
	}
	
	/* Traverse the tree in an in-order manner and convert the nodes such that left pointer
	 * becomes the previous pointer and right pointer becomes the next in the in linked list.
	 * prev[] is used to keep track of the previously visited array, similarly head[] is used to
	 * keep track of head of the linked list. As we traverse the tree in in-order manner,
	 * we make the current node's left as the previously visited node in in-order traversal
	 * which is the previous pointer in the linked list. Also we make this node the right of
	 * previously visited node, the right act as next pointer of the linked list.
	 * Also when we reach the left most node, its previous(left) is null and thus it becomes
	 * the head of the linked list. Note: for a node the previously visited node is basically
	 * its predecessor.
	 */
	private static void BinaryTreeToLL(TreeNode root,TreeNode[] prev,TreeNode[] head) 
	{
		 // Base case
	    if (root == null) 
	    	return;
	    
	    // Recursively convert left subtree
	    BinaryTreeToLL(root.getLeft(), prev,head);

	    // Now convert this node
	    
	    // this case occurs only for left most node
	    if (prev[0] == null)
	    	head[0] = root;
	    else
	    {
	        root.setLeft(prev[0]);
	        prev[0].setRight(root);
	    }
	    prev[0] = root; // update the previously visited node  

	    // Finally convert right subtree
	    BinaryTreeToLL(root.getRight(), prev, head);
	}
}
