import java.util.*;
import java.io.*;

/*
 * Assumptions  : The user does not specify where the actual exit is, thus there is no 'Caver has escaped the cave' message.
 *                The user can only end the game manually by typing 'E' or the game ends when the caver dies.
 */
public class CaverController
{
    private boolean gameState = true;   
    private boolean hasCaverDied = false;         
    
    private char userMovementCharacter;
    private String expectedUserMovementCharacters = "MRLE";
    
    private Move move;                  
    private Cave cave = new Cave();     
    private Caver aeo;                  
    
    public void play() throws IOException
    {
        launchGameStoryText();
        userSetupOfCaversInitialSpawnInCave();
        
        while(gameState != false)
        {
            clearScreen();
            
            System.out.println("\t\t\t || ----- Lets Play: The Lost Caver ----- ||"); 

            cave.printCave(aeo.getCurrentCaverXCoordinate(), aeo.getCurrentCaverYCoordinate(), aeo.getCurrentCaverDirection(), false);
            
            gameState = displayUserControlsAndCheckGameState(); 
        } 
        
        if (hasCaverDied == true)
        {
            clearScreen();
            System.out.println("\t\t\t || ----- Lets Play: The Lost Caver ----- ||"); 
            cave.printCave(aeo.getCurrentCaverXCoordinate(), aeo.getCurrentCaverYCoordinate(), aeo.getCurrentCaverDirection(), true);
            printUserOptionsMenuAndCaverCurrentLocation();
            System.out.println("\n\n || ----- Oh... It seems Aeo has met his demise. You tried valiantly. ----- ||"); 
        }
        else 
        {
            System.out.println("\n\t\t || ----- Thanks for playing. ----- ||");  
        }
    }
    
    private void launchGameStoryText() throws IOException
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
   
    private boolean displayUserControlsAndCheckGameState()
    {     
        boolean canCaverMove = true;
        boolean canGameContinue = true;
        move = new Move(aeo);
        
        printUserOptionsMenuAndCaverCurrentLocation();
        
        while (canCaverMove!=false)
        {
            Scanner userKeyboardInputScanner = new Scanner(System.in);
            System.out.print("\n\nHow should Aeo Move?: ");
            String userMovementString = userKeyboardInputScanner.nextLine().trim();
            userMovementString = userMovementString.toUpperCase();
                
            if(userMovementString.length() > 0)
            {
                userMovementCharacter = userMovementString.charAt(0);
                
                if(expectedUserMovementCharacters.indexOf(userMovementCharacter) >= 0)
                {
                    switch(userMovementCharacter)
                    {
                        case 'M': hasCaverDied = move.forward(aeo.getCurrentCaverXCoordinate(), aeo.getCurrentCaverYCoordinate(), aeo.getCurrentCaverDirection());
                        break;
    
                        case 'R': move.right(aeo.getCurrentCaverDirection());
                        break;
    
                        case 'L': move.left(aeo.getCurrentCaverDirection());
                        break;
                        
                        case 'E': canGameContinue = false;
                        break;
                    }
                    canCaverMove = false;
                }else{ System.out.print("\nTry again, invalid move. "); }
            } else {  /*Keep Going*/ }
        }
        
        if (hasCaverDied == true)
        {
            canGameContinue = false;
        }
        
        return canGameContinue;
    }
    
    private void printUserOptionsMenuAndCaverCurrentLocation()
    {
        System.out.println("\nPlay Options:");
        System.out.format("%1s%33s", "M - Move FORWARD", "Aeo's Path to Freedom:");
        System.out.format("%n%1s%40s", "R - Turn RIGHT ", aeo.getCurrentPath());
        System.out.format("%n%1s%40s%6s", "L - Turn LEFT", "Aeo's Current Coordinates:", aeo.getCurrentCaverXYCoordinates());
        System.out.format("%n%1s%38s%2s", "E - EXIT Game", "Aeo's Current Direction:", aeo.getCurrentCaverDirection());
    }
    
    public void userSetupOfCaversInitialSpawnInCave()
    {
        System.out.println("\t\t\t || ----- The GPS: The Lost Caver ----- ||"); 
        
        System.out.println("\nWhere in the cave is Aeo?");
        
        boolean hasXCoordinateSetupFinished = false;
        boolean hasYCoordinateSetupFinished = false;
        boolean hasDirectionCoordinateSetupFinished = false;
        Scanner userKeyboardSetupCaverScanner = new Scanner(System.in);
        
        while (hasXCoordinateSetupFinished!=true)
        {
            System.out.print("\nFirst, enter a number between 0-15 ----> ");
            String caverXCoordinate = userKeyboardSetupCaverScanner.nextLine().trim();
            
            if(caverXCoordinate.length() > 0)
            {
                try
                {
                    int caverXCoordinateInt = Integer.parseInt(caverXCoordinate);
                    if(caverXCoordinateInt > -1 && caverXCoordinateInt < 16)
                    {
                        
                        while (hasYCoordinateSetupFinished!=true) 
                        {
                            System.out.print("\nNow, enter a number between 0-19 ----> ");
                            String caverYCoordinate = userKeyboardSetupCaverScanner.nextLine().trim();
                            
                            if(caverYCoordinate.length() > 0)
                            {
                                try
                                {
                                    int caverYCoordinateInt = Integer.parseInt(caverYCoordinate);  
                                    if(caverYCoordinateInt > -1 && caverYCoordinateInt < 20)
                                    {
                                        
                                        while (hasDirectionCoordinateSetupFinished!=true)
                                        {
                                            System.out.print("\nEnter a direction for Aeo to be facing, Example: N or E or S or W ----> ");
                                            String caverDirection = userKeyboardSetupCaverScanner.nextLine().trim();
                                            caverDirection = caverDirection.toUpperCase();
                                            
                                            String validDirectionOptions = "NESW";
                                            if(caverDirection.length() > 0)
                                            {
                                                if(validDirectionOptions.indexOf(caverDirection) >= 0)
                                                {
                                                   //Load coordinates and direction into the Caver object
                                                   aeo = new Caver(caverXCoordinateInt, caverYCoordinateInt, caverDirection);
                                                   aeo.setCurrentCaverXCoordinate(caverXCoordinateInt);
                                                   aeo.setCurrentCaverYCoordinate(caverYCoordinateInt);
                                                   aeo.setCurrentCaverDirection(caverDirection);
                                                   
                                                   hasXCoordinateSetupFinished = true;
                                                   hasYCoordinateSetupFinished = true;
                                                   hasDirectionCoordinateSetupFinished = true;
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
    
    private void clearScreen()
    {
        System.out.print('\u000C');
    }
}