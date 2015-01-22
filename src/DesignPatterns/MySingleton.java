package DesignPatterns;
// A typical double checked locking singleton class
public class MySingleton 
{
	private static volatile MySingleton singleInstance;
	private MySingleton() {
	
	}
	public static MySingleton getInstance()
	{
		if(singleInstance==null) // this avoids the overhead of synchronization once the single has been created 
		{
			synchronized(singleInstance)
			{
				if(singleInstance==null)
					singleInstance=new MySingleton();
			}
		}
		return singleInstance;
	}
	
}
