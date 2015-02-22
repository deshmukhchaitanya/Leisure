package Strings;


/* Find the longest palindromic substring of a given string.
 * Idea is to try every index as a center of a palindrome and keep
 * track of the longest palindrome found so far. we need to check 
 * if the palindrome around a point between i and i-1 or around i, 
 * in first case the length of the palindrome would be even whereas 
 * in case of around i, it will be odd. we need to consider both 
 * these types of palindromes. O(n^2) method. There is a Manacher's
 * algo for doing this in O(n). 
 */
public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		String str="forgeeksskeegfor";
		String palindrome=getLongestPalindromicSubstring(str);
		System.out.println(palindrome);
	}

	public static String getLongestPalindromicSubstring(String str) {

		int maxLength = 1, start = 0,len = str.length(), low, high;
		// try each index as center
		for (int i = 1; i < str.length(); i++) 
		{
			// first check if there is a palindrome of even length
			// low and high should form a palindrome centered between i and i-1
			low=i-1;
			high=i;
			// expand the palindrome if while palindrome constraint macth
			while(low >= 0 && high < len && str.charAt(low)==str.charAt(high))
			{
				// if this palindrome length is > max so far found then update
				if(high - low + 1 > maxLength)
				{
					maxLength=high-low+1;
					start=low;
				}
				low--;high++;
			}
			// similarly check if there is a palindrome of odd length of pattern _i_
			// low and high should form a palindrome around i
			low=i-1;
			high=i+1;
			while(low >= 0 && high < len && str.charAt(low)==str.charAt(high))
			{
				if(high - low + 1 > maxLength)
				{
					maxLength=high-low+1;
					start=low;
				}
				low--;high++;
			}
		}
		return str.substring(start, start + maxLength);
	}
}
