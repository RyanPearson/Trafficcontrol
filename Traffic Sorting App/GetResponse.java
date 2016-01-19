import java.net.*;
import java.io.*;
import java.util.*;

public class GetResponse implements Runnable{

	//Thread polls the server repeatedly requesting a response

	Socket echoSocket; 
	GetResponse(Socket inSocket)
	{
		echoSocket = inSocket;
	}
	
	@Override
	public void run() {

		try (
		    BufferedReader in =
		        new BufferedReader(
		            new InputStreamReader(echoSocket.getInputStream()));
		    
	){
			
        while (true) {
			String result = in.readLine();
            System.out.println("echo: " +result);
			if (result.contains(";")) { //Splits the input string at each ; so that traffic data can be stored
				String[] splitter = result.split(";"); 
				for (int i = 0; i < splitter.length; i++) {
					Singleton.add(splitter[i]);	
				}
							
			}
        }
		}
		catch (Exception e){
		System.out.print("error, did not connect");
		}
	}
}
