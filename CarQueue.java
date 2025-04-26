package application;

/**
 * Title: Car Animation with Multithreading and Queue Management
 * Lab 9
 * Author: G. Araya
 * Date: April 26, 2025
 * 
 * Description:
 * This program animates multiple car objects moving in random directions
 * inside a window using Java Swing and multithreading. Each car reads its
 * movement direction from a shared CarQueue. When cars hit window boundaries,
 * they bounce back by changing direction.
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
   A queue that holds random directions for cars.
*/
public class CarQueue {
    private Queue<Integer> queue;

    public CarQueue() {
        queue = new LinkedList<>();
        for (int i = 0; i < 6; i++) {
            queue.add(new Random().nextInt(4)); // 0 to 3
        }
    }

    public void addToQueue() {
        class QueueRunnable implements Runnable {
            public void run() {
                try {
                    Random random = new Random();
                    while (true) {
                        queue.add(random.nextInt(4));
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Runnable r = new QueueRunnable();
        Thread t = new Thread(r);
        t.start();
    }

    public int deleteQueue() {
        if (queue.isEmpty()) {
            return 2; // Move right if empty
        } else {
            return queue.remove();
        }
    }
}
