public class Caver
{
    private String currentPath = "";                //Current instructions the user has given to the caver, ex '(0,0) N RRLMMLMM'
    private int currentCaverXCoordinate;
    private int currentCaverYCoordinate;
    private String currentCaverDirection = "";      // ex 'N'=north or 'E'=east or 'S'=south or 'W'=west
    private String currentCaverXYCoordinates = "";  // ex (0,1)
    
    private int initialCaverXCoordinate;
    private int initialCaverYCoordinate;
    private String initialCaverDirection = "";
    
    public Caver(int initialCaverXCoordinate, int initialCaverYCoordinate, String initialCaverDirection)
    {
        this.initialCaverXCoordinate = initialCaverXCoordinate;
        this.initialCaverYCoordinate = initialCaverYCoordinate;
        this.initialCaverDirection = initialCaverDirection;
        
        setupCaversInitialPathString();
        setupCaversInitialCoordinates();
    }
    
    private void setupCaversInitialPathString()
    {
        String initialPath = "(Row: " +initialCaverXCoordinate+ ", Column: " +initialCaverYCoordinate+ ") " + initialCaverDirection + " ";
        setCurrentPath(initialPath);
    }
    
    private void setupCaversInitialCoordinates()
    {
        currentCaverXCoordinate = initialCaverXCoordinate;
        currentCaverYCoordinate = initialCaverYCoordinate;
        setCurrentCaverXYCoordinates();
    }
    
    public void setCurrentCaverXYCoordinates()
    {
        currentCaverXYCoordinates = "(Row: " +currentCaverXCoordinate+ ", Column: " +currentCaverYCoordinate+ ")";
    }
    
    public String getCurrentCaverXYCoordinates()
    {
        return currentCaverXYCoordinates;
    }
    
    public void setCurrentPath(String newMove)
    {
        this.currentPath = this.currentPath + newMove;
    }  
    
    public String getCurrentPath()
    {
        return currentPath;
    }
    
    public String getCurrentCaverDirection()
    {
        return currentCaverDirection;
    }
    
    public void setCurrentCaverDirection(String currentCaverDirection)
    {
        this.currentCaverDirection = currentCaverDirection;
    }
    
    public int getCurrentCaverXCoordinate()
    {
        return currentCaverXCoordinate;
    }
    
    public void setCurrentCaverXCoordinate(int currentCaverXCoordinate)
    {
        this.currentCaverXCoordinate = currentCaverXCoordinate;
    }
 
    public int getCurrentCaverYCoordinate()
    {
        return currentCaverYCoordinate;
    }
    
    public void setCurrentCaverYCoordinate(int currentCaverYCoordinate)
    {
        this.currentCaverYCoordinate = currentCaverYCoordinate;
    }
}
