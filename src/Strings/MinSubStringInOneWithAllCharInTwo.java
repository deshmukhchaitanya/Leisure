package Strings;
import java.util.HashMap;
/*
 * Given two strings string1 and string2, find the smallest substring in string1 containing all 
 * characters of string2 efficiently.

For Example:

Input string1: “this is a test string”
Input string2: “tist”
Output string: “t stri”
http://leetcode.com/2010/11/finding-minimum-window-in-s-which.html
*/
public class MinSubStringInOneWithAllCharInTwo 
{
	public static void main(String[] args) 
	{
		findMinSubStringInOWithAllCharInTwo("this is a test string", "tist");
	}
	// desc: maintain a window that has all chars from two and try to minimize its length by removing char from the start
	public static void findMinSubStringInOWithAllCharInTwo(String one,String two)
	{
		if(one==null || two==null)
			System.out.println("No such sub string found");
		if(two.length()==0)
			System.out.println("empty string found");
		if(one.length()<two.length() || one.length()==0)
			System.out.println("No such sub string found");
		HashMap<Character,Integer> charsFound=new HashMap<Character,Integer>();
		HashMap<Character,Integer> charsNeeded=new HashMap<Character,Integer>();
		int currentWinStart=-1,currentWinLen=0;                    // keeps the track of current window start and length
		int minWinEnd=0,minWinStart=0,minWinLen=Integer.MAX_VALUE; // keeps the track of min window start and length
		int twoLen=two.length(); // length of two
		
		// fill up the charsNeeded hash map
		for(int i=0;i<two.length();i++)
		{
			if(!charsNeeded.containsKey(two.charAt(i)))
				charsNeeded.put(two.charAt(i), 1);
			else
			{
				int val=charsNeeded.get(two.charAt(i));
				val++;
				charsNeeded.put(two.charAt(i), val);
			}
		}
		boolean firstMatch=false; 				// to mark the start of window first time
		
		// now loop over one to find a window with all the strings and then keep on minimizing 
		// the size of window while maintaining the constraint (window must contain all char from two)
		for(int i=0;i<one.length();i++)
		{
			if(charsNeeded.containsKey(one.charAt(i)))
			{
				if(!firstMatch)
					currentWinStart=i;
				firstMatch=true; 				//first time window start marked
				if(!charsFound.containsKey(one.charAt(i)))
					charsFound.put(one.charAt(i), 1);
				else
				{
					int val=charsFound.get(one.charAt(i));
					val++;
					charsFound.put(one.charAt(i), val);
				}
			}
			else
				continue; // do nothing if char in one is not part of two
			if( charsFound.get(one.charAt(i)) <= charsNeeded.get(one.charAt(i)))
				currentWinLen++; 
			if(currentWinLen==twoLen) // window length == length of two. now, we continue to scan ahead in one and also try to minimize the window  
			{
				// if number of times this(i) char found is more than needed(number in two) then we can remove it from 
				// the start of window and push the start ahead until we find a char that is not extra (not more than needed)
				// this in turn minimizes the window length and as window follows the constraint we are getting a min sub string
				while(charsFound.get(one.charAt(currentWinStart))==null || charsFound.get(one.charAt(currentWinStart)) > charsNeeded.get(one.charAt(currentWinStart)))
				{
					if(charsFound.get(one.charAt(currentWinStart))==null)
						currentWinStart++;
					else if(charsFound.get(one.charAt(currentWinStart)) > charsNeeded.get(one.charAt(currentWinStart)))
					{
						int val=charsFound.get(one.charAt(currentWinStart));
						val--;
						charsFound.put(one.charAt(currentWinStart), val);
						currentWinStart++;
					}
					
				}
				// updating the min window
				int winLen=i-currentWinStart+1;
				if (winLen < minWinLen) 
				{
			        minWinStart = currentWinStart;
			        minWinEnd = i;
			        minWinLen = winLen;
			    }
			}
		}
		System.out.println("min sub string : "+one.substring(minWinStart, minWinEnd+1));
	}
}
