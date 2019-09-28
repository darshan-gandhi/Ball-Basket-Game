import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;

public class game2 extends JApplet implements MouseListener,MouseMotionListener
{
    Ball b;
    Vector<Wall> w=new Vector<Wall>();
    long t=0;
    int x1,y1,x2,y2;
    Graphics gr;
    MouseEvent me;
    int mf=0;
    int mx,my;
    Basket B;
    Wall wa;
    
    public void mouseExited(MouseEvent me){;}
    public void mouseEntered(MouseEvent me){;}
    public void mouseReleased(MouseEvent me){;}
    public void mousePressed(MouseEvent me){;}
    public void mouseDragged(MouseEvent me){;}
    
    public void mouseClicked(MouseEvent me)
    {
        mf++;
        mf%=2;
        this.me=me;
        if(mf==1)
        {
            x1=me.getX();
            y1=me.getY();
        }
        else if(mf==0)
        {
            x2=me.getX();
            y2=me.getY();
            w.addElement(new Wall(x1,y1,x2,y2));
        }
    }
    
    public void mouseMoved(MouseEvent me)
    {
        mx=me.getX();
        my=me.getY();
    }
    
    public void init()
    {
        JRootPane rootPane = this.getRootPane();    
        rootPane.putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        addMouseListener(this);
        addMouseMotionListener(this);
        play();
    }
    
    void play()
    {
        w.addElement(new Wall(0,0,600,0));
        w.addElement(new Wall(0,0,0,600));
        w.addElement(new Wall(0,600,600,600));
        w.addElement(new Wall(600,0,600,600));
        b=new Ball(50,50,0,0,-0.003,10,w);
    }
    public void paint(Graphics g)
    {
        if(true)
        {
        if(t%1000==0)
        {  
            B=new Basket((int)(400.0*Math.random()+50.0),(int)(400.0*Math.random()+50.0),w);
        }
        g.setColor(Color.white);
        g.fillRect(0, 0, 600, 600);
        g.setColor(Color.black);
        g.drawOval((int)b.x,(int)b.y,(int)(b.r),(int)(b.r));
        g.drawLine((int)B.x1,(int)B.y1,(int)B.x2,(int)B.y2);
        g.drawLine((int)B.x2,(int)B.y2,(int)B.x3,(int)B.y3);
        g.drawLine((int)B.x3,(int)B.y3,(int)B.x4,(int)B.y4);
        g.drawLine((int)B.x4,(int)B.y4,(int)B.x1,(int)B.y1);
        if(mf==1)
        g.drawLine(x1,y1,mx,my);
        for(int i=0;i<w.size();i++)
        g.drawLine((int)w.elementAt(i).x1,(int)w.elementAt(i).y1,(int)w.elementAt(i).x2,(int)w.elementAt(i).y2);
        b.move();
        wa=b.bounce();
        try{Thread.sleep(3);}catch(Exception e){}
        t++;
        if(!B.isgoal(b.x,b.y,b.vy))
        repaint();
        }
    }

    public String getAppletInfo()
    {
        // provide information about the applet
        return "Title:   \nAuthor:   \nA simple applet example description. ";
    }
}
