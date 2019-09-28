import java.util.Vector;
class Basket
{
    double x1,y1,x2,y2,x3,y3,x4,y4;//4 corners of the basket
    double x,y;//first corner
    static Wall w1,w2,w3;//3 walls of the basket
    Basket(int x,int y,Vector<Wall> walls)
    {
        //removing previous walls and the previous basket
        walls.removeElement(w1);
        walls.removeElement(w2);
        walls.removeElement(w3);
        //initializing new variables for the basket
        this.x=x;
        this.y=y;
        x1=x;
        y1=y;
        x2=x+50;
        y2=y;
        x3=x+37;
        y3=y+50;
        x4=x+13;
        y4=y+50;
        //creating new walls for the basket
        w1=new Wall(x2,y2,x3,y3);
        w2=new Wall(x3,y3,x4,y4);
        w3=new Wall(x4,y4,x1,y1);
        //adding them for consideration in the game
        walls.addElement(w1);
        walls.addElement(w2);
        walls.addElement(w3);
    }
    boolean isgoal(double bx,double by,double bvy)
    {
        if(bx>x+13 && bx<x+37 && by>y && by<y+50)
        return true;
        else
        return false;
    }
}