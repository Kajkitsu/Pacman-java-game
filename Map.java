import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;

public class Map {
    protected String pathToMap = null;
    protected String pathToImgOfMap = null;
    protected int height = 0;
    protected int width = 0;
    protected int pacmanSquareX = 0;
    protected int pacmanSquareY = 0;
    protected int ghostSquareX[] = new int[4];
    protected int ghostSquareY[] = new int[4];
    protected int map[][];
    protected int scoreMap[][];

    public Map(String path) {
        this.pathToMap = path;
        try {
            this.LoadMap();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Map() {
        System.out.println("Podaj sciezke bo pliku konfiguracyjnego mapy: ");

        // this.pathToMap=path;

    }

    public int GetHeight(){
        return this.height;
    }
    public int GetWidth(){
        return this.width;
    }
    public int GetPacmanSquareX(){
        return this.pacmanSquareX;
    }
    public int GetPacmanSquareY(){
        return this.pacmanSquareY;
    }
    public int GetGhostSquareX(int i){
        return this.ghostSquareX[i];
    }
    public int GetGhostSquareY(int i){
        return this.ghostSquareY[i];
    }
    public int GetMap(int x, int y){
        return this.map[x][y];
    }

    public void MakeScoreMap(){
        this.scoreMap = new int[this.width][this.height];
        for (int y = 0; y < this.height; y++)
        {
            for (int x = 0; x < this.width; x++){
                if(map[x][y]==1) this.scoreMap[x][y]=100;
                else if (map[x][y]==2) this.scoreMap[x][y]=500;
                else this.scoreMap[x][y]=0;
                
            }
        }
    }

    public void DrawScoreMap(Graphics g){
        g.setColor(Color.yellow);
        for (int y = 0; y < this.height; y++)
        {
            for (int x = 0; x < this.width; x++){
                if(this.scoreMap[x][y]==100) {
                    g.fillRect(10 + ((x+1) *19 * 2)-21, 10 + ((y+1) * 19 * 2)-21, 4, 4);
                }
                else if (this.scoreMap[x][y]==500) {
                    g.fillRect(10 + ((x+1) *19 * 2)-23, 10 + ((y+1) * 19 * 2)-23, 8, 8);
                }
                
            }
        }

    }

    public int TakeScoreFrom(int pacmanXposition, int pacmanYposition) {
        if((pacmanXposition + 6) / 19 == (pacmanXposition + 13) / 19 ){
            this.scoreMap[(pacmanXposition + 9) / 19][(pacmanYposition + 9) / 19] = 0;
            return this.scoreMap[(pacmanXposition + 9) / 19][(pacmanYposition + 9) / 19];
        }

        // if (this.scoreMap[(pacmanXposition + 9) / 19][(pacmanYposition + 9) / 19] > 0 && this.scoreMap[(pacmanXposition + 10) / 19][(pacmanYposition + 10) / 19] > 0 ) {
        //     this.scoreMap[(pacmanXposition + 9) / 19][(pacmanYposition + 9) / 19] = 0;
        //     return this.scoreMap[(pacmanXposition + 9) / 19][(pacmanYposition + 9) / 19];
        // }
        // if (this.scoreMap[(pacmanXposition + 10) / 19][(pacmanYposition + 10) / 19] > 0) {
        //     this.scoreMap[(pacmanXposition + 10) / 19][(pacmanYposition + 10) / 19] = 0;
        //     return this.scoreMap[(pacmanXposition + 10) / 19][(pacmanYposition + 10) / 19];
        // }
        return 0;
    }


    protected void LoadMap() throws IOException {
        FileReader fileReader = new FileReader(pathToMap);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String readedLine = bufferedReader.readLine();
        int i = 0;
        // 0-sciana
        // 1-wolne
        // 2-duzy punkt

        // wczytanie wyskosci i szerokosci
        for (i = 0; i < readedLine.length(); i++) {
            if (readedLine.charAt(i) != ' ') {
                this.width = this.width * 10;
                this.width += readedLine.charAt(i) - 48;
            } else {

                i++;
                break;
            }
        }
        for (; i < readedLine.length(); i++)
            if (readedLine.charAt(i) != ' ') {
                this.height = this.height * 10;
                this.height += readedLine.charAt(i) - 48;
            } else {

                i++;
                break;
            }

        // wczytanie wsporzednych pacmana

        readedLine = bufferedReader.readLine();
        for (i = 0; i < readedLine.length(); i++) {
            if (readedLine.charAt(i) != ' ') {
                this.pacmanSquareX = this.pacmanSquareX * 10;
                this.pacmanSquareX += readedLine.charAt(i) - 48;
            } else {

                i++;
                break;
            }
        }
        for (; i < readedLine.length(); i++) {
            if (readedLine.charAt(i) != ' ') {
                this.pacmanSquareY = this.pacmanSquareY * 10;
                this.pacmanSquareY += readedLine.charAt(i) - 48;
            } else {

                i++;
                break;
            }
        }
        // wczytanie wsporzednych duchow
        for (int j = 0; j < 4; j++) {
            readedLine = bufferedReader.readLine();
            for (i = 0; i < readedLine.length(); i++) {
                if (readedLine.charAt(i) != ' ') {
                    this.ghostSquareX[j] = this.ghostSquareX[j] * 10;
                    this.ghostSquareX[j] += readedLine.charAt(i) - 48;
                } else {

                    i++;
                    break;
                }
            }
            for (; i < readedLine.length(); i++) {
                if (readedLine.charAt(i) != ' ') {
                    this.ghostSquareY[j] = this.ghostSquareY[j] * 10;
                    this.ghostSquareY[j] += readedLine.charAt(i) - 48;
                } else {

                    i++;
                    break;
                }
            }

        }

        this.map = new int[this.width][this.height];
        // wczytanie mapy
        for (int y = 0; y < this.height; y++) {
            readedLine = bufferedReader.readLine();
            for (int x = 0; x < this.width; x++) {
                this.map[x][y] = readedLine.charAt(x * 2) - 48;
            }
        }
        this.pathToImgOfMap = bufferedReader.readLine();
        bufferedReader.close();
        // DEBUGGING LEVEL HARD
        /*
         * 
         * System.out.println("height: " + this.height); System.out.println("Width: " +
         * this.width); System.out.println("Ghost 4: " + this.ghostSquareX[3] + " " +
         * this.ghostSquareY[3]);
         * 
         * for (i = 0; i < this.height; i++) { for (int j = 0; j < this.width; j++) {
         * System.out.print(map[i][j] + " "); } System.out.println();
         * 
         * } System.out.println(this.pathToImgOfMap);
         */

        
    }

    public void DrawMap(Graphics g, BufferedImage pacmanIconImg) {
        //narozniki

        //skret prawo-dol
        g.drawImage(pacmanIconImg, 10, 10, 10 + 19*2, 10 + 19*2,106,43,125,62,null);
        //skret lewo-dol
        g.drawImage(pacmanIconImg, 10+((this.GetWidth()-1)*19)*2, 10 , 10+(((this.GetWidth()-1)+1)*19)*2, 10 + 19*2,85,43,104,62,null);
        //skret prawo-gora
        g.drawImage(pacmanIconImg, 10, 10+((this.GetHeight()-1)*19)*2, 10 + 19*2, 10+(((this.GetHeight()-1)+1)*19)*2,148,43,167,62,null);
        //skret lewo-gora
        g.drawImage(pacmanIconImg, 10+((this.GetWidth()-1)*19)*2, 10+((this.GetHeight()-1)*19)*2, 10+(((this.GetWidth()-1)+1)*19)*2, 10+(((this.GetHeight()-1)+1)*19)*2,127,43,146,62,null);


        //pasek gorny
        for (int x = 1,y =0; x < this.GetWidth()-1; x++){
            if (this.GetMap(x, y) != 0) {
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,190,22,209,41,null);
            }
            //pionowy
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)!=0
            && this.GetMap(x-1, y)!=0
            && this.GetMap(x, y+1)==0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,85,1,104,20,null);
            }
            //poziomy
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)==0
            && this.GetMap(x-1, y)==0
            && this.GetMap(x, y+1)!=0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,106,1,125,20,null);
            }
            //koniec od gory
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)!=0
            && this.GetMap(x-1, y)!=0
            && this.GetMap(x, y+1)!=0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,148,1,167,20,null);
            }
            //rozgalezienie od lewej
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)!=0
            && this.GetMap(x-1, y)==0
            && this.GetMap(x, y+1)==0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,106,22,125,41,null);
            }
            //rozgalezienie od prawej
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)==0
            && this.GetMap(x-1, y)!=0
            && this.GetMap(x, y+1)==0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,148,22,167,41,null);
            }
            //rozgalezienie od dolu
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)==0
            && this.GetMap(x-1, y)==0
            && this.GetMap(x, y+1)==0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,169,22,188,41,null);
            }
            //skret lewo-gora
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)!=0
            && this.GetMap(x-1, y)==0
            && this.GetMap(x, y+1)!=0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,127,43,146,62,null);
            }
            //skret prawo-gora
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)==0
            && this.GetMap(x-1, y)!=0
            && this.GetMap(x, y+1)!=0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,148,43,167,62,null);
            }

        }

        //pasek dolny
        for (int x = 1,y =this.GetHeight()-1; x < this.GetWidth()-1; x++){
            if (this.GetMap(x, y) != 0) {
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,190,22,209,41,null);
            }
            //pionowy
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)!=0
            && this.GetMap(x-1, y)!=0
            && this.GetMap(x, y-1)==0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,85,1,104,20,null);
            }
            //poziomy
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)==0
            && this.GetMap(x-1, y)==0
            && this.GetMap(x, y-1)!=0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,106,1,125,20,null);
            }
            //koniec od dolu
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)!=0
            && this.GetMap(x-1, y)!=0
            && this.GetMap(x, y-1)!=0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,190,1,209,20,null);
            }
            //rozgalezienie od lewej
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)!=0
            && this.GetMap(x-1, y)==0
            && this.GetMap(x, y-1)==0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,106,22,125,41,null);
            }
            //rozgalezienie od gory
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)==0
            && this.GetMap(x-1, y)==0
            && this.GetMap(x, y-1)==0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,127,22,146,41,null);
            }
            //rozgalezienie od prawej
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)==0
            && this.GetMap(x-1, y)!=0
            && this.GetMap(x, y-1)==0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,148,22,167,41,null);
            }
            //skret lewo-dol
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)!=0
            && this.GetMap(x-1, y)==0
            && this.GetMap(x, y-1)!=0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,85,43,104,62,null);
            }
            //skret prawo-dol
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)==0
            && this.GetMap(x-1, y)!=0
            && this.GetMap(x, y-1)!=0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,106,43,125,62,null);
            }
        }

        //pasek lewy
        for (int y = 1,x = 0; y < this.GetHeight()-1; y++){
            
            if (this.GetMap(x, y) != 0) {
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,190,22,209,41,null);
            }
            //koniec od lewej strony
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)!=0
            && this.GetMap(x, y-1)!=0
            && this.GetMap(x, y+1)!=0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,127,1,146,20,null);
            }
            //pionowy
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)!=0
            && this.GetMap(x, y-1)==0
            && this.GetMap(x, y+1)==0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,85,1,104,20,null);
            }
            //poziomy
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)==0
            && this.GetMap(x, y-1)!=0
            && this.GetMap(x, y+1)!=0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,106,1,125,20,null);
            }
            //rozgalezienie od gory
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)==0
            && this.GetMap(x, y-1)==0
            && this.GetMap(x, y+1)!=0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,127,22,146,41,null);
            }
            //rozgalezienie od prawej
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)==0
            && this.GetMap(x, y-1)==0
            && this.GetMap(x, y+1)==0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,148,22,167,41,null);
            }
            //rozgalezienie od dolu
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)==0
            && this.GetMap(x, y-1)!=0
            && this.GetMap(x, y+1)==0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,169,22,188,41,null);
            }
            //skret lewo-dol
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)!=0
            && this.GetMap(x, y-1)!=0
            && this.GetMap(x, y+1)==0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,85,43,104,62,null);
            }
            //skret lewo-gora
            if (this.GetMap(x, y) == 0
            && this.GetMap(x+1, y)!=0
            && this.GetMap(x, y-1)==0
            && this.GetMap(x, y+1)!=0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,127,43,146,62,null);
            }

        }

        //pasek prawy
        for (int y = 1,x = this.GetWidth()-1; y < this.GetHeight()-1; y++){
            if (this.GetMap(x, y) != 0) {
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,190,22,209,41,null);
            }
            //kolko
            if (this.GetMap(x, y) == 0
            && this.GetMap(x-1, y)!=0
            && this.GetMap(x, y-1)!=0
            && this.GetMap(x, y+1)!=0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,85,22,104,41,null);
            }
            //koniec od prawej strony
            if (this.GetMap(x, y) == 0
            && this.GetMap(x-1, y)!=0
            && this.GetMap(x, y-1)!=0
            && this.GetMap(x, y+1)!=0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,169,1,188,20,null);
            }
            //pionowy
            if (this.GetMap(x, y) == 0
            && this.GetMap(x-1, y)!=0
            && this.GetMap(x, y-1)==0
            && this.GetMap(x, y+1)==0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,85,1,104,20,null);
            }
            //poziomy
            if (this.GetMap(x, y) == 0
            && this.GetMap(x-1, y)==0
            && this.GetMap(x, y-1)!=0
            && this.GetMap(x, y+1)!=0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,106,1,125,20,null);
            }
            //rozgalezienie od lewej
            if (this.GetMap(x, y) == 0
            && this.GetMap(x-1, y)==0
            && this.GetMap(x, y-1)==0
            && this.GetMap(x, y+1)==0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,106,22,125,41,null);
            }
            //rozgalezienie od gory
            if (this.GetMap(x, y) == 0
            && this.GetMap(x-1, y)==0
            && this.GetMap(x, y-1)==0
            && this.GetMap(x, y+1)!=0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,127,22,146,41,null);
            }
            //rozgalezienie od dolu
            if (this.GetMap(x, y) == 0
            && this.GetMap(x-1, y)==0
            && this.GetMap(x, y-1)!=0
            && this.GetMap(x, y+1)==0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,169,22,188,41,null);
            }
            //skret prawo-dol
            if (this.GetMap(x, y) == 0
            && this.GetMap(x-1, y)!=0
            && this.GetMap(x, y-1)!=0
            && this.GetMap(x, y+1)==0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,106,43,125,62,null);
            }
            //skret prawo-gora
            if (this.GetMap(x, y) == 0
            && this.GetMap(x-1, y)!=0
            && this.GetMap(x, y-1)==0
            && this.GetMap(x, y+1)!=0
            ){
                g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,148,43,167,62,null);
            }
        }


        //srodek
        for (int y = 1; y < this.GetHeight()-1; y++) {
            for (int x = 1; x < this.GetWidth()-1; x++) {
                if (this.GetMap(x, y) != 0) {
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,190,22,209,41,null);
                }
                //kolko
                if (this.GetMap(x, y) == 0
                && this.GetMap(x+1, y)!=0
                && this.GetMap(x-1, y)!=0
                && this.GetMap(x, y-1)!=0
                && this.GetMap(x, y+1)!=0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,211,1,230,20,null);
                }
                //koniec od lewej strony
                if (this.GetMap(x, y) == 0
                && this.GetMap(x+1, y)!=0
                && this.GetMap(x-1, y)==0
                && this.GetMap(x, y-1)!=0
                && this.GetMap(x, y+1)!=0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,127,1,146,20,null);
                }
                //koniec od prawej strony
                if (this.GetMap(x, y) == 0
                && this.GetMap(x+1, y)==0
                && this.GetMap(x-1, y)!=0
                && this.GetMap(x, y-1)!=0
                && this.GetMap(x, y+1)!=0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,169,1,188,20,null);
                }
                //pionowy
                if (this.GetMap(x, y) == 0
                && this.GetMap(x+1, y)!=0
                && this.GetMap(x-1, y)!=0
                && this.GetMap(x, y-1)==0
                && this.GetMap(x, y+1)==0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,85,1,104,20,null);
                }
                //poziomy
                if (this.GetMap(x, y) == 0
                && this.GetMap(x+1, y)==0
                && this.GetMap(x-1, y)==0
                && this.GetMap(x, y-1)!=0
                && this.GetMap(x, y+1)!=0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,106,1,125,20,null);
                }
                //koniec od gory
                if (this.GetMap(x, y) == 0
                && this.GetMap(x+1, y)!=0
                && this.GetMap(x-1, y)!=0
                && this.GetMap(x, y-1)==0
                && this.GetMap(x, y+1)!=0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,148,1,167,20,null);
                }
                //koniec od dolu
                if (this.GetMap(x, y) == 0
                && this.GetMap(x+1, y)!=0
                && this.GetMap(x-1, y)!=0
                && this.GetMap(x, y-1)!=0
                && this.GetMap(x, y+1)==0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,190,1,209,20,null);
                }
                //skrzyzowanie
                if (this.GetMap(x, y) == 0
                && this.GetMap(x+1, y)==0
                && this.GetMap(x-1, y)==0
                && this.GetMap(x, y-1)==0
                && this.GetMap(x, y+1)==0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,85,22,104,41,null);
                }
                //rozgalezienie od lewej
                if (this.GetMap(x, y) == 0
                && this.GetMap(x+1, y)!=0
                && this.GetMap(x-1, y)==0
                && this.GetMap(x, y-1)==0
                && this.GetMap(x, y+1)==0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,106,22,125,41,null);
                }
                //rozgalezienie od gory
                if (this.GetMap(x, y) == 0
                && this.GetMap(x+1, y)==0
                && this.GetMap(x-1, y)==0
                && this.GetMap(x, y-1)==0
                && this.GetMap(x, y+1)!=0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,127,22,146,41,null);
                }
                //rozgalezienie od prawej
                if (this.GetMap(x, y) == 0
                && this.GetMap(x+1, y)==0
                && this.GetMap(x-1, y)!=0
                && this.GetMap(x, y-1)==0
                && this.GetMap(x, y+1)==0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,148,22,167,41,null);
                }
                //rozgalezienie od dolu
                if (this.GetMap(x, y) == 0
                && this.GetMap(x+1, y)==0
                && this.GetMap(x-1, y)==0
                && this.GetMap(x, y-1)!=0
                && this.GetMap(x, y+1)==0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,169,22,188,41,null);
                }
                //skret lewo-dol
                if (this.GetMap(x, y) == 0
                && this.GetMap(x+1, y)!=0
                && this.GetMap(x-1, y)==0
                && this.GetMap(x, y-1)!=0
                && this.GetMap(x, y+1)==0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,85,43,104,62,null);
                }
                //skret prawo-dol
                if (this.GetMap(x, y) == 0
                && this.GetMap(x+1, y)==0
                && this.GetMap(x-1, y)!=0
                && this.GetMap(x, y-1)!=0
                && this.GetMap(x, y+1)==0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,106,43,125,62,null);
                }
                //skret lewo-gora
                if (this.GetMap(x, y) == 0
                && this.GetMap(x+1, y)!=0
                && this.GetMap(x-1, y)==0
                && this.GetMap(x, y-1)==0
                && this.GetMap(x, y+1)!=0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,127,43,146,62,null);
                }
                //skret prawo-gora
                if (this.GetMap(x, y) == 0
                && this.GetMap(x+1, y)==0
                && this.GetMap(x-1, y)!=0
                && this.GetMap(x, y-1)==0
                && this.GetMap(x, y+1)!=0
                ){
                    g.drawImage(pacmanIconImg, 10+(x*19)*2, 10+(y*19)*2, 10+((x+1)*19)*2, 10+((y+1)*19)*2,148,43,167,62,null);
                }
            }
        }
    }

}
