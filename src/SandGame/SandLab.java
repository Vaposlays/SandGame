package SandGame;
import java.awt.*;
import java.util.*;

public class SandLab
{
  //Step 4,6
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  public static final int GAS = 4;
  
  //do not add any more fields below
  private int[][] grid;
  private SandDisplay display;
  
  
  /**
   * Constructor for SandLab
   * @param numRows The number of rows to start with
   * @param numCols The number of columns to start with;
   */
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    // Change this value to add more buttons
    //Step 4,6
    names = new String[5];
    // Each value needs a name for the button
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[SAND] = "Sand";
    names[WATER] = "Water";
    names[GAS] = "Gas";
    
    //1. Add code to initialize the data member grid with same dimensions
    grid = new int [numRows][numCols];
    
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
    //2. Assign the values associated with the parameters to the grid
	  grid[row][col] = tool; 
	  
  }

  //copies each element of grid into the display
  public void updateDisplay()
  {
      //Step 3
   //Hint - use a nested for loop
    for(int row = 0; row < grid.length; row++)
    {
    		for(int col= 0; col< grid[0].length; col++)
    		{
    			Color black = Color.BLACK;
    			Color gray = Color.GRAY;
    			if(grid[row][col] == EMPTY)
    			{
    				display.setColor(row, col, black);
    			}
    			else if(grid[row][col] == METAL)
    			{
    				display.setColor(row, col, gray);
    			}
    			else if(grid[row][col] == SAND)
    			{
    				display.setColor(row, col, Color.YELLOW);
    			}
    			else if(grid[row][col] == WATER)
    			{
    				display.setColor(row, col, Color.BLUE);
    			}
    			else if(grid[row][col] == GAS)
    			{
    				display.setColor(row, col, Color.WHITE);
    			}
    		}
    }
  }

  //Step 5,7
  //called repeatedly.
  //causes one random particle in grid to maybe do something.
  public void step()
  {
    //Remember, you need to access both row and column to specify a spot in the array
    //The scalar refers to how big the value could be
    //int someRandom = (int) (Math.random() * scalar)
    //remember that you need to watch for the edges of the array
    int randomLocationX = (int)(Math.random() * (grid.length -1));
    int randomLocationY = (int)(Math.random() * grid[0].length);
    
    
    if(grid[randomLocationX][randomLocationY] == SAND && grid[randomLocationX + 1][randomLocationY] == EMPTY)
    {
    		grid[randomLocationX][randomLocationY] = EMPTY;
    		 grid[randomLocationX + 1][randomLocationY] = SAND;
    }
    else if(grid[randomLocationX][randomLocationY] == WATER)
	
    {
    	 if(grid[randomLocationX + 1][randomLocationY] == EMPTY)
    	 {
    		 grid[randomLocationX][randomLocationY] = EMPTY;
    		 grid[randomLocationX + 1][randomLocationY] = WATER;
    	 }
    	 else if(grid[randomLocationX ][randomLocationY + 1] == EMPTY)
    	 {
    		 grid[randomLocationX][randomLocationY] = EMPTY;
    		 grid[randomLocationX ][randomLocationY + 1] = WATER;
    	 }
    	 else if(grid[randomLocationX ][randomLocationY - 1] == EMPTY)
    	 {
    		 grid[randomLocationX][randomLocationY] = EMPTY;
    		 grid[randomLocationX ][randomLocationY - 1] = WATER;
    	 }
    }
    else if(grid[randomLocationX][randomLocationY] == SAND && grid[randomLocationX + 1][randomLocationY] == WATER)
    {
    		grid[randomLocationX][randomLocationY] = WATER;
		 grid[randomLocationX + 1][randomLocationY] = SAND;
    }
   
    else if (grid[randomLocationX][randomLocationY] == GAS)
    {
    	if(grid[randomLocationX + 1][randomLocationY] == EMPTY)
   	 {
   		 grid[randomLocationX][randomLocationY] = EMPTY;
   		 grid[randomLocationX - 1][randomLocationY] = GAS;
   	 }
   	 else if(grid[randomLocationX ][randomLocationY + 1] == EMPTY)
   	 {
   		 grid[randomLocationX][randomLocationY] = EMPTY;
   		 grid[randomLocationX ][randomLocationY + 1] = GAS;
   	 }
   	 else if(grid[randomLocationX ][randomLocationY - 1] == EMPTY)
   	 {
   		 grid[randomLocationX][randomLocationY] = EMPTY;
   		 grid[randomLocationX ][randomLocationY - 1] = GAS;
   	 }
    }
  } 
  
  //do not modify this method!
  public void run()
  {
    while (true) // infinite loop
    {
      for (int i = 0; i < display.getSpeed(); i++)
      {
        step();
      }
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
      {
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
      }
    }
  }
}

