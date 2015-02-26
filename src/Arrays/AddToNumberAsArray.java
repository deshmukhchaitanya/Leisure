package Arrays;

// A number is given in the form of an array, add a given number to the number
// given as array.

public class AddToNumberAsArray {

	public static void main(String[] args) 
	{
		int [] arr={9,9,9};
		addToArrayAndPrint(arr,7);
	}

	public static void addToArrayAndPrint(int[] arr, int i) {
		int nextAdd=i;
		int [] brr=new int[arr.length+1];
		int j;
		for(j=arr.length-1;j>=0;j--)
		{
			int sum=arr[j]+nextAdd;
			if(sum>=10)
			{
				brr[j+1]=sum%10;
				nextAdd=1;
			}
			else
			{
				brr[j+1]=sum;
				nextAdd=0;
			}
		}
		if(nextAdd==1)
			brr[j+1]=nextAdd;
		for (int k=0;k<brr.length;k++)
			System.out.print(brr[k]);
	}
}
