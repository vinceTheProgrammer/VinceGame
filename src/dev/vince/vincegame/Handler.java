package dev.vince.vincegame;

import dev.vince.vincegame.gfx.GameCamera;
import dev.vince.vincegame.input.KeyManager;
import dev.vince.vincegame.input.MouseManager;
import dev.vince.vincegame.worlds.World;

public class Handler {

    private Game game;
    private World world;

    public Handler(Game game) {
        this.game = game;
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
