import java.net.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

// This is code (Java)
public class ConnectToServer implements Runnable
{
	Socket echoSocket;
	ConnectToServer(Socket inSocket)
	{ 
		echoSocket = inSocket;
		//Passed in socket for server connection
	}

	@Override
	public void run() {
		
			//Starts the buffer reader to connect to server
		try (
		    PrintWriter out =
		        new PrintWriter(echoSocket.getOutputStream(), true);
		    BufferedReader stdIn =
		        new BufferedReader(
		            new InputStreamReader(System.in));
				
		){
        String userInput = "";
		out.println("BEGI");
        while ((userInput = stdIn.readLine()) != null) { 
			out.println(userInput); 
			// Debug statement for list
			if (userInput.equals("LIST"))
			{
				System.out.println("--------BEGIN LIST---------");
				Iterator it = Singleton.it();
				int count = 0; 
				while ( it.hasNext( ) ) { 
					count++; 
					System.out.println(count + ": "+it.next());
				}
			} 
        }   
		}
		
		catch (Exception e){
		System.out.print("error, did not connect");
			
		}
	}
}