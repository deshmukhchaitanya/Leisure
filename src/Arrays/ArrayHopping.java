package Arrays;

/*
 * Array hopper program to find the min number of hops to reach the end of an array
 * HopPath class is required to get the path of the min hops. In case where the hop
 * goes out of the array the path does not include last element but for cases where
 * the last hop lands exactly on the last element, then last element in included in
 * the path. there could be more than one paths with same number of hops, such cases
 * we choose first found path as the recursion rolls back. The programs also compares
 * different methods to accomplish this task which are: naive approach(exponential),
 * memoization with recursion and memoization with iteration which has best running
 * time(n^2) among others. Path finding is included only in naive method and could be
 * extended for other two methods.
 */


class HopPath
{
	private int hops;
	private String path;
	
	public HopPath(int i, String string) {
		hops=i;
		path=string;
	}
	public int getHops() {
		return hops;
	}
	public void setHops(int hops) {
		this.hops = hops;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void addToPath(int item)
	{
		this.path=item+" "+this.path;
	}
}
public class ArrayHopping 
{
	private static int []minHops;
	private static int memoizedCalls=0;
	private static int nonMemoizedCalls=0;
	private static int IterativeMemoizedCalls=0;
	public static void main(String[] args) 
	{
		int [] arr = {1, 3, 6, 3, 2, 3, 6, 8, 9, 5};
		minHops=new int[arr.length];
		for(int i=0;i<minHops.length-1;i++)
			minHops[i]=-1;
		HopPath hop=jumpsNaive(arr, 0);
		System.out.println("Minimum hops required using nonMemoizedCalls= "+hop.getHops());
		System.out.println("Total Calls made: "+nonMemoizedCalls);
		System.out.println("Minimum hops required using recursive MemoizedCalls= "+jumpsMemoized(arr, 0));
		System.out.println("Total Calls made: "+memoizedCalls);
		System.out.println("Minimum hops required using iterative MemoizedCalls= "+minJumps(arr, arr.length));
		System.out.println("Total Calls made: "+IterativeMemoizedCalls);
		System.out.println("Path for hops: "+hop.getPath());
	}
	// Memoized approach
	public static int jumpsMemoized(int[] arr, int i) 
	{
		if(i>=arr.length-1)
			return minHops[arr.length-1];
		if(minHops[i]==-1)
		{
			int numOfPaths=arr[i];
			int minj=Integer.MAX_VALUE-1;
			while(numOfPaths!=0)
			{
				int j=jumpsMemoized(arr, i+numOfPaths);
				memoizedCalls++;
				if(minj>j)
					minj=j;
				numOfPaths--;

			}
			minHops[i]=minj+1;
		}
		return minHops[i];
	}
	// non memoized naive recursive approach with modification to store the hop path
	public static HopPath jumpsNaive(int[] arr, int i) 
	{
		if(i>=arr.length-1)
		{
			HopPath hp;
			if(i==arr.length-1)
				hp=new HopPath(0,arr[arr.length-1]+"");
			else
				hp=new HopPath(0,"");
			return hp;
		}
		int numOfPaths=arr[i];
		HopPath minHop=new HopPath(Integer.MAX_VALUE-1,"");
		while(numOfPaths!=0)
		{
			HopPath hp=jumpsNaive(arr, i+numOfPaths);
			int j=hp.getHops();
			nonMemoizedCalls++;
			if(j<minHop.getHops())
			{
				minHop.setHops(j);
				minHop.setPath(hp.getPath());
				minHop.addToPath(arr[i]);
			}
			numOfPaths--;
		}
		minHop.setHops(minHop.getHops()+1);
		return minHop;
	}
	// from geeksforgeeks: here jumps[i] denote min number of jumps needed to reach i from a[0] 
	public static int minJumps(int arr[], int n)
	{
		int []jumps = new int[n];  // jumps[n-1] will hold the result
		int i, j;

		if (n == 0 || arr[0] == 0)
			return Integer.MAX_VALUE;

		jumps[0] = 0;

		// Find the minimum number of jumps to reach arr[i] from arr[0], and assign this value to jumps[i]
		// start with i=1
		for (i = 1; i < n; i++)
		{
			jumps[i] = Integer.MAX_VALUE;;
			// start from j=0 to i-1, check if i could be reached from j
			// this is possible if i <= j+arr[j] (i should fall within (index + value  at that index)) 
			// and jumps[i] is not already visited i.e. it should be MAX. Now jumps[i] could be found by
			// taking min from any prev value of jumps[i] and jumps[j] (for 0 to j jump) + 1 (j to i jump)
			for (j = 0; j < i; j++)
			{
				if (i <= j + arr[j] && jumps[j] != Integer.MAX_VALUE)
				{
					jumps[i] = Math.min(jumps[i], jumps[j] + 1);
					IterativeMemoizedCalls++;
					break;
				}
			}
		}
		return jumps[n-1];
	}
}
