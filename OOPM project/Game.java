import java.util.Vector;
class Game
{
    static int t=0;
    static Ball b;
    static Basket B;
    static Vector<Wall> w=new Vector(6);
    static void play()
    {
        w.addElement(new Wall(0,0,600,0));
        w.addElement(new Wall(0,0,0,600));
        w.addElement(new Wall(0,600,600,600));
        w.addElement(new Wall(600,0,600,600));
        w.addElement(new Wall(500,0,600,500));
        w.addElement(new Wall(500,500,600,600));
        b=new Ball(100,100,5,5,0,20,w);
        B=new Basket(200,200,w);
    }
    static public void paint()
    {
    if(t==0)
    play();
    if(t<30)
    {
        b.move();
        b.bounce();
        try{Thread.sleep(10);}catch(Exception e){}
        t++;
        if(!B.isgoal(b.x,b.y,b.vy))
        paint();
    }
    }
}