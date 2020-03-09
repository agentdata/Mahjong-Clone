import java.util.ArrayList;

public class MahjongBoardModel {
    //Represent the map in boolean values
    //false spaces are blank,
    //true spaces will be populated with tiles
    int[][][] refBoard = {  {		{0,1,1,1,1,1,1,1,1,1,1,1,1,0,0},	// [0][x][y]
                                        {0,0,0,1,1,1,1,1,1,1,1,0,0,0,0},
                                        {0,0,1,1,1,1,1,1,1,1,1,1,0,0,0},
                                        {0,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
                                        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                                        {0,0,1,1,1,1,1,1,1,1,1,1,0,0,0},
                                        {0,0,0,1,1,1,1,1,1,1,1,0,0,0,0},
                                        {0,1,1,1,1,1,1,1,1,1,1,1,1,0,0}},
                                    {   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	// [1][x][y]
                                        {0,0,0,0,1,1,1,1,1,1,0,0,0,0,0},
                                        {0,0,0,0,1,1,1,1,1,1,0,0,0,0,0},
                                        {0,0,0,0,1,1,1,1,1,1,0,0,0,0,0},
                                        {0,0,0,0,1,1,1,1,1,1,0,0,0,0,0},
                                        {0,0,0,0,1,1,1,1,1,1,0,0,0,0,0},
                                        {0,0,0,0,1,1,1,1,1,1,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}},
                                    {   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	// [2][x][y]
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,1,1,1,1,0,0,0,0,0,0},
                                        {0,0,0,0,0,1,1,1,1,0,0,0,0,0,0},
                                        {0,0,0,0,0,1,1,1,1,0,0,0,0,0,0},
                                        {0,0,0,0,0,1,1,1,1,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}},
                                    {   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},    // [3][x][y]
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}},
                                    {   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},    // [4][x][y]
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}},
                                     };
	final static int X_AXIS = 15;
	final static int Y_AXIS = 8;
	final static int Z_AXIS = 5;
	final static int TOTAL_TILES = 144;
	MahjongTile[][][] board = new MahjongTile[Z_AXIS][Y_AXIS][X_AXIS];
	TileDeck MahjongTileDeck;

	MahjongTile SelectedTile;

	static ArrayList<MahjongTile> removedTiles;
	int clearedTiles;

	public MahjongBoardModel(int GameSeed) {

		clearedTiles = 0;
		SelectedTile = new MahjongTile(false);
		removedTiles = new ArrayList<MahjongTile>();
		MahjongTileDeck = new TileDeck();
		MahjongTileDeck.shuffle((long) GameSeed);
		addToBoard();
	}

	public void addToBoard() {
		for (int z = 0; z < Z_AXIS; z++) {
			for (int y = 0; y < Y_AXIS; y++) {
				for (int x = 0; x < X_AXIS; x++) {
					// check refBoardMap coords and if it's a 1 then place the MahjongTile there.
					if (refBoard[z][y][x] == 1) {
						board[z][y][x] = new MahjongTile(MahjongTileDeck.deal(), x, y, z);
					}
					// if int at coords equals 0 then enter a blank MahongTile as a place holder
					else {
						// or place an empty piece
						board[z][y][x] = new MahjongTile(false);
						;
					}
				}
			}
		}
	}

	
	//return mahjongtile at the specified coordinates
	public MahjongTile getTile(int x, int y, int z) {
		return board[z][y][x];
	}

	//checks the specified location to see if the mahjongtile there is clickable
	boolean isClickable(int x, int y, int z) {

		// check edge cases/the oddballs to the left,right and top

		if ((z == 0 && x == 1) && ((y == 3) || (y == 4))) {
			if (getTile(0, 4, 0).isTile())
				return false;
		} else if ((z == 0 && x == 12) && ((y == 3) || (y == 4))) {
			if (getTile(13, 4, 0).isTile())
				return false;
		} else if ((z == 0 && x == 13) && y == 4) {
			if (getTile(14, 4, 0).isTile())
				return false;
		}
		// if far right, it is clickable so return true
		else if ((z == 0 && x == 14) && y == 4) {
			return true;
		}
		// if far left, it is clickable so return true
		else if ((z == 0 && x == 0) && y == 4) {
			return true;
		}

		// check the 2nd layer from the top if the top tile is present
		else if (z == 3) {
			if (getTile(6, 3, 4).isTile())
				return false;
		}

		// check normal cases
		else {
			// check left and right neighbors
			// if both are active then return false.
			if (getTile(x - 1, y, z).isTile() & getTile(x + 1, y, z).isTile())
				return false;
			// if left and right neighbors don't both exist then verify the neighbor above
			else if (z != 4 && getTile(x, y, z + 1).isTile())
				return false;
		}
		return true;
	}

	//helper to see if undo can be completed
	public boolean hasUndo() {
		return !removedTiles.isEmpty();
	}

	//completes an undo by taking the last 2 from the removed list and placing them back on the board
	public void undo() {
		// get tiles back from list
		MahjongTile temp1 = removedTiles.remove(removedTiles.size() - 1);
		MahjongTile temp2 = removedTiles.remove(removedTiles.size() - 1);

		// return them to the board on board
		board[temp1.z][temp1.y][temp1.x] = temp1;
		board[temp2.z][temp2.y][temp2.x] = temp2;
		
		// set to visible
		temp1.t.setVisible(true);
		temp2.t.setVisible(true);
		
		// update tile count
		clearedTiles -= 2;
	}

	//gets tiles for the show removed tiles function in the MahjongBoardView
	public ArrayList getRemovedTiles() {
		ArrayList removedTilesCopy = new ArrayList();
		for (MahjongTile tc : removedTiles) {
			try {
				removedTilesCopy.add(tc.t.copy());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return removedTilesCopy;
	}
}