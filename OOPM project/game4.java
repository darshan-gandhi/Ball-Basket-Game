import java.util.*;//for vector
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;
//<applet code="game4.class" height=500 width=500></applet>

public class game4 extends JApplet implements MouseListener,MouseMotionListener//mouse listeners for mouse inputs
{
    Ball b;//ball reference
    Vector<Wall> w=new Vector<Wall>();//vector of walls
    long t=0;//looping variable
    int x1,y1,x2,y2;//for creating new edge using mouse input
    MouseEvent me;//mouse input
    int mf=0;//flagging mouse creation of an edge
    int mx,my;//mouse co-ordinates
    Basket B;//basket reference
    Wall wa,wa2;//wa is for collided wall and wa2 for wall being removed
    int p=1;//getting player using the mouse
    int sleeptime=4;//time used for sleep in game loop
    int winningPlayer;//player winning a round
    boolean over=false;//checking game-over
    int wallCapacity=4,i;//i for iterations and wallCapacity is for maximum number of walls in the game at a given time
    int maxscore=3,p1score=0,p2score=0;//max score is the maximum score a player can gain before he is declared a winner 
    //functions not needed but present for interface
    public void mouseExited(MouseEvent me){;}

    public void mouseEntered(MouseEvent me){;}

    public void mouseReleased(MouseEvent me){;}

    public void mousePressed(MouseEvent me){;}

    public void mouseDragged(MouseEvent me){;}

    public void mouseClicked(MouseEvent me)//detecting mouse click
    {
        mf++;//flagging a click
        mf%=2;//getting the point selected
        this.me=me;
        if(mf==1)
        {
            //getting co-ordinates of the first point of an edge
            x1=me.getX();
            y1=me.getY();
        }
        else if(mf==0)
        {
            //toggling the player
            if(p==1)
                p=2;
            else
                p=1;
            //getting ending co-ordinates of an edge
            x2=me.getX();
            y2=me.getY();
            //creating and adding new edge
            w.addElement(new Wall(x1,y1,x2,y2,p));
            wallCapacity--;//updating wall capacity
            if(wallCapacity<0)
            {
                i=0;
                //finding wall to be deleted
                while(w.elementAt(i).player!=1 && w.elementAt(i).player!=2)
                    i++;
                wa2=w.elementAt(i);//getting its reference to remove from drawing
                w.removeElement(wa2);//removing from vector
            }
        }
    }

    public void mouseMoved(MouseEvent me)//getting mouse location for showing the possible ending point of the edge
    {
        mx=me.getX();
        my=me.getY();
    }

    public void init()
    {
        JRootPane rootPane = this.getRootPane();    
        rootPane.putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        //adding mouse listeners
        addMouseListener(this);
        addMouseMotionListener(this);
        play();
    }

    void play()
    {
        //creating round variables
        //external walls
        w.addElement(new Wall(0,0,500,0));
        w.addElement(new Wall(0,0,0,500));
        w.addElement(new Wall(0,500,500,500));
        w.addElement(new Wall(500,0,500,500));
        b=new Ball(50,50,0,0,-0.003,5,w);//creating the ball       
    }

    public void paint(Graphics g)
    {
        if(!over)//if the game is not over
        {
            g.setColor(Color.darkGray);//setting color for the game constructs
            
            if(t%1000==0)//basket movement timing
            {  
                B=new Basket((int)(400.0*Math.random()+50.0),(int)(400.0*Math.random()+50.0),w);//random positioning of the basket
            }
	    //drawing the basket
            g.drawLine((int)B.x1,(int)B.y1,(int)B.x2,(int)B.y2);
            g.drawLine((int)B.x2,(int)B.y2,(int)B.x3,(int)B.y3);
            g.drawLine((int)B.x3,(int)B.y3,(int)B.x4,(int)B.y4);
            g.drawLine((int)B.x4,(int)B.y4,(int)B.x1,(int)B.y1);
            g.drawLine((int)((B.x1+B.x2)/2),(int)((B.y1+B.y2)/2),(int)((B.x2+B.x3)/2),(int)((B.y2+B.y3)/2));
            g.drawLine((int)((B.x2+B.x3)/2),(int)((B.y2+B.y3)/2),(int)((B.x3+B.x4)/2),(int)((B.y3+B.y4)/2));
            g.drawLine((int)((B.x3+B.x4)/2),(int)((B.y3+B.y4)/2),(int)((B.x4+B.x1)/2),(int)((B.y4+B.y1)/2));
            g.drawLine((int)((B.x4+B.x1)/2),(int)((B.y4+B.y1)/2),(int)((B.x1+B.x2)/2),(int)((B.y1+B.y2)/2));
            g.drawLine((int)B.x1,(int)B.y1,(int)B.x3,(int)B.y3);
            g.drawLine((int)B.x2,(int)B.y2,(int)B.x4,(int)B.y4);	
            
            g.fillOval((int)b.x,(int)b.y,(int)(2*b.r),(int)(2*b.r));//drawing the ball 
            
            if(mf==1)
                g.drawLine(x1,y1,mx,my);//drawing apparent edge when the user wants to construct an edge
                
            for(int i=0;i<w.size();i++)
            {
                //drawing all the walls of the given color
                g.setColor(w.elementAt(i).c);
                g.drawLine((int)w.elementAt(i).x1,(int)w.elementAt(i).y1,(int)w.elementAt(i).x2,(int)w.elementAt(i).y2);
            }
            
            if(t%10000==0 && sleeptime!=2)//updating sleep time
                sleeptime--;
            try{Thread.sleep(sleeptime);}catch(Exception e){}//synchronizing game time with real time
            t++;//updating game time
            
            g.setFont(new Font( "SansSerif", Font.PLAIN, 20));//setting score font
            g.setColor(Color.green);//score color green
            g.drawString("Player1: "+p1score+" Player2: "+p2score,150,20);//printing score
            
            
            g.setColor(Color.white);//erasing the constructs that will be modified by paintinig them white
            
            g.fillOval((int)b.x,(int)b.y,(int)(2*b.r),(int)(2*b.r));//erasing the ball
            
            if(t%1000==0)//erasinig basket
            {
                g.drawLine((int)B.x1,(int)B.y1,(int)B.x2,(int)B.y2);
                g.drawLine((int)B.x2,(int)B.y2,(int)B.x3,(int)B.y3);
                g.drawLine((int)B.x3,(int)B.y3,(int)B.x4,(int)B.y4);
                g.drawLine((int)B.x4,(int)B.y4,(int)B.x1,(int)B.y1);
                g.drawLine((int)((B.x1+B.x2)/2),(int)((B.y1+B.y2)/2),(int)((B.x2+B.x3)/2),(int)((B.y2+B.y3)/2));
                g.drawLine((int)((B.x2+B.x3)/2),(int)((B.y2+B.y3)/2),(int)((B.x3+B.x4)/2),(int)((B.y3+B.y4)/2));
                g.drawLine((int)((B.x3+B.x4)/2),(int)((B.y3+B.y4)/2),(int)((B.x4+B.x1)/2),(int)((B.y4+B.y1)/2));
                g.drawLine((int)((B.x4+B.x1)/2),(int)((B.y4+B.y1)/2),(int)((B.x1+B.x2)/2),(int)((B.y1+B.y2)/2));
                g.drawLine((int)B.x1,(int)B.y1,(int)B.x3,(int)B.y3);
                g.drawLine((int)B.x2,(int)B.y2,(int)B.x4,(int)B.y4);
            }
            
            if(mf==1)//erasing plausible edge
                g.drawLine(x1,y1,mx,my);
                
            if(wallCapacity<0)//erasing old wall if capacity is full
                g.drawLine((int)wa2.x1,(int)wa2.y1,(int)wa2.x2,(int)wa2.y2);
            
            //updating ball motion
            b.move();
            wa=b.bounce();
            
            //getting the player with the last collision to the wall
            try{
                if(wa.player==1)
                    winningPlayer=2;
                else if(wa.player==2)
                    winningPlayer=1;
                }catch(Exception e){}//may throw null pointer exception
                
            //looping if the round is not over
            if(!B.isgoal(b.x,b.y,b.vy))
            {
                repaint();
            }
            
            else//doing at round end
            {
                g.setColor(Color.white);//erasing the score
                g.drawString("Player1: "+p1score+" Player2: "+p2score,150,20);
                
                g.setColor(Color.green);//printing round result
                g.setFont(new Font( "SansSerif", Font.PLAIN, 40));
                g.drawString("Player: "+winningPlayer+" Scores",100,100);
                
                if(winningPlayer==1)//updating scores
                    p1score++;
                else
                    p2score++;
                    
                play();//creating the next round
                
                try{Thread.sleep(3000);}catch(Exception e){}//keeping the result of the round on screen for 3 seconds
                
                g.setColor(Color.white);//erasing the result
                g.drawString("Player: "+winningPlayer+" Scores",100,100);
                
                //checking for game end
                if(p1score>=maxscore)
                {
                    g.setColor(new Color(0,255,0));
                    g.drawString("Player: 1 wins",100,100);  
                    over=true;
                }
                else if(p2score>=maxscore)
                {
                    g.setColor(new Color(0,255,0));
                    g.drawString("Player: 2 wins",100,100);  
                    over=true;
                }
                
                repaint();//looping for the next round
            }
        }
    }

    public String getAppletInfo()
    {
        // provide information about the applet
        return "Title:   \nAuthor:   \nA simple applet example description. ";
    }
}
