package at.htlVillach.noahArsic.spaceInvaders;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Keyinput extends KeyAdapter {
    private final Handler handler;
    private final Game game;
    private final boolean[] keys = new boolean[3];

    public Keyinput(Handler handler, Game game) {
        this.handler = handler;
        Arrays.fill(keys, false);
        this.game = game;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(-5);
                    keys[0] = true;
                }
                else if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(5);
                    keys[1] = true;
                }
                else if (key == KeyEvent.VK_SPACE && !game.getPlayerShotInFrame()) {
                    handler.addObject(new PlayerShot(tempObject.getX() + 64,
                            tempObject.getY() + 50, ID.PlayerShot, handler, game, false));
                    game.setPlayerShotInFrame(true);
                }
            }
        }
        if (key == KeyEvent.VK_ESCAPE) System.exit(0);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_A) keys[0] = false;
                else if (key == KeyEvent.VK_D) keys[1] = false;
                if (!keys[0] && !keys[1]) {
                    tempObject.setVelX(0);
                }
            }
        }
    }
}
