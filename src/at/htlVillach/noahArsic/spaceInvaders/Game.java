package at.htlVillach.noahArsic.spaceInvaders;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1000, HEIGHT = (WIDTH / 16) * 9;
    private Thread thread;
    private Thread thread2;
    private boolean running = false;
    private boolean playerShotInFrame = false;
    private final Handler handler;
    private final HUD hud;
    private STATE gameState = STATE.Menu;
    private final Menu menu;
    private final GameOver gameOver;

    public enum STATE {
        Menu,
        Game,
        GameOver
    }

    public Game() {
        handler = new Handler();
        menu = new Menu(this, handler);
        gameOver = new GameOver(this, handler);
        hud = new HUD();
        this.addKeyListener(new Keyinput(handler, this));
        this.addMouseListener(menu);
        new Window(WIDTH, HEIGHT, "Space Invaders", this);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread2 = new Thread(new AlienMover(this.handler));
        thread.start();
        thread2.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            thread2.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        double maxFPS = 60.0;
        long renderLastTime = System.nanoTime();
        double renderNs = 1000000000 / maxFPS;
        double renderDelta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            now = System.nanoTime();
            renderDelta += (now - renderLastTime) / renderNs;
            renderLastTime = now;
            while(running && renderDelta >= 1){
                render();
                frames++;
                renderDelta--;
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                hud.setFrames(frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        handler.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        handler.render(g);
        if (gameState == STATE.Menu) menu.render(g);
        else if (gameState == STATE.Game) hud.render(g);
        else gameOver.render(g);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        // write your code here
        new Game();
    }

    public void setPlayerShotInFrame(boolean playerShotInFrame) {
        this.playerShotInFrame = playerShotInFrame;
    }

    public boolean getPlayerShotInFrame() {
        return this.playerShotInFrame;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public HUD getHud() {
        return hud;
    }

    public void setGameState(STATE gameState) {
        this.gameState = gameState;
    }

    public GameOver getGameOver() {
        return this.gameOver;
    }
}
