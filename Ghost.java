import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ghost {
    private int ghostXPosition = 0;
    private int ghostYPosition = 0;
    private int ghostXDirection = 0;
    private int ghostYDirection = 0;
    private Random rand = new Random();

    private Map mapPacman = null;
    private Pacman pacmanPlayer = null;

    public Ghost() {

    }

    public Ghost(int x, int y, Map map, Pacman pacman) {
        this.ghostXPosition = x;
        this.ghostYPosition = y;
        this.mapPacman = map;
        this.pacmanPlayer=pacman;
        this.ghostYDirection=0;
        this.ghostXDirection=0;
        this.ChangeToRandomDirection();
    }

    public int GetXPosition() {
        return this.ghostXPosition;
    }

    public int GetYPosition() {
        return this.ghostYPosition;
    }

    public void DrawGhost(int grapCycle, BufferedImage pacmanIconImg, Graphics g, int color){

        if(this.ghostXDirection == 0 && this.ghostYDirection==-1){
            g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,1+(grapCycle%2*21),85+(color*21),20+(grapCycle%2*21),104+(color*21),null);
        }
        if(this.ghostXDirection == 0 && this.ghostYDirection==1){
            g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,44+(grapCycle%2*21),85+(color*21),61+(grapCycle%2*21),104+(color*21),null);
        }
        if(this.ghostXDirection != 1 && this.ghostYDirection==0){
            g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,85+(grapCycle%2*21),85+(color*21),104+(grapCycle%2*21),104+(color*21),null);
        }
        if(this.ghostXDirection == 1 && this.ghostYDirection==0){
            g.drawImage(pacmanIconImg, 10 + this.GetXPosition()*2, 10 + this.GetYPosition() *2, 10 + this.GetXPosition()*2 + 2 * 19, 10 + this.GetYPosition() *2 + 2 * 19,127+(grapCycle%2*21),85+(color*21),146+(grapCycle%2*21),104+(color*21),null);
        }
    }




    public void MoveGhost() {
    

        if (this.mapPacman.GetMap((this.ghostXPosition + this.ghostXDirection) / 19, (this.ghostYPosition + this.ghostYDirection) / 19) != 0
                && this.mapPacman.GetMap(((this.ghostXPosition + this.ghostXDirection + 18) / 19), ((this.ghostYPosition + this.ghostYDirection + 18) / 19)) != 0
                && this.mapPacman.GetMap(((this.ghostXPosition + this.ghostXDirection) / 19), ((this.ghostYPosition + this.ghostYDirection + 18) / 19)) != 0
                && this.mapPacman.GetMap(((this.ghostXPosition + this.ghostXDirection + 18) / 19), ((this.ghostYPosition + this.ghostYDirection) / 19)) != 0) {
            this.ghostXPosition += this.ghostXDirection;
            this.ghostYPosition += this.ghostYDirection;

        } else {
            this.ghostXDirection=0;
            this.ghostYDirection=0;
            this.ChangeToRandomDirection();
            this.MoveGhost();

        }
    }

    public void DetectPacman() {
        boolean isObstacle = false;

        if (this.pacmanPlayer.GetXPosition() == this.ghostXPosition) {
            if (this.pacmanPlayer.GetYPosition() > this.ghostYPosition) {
                int j = this.ghostYPosition;
                while (this.pacmanPlayer.GetYPosition() > j) {
                    j++;
                    if (this.mapPacman.GetMap(this.pacmanPlayer.GetXPosition() / 19, j / 19) == 0) {
                        isObstacle = true;
                    }
                }
            } else {
                int j = this.pacmanPlayer.GetYPosition();
                while (this.ghostYPosition > j) {
                    j++;
                    if (this.mapPacman.GetMap(this.pacmanPlayer.GetXPosition() / 19, j / 19) == 0) {
                        isObstacle = true;
                    }
                }
            }
            if (!isObstacle) {
                this.ghostXDirection = 0;
                this.ghostYDirection = this.pacmanPlayer.GetYPosition() > this.ghostYPosition ? 1 : (-1);
            }

        } else if (this.pacmanPlayer.GetYPosition() == this.ghostYPosition) {
            if (this.pacmanPlayer.GetXPosition() > this.ghostXPosition) {
                int j = this.ghostXPosition;
                while (this.pacmanPlayer.GetXPosition() > j) {
                    j++;
                    if (this.mapPacman.GetMap(j / 19, this.pacmanPlayer.GetYPosition() / 19) == 0) {
                        isObstacle = true;
                    }
                }
            } else {
                int j = this.pacmanPlayer.GetXPosition();
                while (this.ghostXPosition > j) {
                    j++;
                    if (this.mapPacman.GetMap(j / 19, this.pacmanPlayer.GetYPosition() / 19) == 0) {
                        isObstacle = true;
                    }
                }
            }
            if (!isObstacle) {
                this.ghostXDirection = this.pacmanPlayer.GetXPosition() > this.ghostXPosition ? 1 : (-1);
                this.ghostYDirection = 0;
            }

        }

    }

    public boolean TryToKill(Pacman pacman){
        Rectangle rectPac = new Rectangle(pacman.GetXPosition()-2,pacman.GetYPosition()-2,15,15);
        Rectangle rectGhost = new Rectangle(this.GetXPosition()-2,this.GetYPosition()-2,15,15);

        
        if(rectGhost.intersects(rectPac)){
            return true;            
        }
        else return false;

    }

    public void ChangeToRandomDirection(){
        int randWandtedGhostXDirection = 0;
        int randWandtedGhostYDirection = 0;
        int n = 0;

        while ((this.ghostXDirection == 0) && (this.ghostYDirection == 0)) {
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
                this.mapPacman.GetMap((this.ghostXPosition+randWandtedGhostXDirection)/19, (this.ghostYPosition+randWandtedGhostYDirection)/19)!=0 &&
                this.mapPacman.GetMap(((this.ghostXPosition+randWandtedGhostXDirection+18)/19), ((this.ghostYPosition+randWandtedGhostYDirection+18)/19))!=0 &&
                this.mapPacman.GetMap(((this.ghostXPosition+randWandtedGhostXDirection)/19), ((this.ghostYPosition+randWandtedGhostYDirection+18)/19))!=0 &&
                this.mapPacman.GetMap(((this.ghostXPosition+randWandtedGhostXDirection+18)/19), ((this.ghostYPosition+randWandtedGhostYDirection)/19))!=0 )
            {
                this.ghostYDirection = randWandtedGhostYDirection;
                this.ghostXDirection = randWandtedGhostXDirection;
            }

        }

    }
}