import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Bamboo1Tile extends PictureTile
{
	public Bamboo1Tile()
	{
		super("Sparrow");
	}

	public String toString()
	{
		return "Bamboo 1";
	}

	@Override
	public Tile copy() {
		// TODO Auto-generated method stub
		return new Bamboo1Tile();
	}
}