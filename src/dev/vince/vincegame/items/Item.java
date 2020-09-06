package dev.vince.vincegame.items;

import dev.vince.vincegame.Handler;
import dev.vince.vincegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

    // Handler

    public static Item[] items = new Item[256];
    public static Item woodItem = new Item(Assets.getInstance().logs, "Wood", 0);
    public static Item rockItem = new Item(Assets.getInstance().rocks, "Rock", 1);

    // Class

    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;

    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;

    protected Rectangle bounds;


    protected int x, y, count;
    protected boolean pickedUp = false;

    public Item(BufferedImage texture, String name, int id) {
        this.texture = texture;
        this.name = name;
        this.id = id;
        this.count = 1;

        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);

        items[id] = this;
    }

    public void tick() {
        if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)) {
            pickedUp = true;
            handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
        }
    }

    public void render(Graphics g) {
        if (handler == null)
            return;
        render(g, (int) (x - handler.getGame().getGameCamera().getxOffset()), (int) (y - handler.getGame().getGameCamera().getyOffset()));
    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }

    // Debug method
    public Item createNew(int count) {
        Item i = new Item(texture, name, id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }

    public Item createNew(int x, int y) {
        Item i = new Item(texture, name, id);
        i.setPosition(x, y);
        return i;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    // GETTERS & SETTERS

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
}
