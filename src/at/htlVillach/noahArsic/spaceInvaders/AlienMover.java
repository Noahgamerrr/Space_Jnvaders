package at.htlVillach.noahArsic.spaceInvaders;

public class AlienMover implements Runnable{
    private final Handler handler;

    public AlienMover(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
        }
    }

    private void tick() {
        handler.moveAlien();
    }
}
