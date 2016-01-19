import java.net.*;
import java.io.*;
import java.util.*;
public class Exits{

	ArrayList laneEnter;
	ArrayList laneExit;
	int pedCount;
	int laneCount;
	int exit;

	//Class to store all info for an individual exit

	Exits(int in)
	{
		exit = in;
		laneEnter = new ArrayList();
		laneExit = new ArrayList();
		pedCount = 0;
		laneCount = 0;
	}
	
	public int getExit()
	{
		return exit;
	}
	
	public void addPed()
	{
		pedCount++;
	}
	
	public int getPeds()
	{
		return pedCount;
	}
	
	public void addCar(int to)
	{
		laneEnter.add(to);
		laneCount++;
	}
	
	public void removePeds(int count)
	{
		pedCount -= count;
		if (pedCount < 0) pedCount = 0;
	}
	
	public void removeCars(int count)
	{ // Count is how many cars to remove
		for (int i = 0; i < count; i++)
		{
			if (laneCount > 0)
			{
				laneCount--;
				laneEnter.remove(0);
			}
		}
	}
	
	public int GetCarCount()
	{
		return laneCount;
	}
	
	public int size()
    {
	   return laneEnter.size();
    }
	
	public void PrintContents()
	{
		System.out.println("-----BEGIN LIST-------");
		for (int i = 0; i < laneCount; i++)
		{			
			System.out.println("From: "+exit + " - " + laneEnter.get(i));			
		}
		System.out.println("-----END LIST--------");
	}
}
