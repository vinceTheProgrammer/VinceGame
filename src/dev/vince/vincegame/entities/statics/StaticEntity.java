package dev.vince.vincegame.entities.statics;

import dev.vince.vincegame.Handler;
import dev.vince.vincegame.entities.Entity;

public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }

}
