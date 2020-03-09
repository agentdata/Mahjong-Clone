import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class FlowerTile extends PictureTile
{
	public FlowerTile(String name)
	{
		super(name);
	}

	@Override
	public Tile copy() {
		// TODO Auto-generated method stub
		return new FlowerTile(super.toString());
	}
}