/**
 * CaveController.java - Controls the gameplay of the game using the other classes.
 * 
 * @Hazel Lobos
 * @March 22, 2015 Version 1.0
 */

import java.util.*;
import java.io.*;

/*
 * Assumptions  : The user does not specify where the actual exit is, thus there is no 'Caver has escaped the cave' message.
 *  The user can only end the game manually by typing 'E' or the game ends when the caver dies.
 * Known Errors : 
 */
public class CaverController
{
     //Methods for the CaverController class (individual descriptions below).

    private boolean gameState = true;   //If the game is still running = T, End game loop = F
    boolean caverDeath = false;         //T=forward value will cause an out of bounds of the array, caver dies F=Caver lives
    private char finalMoveUser;         //User movement choice converted into char.
    private String expectedCharacters = "MRLE"; //Expected movement characters that user chooses as move options
    private Move move;                  //Move class holds movement options for caver
    private Cave cave = new Cave();     //Object to call print cave methods
    private Caver aeo;                  //The caver, named 'Aeo', object hold methods to grab information of the spelunker
    
    /**
     * 1. Shows the game story for the user as to the purpose of the game.
     * 2. User is then asked the the (x,y) on where the caver is located on the grid of the 'gps'
     * 3. Game loop, prints the title, cave array and the move options for the user. The user will
     *  make a play choice executed by moveSet().
     * 4. moveSet() checks for the state of the game, if the game ends via the user or the caver has died, 
     *  then the appropriate end message is displayed.
     */
    public void play() throws IOException
    {
        gameStory();  //The game story is explained to the user
        caverSetup(); //Asks the user the coordinates and direction of the caver
        
        //Execute game loop, see comments above #3
        while(gameState != false)
        {
            clearScreen();
            
            System.out.println("\t\t\t || ----- Lets Play: The Lost Caver ----- ||"); 

            cave.printCave(aeo.getCurrCX(), aeo.getCurrCY(), aeo.getCurrDirection(), false); //Show user where Aeo is in Cave
            
            //Execute move functions, if user chooses to endgame 'gameState' will result in false, ending the loop and the game.
            gameState = moveSet(); 
        } 
        
        if (caverDeath == true)
        {
            clearScreen();
            System.out.println("\t\t\t || ----- Lets Play: The Lost Caver ----- ||"); 
            cave.printCave(aeo.getCurrCX(), aeo.getCurrCY(), aeo.getCurrDirection(), true); //Show user where Aeo is in Cave
            caverGps();   //Display coordinates, location, current path of the caver and player move options
            System.out.println("\n\n || ----- Oh... It seems Aeo has met his demise. You tried valiantly. ----- ||"); 
        }
        else 
        {
            System.out.println("\n\t\t || ----- Thanks for playing. ----- ||");  
        }
    }
    
    /**
     * Displays the story and purpose of the game, asks the user to press ENTER to begin the game.
     */
    private void gameStory() throws IOException
    {
        System.out.println("\t\t\t || ----- The Story: The Lost Caver ----- ||"); 
        
        System.out.println("\nPoor Aeo the Caver has adventured too much and has gotten himself deep in an unexplored "  
        + "\nsection of a cave. His flashlight is all but burnt out and it is up to you, his dear, "
        + "\ndear friend to help him out yet again (lucky him!). "
        + "\n\nYou, at the surface of the cave are able to communicate with poor Aeo and guide him "
        + "\nvia a handy GPS. You look up his current coordinates and have devised a plan to get your "
        + "\nfriend out, now that you can see the exit...");
        
        System.out.print("\n\nPress ENTER on your keyboard to continue ---->");
        System.in.read();
        clearScreen();
    }
   
    /**
     * moveSet() will display the current information of the caver via 'caverGPS()'. Caver info: (x,y) coordinates, 
     *  facing direction, current path of caver movement so far and the player move options. 
     *  It will then ask the player to make a choice via the menu options:
     *      M - Forward
     *      R - Move 90 degrees to the RIGHT
     *      L - Move 90 degrees to the LEFT
     *      E - EXIT Game
     *  moveSet() loop will continually check for valid data and whether or not a forward movement kills the caver,
     *  boolean 'caverDeath'.
     *  
     *  @return     BOOLEAN gameEnd, F=Caver has died or the user has chosen to end the game. 
     */
    private boolean moveSet()
    {     
        boolean moveLoop = true; //T=Stop asking for user moves. F=End of user turm
        boolean gameEnd = true; //T=Keep going with game, user has played a valid move, F=Endgame
        move = new Move(aeo);
        caverGps();   //Display coordinates, location, current path of the caver and player move options
        
        //User chooses a moveset option:
        while (moveLoop!=false)
        {
            Scanner keyboard1 = new Scanner(System.in);
            System.out.print("\n\nHow should Aeo Move?: ");
            String moveUser = keyboard1.nextLine().trim();
            moveUser = moveUser.toUpperCase();
                
            if(moveUser.length() > 0) /*User has to enter at least one character.*/
            {
                finalMoveUser = moveUser.charAt(0); //Takes the first element in the inputed string.
                //check to see if choice is valid letter.
                if(expectedCharacters.indexOf(finalMoveUser) >= 0)
                {
                    switch(finalMoveUser)
                    {
                        case 'M': caverDeath = move.forward(aeo.getCurrCX(), aeo.getCurrCY(), aeo.getCurrDirection()); moveLoop = false;
                        break;
    
                        case 'R': move.right(aeo.getCurrDirection()); moveLoop = false;
                        break;
    
                        case 'L': move.left(aeo.getCurrDirection()); moveLoop = false;
                        break;
                        
                        case 'E': moveLoop = false; gameEnd = false;
                        break;
                    }
                }else{ System.out.print("\nTry again, invalid move. "); }
            } else {  /*Keep Going*/ }
        }
        
        if (caverDeath == true)
        {
            gameEnd = false;
        }
        
        return gameEnd;
    }
    
    /**
     * Displays a caver's information ((x,y) coordinates, current facing direction, current path of caver movement so far)
     * and player menu options:
     *      M - Forward
     *      R - Move 90 degrees to the RIGHT
     *      L - Move 90 degrees to the LEFT
     *      E - EXIT Game
     */
    private void caverGps()
    {
        System.out.println("\nPlay Options:");
        System.out.format("%1s%33s", "M - Move FORWARD", "Aeo's Path to Freedom:");
        System.out.format("%n%1s%40s", "R - Turn RIGHT ", aeo.getCurrPath());
        System.out.format("%n%1s%40s%6s", "L - Turn LEFT", "Aeo's Current Coordinates:", aeo.getCurrCoordinates());
        System.out.format("%n%1s%38s%2s", "E - EXIT Game", "Aeo's Current Direction:", aeo.getCurrDirection());
    }
    
    /**
     * This is where the user is asked for the caver's inital coordinates/direction to set the game.
     * It requests the X, the Y and what direction the caver is facing. North = 'N', etc.
     * It also does checks for valid data to be inputed.
     * Once all requested data is set, the caver, named 'Aeo' is created and its initial stats are updated.
     */
    public void caverSetup()
    {
        System.out.println("\t\t\t || ----- The GPS: The Lost Caver ----- ||"); 
        
        System.out.println("\nWhere in the cave is Aeo?");
        
        boolean xLoop = false; //T=Stop asking for user coordinates or direction. F=User needs to enter valid data
        boolean yLoop = false; //T=Stop asking for user coordinates or direction. F=User needs to enter valid data
        boolean dLoop = false; //T=Stop asking for user coordinates or direction. F=User needs to enter valid data
        Scanner keyboardSetup = new Scanner(System.in);
        
        while (xLoop!=true)
        {
            //Grab X coordinate of caver
            System.out.print("\nFirst, enter a number between 0-15 ----> ");
            String xCoor = keyboardSetup.nextLine().trim();
            
            if(xCoor.length() > 0) /*User has to enter at least one character.*/
            {
                try
                {
                    //If the single index String is a number, will turn it into its proper int.
                    //Else, will re-ask for proper input in exception.
                    int xCoorInt = Integer.parseInt(xCoor);
                    if(xCoorInt > -1 && xCoorInt < 16)
                    {
                        //Now, grab Y coordinate
                        while (yLoop!=true) 
                        {
                            System.out.print("\nNow, enter a number between 0-19 ----> ");
                            String yCoor = keyboardSetup.nextLine().trim();
                            
                            if(yCoor.length() > 0) /*User has to enter at least one character.*/
                            {
                                try
                                {
                                    //If the single index String is a number, will turn it into its proper int.
                                    //Else, will re-ask for proper input in exception.
                                    int yCoorInt = Integer.parseInt(yCoor);  
                                    if(yCoorInt > -1 && yCoorInt < 20)
                                    {
                                        //Now, grab caver direction coordinate
                                        while (dLoop!=true)
                                        {
                                            System.out.print("\nEnter a direction for Aeo to be facing, Example: N or E or S or W ----> ");
                                            String aeoDir = keyboardSetup.nextLine().trim();
                                            aeoDir = aeoDir.toUpperCase();
                                            String dirOptions = "NESW"; //Only options for valid directions
                                            if(aeoDir.length() > 0) /*User has to enter at least one character.*/
                                            {
                                                //check to see if choice is valid letter.
                                                if(dirOptions.indexOf(aeoDir) >= 0)
                                                {
                                                   //Load coordinates and direction into the Caver object
                                                   aeo = new Caver(xCoorInt, yCoorInt, aeoDir);
                                                   aeo.setCurrCX(xCoorInt);
                                                   aeo.setCurrCY(yCoorInt);
                                                   aeo.setCurrDirection(aeoDir);
                                                   
                                                   xLoop = true;
                                                   yLoop = true;
                                                   dLoop = true;
                                                   clearScreen();
                                                }else{ System.out.print("\nTry again, invalid direction. \n"); }
                                            } else {  /*Keep Going*/ }
                                        }
                                    } 
                                    else 
                                    {  
                                        System.out.print("\nTry again, invalid move. \n");
                                    }
                                }
                                catch (NumberFormatException e)
                                {
                                    System.out.print("\nTry again, invalid move. \n");
                                }
                            } else {  /*Keep Going*/ }
                        }
                    } 
                    else 
                    {  
                        System.out.print("\nTry again, invalid move. \n");
                    }
                }
                catch (NumberFormatException e)
                {
                    System.out.print("\nTry again, invalid move. \n");
                }
            } else {  /*Keep Going*/ }
        }
    }
    
    /**
     * Clears the console screen in a blueJ terminal.
     */
    private void clearScreen()
    {
        System.out.print('\u000C');
    }
}