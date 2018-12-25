
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
import java.util.concurrent.TimeUnit;

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
    private int delay = 10;

    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private Map mapPacman = null;

    BufferedImage pacmanIconImg = null;
    BufferedImage pacmanMapImg = null;

    private static final long serialVersionUID = 1L;

    // public void DrawMap(Graphics g) {
    //     g.setColor(Color.blue);
    //     for (int i = 0; i < mapPacman.GetWidth(); i++) {
    //         for (int j = 0; j < mapPacman.GetHeight(); j++) {
    //             if(mapPacman.GetMap(j, i)==0){
    //                 g.fillRoundRect(10+(i*19)*2+6, 10+(j*19)*2+6, 2*19-12, 2*19-12, 12, 12  );
    //                 if(mapPacman.GetMap(j+1, i)==0 && j<mapPacman.GetWidth()-1) g.fillRoundRect(10+(i*19)*2+6, 10+(j*19)*2+6, (2*19-12)*2, 2*19-12, 12, 12);
    //                 // if(mapPacman.GetMap(j-1, i)==0);
    //                 // if(mapPacman.GetMap(j, i+1)==0);
    //                 // if(mapPacman.GetMap(j, i-1)==0);
    //             }
    //             g.fillRoundRect(10+(i*19)*2+6, 10+(j*19)*2+6, 2*19-12, 2*19-12, 12, 12  );
    //             //g.fillRect(10, 10, mapPacman.GetWidth() * 19 * 2, mapPacman.GetHeight() * 2 * 19);
    //         }
    //     }
    // }

    public void DrawMap(Graphics g) {
        for (int y = 1; y < mapPacman.GetHeight()-1; y++) {
            for (int x = 1; x < mapPacman.GetWidth()-1; x++) {
                if (mapPacman.GetMap(x, y) != 0) {
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,190,22,209,41,null);
                }
                //kolko
                if (mapPacman.GetMap(x, y) == 0
                && mapPacman.GetMap(x+1, y)!=0
                && mapPacman.GetMap(x-1, y)!=0
                && mapPacman.GetMap(x, y+1)!=0
                && mapPacman.GetMap(x, y-1)!=0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,85,22,104,41,null);
                }
                //koniec od lewex strony
                if (mapPacman.GetMap(x, y) == 0
                && mapPacman.GetMap(x+1, y)!=0
                && mapPacman.GetMap(x-1, y)==0
                && mapPacman.GetMap(x, y+1)!=0
                && mapPacman.GetMap(x, y-1)!=0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,127,1,146,20,null);
                }
                //koniec od prawex strony
                if (mapPacman.GetMap(x, y) == 0
                && mapPacman.GetMap(x+1, y)!=0
                && mapPacman.GetMap(x-1, y)!=0
                && mapPacman.GetMap(x, y+1)!=0
                && mapPacman.GetMap(x, y-1)!=0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,169,1,188,20,null);
                }
                if (mapPacman.GetMap(x, y) == 0
                && mapPacman.GetMap(x+1, y)!=0
                && mapPacman.GetMap(x-1, y)==0
                && mapPacman.GetMap(x, y+1)!=0
                && mapPacman.GetMap(x, y-1)!=0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,127,1,146,20,null);
                }
            }
        }
    }

    public Gameplay(Map mapPacman) {
        this.mapPacman = mapPacman;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        try {
            pacmanIconImg = ImageIO.read(new File("pacmanIconImg.png"));
        } catch (IOException e) {
        }
        /*
         * try { pacmanMapImg = ImageIO.read(new File("mapagolge.png")); } catch
         * (IOException e) { }
         */
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // backgorund
        g.setColor(Color.black);
        g.fillRect(0, 0, mapPacman.GetWidth() * 19 * 2 + 20, mapPacman.GetHeight() * 2 * 19 + 100);
        g.setColor(Color.gray);
        g.fillRect(10, 10, mapPacman.GetWidth() * 19 * 2, mapPacman.GetHeight() * 2 * 19);
        
        //Rysowanie mapy
        DrawMap(g);
        
        
        /*
         * // boredrs g.setColor(Color.yellow); g.fillRect(0, 0, 3, 592); g.fillRect(0,
         * 0, 692, 3); g.fillRect(691, 0, 3, 592);
         * 
         * // the paddle g.setColor(Color.green); g.fillRect(playerX, 550, 100, 8);
         */
        // the ball
        // g.setColor(Color.red);
        // g.fillOval(ballposX, ballposY, 20, 20);
        // g.drawImage(pacmanIconImg, ballposX, ballposY, 32, 32, Color.black, null);
        // g.drawImage(pacmanIconImg, 10+ballposX, 10+ballposY, 10+ballposX+32,
        // 10+ballposY+32,2,2,18,18,null);
        /*
         * g.drawImage(pacmanMapImg, 5, 5, 464*2+5, 136*2+5, 0, 0, 464, 136, null);
         * 
         * g.setColor(Color.white);
         * 
         * for(int i=0; i<45; i++){ g.fillRect(0+i*22, 0, 1,136*2+10+80); } for(int i=0;
         * i<16; i++){ g.fillRect(0, 0+i*22, 464*2+5,1); }
         * 
         * 
         * //for(int i=0; i< 464*2+5 ; i=+40){ // g.fillRect(x, y, width, height); //
         * g.fillRect(i, 0, 1,136*2+10+80); //}
         */
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