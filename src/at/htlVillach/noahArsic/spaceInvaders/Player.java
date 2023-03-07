package at.htlVillach.noahArsic.spaceInvaders;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends GameObject{
    private final BufferedImage image;
    private final Handler handler;
    private final Game game;

    public Player(int x, int y, ID id, Handler handler, Game game, boolean isEnemy) {
        super(x, y, id, isEnemy);
        this.handler = handler;
        this.game = game;
        BufferedImageLoader loader = new BufferedImageLoader();
        image = loader.loadImage("Sprites/Player.gif");
    }

    public void tick() {
        x += velX;
        x = clamp(Game.WIDTH - 113, -35);
        shotPlayer();
    }

    public void render(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    private void shotPlayer() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.EnemyShot && getBounds().intersects(tempObject.getBounds())) {
                x = 425;
                game.getHud().setLives(game.getHud().getLives() - 1);
                handler.removeObject(tempObject);
                if (game.getHud().getLives() == 0) {
                    removeObjects();
                    game.setGameState(Game.STATE.GameOver);
                    game.addMouseListener(game.getGameOver());
                }
            }
        }
    }

    private void removeObjects() {
        for (int i = handler.object.size() - 1; i >= 0; i--) {
            handler.removeObject(handler.object.get(i));
        }
    }

    private int clamp(int max, int min) {
        if (this.x >= max) return max;
        else return Math.max(this.x, min);
    }
}
