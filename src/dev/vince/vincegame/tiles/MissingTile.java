package dev.vince.vincegame.tiles;

import dev.vince.vincegame.gfx.Assets;

public class MissingTile extends Tile {
    public MissingTile(int id) {
        super(Assets.getInstance().missing, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
