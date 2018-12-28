import java.io.*;

import javax.swing.JFrame;

public class Game extends Map {
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        Map mapPacman = new Map("map1.txt");
        Gameplay gamePlay = new Gameplay(mapPacman);
        obj.setBounds(0,0,mapPacman.GetWidth()*19*2+20,mapPacman.GetHeight()*2*19+100);
        //obj.setBounds(0,0,464*2+10,136*2+10+80);
        obj.setTitle("Pacman by Kajkitsu");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
        
        /*
        System.out.println("");
        Mapa mapaPacman = new Mapa("mapagolge.txt");
        mapaPacman.loadMap();
        */
        /*
        JFrame obj = new JFrame();
        Gameplay gamePlay = new Gameplay();
        obj.setBounds(10,01,600,600);
        obj.setTitle("Pacman by Kajkitsu");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
        */
    }

    /*
    protected int mapa[][] = new int[21][21];

    public static void main(String[] args) {

        System.out.println("");
        String x = "TEST";
        System.out.println(x + " ");
        System.out.println("");
        Pacman gra = new Pacman();
        gra.PierwszeUruchominie();

    }

    public void PierwszeUruchominie() {
        try {
            wczytajMape("mapa1.pac");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 21; j++) {
                System.out.print(mapa[i][j]);
                
            }
            System.out.println("");
        }

    }

    public void wczytajMape(String sciezkaMapy) throws IOException {
        FileReader czytaczPliku = new FileReader(sciezkaMapy);
        BufferedReader czytaczBufforu = new BufferedReader(czytaczPliku);

        String liczba;
        char znak;
        for (int i = 0; i < 21; i++) {
            liczba = czytaczBufforu.readLine();
            for (int j = 0; j < 21; j++) {
                znak=liczba.charAt(j);
                mapa[i][j] = ((int)znak ) - 48;
            }

        }

        czytaczBufforu.close();
    }
    */

}