package Matrix;

/*
 * Given a M * N matrix, if the element in the matrix is larger than other 8 elements who 
 * stay around it, then named that element be mountain point. Print all the mountain points.
 */
public class MountainPoints 
{
	public static void main(String[] args) 
	{
		int mat[][]={{10,2,3,4},{5,6,7,8},{9,10,11,12}};
		boolean out[][]=findMountainPoints(mat);
		for(int i=0;i<out.length;i++)
		{
			for(int j=0;j<out[0].length;j++)
			{
				if(out[i][j])
					System.out.println(i+","+j+" is a mountain point");
			}
		}
	}

	private static boolean[][] findMountainPoints(int[][] mat) {
		boolean out[][]=new boolean[mat.length][mat[0].length];
		for(int i=0;i<mat.length;i++)
		{
			for(int j=0;j<mat[0].length;j++)
			{
				int xMin=(i==0)?0:i-1;
				int xMax=(i==mat.length-1)?mat.length-1:i+1;
				int yMin=(j==0)?0:j-1;
				int yMax=(j==mat[0].length-1)?mat[0].length-1:j+1;
				out[i][j]=ifMaxInSurrounding(xMin,xMax,yMin,yMax,mat,i,j);
			}
		}
		return out;
	}

	private static boolean ifMaxInSurrounding(int xMin, int xMax, int yMin,
			int yMax, int[][] mat,int i,int j) {
		int num=mat[i][j];
		int max=num;
		for(int k=xMin;k<=xMax;k++)
		{
			for(int l=yMin;l<=yMax;l++)
			{
				if(mat[k][l]>max)
					max=mat[k][l];
			}
		}
		if(max==num)
			return true;
		else
			return false;
	}
}
