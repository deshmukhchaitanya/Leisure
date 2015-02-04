package Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// Print the next greater element(NGE) for each element in an array. If the NGE for 
// an element is not present then assign -1 as NGE. 

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

	private static HashMap<Integer,Integer> findNGE(int[] arr) 
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
		while(!stck.isEmpty())
		{
			out.put(stck.pop(),-1);
		}
		return out;
		
	}
}
