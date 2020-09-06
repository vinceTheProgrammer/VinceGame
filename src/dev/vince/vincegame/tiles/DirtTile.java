package dev.vince.vincegame.tiles;

import dev.vince.vincegame.gfx.Assets;

public class DirtTile extends Tile {
    public DirtTile(int id) {
        super(Assets.getInstance().dirt, id);
    }
}
