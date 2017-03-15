/*
 * Assumptions  : Moving forward is the only possibility where a caver can meet his death. Thus, a death boolean is 
 *                created to track whether a move is legal (within the parameters of the grid array)
 */
public class Move
{
    private Caver caver;
    
    public  Move(Caver caver)
    {
        this.caver = caver;
    }
    
    public boolean forward(int x, int y, String direction)
    {
        boolean caverDeath = false;
        
        //Check out-of-bounds forward movement
        switch(direction)
        {
            case "N": if ((x+1) > 15) {  caverDeath = true; } else { x = x+1; };
            break;

            case "E": if ((y+1) > 19) {  caverDeath = true; } else { y = y+1; };
            break;

            case "S": if ((x-1) < 0) {  caverDeath = true; } else { x = x-1; };
            break;
            
            case "W": if ((y-1) < 0) {  caverDeath = true; } else { y = y-1; };
            break;
        }
        
        caver.setCurrentCaverXCoordinate(x);
        caver.setCurrentCaverYCoordinate(y);
        caver.setCurrentCaverXYCoordinates();
        caver.setCurrentPath("M");
        
        return caverDeath;
    }
    
    public void right(String direction)
    {
        switch(direction)
        {
            case "N": direction = "E";
            break;

            case "E": direction = "S";
            break;

            case "S": direction = "W";
            break;
            
            case "W": direction = "N";
            break;
        }
        
        caver.setCurrentCaverDirection(direction);
        caver.setCurrentPath("R");
    }
    
    public void left(String direction)
    {
        switch(direction)
        {
            case "N": direction = "W";
            break;

            case "E": direction = "N";
            break;

            case "S": direction = "E";
            break;
            
            case "W": direction = "S";
            break;
        }
        
        caver.setCurrentCaverDirection(direction);
        caver.setCurrentPath("L");
    }
}
