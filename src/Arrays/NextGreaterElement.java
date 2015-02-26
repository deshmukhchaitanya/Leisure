package Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//Print the next greater element(NGE) to the right for each element in an array. 
// If the NGE for an element is not present then assign -1 as NGE. this is a O(n) 
// method with elegant use of of a stack otherwise brute force O(n^2) method is there.

public class NextGreaterElement {

	public static void main(String[] args) 
	{
		int [] arr={5,4,3,2,1,6};
		HashMap<Integer,Integer> out=findNGE(arr);
		if(out==null)
			return;
		for(Map.Entry<Integer, Integer> entry:out.entrySet())
		{
			System.out.println(entry.getKey()+"->"+entry.getValue());
		}
	}
	// push the first number in stack, then iterate over the array such that 
	// when you get a number greater than top of stack then keep popping till
	// you reach a larger number, at this point push the number at current index
	// Maintain a hash map for mapping a element to its nge. 
	public static HashMap<Integer,Integer> findNGE(int[] arr) 
	{
		if(arr==null)
			return null;
		HashMap<Integer,Integer> out=new HashMap<Integer,Integer>();
		
		Stack<Integer> stck=new Stack<Integer>();
		stck.push(arr[0]);
		for (int i = 1; i < arr.length; i++) 
		{
			while(!stck.isEmpty() && arr[i]>stck.peek())
			{
				int prev=stck.pop();
				out.put(prev, arr[i]);
			}
			stck.push(arr[i]);
		}
		// when all elements have been scanned but the stack is not empty that means
		// the elements do not have nge thus assign -1 as their nge.
		while(!stck.isEmpty())
		{
			out.put(stck.pop(),-1);
		}
		return out;
		
	}
}
