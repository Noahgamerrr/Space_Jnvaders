package at.htlVillach.noahArsic.spaceInvaders;

import java.awt.*;
import java.util.LinkedList;

public class Handler{
    LinkedList<GameObject> object = new LinkedList<>();

    public void moveAlien() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            if (tempObject.isEnemy()) {
                Alien tempAlien = (Alien) tempObject;
                tempAlien.makeMove();
            }
        }
    }

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }
}
