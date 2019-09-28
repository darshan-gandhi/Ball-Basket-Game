import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class BackgroundColorApplet extends Applet {
    public void init() {
        //
        // Here we change the default gray color background of an 
        // applet to yellow background.
        //
        setBackground(Color.YELLOW);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString("Applet background example", 0, 50);
        g.drawRect(0,0,1000,1000);
    }
}