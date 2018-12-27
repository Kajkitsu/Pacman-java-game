
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

//import com.sun.corba.se.spi.orbutil.fsm.Action;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;

    private Timer timer;
    private int delay = 20;

    private int pacmanXpos = 0;
    private int pacmanYpos = 0;
    private int pacmanXDirection = 0;
    private int pacmanYDirection = 0;

    private int wantedXDirection =0;
    private int wantedYDirection =0;

    private int score = 0;

    private int totalBricks = 21;

    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;

    private Map mapPacman = null;

    BufferedImage pacmanIconImg = null;

    private static final long serialVersionUID = 1L;

    public Gameplay(Map mapPacman) {
        this.mapPacman = mapPacman;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        try {
            pacmanIconImg = ImageIO.read(new File("pacmanIconImg.png"));
        } catch (IOException e) {
        }

        timer = new Timer(delay, this);
        timer.start();
        pacmanXpos = mapPacman.GetPacmanSquareX() * 19;
        pacmanYpos = mapPacman.GetPacmanSquareY() * 19;
    }

    public void paint(Graphics g) {
        // backgorund
        g.setColor(Color.black);
        g.fillRect(0, 0, mapPacman.GetWidth() * 19 * 2 + 20, mapPacman.GetHeight() * 2 * 19 + 100);
        g.setColor(Color.gray);
        g.fillRect(10, 10, mapPacman.GetWidth() * 19 * 2, mapPacman.GetHeight() * 2 * 19);

        // Rysowanie mapy
        mapPacman.DrawMap(g, pacmanIconImg);

        // Rysowanie pacmana
        g.setColor(Color.yellow);
        g.fillRect(10 + pacmanXpos*2 , 10 + pacmanYpos*2 , 19 * 2, 19 * 2);

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

        //siatka do Debugowania
        g.setColor(Color.white);
        for(int i=0; i< mapPacman.GetHeight()+19*2+60 ; i++){
            g.fillRect(10,9+(i*19*2), mapPacman.GetWidth()*2*19 ,2);
        }
        for(int i=0; i< mapPacman.GetWidth()+19*2 ; i++){
            g.fillRect(9+(i*19*2),10, 2, mapPacman.GetHeight()*2*19 );
        }



        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        TryToChangeDirectionOfPacman(wantedXDirection,wantedYDirection);
        if (play) {
            if(mapPacman.GetMap((pacmanXpos+pacmanXDirection)/19, (pacmanYpos+pacmanYDirection)/19)!=0 &&
            mapPacman.GetMap(((pacmanXpos+pacmanXDirection+18)/19), ((pacmanYpos+pacmanYDirection+18)/19))!=0 &&
            mapPacman.GetMap(((pacmanXpos+pacmanXDirection)/19), ((pacmanYpos+pacmanYDirection+18)/19))!=0 &&
            mapPacman.GetMap(((pacmanXpos+pacmanXDirection+18)/19), ((pacmanYpos+pacmanYDirection)/19))!=0 ){
                pacmanXpos+=pacmanXDirection;
                pacmanYpos+=pacmanYDirection;
            }
            else {
                pacmanXDirection=0;
                pacmanYDirection=0;
            }


        }
        
        System.out.println("pacmanXpos "+pacmanXpos+" pacmanYpos "+pacmanYpos);
        System.out.println("pacmanXpos/19 "+pacmanXpos/19+" pacmanYpos/19 "+pacmanYpos/19);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {


        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            wantedXDirection=1;
            wantedYDirection=0;
            play=true;

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            wantedXDirection=-1;
            wantedYDirection=0;
            play=true;

        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            wantedXDirection=0;
            wantedYDirection=-1;
            play=true;

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            wantedXDirection=0;
            wantedYDirection=1;
            play=true;

        }
        
    }

    private void TryToChangeDirectionOfPacman(int wantedXDirection, int wantedYDirection){
        if(mapPacman.GetMap((pacmanXpos+wantedXDirection)/19, (pacmanYpos+wantedYDirection)/19)!=0 &&
            mapPacman.GetMap(((pacmanXpos+wantedXDirection+18)/19), ((pacmanYpos+wantedYDirection+18)/19))!=0 &&
            mapPacman.GetMap(((pacmanXpos+wantedXDirection)/19), ((pacmanYpos+wantedYDirection+18)/19))!=0 &&
            mapPacman.GetMap(((pacmanXpos+wantedXDirection+18)/19), ((pacmanYpos+wantedYDirection)/19))!=0 ){
                pacmanYDirection=wantedYDirection;
                pacmanXDirection=wantedXDirection;
            }

    }

    // @Override
    // public void keyPressed(KeyEvent e) {
    //     int pacmanXSquare = ((pacmanXpos) / 19);
    //     int pacmanYSquare = ((pacmanYpos) / 19);

    //     if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
    //         play=true;
    //         if(mapPacman.GetMap((pacmanXpos+1)/19, (pacmanYpos)/19)!=0 &&
    //         mapPacman.GetMap(((pacmanXpos+1+18)/19), ((pacmanYpos+18)/19))!=0 &&
    //         mapPacman.GetMap(((pacmanXpos+1)/19), ((pacmanYpos+18)/19))!=0 &&
    //         mapPacman.GetMap(((pacmanXpos+1+18)/19), ((pacmanYpos)/19))!=0 ){
    //             pacmanXDirection = 1;
    //             pacmanYDirection = 0;
    //         }
    //     }
    //     if (e.getKeyCode() == KeyEvent.VK_LEFT) {
    //         play=true;
    //         if(mapPacman.GetMap((pacmanXpos-1)/19, (pacmanYpos)/19)!=0 &&
    //         mapPacman.GetMap(((pacmanXpos-1+18)/19), ((pacmanYpos+18)/19))!=0 &&
    //         mapPacman.GetMap(((pacmanXpos-1)/19), ((pacmanYpos+18)/19))!=0 &&
    //         mapPacman.GetMap(((pacmanXpos-1+18)/19), ((pacmanYpos)/19))!=0 ) {
    //             pacmanXDirection = -1;
    //             pacmanYDirection = 0;
    //         }
    //     }
    //     if (e.getKeyCode() == KeyEvent.VK_UP) {
    //         play=true;
    //         if(mapPacman.GetMap((pacmanXpos)/19, (pacmanYpos-1)/19)!=0 &&
    //         mapPacman.GetMap(((pacmanXpos+18)/19), ((pacmanYpos-1+18)/19))!=0 &&
    //         mapPacman.GetMap(((pacmanXpos)/19), ((pacmanYpos-1+18)/19))!=0 &&
    //         mapPacman.GetMap(((pacmanXpos+18)/19), ((pacmanYpos-1)/19))!=0 ) {
    //             pacmanXDirection = 0;
    //             pacmanYDirection = -1;
    //         }
    //     }
    //     if (e.getKeyCode() == KeyEvent.VK_DOWN) {
    //         play=true;
    //         if(mapPacman.GetMap((pacmanXpos)/19, (pacmanYpos+1)/19)!=0 &&
    //         mapPacman.GetMap(((pacmanXpos+18)/19), ((pacmanYpos+1+18)/19))!=0 &&
    //         mapPacman.GetMap(((pacmanXpos)/19), ((pacmanYpos+1+18)/19))!=0 &&
    //         mapPacman.GetMap(((pacmanXpos+18)/19), ((pacmanYpos+1)/19))!=0 ) {
    //             pacmanXDirection = 0;
    //             pacmanYDirection = 1;
    //         }
    //     }
    // }

    @Override
    public void keyReleased(KeyEvent e) {


    }

   
}