import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TileDeck
{
	private	ArrayList<Tile>	deck = new ArrayList<>(144);
	
	/**
	 * This constructor method fills the deck with all the cards needed to play a game.
	 */
	public TileDeck()
	{
		for (int i = 0; i < 4; i++)
		{
			// CharacterTiles
			for (char c = '1'; c <= '9'; c++)
				deck.add(new CharacterTile(c));
			deck.add(new CharacterTile('N'));
			deck.add(new CharacterTile('E'));
			deck.add(new CharacterTile('W'));
			deck.add(new CharacterTile('S'));
			deck.add(new CharacterTile('C'));
			deck.add(new CharacterTile('F'));

			// CircleTiles
			for (int j = 1; j < 10; j++)
				deck.add(new CircleTile(j));

			// BambooTiles
			for (int j = 2; j < 10; j++)
				deck.add(new BambooTile(j));

			// Special Tiles
			deck.add(new WhiteDragonTile());
			deck.add(new Bamboo1Tile());
		}

		// FlowerTiles
		deck.add(new FlowerTile("Chrysanthemum"));
		deck.add(new FlowerTile("Orchid"));
		deck.add(new FlowerTile("Plum"));
		deck.add(new FlowerTile("Bamboo"));

		// SeasonTiles
		deck.add(new SeasonTile("Spring"));
		deck.add(new SeasonTile("Summer"));
		deck.add(new SeasonTile("Fall"));
		deck.add(new SeasonTile("Winter"));
	}

	/**
	 * Shuffles the deck of tiles randomly
	 */
	public void shuffle()
	{
		Collections.shuffle(this.deck, new Random(System.currentTimeMillis()));
	}

	/**
	 * Shuffles the deck of tiles based on "gameNumber." Doing this will allow a player to replay a game represented by a game number
	 * @param game
	 */
	public void shuffle(long game)
	{
		Collections.shuffle(this.deck, new Random(game));
	}

	/**
	 * Removes a tile from the deck and returns it; returns null if the deck is empty.
	 * Tiles may be conveniently removed from either the beginning or the end of the deck;
	 * it's more efficient to remove them from the end, but this operation takes place infrequently, so speed is of little concern.
	 * @return Tile from the deck
	 */
	public Tile deal()
	{
		if(this.deck.size() != 0)	
			return this.deck.remove(this.deck.size()-1);
		else
			return null;
	}
}