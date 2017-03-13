/**
 * Cave.java - Creates the cave grid array, automatically set to 20 wide (cols) 16 high (rows). 
 *  Each time cavePrint() is called, it will reprint the array with the coordinates and direction 
 *  of the caver, also passing a boolean on whether the caver is dead or not. 
 *  Death is caused by a forward movement that is outside the array walls of the grid.
 * 
 * @Hazel Lobos
 * @March 22, 2015 Version 1.0
 */

import java.util.*;

/*
 * Assumptions  : ---
 * Known Errors : ---
 */
public class Cave
{
     //Methods for the Cave class (individual descriptions below).
    
    private String cave[][] = new String[16][20];   //Cave grid 20 wide (cols) 16 high (rows)
    
    /**
     * printCave will reprint the 'cave' array with the (x,y) coordinates and direction of the caver, 
     *  also passing a boolean on whether the caver is dead or not.
     * 
     * @param      INT x, current x coordinate of caver
     * @param      INT y, current y coordinate of caver
     * @param      BOOLEAN caverDeath, current state of the caver T=Dead F=Alive
     */
    public void printCave(int x, int y, String dir, boolean caverDeath)
    {
        System.out.print("Welcome to: ESKAH CAVE\n\n");
        
        //Note: The cave is filled out so that the origin (0,0) is at the bottom left when printed. 
        //Will fill the grid with "_" strings, starting at row 15, col 0. Unless the caver is occupying that cave[row][col] 
        for(int row = 15; row > -1; row--)
        {
            for(int col = 0; col < 20; col++)
            {  
                if (row == x && col == y)
                {
                    //Check if caver died or update direction of caver
                    if(caverDeath == true)
                    {
                        cave[row][col] = "X";
                    }
                    else
                    {
                        if (dir.equals("N"))
                        {
                            cave[row][col] = "^";
                        }
                        else if (dir.equals("E"))
                        {
                            cave[row][col] = ">";
                        }
                        else if (dir.equals("S"))
                        {
                            cave[row][col] = "V";
                        }
                        else if (dir.equals("W"))
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
            printRow(cave[row]);
        }
    }
    
    /**
     * printRow - For every row passed in from the cave grid array, print the row and a space.
     * 
     * @param      STRING[] row, the string of a row in the grid.
     */
    public void printRow(String[] row) 
    {
        for (String i : row) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
    }
}
