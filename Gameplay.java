
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Object.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import javax.swing.JPanel;
import javax.swing.Timer;

//import jdk.nashorn.internal.ir.Flags;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private boolean killed = false;
    private boolean win = false;

    private Timer timer;
    private int delay = 20;
    private int cycle = 0;
    private int grapCycyle =1;
    private int timeForEat=0;

    private Pacman pacmanPlayer;
    private Ghost ghost[] = new Ghost[4];


    private int wantedXDirection = 0;
    private int wantedYDirection = 0;

    private int score = 0;


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


        //tworzenie mapy punktow
        mapPacman.MakeScoreMap();

        timer = new Timer(delay, this);
        timer.start();

       
        //tworzenie Pacmana i Ghostow
        pacmanPlayer = new Pacman(mapPacman.GetPacmanSquareX() * 19,mapPacman.GetPacmanSquareY() * 19,mapPacman);
        for (int i = 0; i < 4; i++){
            ghost[i] = new Ghost(mapPacman.GetGhostSquareX(i) *19 ,mapPacman.GetGhostSquareY(i)*19,mapPacman,pacmanPlayer);
            ghost[i].ChangeToRandomDirection();
        }
        // System.out.println("Koniec Gameplay()");
        

    }

    public void paint(Graphics g) {

        // System.out.println("paint()");

        if(cycle%3==0){
            grapCycyle++;
        }
        if (grapCycyle==7) grapCycyle=1;

        // backgorund
        g.setColor(Color.black);
        g.fillRect(0, 0, mapPacman.GetWidth() * 19 * 2 + 20, mapPacman.GetHeight() * 2 * 19 + 100);
        g.setColor(Color.gray);
        g.fillRect(10, 10, mapPacman.GetWidth() * 19 * 2, mapPacman.GetHeight() * 2 * 19);

        // Wypisanie punktow
        g.setColor(Color.white);
        g.setFont(new Font(null, Font.BOLD, 40));
        g.drawString("Score: "+score, 10 , mapPacman.GetHeight() * 2 * 19 + 50 );

        // Rysowanie mapy
        mapPacman.DrawMap(g, pacmanIconImg);

        //Rysowanie mapy punktow
        mapPacman.DrawScoreMap(g);


        // Rysowanie pacmana
        pacmanPlayer.DrawPacman(grapCycyle, pacmanIconImg, g); 


        //Rysowanie Ghostow
        for (int i = 0; i < 4; i++) {
            ghost[i].DrawGhost(grapCycyle, pacmanIconImg, g, i, timeForEat);
        }

        //czy zabity
        if(killed){
            g.setColor(Color.red);
            g.setFont(new Font(null, Font.BOLD,60));
            g.drawString("GAME OVER", 10+(mapPacman.GetWidth()*19)/2-30, 10+mapPacman.GetHeight()*19);
        }

        //czy wygrana
        if(win){
            g.setColor(Color.green);
            g.setFont(new Font(null, Font.BOLD,60));
            g.drawString("YOU WIN!", 10+(mapPacman.GetWidth()*19)/2-30, 10+mapPacman.GetHeight()*19);
        }

        //czy pasua
        if(!play && !killed){
            g.setColor(Color.magenta);
            g.setFont(new Font(null, Font.BOLD,60));
            g.drawString("PAUSA", 10+(mapPacman.GetWidth()*19)/2-30, 10+mapPacman.GetHeight()*19);
        }

        

        // Rysowanie pacmana
        // g.setColor(Color.yellow);
        // g.fillRect(10 + pacmanPlayer.GetXPosition() * 2, 10 + pacmanPlayer.GetYPosition() * 2, 19 * 2, 19 * 2);

        // Rysowanie Ghostow
        // g.setColor(Color.pink);
        // for (int i = 0; i < 4; i++) {
        //     g.fillRect(10 + ghost[i].GetXPosition() * 2, 10 + ghost[i].GetYPosition() * 2, 19 * 2, 19 * 2);
        // }

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
        // g.setColor(Color.white);
        // for (int i = 0; i < mapPacman.GetHeight() + 19 * 2 + 60; i++) {
        //     g.fillRect(10, 9 + (i * 19 * 2), mapPacman.GetWidth() * 2 * 19, 2);
        // }
        // for (int i = 0; i < mapPacman.GetWidth() + 19 * 2; i++) {
        //     g.fillRect(9 + (i * 19 * 2), 10, 2, mapPacman.GetHeight() * 2 * 19);
        // }

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // System.out.println("void actionPerformed(ActionEvent e)");
        timer.start();

        if (play && !killed) {
            // System.out.println("play=true");
            pacmanPlayer.TryToChangeDirectionOfPacman(wantedXDirection, wantedYDirection);

            cycle++;
            if (cycle == 8)
                cycle = 0;

            // poruszanie sie pacmana

            if(cycle!=0) pacmanPlayer.MovePacman();

            int points = mapPacman.TakeScoreFrom(pacmanPlayer.GetXPosition(), pacmanPlayer.GetYPosition());
            if(points == 500) {
                timeForEat=250;
            }
            score=points+score;
            if(timeForEat>0) {
                for(int i=0; i < 4; i++){
                    if(pacmanPlayer.TryToKill(ghost[i])) score=score+1000;
                }
                timeForEat--;
            }

            // ograniczone cyklami gry
            //if (cycle % 3 != 0)
            if (true) {
                // poruszanie sie ghostow
                for (int i = 0; i < 4; i++) {
                    ghost[i].MoveGhost();
                    if(ghost[i].TryToKill(pacmanPlayer) && timeForEat==0){
                        killed=true;
                        play=false;
                        break;
                    }

                }
            }

            // wykrywanie ghostow
            if (timeForEat == 0)
                for (int i = 0; i < 4; i++)
                    ghost[i].DetectPacman();

            if(mapPacman.CheckIsScoreMapEmpty()){
                win=true;
                play=false;
            }

            repaint();


        }

        //System.out.println("pacmanXpos " + pacmanXPosition + " pacmanYPosition " + pacmanYPosition);
        //System.out.println("pacmanXpos/19 " + pacmanXPosition / 19 + " pacmanYPosition/19 " + pacmanYPosition / 19);
        // System.out.println("repaint()");
        if(!killed) repaint();
        // System.out.println("after repaint()");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {


        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            wantedXDirection=1;
            wantedYDirection=0;
            // play=true;

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            wantedXDirection=-1;
            wantedYDirection=0;
            // play=true;

        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            wantedXDirection=0;
            wantedYDirection=-1;
            // play=true;

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            wantedXDirection=0;
            wantedYDirection=1;
            // play=true;

        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !killed){
            play=!play;
        }


    }

   
}