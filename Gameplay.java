
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
    private int cycle = 0;

    private int pacmanXPosition = 0;
    private int pacmanYPosition = 0;
    private int pacmanXDirection = 0;
    private int pacmanYDirection = 0;

    private int wantedXDirection = 0;
    private int wantedYDirection = 0;

    private int ghostXPosition[] = new int[4];
    private int ghostYPosition[] = new int[4];
    private int ghostXDirection[] = new int[4];
    private int ghostYDirection[] = new int[4];

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

        // ustalanie pozycji startowych
        pacmanXPosition = mapPacman.GetPacmanSquareX() * 19;
        pacmanYPosition = mapPacman.GetPacmanSquareY() * 19;

        for (int i = 0; i < 4; i++) {
            ghostXPosition[i] = mapPacman.GetGhostSquareX(i) * 19;
            ghostYPosition[i] = mapPacman.GetGhostSquareY(i) * 19;
            ghostXDirection[i] = 0;
            ghostYDirection[i] = 0;
        }
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
        g.fillRect(10 + pacmanXPosition * 2, 10 + pacmanYPosition * 2, 19 * 2, 19 * 2);

        // Rysowanie Ghostow
        g.setColor(Color.pink);
        for (int i = 0; i < 4; i++) {
            g.fillRect(10 + ghostXPosition[i] * 2, 10 + ghostYPosition[i] * 2, 19 * 2, 19 * 2);
        }

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

        // siatka do Debugowania
        g.setColor(Color.white);
        for (int i = 0; i < mapPacman.GetHeight() + 19 * 2 + 60; i++) {
            g.fillRect(10, 9 + (i * 19 * 2), mapPacman.GetWidth() * 2 * 19, 2);
        }
        for (int i = 0; i < mapPacman.GetWidth() + 19 * 2; i++) {
            g.fillRect(9 + (i * 19 * 2), 10, 2, mapPacman.GetHeight() * 2 * 19);
        }

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if (play) {
            TryToChangeDirectionOfPacman(wantedXDirection, wantedYDirection);

            cycle++;
            if (cycle == 4)
                cycle = 1;

            // poruszanie sie pacmana
            if (mapPacman.GetMap((pacmanXPosition + pacmanXDirection) / 19,
                    (pacmanYPosition + pacmanYDirection) / 19) != 0
                    && mapPacman.GetMap(((pacmanXPosition + pacmanXDirection + 18) / 19),
                            ((pacmanYPosition + pacmanYDirection + 18) / 19)) != 0
                    && mapPacman.GetMap(((pacmanXPosition + pacmanXDirection) / 19),
                            ((pacmanYPosition + pacmanYDirection + 18) / 19)) != 0
                    && mapPacman.GetMap(((pacmanXPosition + pacmanXDirection + 18) / 19),
                            ((pacmanYPosition + pacmanYDirection) / 19)) != 0) {
                pacmanXPosition += pacmanXDirection;
                pacmanYPosition += pacmanYDirection;
            } else {
                pacmanXDirection = 0;
                pacmanYDirection = 0;
            }

            // ograniczone cyklami gry
            if (cycle % 3 != 0) {
                // poruszanie sie ghostow
                for (int i = 0; i < 4; i++) {
                    if (mapPacman.GetMap((ghostXPosition[i] + ghostXDirection[i]) / 19,
                            (ghostYPosition[i] + ghostYDirection[i]) / 19) != 0
                            && mapPacman.GetMap(((ghostXPosition[i] + ghostXDirection[i] + 18) / 19),
                                    ((ghostYPosition[i] + ghostYDirection[i] + 18) / 19)) != 0
                            && mapPacman.GetMap(((ghostXPosition[i] + ghostXDirection[i]) / 19),
                                    ((ghostYPosition[i] + ghostYDirection[i] + 18) / 19)) != 0
                            && mapPacman.GetMap(((ghostXPosition[i] + ghostXDirection[i] + 18) / 19),
                                    ((ghostYPosition[i] + ghostYDirection[i]) / 19)) != 0) {
                        ghostXPosition[i] += ghostXDirection[i];
                        ghostYPosition[i] += ghostYDirection[i];
                    } else {

                        ghostXDirection[i] = 0;
                        ghostYDirection[i] = 0;
                    }
                }
            }

            // wykrywanie ghostow
            for (int i = 0; i < 4; i++) {
                boolean isObstacle = false;
                if (pacmanXPosition == ghostXPosition[i]) {
                    if (pacmanYPosition > ghostYPosition[i]) {
                        int j = ghostYPosition[i];
                        while (pacmanYPosition > j) {
                            j++;
                            if (mapPacman.GetMap(pacmanXPosition / 19, j / 19) == 0) {
                                isObstacle = true;
                            }
                        }
                    } else {
                        int j = pacmanYPosition;
                        while (ghostYPosition[i] > j) {
                            j++;
                            if (mapPacman.GetMap(pacmanXPosition / 19, j / 19) == 0) {
                                isObstacle = true;
                            }
                        }
                    }
                    if (!isObstacle) {
                        ghostXDirection[i] = 0;
                        ghostYDirection[i] = pacmanYPosition > ghostYPosition[i] ? 1 : (-1);
                    }

                } else if (pacmanYPosition == ghostYPosition[i]) {
                    if (pacmanXPosition > ghostXPosition[i]) {
                        int j = ghostXPosition[i];
                        while (pacmanXPosition > j) {
                            j++;
                            if (mapPacman.GetMap(j / 19, pacmanYPosition / 19) == 0) {
                                isObstacle = true;
                            }
                        }
                    } else {
                        int j = pacmanXPosition;
                        while (ghostXPosition[i] > j) {
                            j++;
                            if (mapPacman.GetMap(j / 19, pacmanYPosition / 19) == 0) {
                                isObstacle = true;
                            }
                        }
                    }
                    if (!isObstacle) {
                        ghostXDirection[i] = pacmanXPosition > ghostXPosition[i] ? 1 : (-1);
                        ghostYDirection[i] = 0;
                    }

                }

            }

            // for(int j=0; j<mapPacman.GetHeight(); j++){
            // if(pacmanYPosition==ghostYPosition[i] + j){
            // ghostXDirection[i]=0;
            // ghostYDirection[i]=-1 ;
            // }
            // if(pacmanYPosition==ghostYPosition[i] - j){
            // ghostXDirection[i]=0;
            // ghostYDirection[i]=1 ;
            // }

            // }

        }

        System.out.println("pacmanXpos " + pacmanXPosition + " pacmanYPosition " + pacmanYPosition);
        System.out.println("pacmanXpos/19 " + pacmanXPosition / 19 + " pacmanYPosition/19 " + pacmanYPosition / 19);
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
        if(mapPacman.GetMap((pacmanXPosition+wantedXDirection)/19, (pacmanYPosition+wantedYDirection)/19)!=0 &&
            mapPacman.GetMap(((pacmanXPosition+wantedXDirection+18)/19), ((pacmanYPosition+wantedYDirection+18)/19))!=0 &&
            mapPacman.GetMap(((pacmanXPosition+wantedXDirection)/19), ((pacmanYPosition+wantedYDirection+18)/19))!=0 &&
            mapPacman.GetMap(((pacmanXPosition+wantedXDirection+18)/19), ((pacmanYPosition+wantedYDirection)/19))!=0 ){
                pacmanYDirection=wantedYDirection;
                pacmanXDirection=wantedXDirection;
            }

    }

    // @Override
    // public void keyPressed(KeyEvent e) {
    //     int pacmanXSquare = ((pacmanXpos) / 19);
    //     int pacmanYSquare = ((pacmanYPosition) / 19);

    //     if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
    //         play=true;
    //         if(mapPacman.GetMap((pacmanXpos+1)/19, (pacmanYPosition)/19)!=0 &&
    //         mapPacman.GetMap(((pacmanXpos+1+18)/19), ((pacmanYPosition+18)/19))!=0 &&
    //         mapPacman.GetMap(((pacmanXpos+1)/19), ((pacmanYPosition+18)/19))!=0 &&
    //         mapPacman.GetMap(((pacmanXpos+1+18)/19), ((pacmanYPosition)/19))!=0 ){
    //             pacmanXDirection = 1;
    //             pacmanYDirection = 0;
    //         }
    //     }
    //     if (e.getKeyCode() == KeyEvent.VK_LEFT) {
    //         play=true;
    //         if(mapPacman.GetMap((pacmanXpos-1)/19, (pacmanYPosition)/19)!=0 &&
    //         mapPacman.GetMap(((pacmanXpos-1+18)/19), ((pacmanYPosition+18)/19))!=0 &&
    //         mapPacman.GetMap(((pacmanXpos-1)/19), ((pacmanYPosition+18)/19))!=0 &&
    //         mapPacman.GetMap(((pacmanXpos-1+18)/19), ((pacmanYPosition)/19))!=0 ) {
    //             pacmanXDirection = -1;
    //             pacmanYDirection = 0;
    //         }
    //     }
    //     if (e.getKeyCode() == KeyEvent.VK_UP) {
    //         play=true;
    //         if(mapPacman.GetMap((pacmanXpos)/19, (pacmanYPosition-1)/19)!=0 &&
    //         mapPacman.GetMap(((pacmanXpos+18)/19), ((pacmanYPosition-1+18)/19))!=0 &&
    //         mapPacman.GetMap(((pacmanXpos)/19), ((pacmanYPosition-1+18)/19))!=0 &&
    //         mapPacman.GetMap(((pacmanXpos+18)/19), ((pacmanYPosition-1)/19))!=0 ) {
    //             pacmanXDirection = 0;
    //             pacmanYDirection = -1;
    //         }
    //     }
    //     if (e.getKeyCode() == KeyEvent.VK_DOWN) {
    //         play=true;
    //         if(mapPacman.GetMap((pacmanXpos)/19, (pacmanYPosition+1)/19)!=0 &&
    //         mapPacman.GetMap(((pacmanXpos+18)/19), ((pacmanYPosition+1+18)/19))!=0 &&
    //         mapPacman.GetMap(((pacmanXpos)/19), ((pacmanYPosition+1+18)/19))!=0 &&
    //         mapPacman.GetMap(((pacmanXpos+18)/19), ((pacmanYPosition+1)/19))!=0 ) {
    //             pacmanXDirection = 0;
    //             pacmanYDirection = 1;
    //         }
    //     }
    // }

    @Override
    public void keyReleased(KeyEvent e) {


    }

   
}