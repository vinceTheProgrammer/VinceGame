package dev.vince.vincegame.states;

import dev.vince.vincegame.Game;
import dev.vince.vincegame.Handler;
import dev.vince.vincegame.entities.statics.StaticEntity;
import dev.vince.vincegame.gfx.Assets;
import dev.vince.vincegame.ui.ClickListener;
import dev.vince.vincegame.ui.UIImageButton;
import dev.vince.vincegame.ui.UIManager;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;


    @Override
    protected void onOpen() {
        handler.getMouseManager().setUiManager(uiManager);
    }

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);

        uiManager.addObject(new UIImageButton(handler.getGame().getWidth() / 2 - 100, handler.getGame().getHeight() / 2 - 24, 200, 48, Assets.getInstance().btn_play, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));

        uiManager.addObject(new UIImageButton(handler.getGame().getWidth() / 2 - 100, handler.getGame().getHeight() / 2 + 48, 200, 48, Assets.getInstance().btn_settings, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                State.setState(handler.getGame().settingsState);
            }
        }));

        uiManager.addObject(new UIImageButton(0, 0, 28, 27, Assets.getInstance().btn_exit, new ClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}
