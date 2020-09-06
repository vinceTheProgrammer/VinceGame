package dev.vince.vincegame.entities.creatures;

import dev.vince.vincegame.Handler;
import dev.vince.vincegame.entities.Entity;
import dev.vince.vincegame.gfx.Animation;
import dev.vince.vincegame.gfx.Assets;
import dev.vince.vincegame.inventory.Inventory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    // Animations
    private final Animation animIdle;
    private final Animation animRight;
    private final Animation animLeft;
    private final Animation animUp;
    private final Animation animDown;
    // Attacks
    private long lastAttackTimer;
    private final long attackCooldown = 300;
    private long attackTimer = attackCooldown;
    private Rectangle arc;
    // Inventory
    private final Inventory inventory;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 0;
        bounds.y = 38;
        bounds.width = 24;
        bounds.height = 20;

        // Animations
        animIdle = new Animation(500, Assets.getInstance().player_idle);
        animRight = new Animation(100, Assets.getInstance().player_right);
        animLeft = new Animation(100, Assets.getInstance().player_left);
        animUp = new Animation(100, Assets.getInstance().player_up);
        animDown = new Animation(100, Assets.getInstance().player_down);

        inventory = new Inventory(handler);

    }

    @Override
    public void tick() {

        // Animations
        animIdle.tick();
        animRight.tick();
        animLeft.tick();
        animUp.tick();
        animDown.tick();

        // Movements
        getInput();
        move();
        handler.getGame().getGameCamera().centerOnEntity(this);
        checkAttacks();

        inventory.tick();

    }

    private void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        if (attackTimer > System.currentTimeMillis() - 1000) {
            attackTimer = 300;
        }
        lastAttackTimer = System.currentTimeMillis();
        //System.out.println(attackTimer + " < " + attackCooldown);
        if(attackTimer < attackCooldown)
            return;

        if (inventory.isActive())
            return;

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();

        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        arc = ar;


        if (handler.getGame().getKeyManager().aUp) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
            System.out.println("Attacked!");
        } else if (handler.getGame().getKeyManager().aDown) {
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
            System.out.println("Attacked!");
        } else if (handler.getGame().getKeyManager().aLeft) {
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
            System.out.println("Attacked!");
        } else if (handler.getGame().getKeyManager().aRight) {
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
            System.out.println("Attacked!");
        } else {
                return;
        }

        attackTimer = 0;

        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;
            if(e.getCollisionBounds(0, 0).intersects(ar)) {
                e.hurt(10);
                return;
            }
        }
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (inventory.isActive())
            return;

        if(handler.getGame().getKeyManager().up)
            yMove = -speed;
        if(handler.getGame().getKeyManager().down)
            yMove = speed;
        if(handler.getGame().getKeyManager().left)
            xMove = -speed;
        if(handler.getGame().getKeyManager().right)
            xMove = speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGame().getGameCamera().getxOffset()), (int) (y - handler.getGame().getGameCamera().getyOffset()), width, height, null);

        if (arc != null) {
            g.setColor(Color.RED);
            g.fillRect((int) (arc.x - handler.getGame().getGameCamera().getxOffset()), (int) (arc.y - handler.getGame().getGameCamera().getyOffset()), arc.width, arc.height);
        }
    }

    public void postRender(Graphics g) {
        inventory.render(g);
    }

    @Override
    public void die() {
        System.out.println("You died.");
    }

    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            return animUp.getCurrentFrame();
        } else if (yMove > 0) {
            return animDown.getCurrentFrame();
        } else {
            return animIdle.getCurrentFrame();
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

}
