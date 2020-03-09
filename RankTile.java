
public abstract class RankTile extends Tile
{
	protected int rank;
	
	public void RanktTile(int rank)
	{
		this.rank = rank;
	}
	
	public boolean matches(Tile other)
	{
		if(super.matches(other) && this.rank == ((RankTile)other).rank)
			return true;
		else
			return false;
	}
}