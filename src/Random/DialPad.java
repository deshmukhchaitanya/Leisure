package Random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DialPad 
{
	static HashMap<Integer,ArrayList<Character>> numbers;

	public static void main(String[] args) 
	{
		String dialedNumber="74745268";
		dialedNumber=dialedNumber.trim();
		makeDialPad(dialedNumber);
		ArrayList<String> words= getAllWords(dialedNumber,dialedNumber.length());
		if(words!=null)
		{
			for(String word:words)
				System.out.println(word);
		}
	}
	private static ArrayList<String> getAllWords(String dialedNumber,int offset)
	{
		boolean isNumber=validateDialedNumber(dialedNumber);
		if(dialedNumber.isEmpty())
		{
			System.out.println("No number dialed");
			return null;
		}
		if(!isNumber)
		{
			System.out.println("Invalid characters entered, only numbers from 0-9 are allowed");
			return null;
		}
		ArrayList<String> listOfWords=new ArrayList<String>();
		if(offset==0)
		{
			listOfWords.add("");
			return listOfWords;
		}	
		int numberInt=dialedNumber.charAt(dialedNumber.length()-offset)-'0';
		ArrayList<String> listOfWordsforNum=getWordsforThisNum(numberInt);
		ArrayList<String> nextlistOfWords=getAllWords(dialedNumber, offset-1);
		for(String word:listOfWordsforNum)
		{			
			for(String wordsInNextList:nextlistOfWords)
			{
				StringBuffer temporaryWord=new StringBuffer();
				temporaryWord.append(word);
				temporaryWord.append(wordsInNextList);
				listOfWords.add(temporaryWord.toString());
			}
		}
		return listOfWords;
	}

	private static boolean validateDialedNumber(String dialedNumber) 
	{
		boolean isNumber=false;
		Pattern pattern = Pattern.compile("\\d");
		char dialedNumberChar[]=dialedNumber.toCharArray();
		for(char eachNumber:dialedNumberChar)
		{
			StringBuffer character=new StringBuffer();
			character.append(eachNumber);
			Matcher matcher = pattern.matcher(character.toString());
			isNumber = matcher.matches();
			if(!isNumber)
				break;
		}
		return isNumber;
	}
	private static ArrayList<String> getWordsforThisNum(int n) 
	{
		ArrayList<Character> charList=numbers.get(n);
		ArrayList<String> listOfStringforthisNum=new ArrayList<String>();
		for(char c: charList)
		{
			StringBuffer stringOfChar=new StringBuffer();
			if(c==' ')
				stringOfChar.append("");
			else
				stringOfChar.append(c);
			listOfStringforthisNum.add(stringOfChar.toString());
		}
		return listOfStringforthisNum;
	}
	private static void makeDialPad(String dialedNumber) 
	{
		numbers=new HashMap<Integer,ArrayList<Character>>();
		char []dialednumberCharArray=dialedNumber.toCharArray();
		for(char number:dialednumberCharArray)
		{
			int numInt=number-'0';
			ArrayList<Character> listOfChar=new ArrayList<Character>();
			if(numInt==0)
			{
				listOfChar.add(' ');
			}
			else if(numInt==1)
			{
				listOfChar.add(' ');
			}
			else if(numInt==2)
			{
				listOfChar.add('a');
				listOfChar.add('b');
				listOfChar.add('c');
			}
			else if(numInt==3)
			{
				listOfChar.add('d');
				listOfChar.add('e');
				listOfChar.add('f');
			}
			else if(numInt==4)
			{
				listOfChar.add('g');
				listOfChar.add('h');
				listOfChar.add('i');
			}
			else if(numInt==5)
			{
				listOfChar.add('j');
				listOfChar.add('k');
				listOfChar.add('l');
			}
			else if(numInt==6)
			{
				listOfChar.add('m');
				listOfChar.add('n');
				listOfChar.add('o');
			}
			else if(numInt==7)
			{
				listOfChar.add('p');
				listOfChar.add('q');
				listOfChar.add('r');
				listOfChar.add('s');
			}
			else if(numInt==8)
			{
				listOfChar.add('t');
				listOfChar.add('u');
				listOfChar.add('v');
			}
			else if(numInt==9)
			{
				listOfChar.add('w');
				listOfChar.add('x');
				listOfChar.add('y');
				listOfChar.add('z');
			}
			numbers.put(numInt,listOfChar);
		}
	}
}