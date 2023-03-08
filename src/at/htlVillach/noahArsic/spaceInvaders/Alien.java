package at.htlVillach.noahArsic.spaceInvaders;

import java.util.Random;

public abstract class Alien extends GameObject {
    protected static int timeBetweenMovement = 1000000000;
    protected static long timeBetweenShot = 2133333333;
    protected static int move = -32;
    protected static boolean movedDownwards = false;
    protected static long lastTime = System.nanoTime();
    protected static long shotTime = System.nanoTime();
    protected static Handler handler;
    protected static Game game;
    protected static boolean firstImage = true;
    protected static int enemyCount = 50;

    protected abstract void changeImage();
    protected abstract void alienShoots();
    protected abstract void addToScore();

    public Alien(int x, int y, ID id, Handler handler1, Game game, boolean isEnemy) {
        super(x, y, id, isEnemy);
        handler = handler1;
        Alien.game = game;
    }

    @Override
    public void tick() {
        shotAlien();
        chooseShootingAlien();
    }

    public void makeMove() {
        GameObject lastAlien = lastAlienOfHandler();
        if (System.nanoTime() > lastTime + timeBetweenMovement) {
            lastTime = System.nanoTime();
            firstImage = !firstImage;
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                if (tempObject.isEnemy) {
                    Alien nextAlien = (Alien) tempObject;
                    if ((tempObject.getX() < 50 && move < 0) || (lastAlien.getX() > 900 && move > 0)) {
                        for (int j = 0; j < handler.object.size(); j++) {
                            GameObject tempObject2 = handler.object.get(j);
                            if (tempObject2.isEnemy) {
                                tempObject2.setY(tempObject2.getY() + 32);
                            }
                        }
                        move *= -1;
                        movedDownwards = true;
                    } else if (!movedDownwards) tempObject.setX(tempObject.getX() + move);
                    nextAlien.changeImage();
                }
            }
        }
        movedDownwards = false;
    }

    private void respawnEnemies() {
        for (int i = handler.object.size() - 1; i > 0; i--) {
            GameObject tempObject = handler.object.get(i);
            handler.removeObject(tempObject);
        }
        for (int i = 1; i <= 10; i++) {
            handler.addObject(new PurpleAlien(50*i+200, 250, ID.PurpleAlien, handler, game, true));
            handler.addObject(new PurpleAlien(50*i+200, 220, ID.PurpleAlien, handler, game,true));
            handler.addObject(new GreenAlien(50*i+200, 190, ID.GreenAlien, handler, game,true));
            handler.addObject(new GreenAlien(50*i+200, 160, ID.GreenAlien, handler, game,true));
            handler.addObject(new YellowAlien(50*i+203, 130, ID.YellowAlien, handler, game,true));
        }
        enemyCount = 50;
    }

    private void chooseShootingAlien() {
        if (System.nanoTime() > shotTime + timeBetweenShot) {
            shotTime = System.nanoTime();
            Random r = new Random();
            int ranNum = r.nextInt(enemyCount);
            Alien shootingAlien = (Alien) handler.object.get(ranNum + 1);
            shootingAlien.alienShoots();
        }
    }

    private void shotAlien() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.PlayerShot && getBounds().intersects(tempObject.getBounds())) {
                addToScore();
                enemyCount--;
                handler.removeObject(this);
                handler.removeObject(tempObject);
                game.setPlayerShotInFrame(false);
                if (enemyCount == 0) {
                    respawnEnemies();
                }
            }
        }
    }

    private GameObject lastAlienOfHandler() {
        int i = handler.object.size() - 1;
        while (!handler.object.get(i).isEnemy) {
            i--;
        }
        return handler.object.get(i);
    }

    public static void setEnemyCount(int enemyCount) {
        Alien.enemyCount = enemyCount;
    }
}
