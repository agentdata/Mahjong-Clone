import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;

public class WhiteDragonTile extends Tile
{
	private String imageLoc = "src/images/dragon_bg.png";
	
	public WhiteDragonTile()
	{
		setToolTipText(toString());
	}
	
	public final String toString()
	{
		return "White Dragon";
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        Stroke strokeStyle = new BasicStroke(4.0f,    // Width
                           BasicStroke.CAP_SQUARE,    // End cap
                           BasicStroke.JOIN_MITER,    // Join style
                           10.0f,                     // Miter limit
                           new float[] {6.0f,15.0f},  // Dash pattern
                           1.0f);                     // Dash phase

        g2.setStroke(strokeStyle);
        g2.setPaint(Color.decode("#0000FF"));

        g2.draw(new Rectangle(33,18,45,45));
        
        g2.setStroke(new BasicStroke());
              
        g2.draw(new Rectangle(31,16,49,49));
        g2.draw(new Rectangle(35,20,41,41));
	}

	@Override
	public Tile copy() {
		// TODO Auto-generated method stub
		return new WhiteDragonTile();
	}
}