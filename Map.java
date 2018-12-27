import java.io.*;

public class Map {
    protected String pathToMap;
    protected String pathToImgOfMap;
    protected int height;
    protected int width;
    protected int pacmanSquareX;
    protected int pacmanSquareY;
    protected int ghostSquareX[] = new int[4];
    protected int ghostSquareY[] = new int[4];
    protected int map[][];

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
                this.height = this.height * 10;
                this.height += readedLine.charAt(i) - 48;
            } else {

                i++;
                break;
            }
        }
        for (; i < readedLine.length(); i++)
            if (readedLine.charAt(i) != ' ') {
                this.width = this.width * 10;
                this.width += readedLine.charAt(i) - 48;
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

}
