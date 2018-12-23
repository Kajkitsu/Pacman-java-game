
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Object.*;
import javax.imageio.ImageIO;

//import javax.management.timer.Timer;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.sun.corba.se.spi.orbutil.fsm.Action;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;

    private int totalBricks = 21;
    private Timer timer;
    private int delay = 6;

    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;

    BufferedImage ballImg = null;
    BufferedImage ballImg2 =null;

    private static final long serialVersionUID = 1L;

    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        try {
            ballImg = ImageIO.read(new File("pacman.jpg"));
        } catch (IOException e) {
        }
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // backgorund
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        // boredrs
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        // the paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);

        // the ball
        //g.setColor(Color.red);
        //g.fillOval(ballposX, ballposY, 20, 20);
        g.drawImage(ballImg, ballposX, ballposY, 32, 32, Color.black, null);


        g.dispose();

        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            ballposX += ballXdir;
            ballposY += ballYdir;
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }
        // if(e.getKeyCode() == KeyEvent.VK_UP ){}
        // if(e.getKeyCode() == KeyEvent.VK_DOWN ){}
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void moveRight() {
        play = true;
        playerX += 20;
        System.out.println("moveRight()" + playerX);
    }

    public void moveLeft() {
        play = true;
        playerX -= 20;
    }

}