/**
 * Move.java - The user can move in three ways: forward 'M', left (90 Deg. turn) 'L', right (90 Deg. turn) 'R'.
 *  This class requests an existing Caver object to make updates to the direction or coordinates of a caver.
 *  Additionally, a caver's x and y coordinates and facing direction are requested via parameters to change
 *  the direction or coordinates of a caver.
 * 
 * @Hazel Lobos
 * @March 22, 2015 Version 1.0
 */

/*
 * Assumptions  : Moving forward is the only possibility where a caver can meet his death. Thus, a death boolean is 
 *  created to track whether a move is legal (within the parameters of the grid array)
 * Known Errors : ---
 */
public class Move
{
    // Methods for the Move class (individual descriptions below).
    
    private Caver caver;    //Existing Caver object to change coordinates or facing direction.
    
    /**
     * Constructor
     * 
     * @param      CAVER caver, Existing Caver object to change coordinates or facing direction.
     */
    public  Move(Caver caver)
    {
        this.caver = caver;
    }
    
    /**
     * 'Forward' move set option of the game. Depending on the direction N/E/S/W of the caver and on the
     *  (x, y) coordinates, moving forward might cause an out of bounds (go outside the border of the array)
     *  the caver will then meet instant death. The 'death' boolean will return true.
     * 
     * @return      BOOLEAN death, T=Forward value will cause an out of bounds of the array, caver dies F=Caver lives
     * @param       INT x, x coordinate of current position of the caver
     * @param       INT y, y coordinate of current position of the caver
     * @param       STRING dir, facing direction of current position of the caver ex N/E/S/W
     */
    public boolean forward(int x, int y, String dir)
    {
        boolean death = false;  //For the return statement, see above.
        
        switch(dir)
        {
            case "N": if ((x+1) > 15) {  death = true; } else { x = x+1; };
            break;

            case "E": if ((y+1) > 19) {  death = true; } else { y = y+1; };
            break;

            case "S": if ((x-1) < 0) {  death = true; } else { x = x-1; };
            break;
            
            case "W": if ((y-1) < 0) {  death = true; } else { y = y-1; };
            break;
        }
        
        //The caver has moved, set X, Y, coordinates '(x,y)' and current path of the caver.
        caver.setCurrCX(x);
        caver.setCurrCY(y);
        caver.setCurrCoordinates();
        caver.setCurrPath("M");
        
        return death;
    }
    
    /**
     * 'Right' move set option of the game. Depending on the direction N/E/S/W of the caver, 
     *  they will move clockwise, their new direction will be adjusted.
     * 
     * @param       STRING dir, facing direction of current position of the caver ex N/E/S/W
     */
    public void right(String dir)
    {
        switch(dir)
        {
            case "N": dir = "E";
            break;

            case "E": dir = "S";
            break;

            case "S": dir = "W";
            break;
            
            case "W": dir = "N";
            break;
        }
        
        //Caver has moved direction, set caver's new facing direction and user movement.
        caver.setCurrDirection(dir);
        caver.setCurrPath("R");
    }
    
    /**
     * 'Left' move set option of the game. Depending on the direction N/E/S/W of the caver, 
     *  they will move counter clockwise, their new direction will be adjusted.
     * 
     * @param       STRING dir, facing direction of current position of the caver ex N/E/S/W
     */
    public void left(String dir)
    {
        switch(dir)
        {
            case "N": dir = "W";
            break;

            case "E": dir = "N";
            break;

            case "S": dir = "E";
            break;
            
            case "W": dir = "S";
            break;
        }
        
        //Caver has moved direction, set caver's new facing direction and user movement.
        caver.setCurrDirection(dir);
        caver.setCurrPath("L");
    }
}
