

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.JFrame;

public class CharacterTile extends Tile
{
	protected char symbol;
	
	final static HashMap chineseChars = new HashMap()
	{{   put('1', "\u4E00"); put('2', "\u4E8C"); put('3', "\u4E09"); put('4', "\u56DB"); put('5', "\u4E94"); put('6', "\u516D"); put('7', "\u4E03"); put('8', "\u516B"); put('9', "\u4E5D");
	     put('N', "\u5317"); put('E', "\u6771"); put('W', "\u897F"); put('S', "\u5357"); put('C', "\u4E2D"); put('F', "\u767C"); }};
	final static HashMap toStringMap = new HashMap()
	{{	put('1', "Character 1"); put('2', "Character 2"); put('3', "Character 3"); put('4', "Character 4"); put('5', "Character 5"); put('6', "Character 6"); put('7', "Character 7"); put('8', "Character 8"); put('9', "Character 9");
		put('N', "North Wind"); put('E', "East Wind"); put('W', "West Wind"); put('S', "South Wind"); put('C', "Red Wind"); put('F', "Green Wind");	}};
		
	/**
	 * 
	 * @param symbol
	 */
	public CharacterTile(char symbol)
	{
		if(symbol == 'N' || symbol =='E'|| symbol =='W'|| symbol =='S'|| symbol =='C'||symbol == 'F' ||symbol == '1'||symbol == '2'||symbol == '3'||symbol == '4'||symbol == '5'||symbol == '6'||symbol == '7'||symbol == '8'||symbol == '9')
			this.symbol = symbol;
		setToolTipText(chineseChars.get(symbol)+" Tile");
	}
	
	/**
	 * 
	 */
	public boolean matches(Tile other)
	{
		if(super.matches(other) && this.symbol == ((CharacterTile)other).symbol)
			return true;
		else
			return false;
	}
	
	/**
	 * determines which Chinese character is representative of the symbol, this is used to generate the 
	 * image that will be displayed on the tiles in the GUI.
	 * @return a string representing the string of the
	 */
	public String toString()
	{
		return toStringMap.get(this.symbol).toString();
	}

	/**
	 * determines which Chinese character is representative of the symbol, this is used to generate the 
	 * image that will be displayed on the tiles in the GUI.
	 * @return a string representing the Chinese character
	 */
	public String toChinese()
	{
		return chineseChars.get(this.symbol).toString();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //use custom resize class to get correct proportions and scaling
        //Image scaled = new resizeImage().getImage(img, 50, 50);
        
        g2.setFont(new Font("TimesRoman", Font.PLAIN, 15)); 
        g2.setColor(Color.RED);
    	g2.drawString(Character.toString(symbol), 75, 25);
    	
    	g2.setFont(new Font("TimesRoman", Font.PLAIN, 45));
        g2.setColor(Color.BLACK);
        //Paint N E W S F
        if(symbol == 'N' || symbol =='E'|| symbol =='W'|| symbol =='S')
        {
        	g2.drawString(this.toChinese(), 30, 55);
        }
        //Paint C Red
        else if( symbol =='C')
        {
        	g2.setColor(Color.RED);
        	g2.drawString(this.toChinese(), 30, 55);
        }
        //Paint F Green
        else if(symbol == 'F')
        {
        	g2.setColor(Color.GREEN);
        	g2.drawString(this.toChinese(), 30, 55);
        }
        //Paint 1-9 with "Wan" below it, reduce the size
        else
        {
        	g2.setFont(new Font("TimesRoman", Font.PLAIN, 23)); 
        	g2.drawString(this.toChinese(), 40, 40); 
        	g2.setColor(Color.RED);
        	g2.drawString("\u842C", 40, 65);
        }
	}

	@Override
	public Tile copy() {
		// TODO Auto-generated method stub
		return new CharacterTile(symbol);
	}
}