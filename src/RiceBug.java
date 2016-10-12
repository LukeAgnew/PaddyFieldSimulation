
public class RiceBug {
	
	int bugRow;
	int bugColumn;
	int startingTime;
	String directionalMovement;
	int strength;
	
	int rowMovement;
	int columnMovement;
	
	public RiceBug(int bugRow, int bugColumn, int startingTime, String directionalMovement) 
	{
		this.bugRow = bugRow;
		this.bugColumn = bugColumn;
		this.startingTime = startingTime;
		this.directionalMovement = directionalMovement;
		
		this.strength = 0;
		
		if (directionalMovement.equals("A"))
		{
			rowMovement = -1;
			columnMovement = -1;
		}
		else if (directionalMovement.equals("B"))
		{
			rowMovement = -1;
			columnMovement = 0;
		}
		else if (directionalMovement.equals("C"))
		{
			rowMovement = -1;
			columnMovement = 1;
		}
		else if (directionalMovement.equals("D"))
		{
			rowMovement = 0;
			columnMovement = -1;
		}
		else if (directionalMovement.equals("E"))
		{
			rowMovement = 0;
			columnMovement = 1;
			
		}
		else if (directionalMovement.equals("F"))
		{
			rowMovement = 1;
			columnMovement = -1;
		}
		else if (directionalMovement.equals("G"))
		{
			rowMovement = 1;
			columnMovement = 0;
		}
		else
		{
			rowMovement = 1;
			columnMovement = 1;	
		}	
	}
	
	// Move the bug according to its movement pattern
	public void move()
	{
		bugRow += rowMovement;
		bugColumn += columnMovement;
	}

}
