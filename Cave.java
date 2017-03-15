import java.util.*;

public class Cave
{
    private String cave[][] = new String[16][20];   //Cave grid: 16 high (rows), 20 wide (cols) 
    
    public void printCave(int x, int y, String direction, boolean hasCaverDied)
    {
        System.out.print("Welcome to: ESKAH CAVE\n\n");
        
        // Cave origin (0,0) is at the bottom left when printed. 
        // Will fill the grid with "_" strings, starting at row 15, col 0. Unless the caver is occupying that cave[row][col]
        for(int row = 15; row > -1; row--)
        {
            for(int col = 0; col < 20; col++)
            {  
                if (row == x && col == y)
                {
                    if(hasCaverDied == true)
                    {
                        cave[row][col] = "X";
                    }
                    else
                    {
                        if (direction.equals("N"))
                        {
                            cave[row][col] = "^";
                        }
                        else if (direction.equals("E"))
                        {
                            cave[row][col] = ">";
                        }
                        else if (direction.equals("S"))
                        {
                            cave[row][col] = "V";
                        }
                        else if (direction.equals("W"))
                        {
                            cave[row][col] = "<";
                        }
                    }
                }
                else
                {
                    cave[row][col] = "_";
                }
            }
            printSingleCaveRow(cave[row]);
        }
    }
    
    public void printSingleCaveRow(String[] caveRow) 
    {
        for (String cell : caveRow) {
            System.out.print(cell);
            System.out.print(" ");
        }
        System.out.println();
    }
}
