package Matrix;

/* Program to find the largest square matrix with all ones in a given matrix(not necessarily square).
 * Use DP to solve this problem. Idea is to maintain a matrix of same size as input and fill (i,j)
 * in such a way that it contains the value of largest square matrix with all ones ending at i,j 
 * and i,j is the right-bottom corner of that square matrix.   
*/

public class LargestSquareSubMatrixWithAllOne {

	public static void main(String[] args) 
	{
		int M[][]={{0,1,1,0,1},
				   {1,1,0,1,0},
				   {0,1,1,1,0},
				   {1,1,1,1,0},
				   {1,1,1,1,1},
				   {0,0,0,0,0}};
		// initialize S matrix
		int S[][]=new int[M.length][M[0].length];
		for (int i=0;i<M[0].length-1;i++)
			S[0][i]=M[0][i];
		for (int i=0;i<M.length-1;i++)
			S[i][0]=M[i][0];
		for(int i=1;i<M.length-1;i++)
		{
			for(int j=1;j<M[0].length-1;j++)
			{
				if(M[i][j]==1)
				{
					int min=Math.min(S[i-1][j], S[i][j-1]);
					S[i][j]=Math.min(min, S[i-1][j-1])+1;
				}
				else
					S[i][j]=0;
			}
		}
		int max=M[0][0];
		int row=-1,col=-1;
		for (int i = 0; i < M.length; i++) 
		{
			for (int j = 0; j < M[0].length; j++) 
			{
				if(S[i][j]>max)
				{
					max=S[i][j];
					row=i;
					col=j;
				}
			}
		}
	
		for (int i =row-max+1; i <= row; i++) 
		{
			for (int j = col-max+1; j <= col; j++) 
			{
				System.out.print(M[i][j]+" ");
			}
			System.out.println();
		}

	}
}
