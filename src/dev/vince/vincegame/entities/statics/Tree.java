package dev.vince.vincegame.entities.statics;

import dev.vince.vincegame.Handler;
import dev.vince.vincegame.gfx.Assets;
import dev.vince.vincegame.items.Item;
import dev.vince.vincegame.tiles.Tile;

import java.awt.*;

public class Tree extends StaticEntity {

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH * 2, Tile.TILE_HEIGHT * 2);

                bounds.y = (int) (height * 0.46875f);
        bounds.height = (int) (height * 0.53125);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.getInstance().tree, (int) (x - handler.getGame().getGameCamera().getxOffset()), (int) (y - handler.getGame().getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x, (int) y));
    }
}
