package Tree.BinaryTree;

/* Program to construct a full binary tree from given post and pre order arrays.
 * Idea is to use the pre order array to find the root and its left child
 * then we search the post order array for the left child which gives us all
 * the elements in left sub tree of this node. Because in post order, root is at 
 * last and all the elements that occur before above found left child
 * will be part left sub tree of this root and rest of the elements in post order,
 * except the root itself, will be part of right sub tree. Using this information
 * we can construct the pre and post order arrays for next levels and pass
 * these array recursively to get left and right children of the current node. 
 */

import Tree.BST.TreeNode;

public class FullBinaryTreeFromPostPre {

	public static void main(String[] args) {
		int pre[] = {1,2, 4, 5, 3};
		 int post[] = {4, 5, 2, 3, 1};
		 TreeNode root=buildFullBinaryTreefromPrePost(pre,post);
		 TreeTraversals.inorder(root);
	}
	public static TreeNode buildFullBinaryTreefromPrePost(int[] pre,int[] post)
	{
		if(pre==null || post==null)
			return null;
		TreeNode root=buildFullBinaryTreefromPrePost(pre,0,pre.length-1,post,0,post.length-1);
		return root;
	}
	public static TreeNode buildFullBinaryTreefromPrePost(int[] pre, int preStart,
			int preEnd, int[] post, int postStart, int postEnd) {
		if(preStart>=preEnd || postStart>=postEnd)
		{
			TreeNode node= new TreeNode(pre[preStart]);
			return node;
		}
		TreeNode node= new TreeNode(pre[preStart]);

		// left child of current node
		int next=pre[preStart+1];
		
		// get values of indices of post arrays for left and right child
		int leftPostStart=postStart;
		int leftPostEnd=0;
		for(int i=postStart;i<=postEnd;i++)
		{
			if(post[i]==next)
			{
				leftPostEnd=i;
				break;
			}
		}
		int rightPostStart=leftPostEnd+1;
		int rightPostEnd=postEnd-1;

		// get values of indices of pre arrays for left and right child
		int leftpreStart=preStart+1;
		int leftPreEnd=0;
		for(int i=preStart;i<=preEnd;i++)
		{
			if(pre[i]==post[postEnd-1])
			{
				leftPreEnd=i-1;
				break;
			}
		}
		int rightPreStart=leftPreEnd+1;
		int rightPreEnd=preEnd;
		
		// pass above calculated array indices of pre and post order array to get left child
		TreeNode left=buildFullBinaryTreefromPrePost(pre, leftpreStart, leftPreEnd, post, leftPostStart, leftPostEnd);
		// pass above calculated array indices of pre and post order array to get right child
		TreeNode right=buildFullBinaryTreefromPrePost(pre, rightPreStart, rightPreEnd, post, rightPostStart, rightPostEnd);
		
		// update current node children
		node.setLeft(left);
		node.setRight(right);
		
		return node;
	}

	

}
