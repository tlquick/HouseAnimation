import javax.microedition.lcdui.*; //make the lcdui package available
import javax.microedition.lcdui.game.*;
import java.util.*;
import java.io.*;
public class MyBasicHouse extends Canvas //make this a Canvas
{
    Timer timer;
    SkyTimerTask task;
    Image imgSky;
    Sprite sky;
    public MyBasicHouse() //This is the constructor of MyHouse
    {
        try 
        {
            imgSky = Image.createImage("/sky.png");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        sky = new Sprite(imgSky, 350, 200);
        sky.setPosition(0,0);
        timer = new Timer();
        //create timertask
        task = new SkyTimerTask();
        //schedule timer 
        timer.schedule(task,3000,3000);//every 3seconds after 3seconds delay
    }   
    public void paint(Graphics g) //write code to display elements on the Canvas
    {
        sky.paint(g);
        drawHouse(g);
    }
    private void drawHouse(Graphics g)
    {
       g.setColor(255, 0, 0); //set the colour to red
       g.fillTriangle(120, 100, 20, 200, 220, 200);
       g.setColor(0, 255, 0); //set the colour to green
       g.fillRect(30, 200, 180, 100);
       g.setColor(0, 0, 255); //set the colour to blue
       g.fillRect(110, 250, 20 , 50);
    }
    private class SkyTimerTask extends TimerTask
    {
        public void run()
        {
            if(sky.getFrame()+1 < sky.getFrameSequenceLength())//stop at the last frame
                sky.nextFrame();
            
            repaint();
        }
    }
}
