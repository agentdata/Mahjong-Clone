import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class PictureTile extends Tile
{
	private String name;
	Image img;
	private String imageLoc;
	public PictureTile(String name)
	{
		this.name = name;
		this.imageLoc = "images/"+name+".png";
		setToolTipText(toString());
        try
        {
            img = ImageIO.read(getClass().getResource(imageLoc));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
	}
	
	public String toString()
	{
		return name;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(resize((BufferedImage)img, 50, 50), 30, 15, null);
	}

	private static BufferedImage resize(BufferedImage img, int height, int width)
	{
        Image tempImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImage.createGraphics();
        g2.drawImage(tempImage, 0, 0, null);
        g2.dispose();
        return resizedImage;
    }
}