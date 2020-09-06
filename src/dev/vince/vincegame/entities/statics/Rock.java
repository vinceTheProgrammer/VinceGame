package dev.vince.vincegame.entities.statics;

import dev.vince.vincegame.Handler;
import dev.vince.vincegame.gfx.Assets;
import dev.vince.vincegame.items.Item;
import dev.vince.vincegame.tiles.Tile;

import java.awt.*;

public class Rock extends StaticEntity {

    public Rock(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH * 2, Tile.TILE_HEIGHT * 2);

        bounds.x = (int) (height * 0.15625);
        bounds.y = (int) (height * 0.65625);
        bounds.width = (int) (height * 0.6875);
        bounds.height = (int) (height * 0.25);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.getInstance().rock, (int) (x - handler.getGame().getGameCamera().getxOffset()), (int) (y - handler.getGame().getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int) x, (int) y));
    }
}
