package at.htlVillach.noahArsic.spaceInvaders;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
    private final Game game;
    private final Handler handler;

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (mouseOver(mx, my, Game.WIDTH / 3 + 60, Game.HEIGHT / 2 - 50, 200, 75)) {
            game.setGameState(Game.STATE.Game);
            game.removeMouseListener(this);
            handler.addObject(new Player(425, 400, ID.Player, handler, game, false));
            for (int i = 1; i <= 10; i++) {
                handler.addObject(new PurpleAlien(50*i+200, 250, ID.PurpleAlien, handler, game, true));
                handler.addObject(new PurpleAlien(50*i+200, 220, ID.PurpleAlien, handler, game,true));
                handler.addObject(new GreenAlien(50*i+200, 190, ID.GreenAlien, handler, game,true));
                handler.addObject(new GreenAlien(50*i+200, 160, ID.GreenAlien, handler, game,true));
                handler.addObject(new YellowAlien(50*i+203, 130, ID.YellowAlien, handler, game,true));
            }
        }
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        return (mx > x && mx < x + width) && (my > y && my < y + height);
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        g.drawString("Space Invaders", Game.WIDTH / 4 + 25, 125);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawRect(Game.WIDTH / 3 + 60, Game.HEIGHT / 2 - 50, 200, 75);
        g.drawString("Play", Game.WIDTH / 2 - 40, 275);
    }
}
