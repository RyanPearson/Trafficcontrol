import java.util.*;

public class Singleton{

	//This class stores and manages the incomming data.

	static ArrayList<String> trafficHistory;
	int laneCount;
	private static Singleton instance = null;
	protected Singleton()
	{
		trafficHistory = new ArrayList<String>();
	}
	
	public static Boolean started()
	{
		int s = size();
		if (s > 0)
			return true;
		else
			return false;
	}
		
	public static Singleton getInstance() {
      if(instance == null) {
         instance = new Singleton();
      }
      return instance;
   }
   
   public static String get(int id)
   {
	   return trafficHistory.get(id);
   }
   
   public static int size()
   {
	   return trafficHistory.size();
   }
   
   public static void add(String input)
   {
	   //System.out.println("Added to list: "+input);
	   trafficHistory.add(input);
   }
   
   public static void remove(int id)
   {
	   trafficHistory.remove(trafficHistory.get(0));
   }
   
   public static Iterator it()
   {
	   return trafficHistory.iterator();
   }
}