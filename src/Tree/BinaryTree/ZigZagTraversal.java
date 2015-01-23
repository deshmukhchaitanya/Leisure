package Tree.BinaryTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import Tree.BST.MyTree;
import Tree.BST.TreeNode;

/*	 could be performed using two stacks as well. but while using one stack and one queue always 
	 start by putting root in the stack and maintain the order i.e. if putting left child first 
	 and then right, then do the same for both insertion in queue and stack.
	 when using two stacks we need to alter the insertion order i.e. left then right child for one
	 stack and right then left for other stack.
	 notes: when printing from left to right make sure that rightmost child of rightmost node in current
	 level is picked up for printing or vice versa. this is where stack property helps as rightmost 
	 child of rightmost node in current level will be put last in stack and hence picked up first.
	               root
	              / or \
	          left---->rightmost 
	              |<----     
	              ----->|
*/
public class ZigZagTraversal 
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
		zigzagTraversal(tree.getRoot());
		
	}
	public static void zigzagTraversal(TreeNode root)
	{
		int count=1;
		Deque<TreeNode> queue=new LinkedList<TreeNode>();
		Stack<TreeNode> stack=new Stack<TreeNode>();
		TreeNode current=root;
		stack.push(current);
		while(!queue.isEmpty() || !stack.empty())
		{
			if(count%2!=0)
			{
				while(!stack.empty())
				{
					TreeNode n=stack.pop();
					System.out.print(n.getData()+" ");
					if(n.getLeft()!=null)
						queue.offer(n.getLeft());
					if(n.getRight()!=null)
						queue.offer(n.getRight());
				}
			}
			else
			{
				while(!queue.isEmpty())
				{
					TreeNode n=queue.removeFirst();
					System.out.print(n.getData()+" ");
					if(n.getLeft()!=null)
						stack.push(n.getLeft());
					if(n.getRight()!=null)
						stack.push(n.getRight());
				}
			}
			count++;
		}

	}
}
