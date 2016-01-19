import java.lang.Object;
import java.lang.Thread;
import java.net.Socket;
import java.util.*;
import java.io.*;

public class ConnectTest {
	public static void main(String args[])
	{

		//CHANGE hostName TO THE CORRECT IP ADDRESS

		String hostName = "192.168.0.208";
		int portNumber = 5000;
		System.out.println("Started at IP: " + hostName);
		Socket echoSocket = null;
		Exits exitOne = new Exits(1);
		Exits exitTwo = new Exits(2);
		Exits exitThree = new Exits(3);
		Exits exitFour = new Exits(4);
		
		PrintWriter writer = null;
		
		//Starts the printWriter to log the output

		try { writer = new PrintWriter("output.txt", "UTF-8"); }
		catch (Exception e) { System.out.println("Could not create file!"); }
		
		writer.println("-- OUTPUT LOG --");
					
		try 
		{
			echoSocket = new Socket(hostName, portNumber);
			
			ConnectToServer myConnect = new ConnectToServer(echoSocket);
			GetResponse myResponse = new GetResponse(echoSocket);
			MainThread myMainThread = new MainThread(exitOne, exitTwo, exitThree, exitFour);
			Singleton.getInstance();
			Thread t1 = new Thread(myConnect);
			Thread t2 = new Thread(myResponse);
			Thread mainThread = new Thread(myMainThread);

			//Starts the threads which poll for a response from the server and connects to the server

			t2.start();
			t1.start();

			//
			
			mainThread.start();

			System.out.println("Begin traffic code for 2 minutes");
		
			Exits cExit = null;
			int pedCycleCounter = 0;
			int cycleCounter = 0;
			
			//This section handles the traffic sorting code
			while (true)
			{	
				cycleCounter++;
				if (cycleCounter >= 12) break;
				Thread.sleep(10000);
				if (cExit != null) cExit.removeCars(100);
				else
				{
					exitOne.removePeds(100);
					exitTwo.removePeds(100);
					exitThree.removePeds(100);
					exitFour.removePeds(100);
				}
				System.out.println("-- Car Counts --");
				writer.println("-- Car Counts --");
				System.out.println("Lane 1: "+exitOne.size()+ " Lane 2: "+exitTwo.size()+ " Lane 3: "+exitThree.size()+ " Lane 4: "+exitFour.size());
				writer.println("Lane 1: "+exitOne.size()+ " Lane 2: "+exitTwo.size()+ " Lane 3: "+exitThree.size()+ " Lane 4: "+exitFour.size());
				System.out.println("-- Pedestrians Count -- ");
				writer.println("-- Pedestrians Count -- ");
				System.out.println("Lane 1: "+exitOne.getPeds()+ " Lane 2: "+exitTwo.getPeds()+ " Lane 3: "+exitThree.getPeds()+ " Lane 4: "+exitFour.getPeds());
				writer.println("Lane 1: "+exitOne.getPeds()+ " Lane 2: "+exitTwo.getPeds()+ " Lane 3: "+exitThree.getPeds()+ " Lane 4: "+exitFour.getPeds());
				if (pedCycleCounter < 5)
				 {
					 if (cExit != null) {
						 System.out.println("Lane "+cExit.getExit() + " has turned red");	
						 writer.println("Lane "+cExit.getExit() + " has turned red");	
					 }
					 cExit = null;
					 if (exitOne.size() > exitTwo.size() && exitOne.size() > exitThree.size() && exitOne.size() > exitFour.size()) cExit = exitOne;
					 else if (exitTwo.size() > exitOne.size() && exitTwo.size() > exitThree.size() && exitTwo.size() > exitFour.size()) cExit = exitTwo;
					 else if (exitThree.size() > exitOne.size() && exitThree.size() > exitTwo.size() && exitThree.size() > exitFour.size()) cExit = exitThree;
					 else cExit = exitFour;		

					 System.out.println("Lane " + cExit.getExit() + " has turned to green!");
					 writer.println("Lane " + cExit.getExit() + " has turned to green!");
					 pedCycleCounter ++;
				 }
				 else // Pedestrians 
				 { 		
					System.out.println("All lights have turned red for pedestrians");
					writer.println("All lights have turned red for pedestrians");
					pedCycleCounter = 0;
					cExit = null;
				 }			
			}

			//Closes all threads and the writer.

			writer.close();
			t1.stop();
			t2.stop();
			mainThread.stop();
			System.out.println("Program has ended");
			
		}
		catch (Exception e){
			System.out.print("error, did not connect");
			}
		
		
	}
	
	private static void Output(String message, PrintWriter writer)
	{
		System.out.println(message);
		writer.println(message);
	}
}