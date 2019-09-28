import java.awt.Color;
class Wall
{
    double x1;//starting x co-ordinates of the wall
    double y1;//starting y co-ordinates of the wall
    double x2;//ending x co-ordinates of the wall
    double y2;//ending y co-ordinates of the wall
    double l=0;//length of the wall
    int player=0;//player who created the wall, 0 for pre-created like basket and outer boundaries
    Color c;//color of the wall
    Wall(double X1,double Y1,double X2,double Y2)
    {
        x1=X1;
        x2=X2;
        y1=Y1;
        y2=Y2;
        c=Color.darkGray;//initial color is dark gray
    }
    Wall(double X1,double Y1,double X2,double Y2,int P)
    {
        x1=X1;
        x2=X2;
        y1=Y1;
        y2=Y2;
        player=P;
        //setting player colors
        if(player==1)
        c=Color.blue;
        else if(player==2)
        c=Color.red;
        else
        c=Color.darkGray;       
    }
    double bounce(double x,double y,double r,double vx)
    {
        //computing distance from the 2 ends of the wall
        double s1=Math.sqrt((x-x1)*(x-x1)+(y-y1)*(y-y1));
        double s2=Math.sqrt((x-x2)*(x-x2)+(y-y2)*(y-y2));
        if(s1<=r)//detecting corner collisions
        if(x>x1)
        return Math.atan2(y-y1,x-x1);
        else
        return Math.PI+Math.atan2(y-y1,x-x1);
        if(s2<=r)//detecting corner collisions
        if(x>x2)
        return Math.atan2(y-y2,x-x2);
        else
        return Math.PI+Math.atan2(y-y2,x-x2);
        else
        {
            //using Area from a sides of a triangle and the base height form to detect wall collisions 
            l=Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
            double s=(l+s1+s2)/2;
            double A=Math.sqrt(s*(s-l)*(s-s1)*(s-s2));
            double h=2*A/l;
            if(h<=r && s1<=l && s2<=l)//condition for having l as longest edge
            if(vx<=0)
            return Math.atan2((x1-x2),(y1-y2));
            else if(vx>0)
            return Math.PI+Math.atan2((x1-x2),(y1-y2));
            else
            return -2;
        }
        return -2;
    }
}