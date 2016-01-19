import java.net.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class MainThread implements Runnable
{
	//Thread to add traffic and pedestrian data to the correct exit.
	
	Exits exitOne;
	Exits exitTwo;
	Exits exitThree;
	Exits exitFour;
	MainThread(Exits one, Exits two, Exits three, Exits four)
	{ 
		exitOne = one;
		exitTwo = two;
		exitThree = three;
		exitFour = four;
	}

	@Override
	public void run() {
		
		try {
       
			while (true) {
			
				Boolean check = Singleton.started();
				if (check == true)
					{
						if (Singleton.size() > 0)
						{
							
							String result = Singleton.get(0).toString();
							if (result.contains(":")) { // Car
								String[] fromTo = result.split(":");
								
								switch (Integer.parseInt(fromTo[0]))
								{
									case 1: { exitOne.addCar(Integer.parseInt(fromTo[1])); break; }
									case 2:  { exitTwo.addCar(Integer.parseInt(fromTo[1])); break; }
									case 3: { exitThree.addCar(Integer.parseInt(fromTo[1])); break; }
									case 4: { exitFour.addCar(Integer.parseInt(fromTo[1])); break; }
								}
								
							}
							else { // Pedestrian
								
								switch (Integer.parseInt(result))
								{
									case 1: { exitOne.addPed(); }
									case 2:  { exitTwo.addPed(); }
									case 3: { exitThree.addPed(); }
									case 4: { exitFour.addPed(); }
								}
							}
							
							
							Singleton.remove(0);
						}						
					}
				else{
					
				}
			}
		}
		
		catch (Exception e){
		System.out.print("error, did not connect");
		System.out.println(e.toString());
			
		}
	}
}