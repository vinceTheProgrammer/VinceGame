package dev.vince.vincegame.states;

import dev.vince.vincegame.Game;
import dev.vince.vincegame.Handler;
import dev.vince.vincegame.gfx.Assets;
import dev.vince.vincegame.ui.ClickListener;
import dev.vince.vincegame.ui.UIImageButton;
import dev.vince.vincegame.ui.UIManager;

import java.awt.*;

public class SettingsState extends State {

    private UIManager uiManager;

    @Override
    protected void onOpen() {
        handler.getMouseManager().setUiManager(uiManager);
    }

    public SettingsState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);

        uiManager.addObject(new UIImageButton(0, handler.getGame().getHeight() - 35, 77, 35, Assets.getInstance().btn_back, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                State.setState(handler.getGame().menuState);
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
