import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MazeWalker extends Actor
{
    private final int NORTH = 270;
    private final int SOUTH = 90;
    private final int EAST = 0;
    private final int WEST = 180;
    
    
    public MazeWalker()
    {
        GreenfootImage MazeWalkerImage = new GreenfootImage(40, 40);
        
        MazeWalkerImage.setColor(Color.BLUE);
        MazeWalkerImage.fillRect(0, 0, 40, 40);
        
        setImage(MazeWalkerImage);
        setRotation(NORTH);
        int xOffset =+1;
        int yOffset =0;
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkWin();
        if (wallOnLeft() == true)
        {
            if( canMoveForward() == true){
                move(1);
            }
            else
            {
                setRotation( getRotation() + 90 );
            }
        }
        else
        {
            setRotation( getRotation() - 90 );
            
            if( canMoveForward() == true)
            {
                move(1);
            }
        }
    }    
    
    /** 
     * wallOnLeft checks whether there is a wall on the Player's left
     * @param There are no parameters
     * @return a boolean stating whether or not there is a wall on the left
     */
    private boolean wallOnLeft()
    {
        int xOffset =0;
        int yOffset =0;
        
        boolean wallLeft = false;
        
        if( getRotation() == NORTH)
        {
            xOffset = -1;
        }
        else if( getRotation() == SOUTH)
        {
            xOffset = +1;
        }
        else if( getRotation() == EAST)
        {
            yOffset = -1;
        }
        else if( getRotation() == WEST)
        {
            yOffset = +1;
        }
        
        
        if( getOneObjectAtOffset(xOffset, yOffset, Wall.class) != null)
        {
            wallLeft = true;
        }
        
        return wallLeft;
    }
    
    /**
     * canMoveForward checks whether player can move foward
     * @param there are no parameters
     * @return a boolean stating whether or not we can move foward
     */
    private boolean canMoveForward()
    {
        int xOffset = 0;
        int yOffset = 0;
        
        boolean moveForward = false;
        
        if(getRotation() == EAST)
        {
            xOffset = +1;
        }
        else if(getRotation() == SOUTH)
        {
            yOffset = +1;
        }
        else if(getRotation() == WEST)
        {
            xOffset = -1;
        }
        else
        {
            yOffset = -1;
        }
        
        if(getOneObjectAtOffset(xOffset, yOffset, Wall.class)== null)
        {
            moveForward = true;
        }
        
        return moveForward;
    }
    
    /**
     * checkWin will check if player has reached WinningSpace and display victory message
     * @param There are no parameters
     * @return  Nothing is returned
     */
    private void checkWin()
    {
        if( getOneIntersectingObject(WinningSpace.class) != null )
        {
            getWorld().showText("You Suck!", getWorld().getWidth()/2, getWorld().getHeight()/2);
            Greenfoot.stop();
        }
    }
}
