/*
 * TileContainer.java
 *
 * Contains a Tile and
 * tile meta information
 */

public class MahjongTile {
    public Tile t;
    private boolean isTile;
    int x, y, z, zOrder;

    public MahjongTile(Object t, int x, int y, int z){
            this.t = (Tile)t;
            isTile = true;
            this.x = x;
            this.y = y;
            this.z = z;
    }
    public MahjongTile(boolean b){
            isTile = b;
    }
    public void setZOrder(int zOrder){
        this.zOrder = zOrder;
    }
    public boolean isTile(){
            return isTile;
    }
    public Tile getTile(){
            return t;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getZ(){
        return z;
    }
}
