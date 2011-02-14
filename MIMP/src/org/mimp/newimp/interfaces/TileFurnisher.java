package org.mimp.newimp.interfaces;

import org.mimp.newimp.Tile;

public interface TileFurnisher {

    /**
     * Gets a tile from the furnisher
     * @param x 
     *      The x value number of the tile
     * @param y 
     *      The y value number of the tile
     * @param z 
     *      The zoom value 
     * @return
     *      The tile corresponding to the coordinates
     */
    public Tile getTile(int x, int y, int z);

    /**
     * Add a tile to the furnisher
     * @param tile
     */
    public void addTile(Tile tile);
    
    /**
     * Return the number of tiles handled by the furnisher
     * @return
     */
    public int getTileCount();
}
