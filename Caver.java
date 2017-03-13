/**
 * Caver.java - This class is simply keeping track of the current coordinates (x,y), 
 *  current facing direction and instruction the user has given to the caver (the path) 
 *  every time the caver has moved.
 * 
 * @Hazel Lobos
 * @March 22, 2015 Version 1.0
 */

/*
 * Assumptions  : ---
 * Known Errors : ---
 */
public class Caver
{
     //Methods for the Caver class (individual descriptions below).
     
    private String currPath = "";       //Current instructions the user has given to the caver, ex '(0,0) N RRLMMLMM'
    private int currCX;                 //Current x coordinate of caver
    private int currCY;                 //Current y coordinate of caver
    private String currDirection = "";  //Current facing direction of caver, ex 'N'=north or 'E'=east or 'S'=south or 'W'=west, shorthanded in comments as N/E/S/W
    private String currCoordinates = "";    //The current x and y coordinates of the caver, ex (0,1)
    
    private int initialCX;              //Initial x coordinate of caver specified by user at first move of the game
    private int initialCY;              //Initial y coordinate of caver specified by user at first move of the game
    private String initialD = "";       //Initial facing direction of caver specified by user at first move of the game
    
    /**
     * Constructor - Takes in the initial coordinates and facing position gathered from the user at the start of the game.
     * 
     * @param      INT initialCX, Initial x coordinate of caver
     * @param      INT initialCY, Initial y coordinate of caver
     * @param      STRING initial, Initial facing direction of caver
     */
    public Caver(int initialCX, int initialCY, String initialD)
    {
        this.initialCX = initialCX;
        this.initialCY = initialCY;
        this.initialD = initialD;
        initialPath();
        initialCoordinates();
    }
    
    /**
     * Sets up the string form of the initial path, ex '(0,1) N ' and updates the current path string.
     */
    private void initialPath()
    {
        String initialPath;
        initialPath = "(" +initialCX+ "," +initialCY+ ") " + initialD + " ";
        setCurrPath(initialPath);
    }
    
    /**
     * Sets up the initial coordinates of the caver to the current coordinates.
     */
    private void initialCoordinates()
    {
        currCX = initialCX;
        currCY = initialCY;
        setCurrCoordinates();
    }
    
    /**
     * Setter - Updates the string form of the current coordinates, ex '(1,0)'
     */
    public void setCurrCoordinates()
    {
        currCoordinates = "(" +currCX+ "," +currCY+ ")";
    }
    
    /**
     * Getter - returns the current coordinate string, ex '(1,0)'
     * 
     * @return     STRING currCoordinates, see above.
     */
    public String getCurrCoordinates()
    {
        return currCoordinates;
    }
    
    /**
     * Setter - Updates the caver's current path string, adding a new movement specific by the user, ex 'M'
     * 
     * @param     STRING newMove, new user move 'M' or 'R' or 'L'
     */
    public void setCurrPath(String newMove)
    {
        this.currPath = this.currPath + newMove;
    }  
    
    /**
     * Getter - returns the current path string which is the movement commands the caver has moved.
     * 
     * @return     STRING currPath, see above.
     */
    public String getCurrPath()
    {
        return currPath;
    }
    
    /**
     * Getter - returns the current facing direction of the caver, ex N/E/S/W
     * 
     * @return     STRING currDirection, see above.
     */
    public String getCurrDirection()
    {
        return currDirection;
    }
    
    /**
     * Setter - Updates the current facing direction of the caver, passed in.
     * 
     * @param     STRING currDirection, current direction of the caver, ex N/E/S/W
     */
    public void setCurrDirection(String currDirection)
    {
        this.currDirection = currDirection;
    }
    
    /**
     * Getter - returns the current x coordinate of caver.
     * 
     * @return     INT currCX, see above.
     */
    public int getCurrCX()
    {
        return currCX;
    }
    
    /**
     * Setter - Updates the current x coordinate of caver.
     * 
     * @param     INT currCX, see above.
     */
    public void setCurrCX(int currCX)
    {
        this.currCX = currCX;
    }
 
    /**
     * Getter - returns the current y coordinate of caver.
     * 
     * @return     INT currCY, see above. 
     */
    public int getCurrCY()
    {
        return currCY;
    }
    
    /**
     * Setter - Updates the current y coordinate of caver.
     * 
     * @param     INT currCY, see above.
     */
    public void setCurrCY(int currCY)
    {
        this.currCY = currCY;
    }
}
