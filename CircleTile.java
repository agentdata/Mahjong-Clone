import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JFrame;

public class CircleTile extends RankTile
{
	public double	y = 40.0, x = 55.0;
	public CircleTile(int i)
	{
		if(i>0 && i<10)
			super.RanktTile(i);
		setToolTipText(toString());
	}
	
	public String toString()
	{
		return "Circle " + rank;
	}
	
	@Override public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        ArrayList<Circle> circleList = new ArrayList();
        switch(rank){
            case 1: circleList=oneCircle(); break;
            case 2: circleList=twoCircle(); break;
            case 3: circleList=threeCircle(); break;
            case 4: circleList=fourCircle(); break;
            case 5: circleList=fiveCircle(); break;
            case 6: circleList=sixCircle(); break;
            case 7: circleList=sevenCircle(); break;
            case 8: circleList=eightCircle(); break;
            case 9: circleList=nineCircle(); break;
        }
        for(Circle circle : circleList){
            circle.draw(g2);
        }
    }

    public ArrayList<Circle> oneCircle()
    {
        ArrayList<Circle> list = new ArrayList();

        double r = 27.0;
        list.add(new Circle(x, y, r, Color.GREEN));
        list.add(new Circle(x, y+1, r-22, Color.MAGENTA));
        
        list.add(new Circle(x-12, y-14, r-20, Color.RED));
        list.add(new Circle(x + 12, y-14, r-20, Color.RED));
        
        list.add(new Circle(x-18, y+6, r-20, Color.BLUE));
        list.add(new Circle(x+18, y+6, r-20, Color.BLUE));
        
        list.add(new Circle(x+1, y+20, r-20, Color.BLACK));
        
        return list;
    }
    
    public ArrayList<Circle> twoCircle()
    {
        double o = 13.0;
        double r = 12.0;
        ArrayList<Circle> list = new ArrayList();
        list.add(new Circle(x,y-o,r,Color.GREEN));
        list.add(new Circle(x,y+o,r,Color.RED));
        return list;
    }
    
    public ArrayList<Circle> threeCircle()
    {
        ArrayList<Circle> list = new ArrayList();
        double r = 10.0;
        double o = 16.0;
        list.add(new Circle(x-o,y-o,r,Color.BLUE));
        list.add(new Circle(x,	y,	r,Color.RED));
        list.add(new Circle(x+o,y+o,r,Color.GREEN));
        return list;
    }
    
    public ArrayList<Circle> fourCircle()
    {
        ArrayList<Circle> list = new ArrayList();
        double r = 12.0;
        double o = 13.0;
        list.add(new Circle(x-o,y-o,r,Color.BLUE));
        list.add(new Circle(x+o,y-o,r,Color.GREEN));
        list.add(new Circle(x-o,y+o,r,Color.GREEN));
        list.add(new Circle(x+o,y+o,r,Color.BLUE));
        return list;
    }
    
    public ArrayList<Circle> fiveCircle()
    {
        ArrayList<Circle> list = new ArrayList();
        double r = 8.0;
        double o = 15.0;
        list.add(new Circle(x-o,y-o,r,	Color.BLUE));
        list.add(new Circle(x+o,y-o,r,	Color.GREEN));
        list.add(new Circle(x-o,y+o,r,	Color.GREEN));
        list.add(new Circle(x+o,y+o,r,	Color.BLUE));
        list.add(new Circle(x,	y,	r,	Color.RED));
        return list;
    }
    
    public ArrayList<Circle> sixCircle()
    {
        ArrayList<Circle> list = new ArrayList();
        double r = 8.0;
        double o = 17.0;
        list.add(new Circle(x-o,y-o,r,Color.GREEN));
        list.add(new Circle(x-o,y,	r,Color.RED));
        list.add(new Circle(x-o,y+o,r,Color.RED));
        list.add(new Circle(x+o,y-o,r,Color.GREEN));
        list.add(new Circle(x+o,y,	r,Color.RED));
        list.add(new Circle(x+o,y+o,r,Color.RED));
        return list;
    }
    
    public ArrayList<Circle> sevenCircle()
    {
        ArrayList<Circle> list = new ArrayList();
        double r = 7.0;
        double o = 15.0;
        list.add(new Circle(x-o-3,	y-o-2,	r,Color.GREEN));
        list.add(new Circle(x-o,	y,		r,Color.RED));
        list.add(new Circle(x-o,	y+o,	r,Color.RED));
        list.add(new Circle(x,		y-o,	r,Color.GREEN));
        list.add(new Circle(x+o+3,	y-o+2,	r,Color.GREEN));
        list.add(new Circle(x+o,	y,		r,Color.RED));
        list.add(new Circle(x+o,	y+o,	r,Color.RED));
        return list;
    }
    
    public ArrayList<Circle> eightCircle()
    {
        ArrayList<Circle> list = new ArrayList();
        double r = 6.0;
        double o = 12.0;
        list.add(new Circle(x-o,y-7,	r,Color.BLUE));
        list.add(new Circle(x-o,y-20,	r,Color.BLUE));
        list.add(new Circle(x-o,y+6,	r,Color.BLUE));
        list.add(new Circle(x-o,y+19,	r,Color.BLUE));
        list.add(new Circle(x+o,y-7,	r,Color.BLUE));
        list.add(new Circle(x+o,y-20,	r,Color.BLUE));
        list.add(new Circle(x+o,y+6,	r,Color.BLUE));
        list.add(new Circle(x+o,y+19,	r,Color.BLUE));
        return list;
    }
    
    public ArrayList<Circle> nineCircle()
    {
        ArrayList<Circle> list = new ArrayList();
        double r = 9.0;
        double o = 19.0;
        list.add(new Circle(x-o,y-o,r,Color.GREEN));
        list.add(new Circle(x-o,y,	r,Color.RED));
        list.add(new Circle(x-o,y+o,r,Color.BLUE));
        list.add(new Circle(x,	y-o,r,Color.GREEN));
        list.add(new Circle(x,	y,	r,Color.RED));
        list.add(new Circle(x,	y+o,r,Color.BLUE));
        list.add(new Circle(x+o,y-o,r,Color.GREEN));
        list.add(new Circle(x+o,y,	r,Color.RED));
        list.add(new Circle(x+o,y+o,r,Color.BLUE));
        return list;
    }

	@Override
	public Tile copy() {
		// TODO Auto-generated method stub
		return new CircleTile(super.rank);
	}
}

class Circle 
{
	//radius, x and y coords
    private double radius, x, y;
    private Color color;
    
    public Circle(double x, double y, double r, Color c)
    {
        this.radius = r;
        this.x = x;
        this.y = y;
        this.color = c;
    }

    public void draw(Graphics2D g2)
    {
        blankCircle(x,y,radius,color,g2);
        int fontSize = (32*(int)radius/20);
        g2.setFont(new Font("TimesRoman", Font.PLAIN, fontSize)); 
        g2.setColor(Color.WHITE);
        g2.drawString("\u2605", (int)x-(10*fontSize)/22, (int)y+(10*fontSize)/27);
    }
    
    public void blankCircle(double x, double y, double r, Color c, Graphics2D g2)
    {
        Shape circle = new Ellipse2D.Double(x-r, y-r, 2*r, 2*r);
        g2.setPaint(c);
        g2.fill(circle);
    }
}