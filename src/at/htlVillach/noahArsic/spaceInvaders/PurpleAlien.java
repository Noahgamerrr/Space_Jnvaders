package at.htlVillach.noahArsic.spaceInvaders;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PurpleAlien extends Alien {
    private BufferedImage image;
    BufferedImageLoader loader = new BufferedImageLoader();

    public PurpleAlien(int x, int y, ID id, Handler handler, Game game, boolean isEnemy) {
        super(x, y, id, handler, game, isEnemy);
        image = loader.loadImage("Sprites/Alien0_0.gif");
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    @Override
    protected void changeImage() {
        if (firstImage) image = loader.loadImage("Sprites/Alien0_0.gif");
        else image = loader.loadImage("Sprites/Alien0_1.gif");
    }

    @Override
    protected void alienShoots() {
        handler.addObject(new EnemyShot(x + (image.getWidth() / 2), y + image.getHeight() + 2, ID.EnemyShot, handler, game, false));
    }

    @Override
    protected void addToScore() {
        game.getHud().setScore(game.getHud().getScore() + 200);
    }
}
