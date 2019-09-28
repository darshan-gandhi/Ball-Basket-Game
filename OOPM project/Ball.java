import java.util.Vector;//needs vector of walls
public class Ball
{
    double x;//x co-ordinate
    double y;//y co-ordinate
    double vx;//velocity along x
    double vy;//velocity along y
    double g;//gravity
    double r;//radius
    Vector<Wall> walls;//vector of walls
    Ball(double X,double Y,double VX,double VY,double G,double R,Vector<Wall> wall)
    {
        x=X;
        y=Y;
        vx=VX;
        vy=VY;
        g=G;
        r=R;
        walls=wall;
    }
    void move()
    {
        //moviing by modifying co-ordinates
        x+=vx;
        y+=vy;
        vy-=g;//modifying vertical velocity according to gravity
    }
    Wall bounce()
    {
        double theta=-2;//angle of bounce
        double VX,VY;//new velocities
        int i;//iterating variable
        for(i=0;i<walls.size();i++)
        {
            theta=walls.elementAt((int)i).bounce(x,y,r,vx);//checking collisions with all walls
            if(theta!=-2)
            {
                break;
            }
        }
        if(theta!=-2)
        {
            //performing collision at angle arithematic
            VX=vy*Math.sin(2*theta)-vx*Math.cos(2*theta);
            VY=vx*Math.sin(2*theta)+vy*Math.cos(2*theta);
            vx=(double)VX;
            vy=(double)VY;
            return walls.elementAt(i);//returning the wall collided with
        }
        return null;//else returns null
    }
}