
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

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private boolean play = false;
    private boolean killed = false;
    private boolean win = false;

    private Timer timer;
    private int delay = 20;
    private int cycle = 0;
    private int grapCycyle = 1;
    private int timeForEat = 0;

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

        // tworzenie mapy punktow
        mapPacman.MakeScoreMap();

        timer = new Timer(delay, this);
        timer.start();

        // tworzenie Pacmana i Ghostow
        pacmanPlayer = new Pacman(mapPacman.GetPacmanSquareX() * 19, mapPacman.GetPacmanSquareY() * 19, mapPacman);
        for (int i = 0; i < 4; i++) {
            ghost[i] = new Ghost(mapPacman.GetGhostSquareX(i) * 19, mapPacman.GetGhostSquareY(i) * 19, mapPacman,
                    pacmanPlayer);
        }

    }

    public void paint(Graphics g) {

        // background
        g.setColor(Color.black);
        g.fillRect(0, 0, mapPacman.GetWidth() * 19 * 2 + 20, mapPacman.GetHeight() * 2 * 19 + 100);
        g.setColor(Color.gray);
        g.fillRect(10, 10, mapPacman.GetWidth() * 19 * 2, mapPacman.GetHeight() * 2 * 19);

        // Wypisanie liczby punktow
        g.setColor(Color.white);
        g.setFont(new Font(null, Font.BOLD, 40));
        g.drawString("Score: " + score, 10, mapPacman.GetHeight() * 2 * 19 + 50);

        // Rysowanie mapy
        mapPacman.DrawMap(g, pacmanIconImg);

        // Rysowanie mapy punktow
        mapPacman.DrawScoreMap(g);

        // Rysowanie pacmana
        pacmanPlayer.Draw(grapCycyle, pacmanIconImg, g);

        // Rysowanie Ghostow
        for (int i = 0; i < 4; i++) {
            ghost[i].Draw(grapCycyle, pacmanIconImg, g, i, timeForEat);
        }

        // czy zabity
        if (killed) {
            g.setColor(Color.red);
            g.setFont(new Font(null, Font.BOLD, 60));
            g.drawString("GAME OVER", 10 + (mapPacman.GetWidth() * 19) / 2 - 30, 10 + mapPacman.GetHeight() * 19);
        }

        // czy wygrana
        if (win) {
            g.setColor(Color.green);
            g.setFont(new Font(null, Font.BOLD, 60));
            g.drawString("YOU WIN!", 10 + (mapPacman.GetWidth() * 19) / 2 - 30, 10 + mapPacman.GetHeight() * 19);
        }

        // czy pausa
        if (!play && !killed && !win) {
            g.setColor(Color.magenta);
            g.setFont(new Font(null, Font.BOLD, 60));
            g.drawString("PAUSE", 10 + (mapPacman.GetWidth() * 19) / 2 - 30, 10 + mapPacman.GetHeight() * 19);
        }

        g.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // System.out.println("void actionPerformed(ActionEvent e)");
        timer.start();

        if (play && !killed) {

            pacmanPlayer.TryToChangeDirectionOfPacman(wantedXDirection, wantedYDirection, mapPacman);

            cycle++;
            if (cycle == 8)
                cycle = 0;
            if (cycle % 3 == 0) {
                grapCycyle++;
            }
            if (grapCycyle == 7)
                grapCycyle = 1;

            // poruszanie sie pacmana
            if (cycle != 0)
                pacmanPlayer.Move(mapPacman);

            // zdobycie punktow przez pacmana
            int points = mapPacman.TakeScoreFrom(pacmanPlayer.GetXPosition(), pacmanPlayer.GetYPosition());
            if (points == 500) {
                timeForEat = 250;
            }
            score = points + score;
            if (timeForEat > 0) {
                for (int i = 0; i < 4; i++) {
                    if (pacmanPlayer.TryToKill(ghost[i]))
                        score = score + 1000;
                }
                timeForEat--;
            }

            // poruszanie sie ghostow
            for (int i = 0; i < 4; i++) {
                if (!ghost[i].Move(mapPacman))
                    ghost[i].ChangeToRandomDirectionAndMove(mapPacman);

                if (timeForEat == 0) {
                    if (ghost[i].TryToKill(pacmanPlayer)) {
                        killed = true;
                        play = false;
                        break;
                    }

                }
            }

            // wykrywanie ghostow
            if (timeForEat == 0)
                for (int i = 0; i < 4; i++)
                    ghost[i].DetectPacman(pacmanPlayer, mapPacman);

            if (mapPacman.CheckIsScoreMapEmpty()) {
                win = true;
                play = false;
            }

        }
        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            wantedXDirection = 1;
            wantedYDirection = 0;

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            wantedXDirection = -1;
            wantedYDirection = 0;

        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            wantedXDirection = 0;
            wantedYDirection = -1;

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            wantedXDirection = 0;
            wantedYDirection = 1;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !killed) {
            play = !play;
        }

    }

}