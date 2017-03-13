/**
 * CaverPlay.java - Instantiates the CaverController class which will then 
 *  execute the play() method to run the game. 
 * 
 * @Hazel Lobos 
 * @DATE. March 22, 2015 Version 1.0
 */

import java.io.*;

public class CaverPlay
{
    public static void main(String args[]) throws IOException
    {
        CaverController  controller = new CaverController();
        controller.play();
    }
}
