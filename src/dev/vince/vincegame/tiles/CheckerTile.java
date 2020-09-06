package dev.vince.vincegame.tiles;

import dev.vince.vincegame.gfx.Assets;

public class CheckerTile extends Tile {
    public CheckerTile(int id) {
        super(Assets.getInstance().checkerPatternTile, id);
    }
}
