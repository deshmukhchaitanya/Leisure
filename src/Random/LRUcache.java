package Random;
import java.util.HashMap;
import java.util.Map;

public class LRUcache 
{
	private HashMap<Integer,DoublyLinkedListNode> cache; // contains a page number and corresponding page (doubly linked list node)  
	private DoublyLinkedListNode head; // denotes most recently used page 
	private DoublyLinkedListNode end;  // denotes least recently used page
	private int size; // chache size
	private int capacity; // max capacity
	public LRUcache(int capacity) 
	{
		this.capacity=capacity;
		this.cache=new HashMap<Integer,DoublyLinkedListNode>();
		this.head=null;
		this.end=null;
		this.size=0;
	}
	// add a page to the cache
	public void add(int pageNum,int value)
	{
		// its a new page
		if(!cache.containsKey(pageNum))
		{
			DoublyLinkedListNode node=new DoublyLinkedListNode(pageNum,value);
			DoublyLinkedListNode currHead=getHead();
			if(size>=capacity) // remove last recently used node from the cache if it is full, last used page is nothing but end node
				remove();
			if(currHead==null) // first page is inserted into an empty cache thus current head is null
				setEnd(node); // make both head and end point to same page, head is updated below 
			else
				currHead.setPrev(node); // page inserted into a partially filled cache
			node.setNext(currHead);
			setHead(node);
			cache.put(pageNum, node);
			size++;
		}
		// its a already existing page, thus just update/access the page
		else
		{
			access(pageNum,value);
		}
	}
	private void access(int pageNum,int value)
	{
		DoublyLinkedListNode node=cache.get(pageNum);
		node.setData(value); //update
		if(node==getHead()) // single page in cache, no need to do anything
			return;
		// when a page is accessed then its rank increases in the cache meaning its set to new head
		// and necessary changes are made to next/previous pointer in the doubly linked structure
		DoublyLinkedListNode currHead=getHead();
		DoublyLinkedListNode prev=node.getPrev();
		DoublyLinkedListNode nxt=node.getNext();
		if(prev!=null && nxt!=null) // node accessed is in-between
		{
			prev.setNext(nxt);
			nxt.setPrev(prev);
		}
		else if(nxt==null)  // node accessed is last node
		{
			prev.setNext(nxt);
			setEnd(prev);
		}
		currHead.setPrev(node);
		node.setNext(currHead);
		setHead(node);
	}
	// retrieves the data from a page, this is a form of access.
	public int get(int pageNum) 
	{
		if (cache.containsKey(pageNum)) 
		{
			DoublyLinkedListNode node = cache.get(pageNum);
			access(pageNum, node.getData());
			return node.getData();
		} else {
			return -1;
		}
	}
	// remove end node cache and make necessary updates to the doubly linked list structure and cache size
	// we don't remove from a cache, its implicitly taken care of by cache itself
	// thus remove is private method (only add method needs it when size>=capacity)
	private void remove()
	{
		cache.remove(end.getPageNum());
		size--;
		DoublyLinkedListNode prev=end.getPrev();
		prev.setNext(null);
		setEnd(prev); // update the end
	}
	public void displayCache()
	{
		System.out.println("Cache Content:");
		for(Map.Entry<Integer, DoublyLinkedListNode> entry: getCache().entrySet())
		{
			System.out.println(entry.getKey()+"="+entry.getValue().getData());
		}

	}
	public HashMap<Integer, DoublyLinkedListNode> getCache() {
		return cache;
	}
	public void setCache(HashMap<Integer, DoublyLinkedListNode> cache) {
		this.cache = cache;
	}
	public DoublyLinkedListNode getHead() {
		return head;
	}
	public void setHead(DoublyLinkedListNode head) {
		this.head = head;
	}
	public DoublyLinkedListNode getEnd() {
		return end;
	}
	public void setEnd(DoublyLinkedListNode end) {
		this.end = end;
	}
	public int getSize() {
		return size;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	// driver method
	public static void main(String[] args) 
	{
		LRUcache myLRUcache=new LRUcache(2);
		myLRUcache.add(0, 0);
		myLRUcache.displayCache();
		System.out.println("Cache Size: "+myLRUcache.getSize());
		myLRUcache.add(1, 1);
		myLRUcache.add(2, 2);
		myLRUcache.displayCache();
		System.out.println("Cache Size: "+myLRUcache.getSize());
		myLRUcache.add(1, 11);
		myLRUcache.displayCache();
		System.out.println("Cache Size: "+myLRUcache.getSize());
		myLRUcache.add(3, 3);
		myLRUcache.add(3, 33);
		myLRUcache.displayCache();
		myLRUcache.add(4, 4);
		System.out.println("Cache Size: "+myLRUcache.getSize());
	}
}
// these nodes act as our pages with some data and page number in them 
class DoublyLinkedListNode
{
	int data; 
	int pageNum;
	private DoublyLinkedListNode prev;
	private DoublyLinkedListNode next;
	public DoublyLinkedListNode(int value,int pageNum) 
	{
		this.data=value;
		this.pageNum=pageNum;
		this.next=null;
		this.prev=null;
	}
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public DoublyLinkedListNode getPrev() {
		return prev;
	}
	public int getData() {
		return data;
	}
	public void setData(int value) {
		this.data = value;
	}
	public void setPrev(DoublyLinkedListNode prev) {
		this.prev = prev;
	}
	public DoublyLinkedListNode getNext() {
		return next;
	}
	public void setNext(DoublyLinkedListNode next) {
		this.next = next;
	}
}