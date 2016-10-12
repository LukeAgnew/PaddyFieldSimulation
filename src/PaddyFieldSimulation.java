/* 
 * Student Name: Luke Agnew
 * Student Number: 14310719 
*/

import java.util.Scanner;
import java.util.ArrayList;

public class PaddyFieldSimulation {
	
	public static void main(String[] args) {
		System.out.println("Input: ");
		
		Scanner input = new Scanner(System.in);
		
		// Get the paddy field dimensions from the user
		String[] fieldSizeValues = input.nextLine().split(" ");
		
		int width = Integer.parseInt(fieldSizeValues[0]);
		int length = Integer.parseInt(fieldSizeValues[1]);	
		
		// Create paddy field according to user's dimensions
		Crop[][] paddyField = new Crop[width][length];
		
		int noOfUninfectedCrops = width * length;	
		ArrayList<Crop> uninfectedCrops = new ArrayList<Crop>();
		
		// Instantiate each crop in the paddy field.
		for (int fieldRow = 0; fieldRow < paddyField.length; fieldRow++)
		{
			for (int fieldColumn = 0; fieldColumn < paddyField[fieldRow].length; fieldColumn++)
			{
				Crop crop = new Crop();
				paddyField[fieldRow][fieldColumn] = crop;
				uninfectedCrops.add(crop);
			}
				
		}
		
		// Get the simulation period from the user
		int simulationPeriod = input.nextInt();
		
		// Get the number of bugs from the user
		int noOfRiceBugs = input.nextInt();
		
		ArrayList<RiceBug> riceBugs = new ArrayList<RiceBug>();
		
		// Instantiate each bug added by user.
		for (int bugIndex = 0; bugIndex < noOfRiceBugs; bugIndex++)
		{
			int bugRow = input.nextInt();									// Starting row of bug
			int bugColumn = input.nextInt();								// Starting column of bug
			
			int startingTime = input.nextInt();								// Starting time of bug
			String directionalMovement = input.next();						// Directional movement of bug
			
			// Instantiate the bug according to these inputs
			RiceBug bug = new RiceBug(bugRow, bugColumn, startingTime, directionalMovement);
			riceBugs.add(bug);
			
		}
		
		// Simulates the behaviour of the bugs in the field for the period 
		// specified by the user.	
		for (int index = 0; index < simulationPeriod; index++)
		{
			// For each bug still alive, move it according to its movement
			// pattern.
			for (int bugIndex = 0; bugIndex < noOfRiceBugs; bugIndex++)
			{
				RiceBug bug = riceBugs.get(bugIndex);
							
				if (index > bug.startingTime)
				{
					Crop oldCrop = paddyField[bug.bugRow][bug.bugColumn];
								
					oldCrop.bugs.remove(bug);
								
					bug.move();
								
					if (bug.bugRow >= 0 && bug.bugRow < paddyField.length && bug.bugColumn >= 0 && bug.bugColumn < paddyField[0].length)
					{
									
						Crop newCrop = paddyField[bug.bugRow][bug.bugColumn];
						newCrop.bugs.add(bug);
									
					}
					else
					{
						riceBugs.remove(bug); // Bug crosses electric fence and is killed
						noOfRiceBugs--;
					}
					
				}
				else if (index == bug.startingTime)
				{
					Crop startingCrop = paddyField[bug.bugRow][bug.bugColumn];

					startingCrop.bugs.add(bug);
				}
			}						
			
			
			// For each crop in the field with one or more bugs, retain the
			// strongest bug(s), eliminate the others and increase strength
			// of strongest bugs and infect the crop if previously uninfected.
			for (int fieldRow = 0; fieldRow < paddyField.length; fieldRow++)
			{
				for (int fieldColumn = 0; fieldColumn < paddyField[fieldRow].length; fieldColumn++)
				{
					Crop crop = paddyField[fieldRow][fieldColumn];
					
					if (crop.bugs.size() > 0)
					{
						int maxStrength = -1;
						ArrayList<RiceBug> strongestBugs = new ArrayList<RiceBug>();
						
						for (int bugCount = 0; bugCount < crop.bugs.size(); bugCount++)
						{
							RiceBug cropBug = crop.bugs.get(bugCount);	
							
							if (cropBug.strength > maxStrength)
							{
								strongestBugs.clear();
								strongestBugs.add(cropBug);
								
								maxStrength = cropBug.strength;
							}
							else if (cropBug.strength == maxStrength)
							{
								strongestBugs.add(cropBug);
							}
							else
							{
								riceBugs.remove(cropBug);
								noOfRiceBugs--;
							}	
						}
						
						crop.bugs.clear();
						
						if (!crop.infected)
						{
							for (int strongIndex = 0; strongIndex < strongestBugs.size(); strongIndex++)
							{
								RiceBug strongBug = strongestBugs.get(strongIndex);
								strongBug.strength++;
								crop.bugs.add(strongBug);
							}
							
							crop.infected = true;
							uninfectedCrops.remove(crop);	
							noOfUninfectedCrops--;
						}
						else
						{
							crop.bugs = strongestBugs;		
						}
								
					}
				}		
			}	
			
		}
		
		// Output number of uninfected crops and remaining rice bugs to the user
		System.out.println("Output:\n" + noOfUninfectedCrops + " " + noOfRiceBugs);
		
	}
}
