import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Pacman extends Hero {

    public Pacman () {

    }

    public Pacman (int x, int y, Map map){
        this.xPosition=x;
        this.yPosition=y;
    }

    public void Draw(int grapCycle, BufferedImage pacmanIconImg, Graphics g){
        int cycle = (grapCycle+1)/2;

        if( cycle == 1){
            g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,43,1,62,20,null);
        }
        else {
            if( (this.xDirection == 0 && this.yDirection == 0) || (this.xDirection == -1 && this.yDirection == 0) ){
                if(cycle == 2){
                    g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,1,1,20,20,null);
                }
                if(cycle == 3){
                    g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,22,1,41,20,null);
                }
            }
            if( this.xDirection == 1 && this.yDirection == 0 ){
                if(cycle == 2){
                    g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,1,22,20,41,null);
                }
                if(cycle == 3){
                    g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,22,22,41,41,null);
                }
            }
            if( this.xDirection == 0 && this.yDirection == -1 ){
                if(cycle == 2){
                    g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,1,43,20,62,null);
                }
                if(cycle == 3){
                    g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,22,43,41,62,null);
                }
            }
            if( this.xDirection == 0 && this.yDirection == 1 ){
                if(cycle == 2){
                    g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,1,64,20,83,null);
                }
                if(cycle == 3){
                    g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,22,64,41,83,null);
                }
            }
            
        }

        

    }

    public void TryToChangeDirectionOfPacman(int wantedXDirection, int wantedYDirection, Map mapPacman){
        if( (this.xDirection!=0 && wantedXDirection != 0) 
            || (this.yDirection!=0 && wantedYDirection != 0)
            || (this.xPosition%19==0 && this.yPosition%19==0)
        ){
            if(mapPacman.GetMap((this.xPosition+wantedXDirection)/19, (this.yPosition+wantedYDirection)/19)!=0 &&
            mapPacman.GetMap(((this.xPosition+wantedXDirection+18)/19), ((this.yPosition+wantedYDirection+18)/19))!=0 &&
            mapPacman.GetMap(((this.xPosition+wantedXDirection)/19), ((this.yPosition+wantedYDirection+18)/19))!=0 &&
            mapPacman.GetMap(((this.xPosition+wantedXDirection+18)/19), ((this.yPosition+wantedYDirection)/19))!=0 
            ){
                this.yDirection=wantedYDirection;
                this.xDirection=wantedXDirection;
            }

        }
        

    }

}