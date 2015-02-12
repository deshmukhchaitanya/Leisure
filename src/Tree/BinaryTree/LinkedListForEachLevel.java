package Tree.BinaryTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import Tree.BST.MyTree;
import Tree.BST.TreeNode;

// given a binary tree return a List of Linked list for each level

public class LinkedListForEachLevel {

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
		HashMap<Integer,LinkedList<TreeNode>> hm=new HashMap<Integer,LinkedList<TreeNode>>();
		
		getLevelWiseLinkedList(tree.getRoot(),hm,1);
		for(Map.Entry<Integer, LinkedList<TreeNode>> entry: hm.entrySet())
		{
			for(TreeNode node: entry.getValue())
			{
				System.out.print(node.getData()+" ");
			}
			System.out.println();
		}

	}
	public static void getLevelWiseLinkedList(TreeNode root, HashMap<Integer, LinkedList<TreeNode>> hm,int level) 
	{
		if(root!=null)
		{
			
			LinkedList<TreeNode> list=hm.get(level);
			if(list==null)
				list=new LinkedList<TreeNode>();
			list.add(root);
			hm.put(level, list);
			getLevelWiseLinkedList(root.getLeft(), hm, level+1);
			getLevelWiseLinkedList(root.getRight(), hm, level+1);
		}
	}
}
