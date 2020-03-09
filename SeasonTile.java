import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class SeasonTile extends PictureTile
{
	public SeasonTile(String string)
	{
		super(string);	
	}

	@Override
	public Tile copy() {
		// TODO Auto-generated method stub
		return new SeasonTile(super.toString());
	}
}