import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TexturePaint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

@SuppressWarnings("serial")
public class MahjongBoardView extends JPanel {
	static MahjongBoardModel model;
	static boolean sound;
	static int gameSeed;
	static AudioInputStream audioIn;
	private Image dragon;
	private Image background;
	static String tileClick = ("sounds/tileclick.wav");
	static String tileMatch = ("sounds/tilematch.wav");
	static String notAMatch = ("sounds/notamatch.wav");

	// setup the board by instantiating new MahjonModel
	public MahjongBoardView(int GameSeed) {
		gameSeed = GameSeed;
		model = new MahjongBoardModel(GameSeed);
		sound = true;
		setLayout(null);
		drawTiles();
		repaint();
		setVisible(true);
	}

	public void gameWon() {
		Fireworks f = new Fireworks(this);
		f.setSound(sound);
		f.setExplosions(10, 1000);
		f.fire();
	}

	// draws the elements from the MahjonBoardModel into this JPanel
	@SuppressWarnings("unused")
	private void drawTiles() {
		int zOrder = 0;
		for (int z = model.Z_AXIS - 1; z >= 0; z--) {
			for (int x = 0; x <= model.X_AXIS - 1; x++) {
				for (int y = model.Y_AXIS - 1; y >= 0; y--) {

					if (model.board[z][y][x].isTile()) {
						final Tile t = model.board[z][y][x].getTile();
						// Handle Special Cases
						// Left most tile
						if (x == 0 && y == 4 & z == 0) {
							t.setBounds(x * (100 - 30) + (z * 10) + 30, y * (95 - 35) - (z * 20) - 30 + 20, 95, 100);
						}
						// Right most tile
						else if (x == 14 && y == 4 & z == 0) {
							t.setBounds(x * (100 - 30) + (z * 10) + 30, y * (95 - 35) - (z * 20) - 30 + 20, 95, 100);
						}
						// 2nd right most tile
						else if (x == 13 && y == 4 & z == 0) {
							t.setBounds(x * (100 - 30) + (z * 10) + 30, y * (95 - 35) - (z * 20) - 30 + 20, 95, 100);
						}
						// Top center
						else if (x == 6 && y == 3 & z == 4) {
							t.setBounds(x * (100 - 30) + (z * 10) + (35) + 30, y * (95 - 35) - (z * 20) + (40) + 20, 95,
									100);
						}
						// all other tiles
						else {
							t.setBounds(x * (100 - 30) + (z * 10) + 30, y * (95 - 35) - (z * 20) + 20, 95, 100);
						}
						setComponentZOrder(t, zOrder);
						model.board[z][y][x].setZOrder(zOrder);
						zOrder++;
						// System.out.println(t);
						final int[] c = { x, y, z };
						t.setCoords(c);

						// Add the mouse listener
						t.addMouseListener(addTileMouseListener(t, x, y, z, c));

						// Add the tile to the panel
						add(t);
						repaint();
					}
				}
			}
		}
	}

	public MouseAdapter addTileMouseListener(Tile t, int x, int y, int z, int[] c) {
		return new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mousePressed(MouseEvent e) {
				if (model.isClickable(c[0], c[1], c[2])) {
					playSound(tileClick);
					if (t != model.SelectedTile.t && model.SelectedTile.isTile()) {

						// if they are a match then add them to removed list, actually remove them from
						// the board and blank the selectedTile
						if (model.SelectedTile.t.matches(model.board[c[2]][c[1]][c[0]].t)) {
							playSound(tileMatch);

							// add each tile to the removed Tiles list
							model.removedTiles.add(model.SelectedTile);
							model.removedTiles.add(model.board[c[2]][c[1]][c[0]]);

							// remove tiles from model board by setting equal to blank tile
							model.board[c[2]][c[1]][c[0]] = new MahjongTile(false);
							model.board[model.SelectedTile.getZ()][model.SelectedTile.getY()][model.SelectedTile
									.getX()] = new MahjongTile(false);

							// Set visibility to false
							t.setVisible(false);
							model.SelectedTile.t.setVisible(false);
							model.clearedTiles += 2;

							// set clicked to false
							model.SelectedTile.t.setClicked(false);

							//
							model.SelectedTile = new MahjongTile(false);
							// win condition
							if (model.clearedTiles == model.TOTAL_TILES) {
								gameWon();
							}
						}
						// if they don't match then set the clicked tile as the active tile
						else {
							playSound(notAMatch);
							model.SelectedTile.t.setClicked(false);
							model.SelectedTile = model.board[c[2]][c[1]][c[0]];

							model.board[c[2]][c[1]][c[0]].t.setClicked(true);
							repaint();
						}

					}
					// if clicked tile is the currently selected tile then deselect it.
					else if (t == model.SelectedTile.t) {
						model.SelectedTile.t.setClicked(false);
						model.SelectedTile = new MahjongTile(false);
						model.board[c[2]][c[1]][c[0]].t.setClicked(false);
						repaint();
					}
					// if the selected tile is a blank placeholder then set the selected tile to
					// the clicked tile
					else if (!model.SelectedTile.isTile()) {
						model.SelectedTile = model.board[c[2]][c[1]][c[0]];
						model.board[c[2]][c[1]][c[0]].t.setClicked(true);
						repaint();
					}
				}
			}

			public void mouseEntered(java.awt.event.MouseEvent evt) {
				// HIGHLIGHT the tile
				if (t != model.SelectedTile.t && (model.isClickable(c[0], c[1], c[2]))) {
					t.mousePresent(true);
					repaint();
				}
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				// un-highlight the tile
				if (t != model.SelectedTile.t && (model.isClickable(c[0], c[1], c[2]))) {
					t.mousePresent(false);
					repaint();
				}
			}
		};
	}

	/*
	 * Create and display a scrolling window containing removed tiles
	 */
	static void showRemovedTiles() {
		JPanel inner = new JPanel();
		inner.setLayout(new GridLayout(0, 4, 2, 2));

		ArrayList<Tile> tiles = model.getRemovedTiles();
		for (Tile t : tiles) {
			inner.add(t);
		}
		int height = ((tiles.size() / 4) + 1) * 85;

		inner.setSize(300, height);
		inner.setPreferredSize(new Dimension(300, height));

		JScrollPane scroller = new JScrollPane(inner);

		scroller.setLayout(new ScrollPaneLayout());
		scroller.setPreferredSize(new Dimension(400, 410));
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JDialog popUpContainer = new JDialog();

		popUpContainer.setPreferredSize(new Dimension(400, 410));
		popUpContainer.setBounds(400, 100, 410, 400);
		popUpContainer.setResizable(false);
		popUpContainer.setLayout(new BorderLayout());

		popUpContainer.add(scroller, BorderLayout.CENTER);

		popUpContainer.setVisible(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// Get background images
		try {
			dragon = ImageIO.read(getClass().getResource("images/dragon_bg.png"));
			background = ImageIO.read(getClass().getResource("images/mahjong_bg.jpg"));
		} catch (IOException e) {
			// e.printStackTrace();
		}

		// Bamboo textured
		Rectangle2D r = new Rectangle2D.Double(0, 0, 1150, 650);
		g2.setPaint(new TexturePaint((BufferedImage) background, r));

		// Paint bamboo image
		Rectangle2D bg = new Rectangle2D.Double(0, 0, 1150, 650);
		g2.fill(bg);

		// Set half opacity
		AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
		g2.setComposite(composite);

		// draw dragon
		g2.drawImage(dragon, 200, 20, null);

		// reset opacity for tile painting
		composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
		g2.setComposite(composite); // Set current alpha
	}

	static void playSound(String name) {
		if (sound) {
			PlayClip gameSound = new PlayClip(name);

			gameSound.play();
		}
	}
//	public static synchronized void playSound(String fileName) {
//		new Thread(new Runnable() {
//			// The wrapper thread is unnecessary, unless it blocks on the
//			// Clip finishing; see comments.
//			public void run() {
//				try {
//					Clip clip = AudioSystem.getClip();
//					AudioInputStream inputStream = AudioSystem
//							.getAudioInputStream(getClass().getResourceAsStream(fileName));
//					clip = AudioSystem.getClip();
//					clip.open(inputStream);
//					clip.start();
//				} catch (Exception e) {
//					System.err.println(e.getMessage());
//				}
//			}
//		}).start();
//	}
}
