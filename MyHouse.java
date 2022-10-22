import javax.microedition.lcdui.*; //make the lcdui package available
import javax.microedition.lcdui.game.*;
import java.util.*;
import java.io.*;
public class MyHouse extends Canvas //make this a Canvas
{
    Timer timer;
    SkyTimerTask skyTask;
    SunTimerTask sunTask;
    CloudTimerTask cloudTask;
    RainTimerTask rainTask;
    Image imgSky, imgCloud, imgRain;
    Sprite sky;
    int sunX = 0, sunY = 100;
    int cloudX = 250, cloudY = 20;
    int yIncrement = -2;
    int rainY = 70;
    boolean rain = false;
    public MyHouse() //This is the constructor of MyHouse
    {
        try 
        {
            imgSky = Image.createImage("/sky.png");
            imgCloud = Image.createImage("/clouds.png");
            imgRain = Image.createImage("/raindrop.png");
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        sky = new Sprite(imgSky, 300, 200);
        sky.setPosition(0,0);
        timer = new Timer();
        //create timertask
        skyTask = new SkyTimerTask();
        sunTask = new SunTimerTask();
        cloudTask = new CloudTimerTask();
        rainTask = new RainTimerTask();
        //schedule timer 
        timer.schedule(skyTask,3000,5000);//every 5 seconds after 3 seconds delay
        timer.schedule(sunTask,200,225);
        timer.schedule(cloudTask,500,125);
        timer.schedule(rainTask,13000,95);
    }   
    public void paint(Graphics g) //write code to display elements on the Canvas
    {
        drawBackground(g);
        drawHouse(g);
        drawSun(g);
        drawCloud(g);
        if(rain)
            drawRain(g);
            
    }
    private void drawBackground(Graphics g)
    {
        g.setColor(0, 128, 0);//dark green for grass
        g.fillRect(0, 0, getWidth(), getHeight());
        sky.paint(g);
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
    private void drawSun(Graphics g)
    {
        g.setColor(255, 255, 0);//yellow
        g.fillArc(sunX, sunY, 30, 30, 0, 360);
    }
    private void drawCloud(Graphics g)
    {
        g.drawImage(imgCloud, cloudX, cloudY, Graphics.TOP | Graphics.LEFT);
    }
    private void drawRain(Graphics g)
    {//3 rain drops only
        g.drawImage(imgRain, 100, rainY, Graphics.TOP | Graphics.LEFT);
        g.drawImage(imgRain, 123, rainY+5, Graphics.TOP | Graphics.LEFT);
        g.drawImage(imgRain, 77, rainY-5, Graphics.TOP | Graphics.LEFT);
    }
    private class SkyTimerTask extends TimerTask
    {
        public void run()
        {
            if(sky.getFrame()+1 < sky.getFrameSequenceLength())//stop at the last frame
            {
                sky.nextFrame();
            }
            else
                timer.cancel();
            repaint();
        }
    }
    private class SunTimerTask extends TimerTask
    {
        public void run()
        {
            //move sun across the sky
            if(sunY < 20)
                yIncrement = -yIncrement;
           
            sunY += yIncrement;
            sunX += 3;
            repaint();
        }
    }
    private class CloudTimerTask extends TimerTask
    {
        public void run()
        {
            cloudX -= 2;
            repaint();
        }
    }
    private class RainTimerTask extends TimerTask
    {
        public void run()
        {
            rain = true;
            rainY += 2;
            repaint();
        }
    }
}
