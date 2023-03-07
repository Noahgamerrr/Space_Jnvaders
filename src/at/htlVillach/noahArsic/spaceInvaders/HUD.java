package at.htlVillach.noahArsic.spaceInvaders;

import java.awt.*;

public class HUD {
    private int frames;
    private int score;
    private int lives = 3;

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("FPS: " +frames, 10, 20);
        g.drawString("Score: " +score, 10, 35);
        g.drawString("Lives: " +lives, 10, 50);
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }
}
