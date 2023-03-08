package at.htlVillach.noahArsic.spaceInvaders;

import java.awt.*;

public abstract class GameObject {
    protected int x, y;
    protected ID id;
    protected int velX;
    protected boolean isEnemy;

    public GameObject(int x, int y, ID id, boolean isEnemy) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.isEnemy = isEnemy;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public ID getId() {
        return this.id;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isEnemy() {
        return isEnemy;
    }
}
