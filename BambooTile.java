import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import javax.swing.JFrame;

public class BambooTile extends RankTile
{
	protected int	y = 40, x = 55;
	public BambooTile(int i)
	{
		if(i>0 && i<10)
			super.RanktTile(i);
		setToolTipText(toString());
	}
	
	public final String toString()
	{
		return "Bamboo " + rank;
	}
	
	@Override public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        ArrayList<Bamboo> bambooPieces= new ArrayList();
        switch(rank){
            case 2: bambooPieces=twoBamboo(); 	break;
            case 3: bambooPieces=threeBamboo(); break;
            case 4: bambooPieces=fourBamboo(); 	break;
            case 5: bambooPieces=fiveBamboo(); 	break;
            case 6: bambooPieces=sixBamboo(); 	break;
            case 7: bambooPieces=sevenBamboo(); break;
            case 8: bambooPieces=eightBamboo(); break;
            case 9: bambooPieces=nineBamboo(); 	break;
        }
        for(Bamboo b : bambooPieces){
            b.draw(g2);
        }
    }
    
    public ArrayList<Bamboo> twoBamboo(){
        ArrayList<Bamboo> list = new ArrayList();
        list.add(new Bamboo(x, y-13,    Color.BLUE));
        list.add(new Bamboo(x, y+13,    Color.GREEN));
        return list;
    }
    
    public ArrayList<Bamboo> threeBamboo(){
        ArrayList<Bamboo> list = new ArrayList();
        list.add(new Bamboo(x,    y-13, Color.BLUE));
        list.add(new Bamboo(x-10, y+13, Color.GREEN));
        list.add(new Bamboo(x+10, y+13, Color.GREEN));
        return list;
    }
    
    public ArrayList<Bamboo> fourBamboo(){
        ArrayList<Bamboo> list = new ArrayList();
        list.add(new Bamboo(x-10, y-13, Color.BLUE));
        list.add(new Bamboo(x+10, y-13, Color.GREEN));
        list.add(new Bamboo(x-10, y+13, Color.GREEN));
        list.add(new Bamboo(x+10, y+13, Color.BLUE));
        return list;
    }
    
    public ArrayList<Bamboo> fiveBamboo(){
        ArrayList<Bamboo> list = new ArrayList();
        list.add(new Bamboo(x-15, y-13, Color.GREEN));
        list.add(new Bamboo(x+15, y-13, Color.BLUE));
        list.add(new Bamboo(x-15, y+13, Color.BLUE));
        list.add(new Bamboo(x+15, y+13, Color.GREEN));
        list.add(new Bamboo(x,    y,    Color.RED));
        return list;
    }
    
    public ArrayList<Bamboo> sixBamboo(){
        ArrayList<Bamboo> list = new ArrayList();
        list.add(new Bamboo(x-10, y-13, Color.GREEN));
        list.add(new Bamboo(x,    y-13, Color.GREEN));
        list.add(new Bamboo(x+10, y-13, Color.GREEN));
        list.add(new Bamboo(x-10, y+13, Color.BLUE));
        list.add(new Bamboo(x,    y+13, Color.BLUE));
        list.add(new Bamboo(x+10, y+13, Color.BLUE));
        return list;
    }
    
    public ArrayList<Bamboo> sevenBamboo(){
        ArrayList<Bamboo> list = new ArrayList();
        list.add(new Bamboo(x,    y-17, Color.RED));
        list.add(new Bamboo(x-15, y,    Color.GREEN));
        list.add(new Bamboo(x,    y,    Color.BLUE));
        list.add(new Bamboo(x+15, y,    Color.GREEN));
        list.add(new Bamboo(x-15, y+17, Color.GREEN));
        list.add(new Bamboo(x,    y+17, Color.BLUE));
        list.add(new Bamboo(x+15, y+17, Color.GREEN));
        return list;
    }
    
    public ArrayList<Bamboo> eightBamboo(){
        ArrayList<Bamboo> list = new ArrayList();
        list.add(new Bamboo(x-19, y-17, Color.GREEN));
        list.add(new Bamboo(x,    y-17, Color.GREEN));
        list.add(new Bamboo(x+19, y-17, Color.GREEN));
        list.add(new Bamboo(x-10, y,    Color.RED));
        list.add(new Bamboo(x+10, y,    Color.RED));
        list.add(new Bamboo(x-19, y+17, Color.BLUE));
        list.add(new Bamboo(x,    y+17, Color.BLUE));
        list.add(new Bamboo(x+19, y+17, Color.BLUE));
        return list;
    }
    
    public ArrayList<Bamboo> nineBamboo(){
        ArrayList<Bamboo> list = new ArrayList();
        list.add(new Bamboo(x-19, y-17, Color.RED));
        list.add(new Bamboo(x,    y-17, Color.BLUE));
        list.add(new Bamboo(x+19, y-17, Color.GREEN));
        list.add(new Bamboo(x-19, y,    Color.RED));
        list.add(new Bamboo(x,    y,    Color.BLUE));
        list.add(new Bamboo(x+19, y,    Color.GREEN));
        list.add(new Bamboo(x-19, y+17, Color.RED));
        list.add(new Bamboo(x,    y+17, Color.BLUE));
        list.add(new Bamboo(x+19, y+17, Color.GREEN));
        return list;
    }

	@Override
	public Tile copy() {
		// TODO Auto-generated method stub
		return new BambooTile(super.rank);
	}
}

class Bamboo
{
    private int x, y;
    private Color c;
    public Bamboo(int x, int y, Color c)
    {
        this.x = x;
        this.y = y;
        this.c = c;
    }

    public void draw(Graphics2D g2)
    {
        Polygon shape = new Polygon();
        shape.addPoint(x-3, y-9);
        shape.addPoint(x+3, y-9);
        shape.addPoint(x+4, y-8);
        shape.addPoint(x+4, y-6);
        shape.addPoint(x+3, y-6);
        shape.addPoint(x+2, y-5);
        shape.addPoint(x+2, y-2);
        shape.addPoint(x+4, y+1);
        shape.addPoint(x+4, y+3);
        shape.addPoint(x+3, y+3);
        shape.addPoint(x+2, y+4);
        shape.addPoint(x+2, y+7);
        shape.addPoint(x+4, y+9);
        shape.addPoint(x+4, y+11);
        shape.addPoint(x-4, y+11);
        shape.addPoint(x-4, y+9);
        shape.addPoint(x-2, y+7);
        shape.addPoint(x-2, y+4);
        shape.addPoint(x-3, y+3);
        shape.addPoint(x-4, y+3);
        shape.addPoint(x-4, y);
        shape.addPoint(x-2, y-1);
        shape.addPoint(x-2, y-6);
        shape.addPoint(x-3, y-6);
        shape.addPoint(x-4, y-7);
        shape.addPoint(x-4, y-8);

        g2.setColor(c);
        g2.fillPolygon(shape);
        g2.setColor(Color.WHITE);
        g2.drawLine(x, y-1, x, y-6);
        g2.drawLine(x, y+3, x, y+9);
    }
}