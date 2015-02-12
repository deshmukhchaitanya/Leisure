package Matrix;
import java.util.HashSet;
import java.util.TreeSet;


public class BoggleMatrix {

	public static void main(String[] args) {
		char mat[][]={{'S','M','E','F'},
				      {'R','A','T','D'},
				      {'L','O','N','I'},
				      {'K','U','C','B'}};
		HashSet<String> words=boggle(mat);
		TreeSet<String> sortedWords=new TreeSet<String>();
		sortedWords.addAll(words);
		for(String str: sortedWords)
		{
			System.out.println(str);
		}
	}

	public static HashSet<String> boggle(char[][] mat) 
	{
		HashSet<String> totalWords=new HashSet<String>();
		for (int i = 0; i < mat.length; i++) 
		{
			for (int j = 0; j < mat[0].length; j++) 
			{
				HashSet<Character> visited=new HashSet<Character>();
				HashSet<String> words=boggleRecursive(mat,i,j,visited);
				totalWords.addAll(words);
			}
		}
		return totalWords;
	}

	public static HashSet<String> boggleRecursive(char[][] mat, int i, int j, HashSet<Character> visited) 
	{
		HashSet<String> totalwords=new HashSet<String>();
		visited.add(mat[i][j]);
		totalwords.add(mat[i][j]+"");
		if(visited.size()>=mat.length*mat[0].length)
		{
			totalwords.add(mat[i][j]+"");
			return totalwords;
		}
		int xMin=(i==0)?0:i-1;
		int xMax=(i==mat.length-1)?mat.length-1:i+1;
		int yMin=(j==0)?0:j-1;
		int yMax=(j==mat[0].length-1)?mat[0].length-1:j+1;
		for(int k=xMin;k<=xMax;k++)
		{
			for(int l=yMin;l<=yMax;l++)
			{
				if(!(k==i && l==j) && !visited.contains(mat[k][l]))
				{
					HashSet<String> l1=boggleRecursive(mat, k, l, visited);
					for(String str:l1)
					{
						totalwords.add(str);
						if(str.charAt(0)==mat[k][l])
							totalwords.add(mat[i][j]+str);
					}
					visited.remove(mat[k][l]);
				}	
			}
		}
		return totalwords;
	}
}
