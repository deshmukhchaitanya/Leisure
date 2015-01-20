package Matrix;
// implement the MS paint 'fill color' like function
// given a picture as matrix and a position in matrix
// to fill given another color ex. fillColor(mat,4,4,3)
// performs fill operation on mat[4][4] and replace its 
// color '2' with given color '3' and also replaces adjacent
// positions with color 2, just like a paint fill does.

public class MSFillColor 
{
	public static void main(String[] args) 
	{
		int mat[][]={{1, 1, 1, 1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1, 1, 0, 0},
				{1, 0, 0, 1, 1, 0, 1, 1},
				{1, 2, 2, 2, 2, 0, 1, 0},
				{1, 1, 1, 2, 2, 0, 1, 0},
				{1, 1, 1, 2, 2, 2, 2, 0},
				{1, 1, 1, 1, 1, 2, 1, 1},
				{1, 1, 1, 1, 1, 2, 2, 1},
		};
		System.out.println("Before fill:");
		for(int i=0;i<mat.length;i++)
		{
			for(int j=0;j<mat[0].length;j++)
				System.out.print(mat[i][j]+" ");
			System.out.println();
		}
		System.out.println("After fill:");
		fillColor(mat,4,4,3);  // performs fill operation on mat[4][4] and replace with color '3'
		for(int i=0;i<mat.length;i++)
		{
			for(int j=0;j<mat[0].length;j++)
				System.out.print(mat[i][j]+" ");
			System.out.println();
		}
	}
	private static void fillColor(int[][] mat, int x, int y, int color) 
	{
		// get a box around the given x,y and do fillColor for each coordinate in box..repeat
		int oldColor=mat[x][y];
		mat[x][y]=color;
		int xMin=(x==0)?0:x-1;
		int xMax=(x==mat.length-1)?mat.length-1:x+1;
		int yMin=(y==0)?0:y-1;
		int yMax=(y==mat[0].length-1)?mat[0].length-1:y+1;
		fillColorInSurrounding(xMin,xMax,yMin,yMax,mat,color,oldColor);

	}
	private static void fillColorInSurrounding(int xMin, int xMax, int yMin, int yMax, int[][] mat,int color,int oldColor) 
	{
		for(int k=xMin;k<=xMax;k++)
		{
			for(int l=yMin;l<=yMax;l++)
			{
				if(mat[k][l]==oldColor)
					fillColor(mat, k, l, color);
			}
		}
	}
}
