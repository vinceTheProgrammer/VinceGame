package dev.vince.vincegame;

import dev.vince.vincegame.display.Display;
import dev.vince.vincegame.gfx.Assets;
import dev.vince.vincegame.gfx.GameCamera;
import dev.vince.vincegame.gfx.Text;
import dev.vince.vincegame.input.KeyManager;
import dev.vince.vincegame.input.MouseManager;
import dev.vince.vincegame.states.GameState;
import dev.vince.vincegame.states.MenuState;
import dev.vince.vincegame.states.SettingsState;
import dev.vince.vincegame.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game {

    private Display display;
    private int width, height;
    public String title;

    private boolean running = false;

    private BufferStrategy bs;
    private Graphics g;

    // States
    public State gameState;
    public State menuState;
    public State settingsState;

    // Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    // Camera
    private GameCamera gameCamera;

    // Handler
    private Handler handler;

    private int fpsMeasure;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    private void init() {
        initDisplay();
        Assets.getInstance().init();
        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);
        initStates();
    }

    private void initDisplay() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
    }

    private void initStates() {
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        settingsState = new SettingsState(handler);
        State.setState(menuState);
    }

    private void tick() {
        keyManager.tick();

        if(State.getState() != null)
            State.getState().tick();
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        // Clear Screen
        g.clearRect(0, 0, width, height);
        // Draw here
        g.fillRect(0, 0, width, height);

        if(State.getState() != null)
            State.getState().render(g);

        Text.drawString(g, Integer.toString(fpsMeasure), (int) (width * .0078125), (int) (height * .9722), false, Color.WHITE, Assets.getInstance().font28);

        // End drawing
        bs.show();
        g.dispose();
    }

    // @Override
    public void run() {
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();

        long timer = 0;
        int ticks = 0;

        while(running) {

            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000) {
                // System.out.println("Ticks and Frames: " + ticks);
                fpsMeasure = ticks;
                ticks = 0;
                timer = 0;
            }
        }

        System.exit(0);
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public synchronized void start() {
        if(running)
            return;
        running = true;
        run();
    }
}
