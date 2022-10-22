
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.*;

public class House extends MIDlet
{
    private MyHouse canvas;
    public House()
    {
        //Provide any initialisation necessary for your MIDlet.
        canvas = new MyHouse();
    }

   
    public void startApp()
    {
        // Allocate shared resources such as network connections or devices.
       Display.getDisplay(this).setCurrent(canvas);
    }

   
    public void pauseApp()
    {
        // Release shared resources allocated in startApp().
    }

   
    public void destroyApp(boolean unconditional)
    {
        // Release resources acquired in the constructor.
    }
}
