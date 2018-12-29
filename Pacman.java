import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Pacman  {


    private Map mapPacman = null;

    private int pacmanXPosition = 0;
    private int pacmanYPosition = 0;
    private int pacmanXDirection = 0;
    private int pacmanYDirection = 0;


    public Pacman () {

    }

    public Pacman (int x, int y, Map map){
        this.pacmanXPosition=x;
        this.pacmanYPosition=y;
        this.mapPacman=map;
    }

    public int GetXPosition(){
        return this.pacmanXPosition;
    }

    public int GetYPosition(){
        return this.pacmanYPosition;
    }

    public void DrawPacman(int grapCycle, BufferedImage pacmanIconImg, Graphics g){
        int cycle = (grapCycle+1)/2;

        if( cycle == 1){
            g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,43,1,62,20,null);
        }
        else {
            if( (this.pacmanXDirection == 0 && this.pacmanYDirection == 0) || (this.pacmanXDirection == -1 && this.pacmanYDirection == 0) ){
                if(cycle == 2){
                    g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,1,1,20,20,null);
                }
                if(cycle == 3){
                    g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,22,1,41,20,null);
                }
            }
            if( this.pacmanXDirection == 1 && this.pacmanYDirection == 0 ){
                if(cycle == 2){
                    g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,1,22,20,41,null);
                }
                if(cycle == 3){
                    g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,22,22,41,41,null);
                }
            }
            if( this.pacmanXDirection == 0 && this.pacmanYDirection == -1 ){
                if(cycle == 2){
                    g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,1,43,20,62,null);
                }
                if(cycle == 3){
                    g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,22,43,41,62,null);
                }
            }
            if( this.pacmanXDirection == 0 && this.pacmanYDirection == 1 ){
                if(cycle == 2){
                    g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,1,64,20,83,null);
                }
                if(cycle == 3){
                    g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,22,64,41,83,null);
                }
            }
            
        }

        

    }

    public void TryToChangeDirectionOfPacman(int wantedXDirection, int wantedYDirection){
        if(this.mapPacman.GetMap((this.pacmanXPosition+wantedXDirection)/19, (this.pacmanYPosition+wantedYDirection)/19)!=0 &&
            this.mapPacman.GetMap(((this.pacmanXPosition+wantedXDirection+18)/19), ((this.pacmanYPosition+wantedYDirection+18)/19))!=0 &&
            this.mapPacman.GetMap(((this.pacmanXPosition+wantedXDirection)/19), ((this.pacmanYPosition+wantedYDirection+18)/19))!=0 &&
            this.mapPacman.GetMap(((this.pacmanXPosition+wantedXDirection+18)/19), ((this.pacmanYPosition+wantedYDirection)/19))!=0 ){
                this.pacmanYDirection=wantedYDirection;
                this.pacmanXDirection=wantedXDirection;
            }

    }

    public void MovePacman(){
        if (this.mapPacman.GetMap((this.pacmanXPosition + this.pacmanXDirection) / 19,
                    (this.pacmanYPosition + this.pacmanYDirection) / 19) != 0
                    && this.mapPacman.GetMap(((this.pacmanXPosition + this.pacmanXDirection + 18) / 19),
                            ((this.pacmanYPosition + this.pacmanYDirection + 18) / 19)) != 0
                    && this.mapPacman.GetMap(((this.pacmanXPosition + this.pacmanXDirection) / 19),
                            ((this.pacmanYPosition + this.pacmanYDirection + 18) / 19)) != 0
                    && this.mapPacman.GetMap(((this.pacmanXPosition + this.pacmanXDirection + 18) / 19),
                            ((this.pacmanYPosition + this.pacmanYDirection) / 19)) != 0) {
                this.pacmanXPosition += this.pacmanXDirection;
                this.pacmanYPosition += this.pacmanYDirection;
            } else {
                this.pacmanXDirection = 0;
                this.pacmanYDirection = 0;
            }
    }
    

}