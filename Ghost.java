import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ghost extends Hero {

    private Random rand = new Random();

    public Ghost() {

    }

    public Ghost(int x, int y, Map map, Pacman pacman) {
        this.xPosition = x;
        this.yPosition = y;
        this.yDirection=0;
        this.xDirection=0;
        this.ChangeToRandomDirectionAndMove(map);
    }

    public void Draw(int grapCycle, BufferedImage pacmanIconImg, Graphics g, int color, int timeToDay){
        if(timeToDay==0 && this.isAlive){
            if(this.xDirection == 0 && this.yDirection==-1){
                g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,1+(grapCycle%2*21),85+(color*21),20+(grapCycle%2*21),104+(color*21),null);
            }
            if(this.xDirection == 0 && this.yDirection==1){
                g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,44+(grapCycle%2*21),85+(color*21),61+(grapCycle%2*21),104+(color*21),null);
            }
            if(this.xDirection != 1 && this.yDirection==0){
                g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,85+(grapCycle%2*21),85+(color*21),104+(grapCycle%2*21),104+(color*21),null);
            }
            if(this.xDirection == 1 && this.yDirection==0){
                g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,127+(grapCycle%2*21),85+(color*21),146+(grapCycle%2*21),104+(color*21),null);
            }
        }
        else if(timeToDay<50 && this.isAlive){
            if(this.xDirection == 0 && this.yDirection==-1){
                g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,1+(grapCycle%4*21),169,20+(grapCycle%4*21),188,null);
            }
            if(this.xDirection == 0 && this.yDirection==1){
                g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,1+(grapCycle%4*21),169,20+(grapCycle%4*21),188,null);
            }
            if(this.xDirection != 1 && this.yDirection==0){
                g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,1+(grapCycle%4*21),169,20+(grapCycle%4*21),188,null);
            }
            if(this.xDirection == 1 && this.yDirection==0){
                g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,1+(grapCycle%4*21),169,20+(grapCycle%4*21),188,null);
            }
        }
        else if(timeToDay>0 && this.isAlive){
            if(this.xDirection == 0 && this.yDirection==-1){
                g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,1+(grapCycle%2*21),169,20+(grapCycle%2*21),188,null);
            }
            if(this.xDirection == 0 && this.yDirection==1){
                g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,1+(grapCycle%2*21),169,20+(grapCycle%2*21),188,null);
            }
            if(this.xDirection != 1 && this.yDirection==0){
                g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,1+(grapCycle%2*21),169,20+(grapCycle%2*21),188,null);
            }
            if(this.xDirection == 1 && this.yDirection==0){
                g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,1+(grapCycle%2*21),169,20+(grapCycle%2*21),188,null);
            }
        }

        
    }

    public void DetectPacman(Pacman pacmanPlayer, Map mapPacman) {
        boolean isObstacle = false;

        if (pacmanPlayer.GetXPosition() == this.xPosition && this.isAlive) {
            if (pacmanPlayer.GetYPosition() > this.yPosition) {
                int j = this.yPosition;
                while (pacmanPlayer.GetYPosition() > j) {
                    j++;
                    if (mapPacman.GetMap(pacmanPlayer.GetXPosition() / 19, j / 19) == 0) {
                        isObstacle = true;
                    }
                }
            } else {
                int j = pacmanPlayer.GetYPosition();
                while (this.yPosition > j) {
                    j++;
                    if (mapPacman.GetMap(pacmanPlayer.GetXPosition() / 19, j / 19) == 0) {
                        isObstacle = true;
                    }
                }
            }
            if (!isObstacle) {
                this.xDirection = 0;
                this.yDirection = pacmanPlayer.GetYPosition() > this.yPosition ? 1 : (-1);
            }

        } else if (pacmanPlayer.GetYPosition() == this.yPosition && this.isAlive) {
            if (pacmanPlayer.GetXPosition() > this.xPosition) {
                int j = this.xPosition;
                while (pacmanPlayer.GetXPosition() > j) {
                    j++;
                    if (mapPacman.GetMap(j / 19, pacmanPlayer.GetYPosition() / 19) == 0) {
                        isObstacle = true;
                    }
                }
            } else {
                int j = pacmanPlayer.GetXPosition();
                while (this.xPosition > j) {
                    j++;
                    if (mapPacman.GetMap(j / 19, pacmanPlayer.GetYPosition() / 19) == 0) {
                        isObstacle = true;
                    }
                }
            }
            if (!isObstacle) {
                this.xDirection = pacmanPlayer.GetXPosition() > this.xPosition ? 1 : (-1);
                this.yDirection = 0;
            }

        }

    }

    public void ChangeToRandomDirectionAndMove(Map mapPacman){
        int randWandtedGhostXDirection = 0;
        int randWandtedGhostYDirection = 0;
        int n = 0;

        while ((this.xDirection == 0) && (this.yDirection == 0) &&  this.isAlive) {
            n = rand.nextInt(4) + 1;

            switch (n) {
            case 1:
                randWandtedGhostXDirection = 1;
                randWandtedGhostYDirection = 0;
                break;

            case 2:
                randWandtedGhostXDirection = -1;
                randWandtedGhostYDirection = 0;
                break;

            case 3:
                randWandtedGhostXDirection = 0;
                randWandtedGhostYDirection = 1;
                break;

            case 4:
                randWandtedGhostXDirection = 0;
                randWandtedGhostYDirection = -1;
                break;

            default:
                break;
            }

            if(
                mapPacman.GetMap((this.xPosition+randWandtedGhostXDirection)/19, (this.yPosition+randWandtedGhostYDirection)/19)!=0 &&
                mapPacman.GetMap(((this.xPosition+randWandtedGhostXDirection+18)/19), ((this.yPosition+randWandtedGhostYDirection+18)/19))!=0 &&
                mapPacman.GetMap(((this.xPosition+randWandtedGhostXDirection)/19), ((this.yPosition+randWandtedGhostYDirection+18)/19))!=0 &&
                mapPacman.GetMap(((this.xPosition+randWandtedGhostXDirection+18)/19), ((this.yPosition+randWandtedGhostYDirection)/19))!=0 )
            {
                this.yDirection = randWandtedGhostYDirection;
                this.xDirection = randWandtedGhostXDirection;
            }

        }

    }
}