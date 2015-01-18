package Random;
import java.util.LinkedList;
// A hash map could be considered as an array/list of buckets
// each bucket is a linked list of key-value pairs.
public class MyHashMap<K,T> 
{
	// static nested class of Pairs
	static class Pair<K,T>{
		K key;
		T value;
		Pair(K k,T v){
			key = k;
			value = v;
		}
	}
	int size=10;
	@SuppressWarnings("rawtypes")
	LinkedList buckets[]=new LinkedList[size];
	int hash(K key){
		return key.hashCode()%size;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	void put(K key,T value)
	{
		int index=hash(key);
		if(buckets[index]==null)
		{
			buckets[index]=new LinkedList();
		}
		for(Object p:buckets[index])
		{
			Pair<K,T> pair=(Pair<K,T>)p;
			if(pair.key.equals(key)){
				pair.value=value;
				return;
			}
		}
		buckets[index].add(new Pair(key,value));
	}
	@SuppressWarnings("unchecked")
	T get(K key)
	{
		int index=hash(key);
		if(buckets[index]==null) return null;
		for(Object p:buckets[index]){
			Pair<K,T> pair=(Pair<K,T>)p;
			if(pair.key.equals(key))
				return pair.value;
		}
		return null;
	}
	public static void main(String[] args) {
		MyHashMap<String, String> map = new MyHashMap<String, String>();
		map.put("CA", "California");
		map.put("TX", "Texas");
		map.put("TX", "Texas1");
		map.put("TX", "Texas2");
		map.put("GA", "Georgia");
		System.out.println(map.get("CA"));
		System.out.println(map.get("TX"));
		System.out.println(map.get("GA"));
	}
}