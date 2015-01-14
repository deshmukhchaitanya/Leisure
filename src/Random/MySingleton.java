package Random;

public class MySingleton 
{
	private static volatile MySingleton singleInstance;
	private MySingleton() {
	
	}
	public static MySingleton getInstance()
	{
		if(singleInstance==null)
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
