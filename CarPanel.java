package application;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 * This component draws and animates a car shape.
 */
public class CarPanel extends JComponent {
    private Car car1;
    private int x, y, delay;
    private CarQueue carQueue;
    private int direction;

    CarPanel(int x1, int y1, int d, CarQueue queue) {
        delay = d;
        x = x1;
        y = y1;
        car1 = new Car(x, y, this);
        carQueue = queue;
    }

    public void startAnimation() {
        class AnimationRunnable implements Runnable {
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        direction = carQueue.deleteQueue();

                        moveCar(); // Move based on direction
                        checkBoundary(); // Bounce if needed

                        repaint();
                        Thread.sleep(delay * 500);
                    }
                } catch (InterruptedException exception) {
                }
            }
        }

        Runnable r = new AnimationRunnable();
        Thread t = new Thread(r);
        t.start();
    }

    private void moveCar() {
        if (direction == 0) {
            y -= 10; // up
        } else if (direction == 1) {
            y += 10; // down
        } else if (direction == 2) {
            x += 10; // right
        } else if (direction == 3) {
            x -= 10; // left
        }
    }

    private void checkBoundary() {
        if (x < 0) {
            x = 0;
            if (direction == 3) direction = 2; // Hit left wall, go right
        }
        if (x > getWidth() - 60) {
            x = getWidth() - 60;
            if (direction == 2) direction = 3; // Hit right wall, go left
        }
        if (y < 0) {
            y = 0;
            if (direction == 0) direction = 1; // Hit top wall, go down
        }
        if (y > getHeight() - 30) {
            y = getHeight() - 30;
            if (direction == 1) direction = 0; // Hit bottom wall, go up
        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        car1.draw(g2, x, y);
    }
}
