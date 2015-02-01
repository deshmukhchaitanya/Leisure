package Strings;
import java.util.ArrayList;
import java.util.List;

// Print all the occurrences of a given pattern in a given text!! KMP algo implementation   

public class MyKMP {

	public static void main(String[] args) {
		String text="cbbcbcbcbcbc"; 
		String pattern="cbc";
		// pre-processing the pattern to construct the longest proper prefix that is also a suffix (lps) array 
		int [] lps=constructLPS(pattern);  
		if(lps!=null)
		{	
			List<Integer> list=KMPSearch(text,pattern,lps);
			if(list.size()==0)
				System.out.println("Pattern not found in text!");
			else
			{
				System.out.print("Pattern found at indices: ");
				for(int index:list)
					System.out.print(index+" ");
			}
		}
		else
			System.out.println("Please enter a valid pattern to search!");
	}
	// KPM search routine its similar to brute force except it avoids 
	// starting the search again from the start of pattern instead
	// it uses the lps array to find the index from where to start the 
	// search. 
	private static List<Integer> KMPSearch(String text, String pattern,	int[] lps) 
	{
		List<Integer> list=new ArrayList<Integer>();
		if(text!=null)
		{
			int i=0,j=0;   // i is marker in text and j is in pattern. j keep the track of the matched characters 
			while(i<text.length())
			{
				// if the char match
				if(text.charAt(i)==pattern.charAt(j)) 
				{
					i++;
					j++;
					if(j==pattern.length())  		  // if the char match then check if the pattern is found 
					{
						list.add(i-pattern.length()); // if pattern is found then add the text index to the list
						j=0;						  // reinitialize the pattern marker to 0.
						i=i-pattern.length()+1;		  // start the search again from next of the index where the pattern is found
					}
					continue;						  // continue the search ahead
				}
				// if the char do no match then use the lps to find the index from where to start the search
				else if(text.charAt(i)!=pattern.charAt(j))
				{
					// in case the mismatch occurs if its at start of pattern then this check avoids exception and 
					// if mismatch is not at the start of the pattern then we know that characters pat[0..j-1] match
					// with text[i-j+1…i-1], and we also know that lps[j-1] characters of pat[0…j-1] are both proper
					// prefix and suffix which means we do not need to match these lps[j-1] characters with text[i-j…i-1]
					// because we know that these characters will anyway match. thus now we start the search from 
					// lps[j-1] index in the pattern 
					if(j!=0)		 
						j=lps[j-1];
					// this occurs when the text char doesn't match with first char in pattern, in this case just move to next char in text
					else			
						i++;
				}
				
			}
		}
		return list;
	}
	// this method constructs longest prefix which is also a suffix array such that given 
	// a sub-pattern pat[0..i], lps[i] is the length of longest prefix of pat[0..i] which is also
	// a suffix of pat[0..i] 
	private static int[] constructLPS(String pattern) {
		if(pattern==null)
			return null;
		if(pattern.length()==0)
			return null;
		int [] lps=new int[pattern.length()];
		// we keep track of the previous prefix and suffix. 
		String oldPrefix="",oldSuffix="",thisPrefix="",thisSuffix="";
		lps[0]=0;
		for(int i=1;i<pattern.length();i++)
		{
			thisSuffix=oldSuffix + pattern.charAt(i);  							// Get current suffix by appending the current char to the previous suffix to 
			thisPrefix=oldPrefix + pattern.charAt(thisSuffix.length()-1);       // Get the current prefix from the pattern of length equal to current suffix

			// if the above two are equal then we have found a prefix that is also a
			// suffix of pat[0..current i] thus add the length of suffix to lps and
		    // update the previous suffix and prefix to this suffix and prefix.
			// else put lps as 0 and re-initialize all suffixes and prefixes to ""
			if(thisSuffix.equals(thisPrefix)) 
			{
				lps[i]=thisSuffix.length();
				oldPrefix=thisPrefix;
				oldSuffix=thisSuffix;
			}
			else
			{
				oldPrefix="";
				thisPrefix="";
				oldSuffix="";
				thisSuffix="";
				lps[i]=0;
			}
		}
		return lps;
	}
}
