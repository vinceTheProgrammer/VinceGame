package dev.vince.vincegame.inventory;

import dev.vince.vincegame.Handler;
import dev.vince.vincegame.gfx.Assets;
import dev.vince.vincegame.gfx.Text;
import dev.vince.vincegame.items.Item;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Inventory {

    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;

    private int invX, invY, invWidth, invHeight, invListCenterX, invListCenterY, invListSpacing, invImgX, invImgY, invImgWidth, invImgHeight, invCountX, invCountY;
    private int selectedItem = 0;


    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();

        invWidth =  (int) (handler.getGame().getWidth() * 0.6);
        invHeight = (int) (handler.getGame().getHeight() * 0.8);
        invX = (handler.getGame().getWidth() / 2) - (invWidth / 2);
        invY = (handler.getGame().getHeight() / 2) - (invHeight / 2);
        invListCenterX = invX + (int) (invWidth * 0.33);
        invListCenterY = invY + invHeight / 2;
        invListSpacing = (int) (invHeight * 0.0833);
        invImgWidth = (int) (invWidth * 0.125);
        invImgHeight = (int) (invHeight * 0.166);
        invImgX = (int) (invWidth * 1.09);
        invImgY = (int) (invHeight * 0.22);
        invCountX = (int) (invWidth * 1.155);
        // or 0.8164
        invCountY = (int) (invHeight * 0.44);
    }

    public void init() {
    }

    public void tick() {
        if(handler.getGame().getKeyManager().keyJustPressed(KeyEvent.VK_E))
            active = !active;
        if (!active)
            return;

        if (handler.getGame().getKeyManager().keyJustPressed(KeyEvent.VK_W) || handler.getGame().getKeyManager().keyJustPressed(KeyEvent.VK_UP))
            selectedItem--;
        if (handler.getGame().getKeyManager().keyJustPressed(KeyEvent.VK_S) || handler.getGame().getKeyManager().keyJustPressed(KeyEvent.VK_DOWN))
            selectedItem++;

        if (selectedItem < 0)
            selectedItem = inventoryItems.size() - 1;
        else if (selectedItem >= inventoryItems.size())
            selectedItem = 0;
    }

    public void render(Graphics g) {
        if (!active)
            return;

        g.drawImage(Assets.getInstance().inventoryScreen, invX, invY, invWidth, invHeight, null);

        int len = inventoryItems.size();
        if (len == 0)
            return;

        for (int i = -5;i < 6;i++) {
            if (selectedItem + i < 0 || selectedItem + i >= len)
                continue;
            if (i == 0) {
                Text.drawString(g, "> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX, invListCenterY + i * invListSpacing, true, Color.YELLOW, Assets.getInstance().font28);
            } else {
                Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX, invListCenterY + i * invListSpacing, true, Color.WHITE, Assets.getInstance().font28);
            }

            Item item = inventoryItems.get(selectedItem);
            g.drawImage(item.getTexture(), invImgX, invImgY, invImgWidth, invImgHeight, null);
            Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.WHITE, Assets.getInstance().font28);

        }
    }

    // INVENTORY

    public void addItem(Item item) {
        for (Item i : inventoryItems) {
            if (i.getId() == item.getId()) {
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }

    // GETTERS & SETTERS

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isActive() {
        return active;
    }
}
