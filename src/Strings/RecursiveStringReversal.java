package Strings;

public class RecursiveStringReversal {

	public static void main(String[] args) {
		String str="chaitanya";
		StringBuilder strReversed=reverse(str);
		if(strReversed!=null)
			System.out.println(strReversed.toString());
	}

	public static StringBuilder reverse(String str) {
		if(str==null)
			return null;
		return reverse(str,0);
	}

	public static StringBuilder reverse(String str,int i) 
	{
		StringBuilder thisString=null;
		if(i>=str.length()-1)
		{
			thisString=new StringBuilder();
			thisString.append(str.charAt(i));
			return thisString;
		}
		thisString=reverse(str,i+1);
		thisString.append(str.charAt(i));
		return thisString;
	}

}
