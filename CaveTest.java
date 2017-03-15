import static org.junit.Assert.*;
import org.junit.Test;

public class CaveTest
{
    public CaveTest()
    {
    }
    
    /**
     * Print Grid Check: Caver is at X1 Y2 facing N on grid and is alive. '^' symbol should appear.
     */
    @Test
    public void PrintCave_CaverAtX1Y2N()
    {
        Cave cave1 = new Cave();
        cave1.printCave(1, 2, "N", false);
    }
    
    /**
     * Print Grid Check: Caver is at X6 Y1 facing E on grid and is alive. '>' symbol should appear.
     */
    @Test
    public void PrintCave_CaverAtX6Y10E()
    {
        Cave cave1 = new Cave();
        cave1.printCave(6, 10, "E", false);
    }
    
    /**
     * Print Grid Check: Caver is at X0 Y0 facing S on grid and is alive. 'V' symbol should appear.
     */
    @Test
    public void PrintCave_CaverAtX0Y0S()
    {
        Cave cave1 = new Cave();
        cave1.printCave(0, 0, "S", false);
    }
    
    /**
     * Print Grid Check: Caver is at X15 Y19 facing W on grid and is alive. '<' symbol should appear.
     */
    @Test
    public void PrintCaver_CaverAtX15Y19W()
    {
        Cave cave1 = new Cave();
        cave1.printCave(15, 19, "W", false);
    }
    
    /**
     * Print Grid Check: Caver is at X0 Y0 facing S on grid and is dead. 'X' symbol should appear.
     */
    @Test
    public void PrintCave_CaverDeathAtX0Y0S()
    {
        Cave cave1 = new Cave();
        cave1.printCave(0, 0, "S", true);
    }
}





