package dev.vince.vincegame.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    // STATIC

    public static Tile[] tiles = new Tile[256];
    public static Tile missingTile = new MissingTile(0);
    public static Tile grassTile = new GrassTile(4);
    public static Tile grassTwoTile = new GrassTwoTile(2);
    public static Tile grassThreeTile = new GrassThreeTile(3);
    public static Tile grassFourTile = new GrassFourTile(1);
    public static Tile dirtTile = new DirtTile(5);
    public static Tile checkerTile = new CheckerTile(6);


    // CLASS

    public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public boolean isSolid() {
        return false;
    }

    public int getId() {
        return id;
    }
}
