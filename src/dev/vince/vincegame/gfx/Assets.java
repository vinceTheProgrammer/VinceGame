package dev.vince.vincegame.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    private static Assets firstInstance = null;

    private Assets() {}

    public static Assets getInstance() {
        if (firstInstance == null) {
            firstInstance = new Assets();
        }
        return firstInstance;
    }

    private final int width = 32, height = 32;
    private final int charwidth = 24, charheight = 58;

    public Font font28;


    // TODO: Figure out how to put all these assets in a HashMap

    public BufferedImage dirt, grass, grassTwo, grassThree, grassFour, missing;
    public BufferedImage checkerPatternTile;
    public BufferedImage bush, bushDark, bushFall, bushDead, grassPatch, grassPatchTwo, stump, stumpBig, flowerpatch, tree, treeDark, treeFall, treeDead, rock;
    public BufferedImage grave, graveWood, hole, dirtPile, board, sign;
    public BufferedImage[] player_idle, player_right, player_left, player_up, player_down;
    public BufferedImage[] btn_play, btn_settings, btn_exit, btn_back;
    public BufferedImage logs, rocks;
    public BufferedImage inventoryScreen;

    public void init() {
        font28 = FontLoader.loadFont("res/fonts/INVASION2000.ttf", 28);

        SpriteSheet spriteSheet = new SpriteSheet(ImageLoader.loadImage("/textures/spritesheet.png"));
        SpriteSheet idlesheet = new SpriteSheet(ImageLoader.loadImage("/textures/vince_idle.png"));
        SpriteSheet rightsheet = new SpriteSheet(ImageLoader.loadImage("/textures/vince_walk_right.png"));
        SpriteSheet leftsheet = new SpriteSheet(ImageLoader.loadImage("/textures/vince_walk_left.png"));
        SpriteSheet ui = new SpriteSheet(ImageLoader.loadImage("/textures/ui.png"));
        SpriteSheet drops = new SpriteSheet(ImageLoader.loadImage("/textures/icons.png"));
        inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");

        // UI
        btn_play = new BufferedImage[2];
        btn_settings = new BufferedImage[2];
        btn_exit = new BufferedImage[2];
        btn_back = new BufferedImage[2];

        btn_play[0] = ui.crop(0, 35, 200, 48);
        btn_play[1] = ui.crop(0, 83, 200, 48);

        btn_settings[0] = ui.crop(0, 131, 200, 48);
        btn_settings[1] = ui.crop(210, 0, 200, 48);

        btn_exit[0] = ui.crop(154, 0, 28, 27);
        btn_exit[1] = ui.crop(182, 0, 28, 27);

        btn_back[0] = ui.crop(0, 0, 77, 35);
        btn_back[1] = ui.crop(77, 0, 77, 35);



        // PLAYER
        // Animations

        player_idle = new BufferedImage[3];
        player_right = new BufferedImage[6];
        player_left = new BufferedImage[6];
        player_up = new BufferedImage[6];
        player_down = new BufferedImage[6];

        player_idle[0] = idlesheet.crop(0,0, charwidth, charheight);
        player_idle[1] = idlesheet.crop(charwidth + 1,0, charwidth, charheight);
        player_idle[2] = idlesheet.crop(charwidth * 2 + 2,0, charwidth, charheight);

        player_right[0] = rightsheet.crop(0, 0, charwidth, charheight);
        player_right[1] = rightsheet.crop(charwidth + 1, 0, charwidth, charheight);
        player_right[2] = rightsheet.crop(charwidth * 2 + 2, 0, charwidth, charheight);
        player_right[3] = rightsheet.crop(0, charheight + 1, charwidth, charheight);
        player_right[4] = rightsheet.crop(charwidth + 1, charheight + 1, charwidth, charheight);
        player_right[5] = rightsheet.crop(charwidth * 2 + 2, charheight + 1, charwidth, charheight);

        player_left[0] = leftsheet.crop(charwidth * 2 + 2, 0, charwidth, charheight);
        player_left[1] = leftsheet.crop(charwidth + 1, 0, charwidth, charheight);
        player_left[2] = leftsheet.crop(0, 0, charwidth, charheight);
        player_left[3] = leftsheet.crop(charwidth * 2 + 2, charheight + 1, charwidth, charheight);
        player_left[4] = leftsheet.crop(charwidth + 1, charheight + 1, charwidth, charheight);
        player_left[5] = leftsheet.crop(0, charheight + 1, charwidth, charheight);

        player_up[0] = rightsheet.crop(0, 0, charwidth, charheight);
        player_up[1] = rightsheet.crop(charwidth + 1, 0, charwidth, charheight);
        player_up[2] = rightsheet.crop(charwidth * 2 + 2, 0, charwidth, charheight);
        player_up[3] = rightsheet.crop(0, charheight + 1, charwidth, charheight);
        player_up[4] = rightsheet.crop(charwidth + 1, charheight + 1, charwidth, charheight);
        player_up[5] = rightsheet.crop(charwidth * 2 + 2, charheight + 1, charwidth, charheight);

        player_down[0] = rightsheet.crop(0, 0, charwidth, charheight);
        player_down[1] = rightsheet.crop(charwidth + 1, 0, charwidth, charheight);
        player_down[2] = rightsheet.crop(charwidth * 2 + 2, 0, charwidth, charheight);
        player_down[3] = rightsheet.crop(0, charheight + 1, charwidth, charheight);
        player_down[4] = rightsheet.crop(charwidth + 1, charheight + 1, charwidth, charheight);
        player_down[5] = rightsheet.crop(charwidth * 2 + 2, charheight + 1, charwidth, charheight);

        // TILES
        // Nature
        grass = crop(spriteSheet, 1, 1, 0, 0);
        grassTwo = crop(spriteSheet, 2, 1, 0, 0);
        grassThree = crop(spriteSheet, 3, 1, 0, 0);
        grassFour = crop(spriteSheet, 4, 1, 0, 0);
        dirt = crop(spriteSheet, 6, 1, 0, 0);

        // Other
        checkerPatternTile = crop(spriteSheet,5,37, 0, 0);

        // ENTITIES
        // Nature

        bush = crop(spriteSheet,1,6, 0, 0);
        bushDark = crop(spriteSheet,2,6, 0, 0);
        bushFall = crop(spriteSheet,3,6, 0, 0);
        bushDead = crop(spriteSheet,4,6, 0, 0);
        flowerpatch = crop(spriteSheet,5,7, 0, 0);
        grassPatch = crop(spriteSheet,1,7, 0, 0);
        grassPatchTwo = crop(spriteSheet,2,7, 0, 0);
        stump = crop(spriteSheet,5,6, 0, 0);
        stumpBig = crop(spriteSheet,6,6, 0, 0);
        tree = crop(spriteSheet,1,2, 2, 2);
        treeDark = crop(spriteSheet,3,2, 2, 2);
        treeFall = crop(spriteSheet,5,2, 2, 2);
        treeDead = crop(spriteSheet,7,2, 2, 2);
        rock = ImageLoader.loadImage("/textures/extras.png");

        // Other

        grave = crop(spriteSheet,4,9, 0, 0);
        graveWood = crop(spriteSheet,5,9, 0, 0);
        hole = crop(spriteSheet,3,9, 0, 0);
        dirtPile = crop(spriteSheet,2,9, 0, 0);
        board = crop(spriteSheet,7,29, 2, 2);
        sign = crop(spriteSheet,4,30, 0, 0);

        // DROPS

        // Nature

        logs = crop(drops, 1, 18, 0, 0);
        rocks = crop(drops, 2, 18, 0, 0);

        // MISSING TILE

        missing = ImageLoader.loadImage("/textures/missing.png");
    }

    private BufferedImage crop(SpriteSheet spriteSheet, int column, int row, int spriteWidth, int spriteHeight) {
        if (spriteWidth == 0) {
            spriteWidth = 32;
        } else if (spriteWidth < 9 && spriteWidth > -9) {
            spriteWidth = width * Math.abs(spriteWidth);
        } else {
            spriteWidth = Math.abs(spriteWidth);
        }
        if (spriteHeight == 0) {
            spriteHeight = 32;
        } else if (spriteHeight < 133 && spriteHeight > -133) {
            spriteHeight = height * Math.abs(spriteHeight);
        } else {
            spriteHeight = Math.abs(spriteHeight);
        }
        return spriteSheet.crop(width * (column - 1), height * (row - 1), spriteWidth, spriteHeight);
    }

}
