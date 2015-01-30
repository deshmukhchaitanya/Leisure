package Random;

public class MinHeap 
{
	private int[] heap;
	private int filledIndex=-1;
	private int capacity;
	public MinHeap(int capacity) 
	{
		this.capacity=capacity;
		this.heap=new int[this.capacity];
	}
	public static int parent(int index)
	{
		if(index==0)
			return 0;
		if(index%2==0)
			return ((index/2)-1);
		else
			return index/2;
	}
	public int getMin()
	{
		if(filledIndex==-1)
			throw new ArrayIndexOutOfBoundsException();
		return heap[0];
	}
	public boolean isEmpty()
	{
		if(filledIndex==-1)
			return true;
		else
			return false;
	}
	public int size()
	{
		return filledIndex+1; 	
	}
	public void insert(int element)
	{
		filledIndex++;
		heap[filledIndex]=element;
		int current=filledIndex;
		while(heap[parent(current)]>heap[current])
		{
			swap(current,parent(current));
			current=parent(current);
		}
	}
	public int remove()
	{
		int min=heap[0];
		heap[0]=heap[filledIndex];
		filledIndex--;
		minHeapify(0);
		return min;
	}
	private void minHeapify(int index) {
		int left=left(index);
		int right=right(index);
		int min=index;
		if(left<filledIndex && left>=0 && heap[left]<heap[min])
			min=left;
		if(right<filledIndex && right>=0 && heap[right]<heap[min])
			min=right;
		if(min!=index)
		{
			swap(index, min);
			minHeapify(min);
		}
		
	}
	private int right(int index) {
		return (2*index)+2;
	}
	private int left(int index) {
		return (2*index)+1;
	}
	public void swap(int current, int parent) 
	{
		int tmp;
        tmp = heap[current];
        heap[current] = heap[parent];
        heap[parent] = tmp;
		
	}
	public static void main(String[] args) 
	{
		MinHeap minHeap=new MinHeap(10);
		minHeap.insert(20);
		minHeap.insert(10);
		minHeap.insert(11);
		System.out.println("Minimum: "+minHeap.getMin());
		minHeap.insert(5);
		System.out.println("Minimum: "+minHeap.getMin());
		minHeap.insert(6);
		minHeap.insert(8);
		minHeap.insert(4);
		System.out.println("Minimum: "+minHeap.getMin());
		minHeap.insert(2);
		System.out.println("Minimum: "+minHeap.getMin());
		System.out.println("Minimum removed: "+minHeap.remove());
		System.out.println("Minimum: "+minHeap.getMin());
		System.out.println("Minimum removed: "+minHeap.remove());
		System.out.println("Minimum: "+minHeap.getMin());
	}

}
