import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import com.sun.corba.se.spi.orbutil.fsm.Action;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
    private boolean play = false;
    private int score =0;

    private int totalBricks =21;
    private Timer time;
    private int delay = 8;

    private int playerX = 310;
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;

    public Gameplay(){
        
    }

    private static final long serialVersionUID = 1L;

}