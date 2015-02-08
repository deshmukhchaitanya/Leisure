package Strings;

/* Find longest palindromic subsequence of a given string.
 * The idea is to use LCS concept which gives us the longest
 * common subsequence between two strings. So, if we reverse the
 * given string and find the LCS of reversed string with the
 * original string we should get the longest common subsequence
 * which is also a palindrome.
 */


public class LongestPalindromicSubsequence {

	public static void main(String[] args) {
		// original string
		String str1="GEEKSFORGEEKS";
		char arr[]=str1.toCharArray();
		// reversing the original string
		for (int i = 0; i < arr.length/2; i++) {
			char temp=arr[i];
			arr[i]=arr[arr.length-i-1];
			arr[arr.length-i-1]=temp;
		}
		String str2=new String(arr);
		
		// building the LCS table for original and reversed string
		int[][] lcs=buildLCS(str1,str2);
		// retrieving the LCS from the table
		String LCS=getLCS(str1,str2,lcs);
		System.out.println("Longest Palindrome: "+LCS);
	}
	// returns the LCS for two given strings using the lcs table
	private static String getLCS(String str1,String str2,int[][] lcs) {
		String LCS=new String();
		int i=lcs.length-1,j=lcs[0].length-1;
		while(i>0 && j>0)
		{
			if (str1.charAt(i-1) == str2.charAt(j-1))
			{
				LCS= LCS + str1.charAt(i-1);
				i--; j--;
			}
			else if (lcs[i-1][j] < lcs[i][j-1])
				j--;
			else
				i--;
		}
		return LCS;
	}
	// builds and return the lcs table for two given strings
	private static int[][] buildLCS(String str1, String str2) {
		
		int[][] lcs=new int[str1.length()+1][str2.length()+1];
		for (int i = 0; i < lcs.length; i++) {
			for (int j = 0; j < lcs[0].length; j++) {

				if(i==0 ||j==0)
					lcs[i][j]=0;
				else if(str1.charAt(i-1)==str2.charAt(j-1))
				{
					lcs[i][j]=lcs[i-1][j-1]+1;
				}
				else
				{
					lcs[i][j]=Math.max(lcs[i-1][j], lcs[i][j-1]);
				}
			}
		}
		return lcs;
	}
}
