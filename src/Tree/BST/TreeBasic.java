package Tree.BST;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import Tree.BinaryTree.TreeTraversals;

public class TreeBasic
{
	public static void main(String args[])
	{
		int[] pre={1,2,4,8,9,10,11,5,3,6,7};
		int[] in={8,4,10,9,11,2,5,1,6,3,7};
		TreeNode n= constructBinaryTree(pre, in, 0, 0, 11);
		MyTree tree2=new MyTree(n);
		TreeTraversals.inorder(tree2.getRoot());
		System.out.println();
		LinkedList<Integer> list=new LinkedList<Integer>();
		list.add(1);
		list.add(3);
		list.add(4);
		list.add(6);
		list.add(9);
		list.add(11);
		list.add(13);
		TreeNode root=sortedListToBST(list);
		MyTree anotherTree=new MyTree(root);
		TreeTraversals.inorder(anotherTree.getRoot());
		System.out.println();

		int arr[]= {0,1,2,4,5,6};

		MyTree tree= new MyTree(20);
		tree.insert(tree.getRoot(),23);
		tree.insert(tree.getRoot(),16);
		tree.insert(tree.getRoot(),14);
		tree.insert(tree.getRoot(),18);
		tree.insert(tree.getRoot(),22);
		tree.insert(tree.getRoot(),25);
		tree.insert(tree.getRoot(),28);
		topViewTree(tree.getRoot());
		leftView(tree.getRoot());
		verticalOrderTravelsal(tree.getRoot());
		int height=findHeightOfTree(tree.getRoot());
		System.out.println("Height of the tree is:"+height);
		System.out.println("Zig zag");
				
		System.out.println();
		TreeTraversals.postorderTwoStack(tree.getRoot());
		printNodeBtwLevels(tree.getRoot(), 3, 3, 1);
		//tree.postorderOneStack(tree.getRoot());
		MyTree tree1= new MyTree(23);
		tree1.insert(tree1.getRoot(),21);
		tree1.insert(tree1.getRoot(),25);
		if(subTree(tree.getRoot(),tree1.getRoot()))
			System.out.println("Second tree is a subtree of first");
		else
			System.out.println("Second tree is not a subtree of first");
		//StringBuffer str=sumPath(tree.getRoot(),64);
		//if(str!=null)
		//System.out.println(str.toString());

		//		int [] buff = new int[100];
		//		System.out.println("printing path(s):");
		//		findSum(tree.getRoot(), 64, buff, 0);
				
		//		// for checking balaced tree
		//		//tree.insert(tree.getRoot(),16);
		//		//tree.insert(tree.getRoot(),17);
		//		//tree.insert(tree.getRoot(),18);
		//		
		//		tree.preorder();
		//		System.out.println();
		//		tree.inorder();
		//		System.out.println();
		//		tree.preorder2nd();
		//		System.out.println();*/
		//		tree.printInorder(tree.getRoot());
		//		System.out.println(" Post order");
		//		tree.postorder();
		//		tree.preorder2nd();
		//		tree.preorder();
				
		//		int a[]={2,1,5,3,6,4};
		//		int index=qiuckselect(a,2);
		//		System.out.println(" index "+index);
		//		Node h=tree.sortedArrayToBST(a);
		//		MyTree tree1= new MyTree(3);
		//		tree1.setRoot(h);
		//		tree1.preorder();
		//		System.out.println();
				
	}
	
	public static void leftView(TreeNode node)
	{
		if(node==null)
			return;
		TreeMap<Integer, Integer> tm=new TreeMap<Integer,Integer>();
		fillTreeMapForLeftView(node,tm,0);
		System.out.print("Left view method: ");
		for(Integer i: tm.values())
		{
			System.out.print(i+" ");
		}
		System.out.println();
		/*buildTreeMapLevelOrder(tm,node); // O(nlogn)
		System.out.print("Top view method 2: ");
		for(Integer i: tm.values())
		{
			System.out.print(i+" ");
		}
		System.out.println();*/
	}
	public static void fillTreeMapForLeftView(TreeNode node,TreeMap<Integer, Integer> tm,int val)
	{
		if(!tm.containsKey(val))
			tm.put(val, node.getData());
		if(node.getLeft()!=null)
			fillTreeMapForLeftView(node.getLeft(), tm, val+1);
		if(node.getRight()!=null)
			fillTreeMapForLeftView(node.getRight(), tm, val+1);
	}
	
	// O(nlogn)
	public static void topViewTree(TreeNode node)
	{
		if(node==null)
			return;
		TreeMap<Integer, Integer> tm=new TreeMap<Integer,Integer>();
		fillTreeMap(node,tm,0);
		System.out.print("Top view method 1: ");
		for(Integer i: tm.values())
		{
			System.out.print(i+" ");
		}
		System.out.println();
		buildTreeMapLevelOrder(tm,node); // O(nlogn)
		System.out.print("Top view method 2: ");
		for(Integer i: tm.values())
		{
			System.out.print(i+" ");
		}
		System.out.println();
	}
	public static void fillTreeMap(TreeNode node,TreeMap<Integer, Integer> tm,int val)
	{
		if(!tm.containsKey(val))
			tm.put(val, node.getData());
		if(node.getLeft()!=null)
			fillTreeMap(node.getLeft(), tm, val-1);
		if(node.getRight()!=null)
			fillTreeMap(node.getRight(), tm, val+1);
	}
	private static void buildTreeMapLevelOrder(TreeMap<Integer, Integer> hm, TreeNode node) 
	{
		class Qnode {
			TreeNode node;
			int hd;
			Qnode(TreeNode node,int hd)
			{
				this.node=node;
				this.hd=hd;
			}
		}
		Queue<Qnode> q = new LinkedList<Qnode>();
		q.add(new Qnode(node, 0));
		while(!q.isEmpty()) // O(n)
		{
			Qnode qi = q.remove();
            int hd = qi.hd;
            TreeNode node1 = qi.node;
 
            if (!hm.containsKey(hd))  // O(logn)
            	hm.put(hd,node1.getData());
            if (node1.getLeft() != null)
                q.add(new Qnode(node1.getLeft(), hd-1));
            if (node1.getRight() != null)
                q.add(new Qnode(node1.getRight(), hd+1));
		}
	}
	// O(nlogn)
	public static void verticalOrderTravelsal(TreeNode node)
	{
		// key is horizontal distance(hd) wrt root and value is the list of all node with that hd
		TreeMap<Integer, List<Integer>> hm=new TreeMap<Integer,List<Integer>>();
		// traverses the tree according to the horizontal distance of a node
		buildTreeMapFromTree(hm,node,0);
		System.out.println("vertical order:");
		for(Map.Entry<Integer, List<Integer>> entry : hm.entrySet())
		{
			for(int i : entry.getValue())
			{
				System.out.print(i+" ");
			}
			System.out.println();
		}
	}
	public static void buildTreeMapFromTree(TreeMap<Integer,List<Integer>> hm, TreeNode node,int hd) 
	{
		List<Integer> l=null;
		if(!hm.containsKey(hd))
			l=new ArrayList<Integer>();
		else
		{
			l=hm.get(hd);
		}
		// add node to to the list of nodes with corresponding hd
		l.add(node.getData());
		hm.put(hd, l);
		if(node.getLeft()!=null)
			buildTreeMapFromTree(hm, node.getLeft(), hd-1);
		if(node.getRight()!=null)
			buildTreeMapFromTree(hm, node.getRight(), hd+1);
	}
	public static int findHeightOfTree(TreeNode node)
	{
		int h1=-1,h2=-1;
		if(node==null)
			return 0;
		else
		{
			h1=findHeightOfTree(node.getLeft());
			h2=findHeightOfTree(node.getRight());
		}
		
		return (Math.max(h1,h2)+1);
	}
	public static TreeNode constructBinaryTree(int[] preOrder, int[] inOrder, int preOrderIndex, int inOrderIndex, int length)
	{
		if (length == 0)
			return null;
		int nodeData = preOrder[preOrderIndex];
		TreeNode node = new TreeNode(nodeData);
		int rootIndex = 0;
		for (int count = inOrderIndex; count < inOrder.length; count++)
		{
			int inOrderData = inOrder[count];

			if (inOrderData == nodeData)
			{
				break;
			}
			rootIndex++;
		}
		node.setLeft(constructBinaryTree(preOrder, inOrder, preOrderIndex + 1,
				inOrderIndex, rootIndex));

		node.setRight(constructBinaryTree(preOrder, inOrder,
				preOrderIndex + rootIndex + 1, inOrderIndex + rootIndex + 1,
				length - (rootIndex + 1)));

		return node;
	}
	public static TreeNode sortedListToBST(LinkedList<Integer> list)
	{
		int size=list.size();
		TreeNode n=sortedListToBST(list,0,size-1);
		return n;
	}
	public static TreeNode sortedListToBST(LinkedList<Integer> list,int start,int end)
	{
		if(start>end)
			return null;
		int mid=start+(end-start)/2;
		TreeNode left=sortedListToBST(list, start, mid-1);
		TreeNode parent=new TreeNode(list.getFirst());
		parent.setLeft(left);
		list.remove();
		TreeNode right=sortedListToBST(list, mid+1, end);
		parent.setRight(right);
		return parent;
	}

	private static StringBuffer sumPath(TreeNode root, int num) 
	{
		//ArrayList<String> list=new ArrayList<String>();
		StringBuffer str=null;
		if(root==null)
			return null;
		num=Math.abs(root.getData()-num);
		if(num==0)
		{
			str=new StringBuffer();
			return str.append(String.valueOf(root.getData())+" ");
		}
		//else if(num<0)
		//return null;
		else
		{
			if(root.getRight()!=null)
			{
				str=sumPath(root.getRight(), num);
				if(str!=null)
				{
					str.append(String.valueOf(root.getData())+" ");


				}
			}
			if(root.getLeft()!=null)
			{
				str=sumPath(root.getLeft(), num);
				if(str!=null)
					str.append(String.valueOf(root.getData())+" ");
			}

		}
		return str;
	}

	// checks if the node2 is present in tree with node1....if present then check if the below 
	// tree is also same as that of node1, if not same then continue search for node2 in the tree(with node1)
	// in left of node1 and right of node1.
	public static boolean subTree(TreeNode node1,TreeNode node2)
	{
		if( node1== null)
			return false;
		if(node2 == null)
			return true;
		if(node1.getData()==node2.getData())
		{
			return matchTree(node1,node2); // matches the below tree
		}
		return ((subTree(node1.getLeft(), node2)) || subTree(node1.getRight(), node2));
	}
	// matches if tree below node1 is same as tree below node2
	private static boolean matchTree(TreeNode node1, TreeNode node2) 
	{
		if(node1==null && node2==null)    // true if reached to the end of both trees
			return true;				  
		if (node1!=null && node2==null)   // false if  one of the tree is not empty
			return false;
		if (node1==null && node2!=null)   // false if  one of the tree is not empty
			return false;
		// continue matching down the trees
		return (matchTree(node1.getLeft(), node2.getLeft())&& matchTree(node1.getRight(), node2.getRight()));

	}
	public static void findSum(TreeNode node, int sum, int [] buffer, int level) {
		if (node == null) { return; }
		int temp = sum;
		buffer[level] = node.getData();

		//look up through the path we have traversed to see if we have found the sum.
		for (int i=level; i>=0; i--) {
			temp = temp - buffer[i];
			if (0 == temp) {
				printPathSum(buffer, i, level);
			}
		}
		findSum(node.getLeft(), sum, buffer, level+1);
		findSum(node.getRight(), sum, buffer, level+1);
	}
	public static void printNodeBtwLevels(TreeNode node,int low,int high,int currentLevel)
	{
		if (node==null)
			return;
		if(currentLevel>=low && currentLevel<=high)
			System.out.println(node.getData());
		if(node.getLeft()!=null)
			printNodeBtwLevels(node.getLeft(), low, high, currentLevel+1);
		if(node.getRight()!=null)
			printNodeBtwLevels(node.getRight(), low, high, currentLevel+1);

	}
	public static void printPathSum(int [] buffer, int start, int end) {
		int len = buffer.length;
		for (int i=start; i<=end; i++) {
			System.out.print(buffer[i] + " -> ");
		}
		System.out.println("null");
	}
	public static int qiuckselect(int[] a,int i)
	{
		//System.out.println(a.length);
		int index=qiuckselect(a,0,a.length-1,i);
		return index;
	}
	public static int qiuckselect(int[] a,int strt,int end, int i) 
	{
		int q;
		if(end==strt)
			return a[end];
		q=partition(a, strt,end);
		int k=end-strt+1;
		if (i==k)
			return a[q];
		if(i<k)
			return qiuckselect(a,0,q-1,i);
		else
			return qiuckselect(a,q+1,end,i-k);
	}
	private static TreeNode mirror(TreeNode node)
	{
		if(node==null)
			return null;
		else 
		{
			node.setRight(mirror(node.getLeft()));
			node.setLeft(mirror(node.getRight()));
		}
		return node;

	}
	private static int partition(int[] a,int strt,int end) 
	{
		int pivot=a[end];
		int i=strt-1;
		int j=strt;
		for(j=strt; j<end-1;j++)
		{
			if( a[j]<=pivot)
			{
				i=i+1;
				int temp;
				temp=a[i];
				a[i]=a[j];
				a[j]=temp;
			}
		}
		int temp;
		temp=a[i+1];
		a[i+1]=a[end];
		a[end]=temp;

		return i+1;
	}
}

