package dev.vince.vincegame.states;

import dev.vince.vincegame.Game;
import dev.vince.vincegame.Handler;
import dev.vince.vincegame.entities.creatures.Player;
import dev.vince.vincegame.entities.statics.Tree;
import dev.vince.vincegame.gfx.Assets;
import dev.vince.vincegame.tiles.Tile;
import dev.vince.vincegame.worlds.World;

import java.awt.*;

public class GameState extends State {

    private World world;

    @Override
    protected void onOpen() {

    }

    public  GameState(Handler handler) {
        super(handler);
        world = new World(handler, "res/worlds/coolworld.lvl");
        handler.setWorld(world);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
}
