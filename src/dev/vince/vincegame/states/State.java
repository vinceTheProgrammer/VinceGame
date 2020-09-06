package dev.vince.vincegame.states;

import dev.vince.vincegame.Game;
import dev.vince.vincegame.Handler;

import java.awt.Graphics;

public abstract class State {

    private static State currentState = null;

    public static void setState(State state) {
        currentState = state;
        state.onOpen();
    }

    protected abstract void onOpen();

    public static State getState() {
        return  currentState;
    }

    // CLASS

    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
