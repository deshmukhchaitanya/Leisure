package Tree.BinaryTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

import Tree.BST.MyTree;
import Tree.BST.TreeNode;

public class TreeTraversals 
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
		postorderTwoStack(tree.getRoot());
		inorder(tree.getRoot());
		preorder(tree.getRoot());
		bfs(tree.getRoot());
	}
	// level order travelsal
	public static void bfs(TreeNode root)
	{
		Deque<TreeNode> q=new LinkedList<TreeNode>();
		TreeNode current=root;
		q.offer(current);
		while(!q.isEmpty())
		{
			current=q.removeFirst();
			System.out.print(current.getData()+" ");
			if(current.getLeft()!=null)
				q.add(current.getLeft());
			if(current.getRight()!=null)
				q.offer(current.getRight());
		}
	}
	// http://www.geeksforgeeks.org/iterative-postorder-traversal-using-stack/
		/*
		 * 1.1 Create an empty stack
		   2.1 Do following while root is not NULL
	    		a) Push root's right child and then root to stack.
	    		b) Set root as root's left child.
		   2.2 Pop an item from stack and set it as root.
	    		a) If the popped item has a right child and the right child 
	       			is at top of stack, then remove the right child from stack,
	       			push the root back and set root as root's right child.
	    		b) Else print root's data and set root as NULL.
		    2.3 Repeat steps 2.1 and 2.2 while stack is not empty.
		 */
		public static void postorderOneStack(TreeNode node)
		{
			if (node==null)
				return;
			Stack<TreeNode> s = new Stack<>();

			do{
				while(node!=null)
				{
					if(node.getRight()!=null)
						s.push(node.getRight());
					s.push(node);
					node=node.getLeft();
				}
				node=s.peek();
				if((node.getRight()!=null) && (node.getRight()==s.peek())) //right child in not processed
				{
					TreeNode right=s.pop();
					s.push(node);
					node=right;
				}
				else
				{
					System.out.print(node.getData()+" ");
					node=null;
				}
			}while(!s.empty());

		}

	public static void postorderTwoStack(TreeNode node)
	{
		if(node==null)
			return;
		Stack<TreeNode> stck1=new Stack<TreeNode>();
		Stack<TreeNode> stck2=new Stack<TreeNode>();
		stck1.push(node);
		while(!stck1.empty())
		{
			TreeNode temp=stck1.pop();

			if(temp.getLeft()!=null)
				stck1.push(temp.getLeft());
			if(temp.getRight()!=null)
				stck1.push(temp.getRight());
			stck2.push(temp);
		}
		System.out.println("Postorder with two stacks:");
		while(!stck2.empty())
			System.out.print(stck2.pop().getData()+" ");
		System.out.println();
	}

	/* start with the root, push the root onto stack, push the right of root on stack, push left
	 * on stack. Print stack, now top of stack is left of root, repeat. 
	 */
	public static void preorder(TreeNode root)
	{
		if( root == null ) return;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode current=root;
		stack.push(current);
		while(!stack.isEmpty())
		{
			current=stack.pop();
			if(current.getRight()!=null)
				stack.push(current.getRight());
			if(current.getLeft()!=null)
				stack.push(current.getLeft());
			System.out.print(current.getData()+ " ");
		}
		System.out.println();
	}
	/* A node could be null or not null, if it is not null add it to stack,
	 * if it is null then two cases arise 1st it could be left/right node with stack empty
	 * i.e. the (rightmost)last node or 2nd it could be any other child of leaf node. 
	 * In 1st case as the algorithm dictates we go back to its parent this is done by popping element from 
	 * stack and making it as the current node. But if it is 2nd case that means we have
	 * traversed the tree thus we change the flag to true 
	 * */
	public static void inorder(TreeNode root)
	{
		if(root==null)
			return;
		TreeNode node=root;
		Stack<TreeNode> stack = new Stack<TreeNode>( );
		boolean done=false;

		while(!done)
		{
			if (node!=null)    
			{
				stack.push(node);
				node=node.getLeft();
			}
			else
			{
				if(stack.isEmpty())
					done=true;
				else
				{	
					node=stack.pop();
					System.out.print(node.getData()+" ");				
					node=node.getRight();
				}
			}
		}
		System.out.println();
	}
}
