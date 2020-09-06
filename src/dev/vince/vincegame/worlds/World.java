package dev.vince.vincegame.worlds;

import dev.vince.vincegame.Handler;
import dev.vince.vincegame.entities.EntityManager;
import dev.vince.vincegame.entities.creatures.Player;
import dev.vince.vincegame.entities.statics.Rock;
import dev.vince.vincegame.entities.statics.Tree;
import dev.vince.vincegame.items.ItemManager;
import dev.vince.vincegame.tiles.Tile;
import dev.vince.vincegame.utils.Utils;

import java.awt.*;

public class World {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;

    // Entities
    private EntityManager entityManager;
    // Items
    private ItemManager itemManager;

    public World(Handler handler, String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));
        itemManager = new ItemManager(handler);
        entityManager.addEntity(new Tree(handler,500, 500));
        entityManager.addEntity(new Tree(handler,500, 600));
        entityManager.addEntity(new Tree(handler,500, 700));
        entityManager.addEntity(new Tree(handler,400, 700));
        entityManager.addEntity(new Tree(handler,500, 800));
        entityManager.addEntity(new Rock(handler, 300, 700));
        entityManager.addEntity(new Rock(handler, 350, 600));

        loadWorld(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    public void tick() {
        itemManager.tick();
        entityManager.tick();
    }

    public void render(Graphics g) {
        int xStart = (int )Math.max(0, handler.getGame().getGameCamera().getxOffset() / Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(width, (handler.getGame().getGameCamera().getxOffset() + handler.getGame().getWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int )Math.max(0, handler.getGame().getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGame().getGameCamera().getyOffset() + handler.getGame().getHeight()) / Tile.TILE_HEIGHT + 1);

        for(int y = yStart;y < yEnd;y++) {
            for(int x= xStart;x < xEnd;x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGame().getGameCamera().getxOffset()), (int) (y * Tile.TILE_HEIGHT - handler.getGame().getGameCamera().getyOffset()));
            }
        }
        // Items
        itemManager.render(g);
        // Entities
        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {

        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.missingTile;

        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
            return Tile.missingTile;
        return t;
    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");

        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]) * Tile.TILE_WIDTH - Tile.TILE_WIDTH;
        spawnY = Utils.parseInt(tokens[3]) * Tile.TILE_HEIGHT - (Tile.TILE_HEIGHT * 2);

        tiles = new int[width][height];
        for(int y = 0;y < height;y++) {
            for(int x = 0;x < width;x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
}
