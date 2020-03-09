import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class Tile extends JPanel {
	static {

	}
	GradientPaint TopSquareGradient = new GradientPaint(0, 0, Color.decode("#FFBBFF"), 85, 85,
			Color.decode("#8B668B"), true);
	int x, y, z;
	boolean clicked;
    public abstract Tile copy();

	public boolean matches(Tile otherTile) {
		return this.getClass().equals(otherTile.getClass());
	}

	public void setSize() {
//		setPreferredSize(50,50);
	}



	@Override
	public void paintComponent(Graphics g) {
		Rectangle2D topSqaure = new Rectangle2D.Double(20, 10, 70, 60);
		Polygon TopBottomPolygon = new Polygon();
		Polygon BottomLeftPolygon = new Polygon();
		Polygon TopLeftPolygon = new Polygon();
		Polygon BottomBottomPolygon = new Polygon();
		
		GradientPaint TopBottomGradient = new GradientPaint(0, 0, Color.decode("#FFBBFF"), 85, 0,
				Color.decode("#8B668B"), true);
		GradientPaint BottomLeftGradient = new GradientPaint(85, 0, Color.decode("#97FFFF"), 0, 0,
				Color.decode("#528B8B"), true);
		GradientPaint TopLeftGradient = new GradientPaint(85, 0, Color.decode("#FFBBFF"), 0, 0, Color.decode("#8B668B"),
				true);
		GradientPaint BottomBottomGradient = new GradientPaint(0, 0, Color.decode("#97FFFF"), 75, 0,
				Color.decode("#528B8B"), true);

		TopBottomPolygon.addPoint(20, 70);
		TopBottomPolygon.addPoint(15, 80);
		TopBottomPolygon.addPoint(85, 80);
		TopBottomPolygon.addPoint(90, 70);

		BottomLeftPolygon.addPoint(20, 10);
		BottomLeftPolygon.addPoint(10, 30);
		BottomLeftPolygon.addPoint(10, 90);
		BottomLeftPolygon.addPoint(20, 70);

		TopLeftPolygon.addPoint(20, 10);
		TopLeftPolygon.addPoint(15, 20);
		TopLeftPolygon.addPoint(15, 80);
		TopLeftPolygon.addPoint(20, 70);

		BottomBottomPolygon.addPoint(15, 80);
		BottomBottomPolygon.addPoint(10, 90);
		BottomBottomPolygon.addPoint(80, 90);
		BottomBottomPolygon.addPoint(85, 80);
		// Set Size
		setSize(95, 100);

		Graphics2D g2 = (Graphics2D) g;

		// Top Square
		g2.setPaint(TopSquareGradient);
		g2.fill(topSqaure);

		// Top Bottom Side
		g2.setPaint(TopBottomGradient);
		g2.fillPolygon(TopBottomPolygon);

		// Bottom Left Side
		g2.setPaint(BottomLeftGradient);
		g2.fillPolygon(BottomLeftPolygon);

		// Top Left Side
		g2.setPaint(TopLeftGradient);
		g2.fillPolygon(TopLeftPolygon);

		// Bottom Bottom Side
		g2.setPaint(BottomBottomGradient);
		g2.fillPolygon(BottomBottomPolygon);

		// Draw Border
		g2.setStroke((Stroke) new BasicStroke(1));
		g2.setColor(Color.decode("#0D0D0D"));
		g2.drawLine(20, 10, 90, 10);
		g2.drawLine(90, 10, 90, 70);
		g2.drawPolygon(TopBottomPolygon);
		g2.drawPolygon(TopLeftPolygon);
		g2.drawPolygon(BottomLeftPolygon);
		g2.drawPolygon(BottomBottomPolygon);
		setOpaque(false);
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
		// change color of top sqaure when selected
		if(clicked)
			TopSquareGradient = new GradientPaint(0,0,Color.decode("#03d100"),85,85,Color.decode("#027300"),true);
		else if(!clicked)
			TopSquareGradient = new GradientPaint(0, 0, Color.decode("#FFBBFF"), 85, 85,
					Color.decode("#8B668B"), true);
	}
	
	
	public void mousePresent(boolean mousePresent) {
		// change color of top sqaure when selected
		if(!clicked && mousePresent)
			TopSquareGradient = new GradientPaint(0,0,Color.decode("#6e4e0e"),85,85,Color.decode("#d4971e"),true);
		else
			TopSquareGradient = new GradientPaint(0, 0, Color.decode("#FFBBFF"), 85, 85,
					Color.decode("#8B668B"), true);
	}
	
	public void setCoords(int[] c) {
		x = c[0];
		y = c[1];
		z = c[2];
	}

	public int[] getCoords() {
		int[] c = { x, y, z };
		return c;
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(95, 100);
	}

	@Override
	public Dimension getPreferredSize() {
		return getMinimumSize();
	}
}