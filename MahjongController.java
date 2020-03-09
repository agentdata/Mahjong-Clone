import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MahjongController extends JFrame {
	MahjongBoardView mahjongBoard;
	JMenuBar menuBar;
	JMenuItem soundItem;

	public static void main(String args[]) {
		new MahjongController();
	}

	public MahjongController() {

		setPreferredSize(new Dimension(1150, 650));
		setResizable(false);
		setLayout(new BorderLayout());
		pack();
		// set starting location to center of screen
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenDim.width / 2 - this.getSize().width / 2, screenDim.height / 2 - this.getSize().height / 2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("MahJong");

		addMenusToPanel();

		mahjongBoard = new MahjongBoardView(((int) System.currentTimeMillis() % 100000));
		add(mahjongBoard);
		pack();
		setVisible(true);
	}

	// creates a menuBar and calls other methods to add menuItems
	private void addMenusToPanel() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu MahjongMenu = new JMenu("Game");
		MahjongMenu.setMnemonic('G');
		menuBar.add(MahjongMenu);
		addGameMenu(MahjongMenu);

		JMenu MovesMenu = new JMenu("Moves");
		MovesMenu.setMnemonic('M');
		menuBar.add(MovesMenu);
		addMovesMenu(MovesMenu);

		JMenu SoundMenu = new JMenu("Sound");
		SoundMenu.setMnemonic('S');
		menuBar.add(SoundMenu);
		addSoundMenu(SoundMenu);

		JMenu HelpMenu = new JMenu("Help");
		HelpMenu.setMnemonic('H');
		menuBar.add(HelpMenu);
		addHelpMenu(HelpMenu);
	}

	// adds move options
	private void addMovesMenu(JMenu mahjongMenu) {
		JMenuItem Operation = new JMenuItem("Undo", 'U');
		Operation.setToolTipText("This undoes the last move, up to the beginning up the game");
		Operation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (mahjongBoard.model.hasUndo()) {
					mahjongBoard.model.undo();
				}
			}
		});
		mahjongMenu.add(Operation);

		JMenuItem SeeRemovedTiles = new JMenuItem("See Cleared Tiles", 'E');
		SeeRemovedTiles.setToolTipText("See all cleared tiles.");
		SeeRemovedTiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				mahjongBoard.showRemovedTiles();
			}
		});
		mahjongMenu.add(SeeRemovedTiles);
		
//		 //simulate fireworks display
//        JMenuItem win = new JMenuItem("Win");
//        win.addActionListener(new ActionListener() {
//          public void actionPerformed(ActionEvent evt) {
//        	  mahjongBoard.gameWon();
//          }
//        });
//        mahjongMenu.add(win);
	}

	// adds help menu items
	private void addHelpMenu(JMenu helpMenu) {
		JMenuItem About = new JMenuItem("About", 'A');
		About.setToolTipText("this about page describese");
		About.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Help h = new Help("help/help.html", "Help");
				h.display();
			}
		});
		helpMenu.add(About);

		JMenuItem HowToPlay = new JMenuItem("How To Play", 'T');
		HowToPlay.setToolTipText("This show rules and how to.");
		HowToPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Help h = new Help("help/how-to-play.html", "Help");
				h.display();
			}
		});
		helpMenu.add(HowToPlay);
	}

	// adds sound menu options
	private void addSoundMenu(JMenu soundMenu) {
		// Toggle Sound
		soundItem = new JMenuItem("Turn Off");
		soundItem.setToolTipText("Toggles the game sounds");
		soundItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				toggleSound();
			}
		});
		soundMenu.add(soundItem);
	}

	// adds the game menu items
	private void addGameMenu(JMenu MahjongMenu) {
		// this play option just starts a new game using the random seem
		JMenuItem Play = new JMenuItem("Play", 'P');
		Play.setToolTipText("New Game");
		Play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int confirm = JOptionPane.showConfirmDialog(null, "Start a new game (discards this one)?", "Message",
						JOptionPane.YES_NO_OPTION);
				if (confirm == 0) {
					// OK
					newGame();
				}
			}
		});
		MahjongMenu.add(Play);

		// adds an option to restart the game using the same seed as the current game so
		// it's the same game
		JMenuItem RestartCurrentGame = new JMenuItem("Restart", 'R');
		RestartCurrentGame.setToolTipText("Reset Current Game");
		RestartCurrentGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				loadGame(mahjongBoard.gameSeed);
			}
		});
		MahjongMenu.add(RestartCurrentGame);

		// item to start a game based on user's number input which is used as the seed
		// so each number is a repeatable game
		JMenuItem NumberedGame = new JMenuItem("Numbered", 'N');
		NumberedGame.setToolTipText("play a numbered (and therefore a repeatable game)");
		NumberedGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				boolean validInput = false;
				while (!validInput) {
					String value = JOptionPane.showInputDialog(null, "Enter game number: ");
					if (!value.isEmpty() && isInt(value)) {
						validInput = true;
						loadGame(Integer.parseInt(value));
					}
				}
			}
		});
		MahjongMenu.add(NumberedGame);

		// Exit item
		JMenuItem exit = new JMenuItem("Exit", 'E');
		exit.setToolTipText("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int confirm = JOptionPane.showConfirmDialog(null, "Exit (quit current game)?", "Message",
						JOptionPane.YES_NO_OPTION);
				if (confirm == 0) {
					// OK
					System.exit(0);
				}
			}
		});

		MahjongMenu.add(exit);
	}

	// This method toggles the sound on and off
	private void toggleSound() {
		mahjongBoard.sound = !mahjongBoard.sound;
		soundItem.setText(mahjongBoard.sound ? "Turn Off" : "Turn On");
	}

	// load a game based on the user inputed number which is used as the random
	// seed, so each number is a repeatable game
	private void loadGame(int seed) {
		remove(mahjongBoard);
		mahjongBoard = new MahjongBoardView(seed);
		add(mahjongBoard);
		setTitle("MahJong (Game ID: " + seed + ")");
		repaint();
		pack();
	}

	// this starts a new random game using currentTimemillis.
	private void newGame() {
		remove(mahjongBoard);
		mahjongBoard = new MahjongBoardView((int) System.currentTimeMillis() % 100000);
		add(mahjongBoard);
		setTitle("MahJong");
		repaint();
		pack();
	}

	// verifies if the string contains only an int
	private boolean isInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}