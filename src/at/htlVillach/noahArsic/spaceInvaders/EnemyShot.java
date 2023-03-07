package at.htlVillach.noahArsic.spaceInvaders;

import java.awt.*;

public class EnemyShot extends GameObject{
    private static final int MOVE_Y = 10;
    private final Handler handler;
    private final Game game;

    public EnemyShot(int x, int y, ID id, Handler handler, Game game, boolean isEnemy) {
        super(x, y, id, isEnemy);
        this.handler = handler;
        this.game = game;
    }

    @Override
    public void tick() {
        this.y += MOVE_Y;
        if (this.y > game.getHeight() + 20) {
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(this.x, this.y, 2, 10);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y,2, 10);
    }
}
