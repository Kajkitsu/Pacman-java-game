import java.io.*;

import javax.swing.JFrame;

public class Game extends Map {
    public static void main(String[] args) {

        JFrame obj = new JFrame();
        Map mapPacman;
        if (args.length > 0) {
         mapPacman = new Map(args[0]);   
        }
        else mapPacman = new Map();
        Gameplay gamePlay = new Gameplay(mapPacman);
        obj.setBounds(0,0,mapPacman.GetWidth()*19*2+20,mapPacman.GetHeight()*2*19+100);

        obj.setTitle("Pacman by Kajkitsu");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
       
    }

}