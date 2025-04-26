package application;

/**
 * Title: Car Animation with Multithreading and Queue Management
 * Lab 9
 * Author: G. Araya
 * Date: April 27, 2025
 * 
 * Description:
 * This program animates multiple car objects moving in random directions
 * inside a window using Java Swing and multithreading. Each car reads its
 * movement direction from a shared CarQueue. When cars hit window boundaries,
 * they bounce back by changing direction.
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

/**
   Creates the frame and adds car panels.
*/
public class CarFrame
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        frame.setSize(600, 400);
        frame.setTitle("Cars");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CarQueue carQueue = new CarQueue();

        JPanel panel = new JPanel();
        panel.setLayout(null); // allow manual positioning
        
        CarPanel component1 = new CarPanel(0, 0, 1, carQueue);
        component1.setBounds(0, 0, 600, 400);
        panel.add(component1);
        
        CarPanel component2 = new CarPanel(80, 100, 2, carQueue);
        component2.setBounds(0, 0, 600, 400);
        panel.add(component2);
        
        CarPanel component3 = new CarPanel(30, 200, 3, carQueue);
        component3.setBounds(0, 0, 600, 400);
        panel.add(component3);

        frame.add(panel);
        frame.setVisible(true);

        carQueue.addToQueue();

        component1.startAnimation();
        component2.startAnimation();
        component3.startAnimation();
    }
}
