package Matrix;

// counting the number of islands of 1's in a matrix
import java.util.HashSet;
import java.util.Objects;

public class CountIslandOfOnes 
{
	public static void main(String[] args) {
		int mat[][]= {  {1, 1, 0, 0, 0},
		                {0, 1, 0, 0, 1},
		                {1, 0, 0, 1, 1},
		                {0, 0, 0, 0, 0},
		                {1, 0, 1, 0, 1}  };
		System.out.println("Number of islands of 1's is: "+countIslandsofOnes(mat));
	}
	// check for all cells if they have value 1 and are not already part of an 
	// previously found island, then traverse it in dfs (bfs would also work)   
	private static int countIslandsofOnes(int[][] mat) 
	{
		int count=0;
		HashSet<Cell> visited=new HashSet<Cell>();
		for(int i=0;i<mat.length;i++)
		{
			for(int j=0;j<mat[0].length;j++)
			{
				Cell cell=new Cell(i, j);
				if(mat[i][j]==1 && !visited.contains(cell))
				{
					visited.add(cell);
					markAllconnectedOnes(mat,i,j,visited);
					count++;
				}
			}
		}
		return count;
	}

	private static void markAllconnectedOnes(int[][] mat, int i, int j,	HashSet<Cell> visited) 
	{
		int xMin=(i==0)?0:i-1;
		int xMax=(i==mat.length-1)?mat.length-1:i+1;
		int yMin=(j==0)?0:j-1;
		int yMax=(j==mat[0].length-1)?mat[0].length-1:j+1;
		// check if the box around this cell has any 1's, if so then visit them and so on
		for(int k=xMin;k<=xMax;k++)
		{
			for(int l=yMin;l<=yMax;l++)
			{
				Cell cell=new Cell(k, l);
				if(mat[k][l]==1 && !visited.contains(cell))
				{
					visited.add(cell);
					markAllconnectedOnes(mat,k,l,visited);
				}
			}
		}
		
	}
}
// class to represent a Cell in a matrix
class Cell
{
	private int x;
	private int y;
	public Cell(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	@Override
	public boolean equals(Object obj) 
	{
		if(!(obj instanceof Cell))
			return false;
		Cell cell=(Cell)obj;
		return (this.x==cell.x && this.y==cell.y)?true:false;
		
	}
	@Override
	public int hashCode() 
	{
		int hashCode1=0,hashCode2=0;
		hashCode1=Objects.hash(this.x);
		hashCode2=Objects.hash(this.y);
		return hashCode1*hashCode2;
	}
}
