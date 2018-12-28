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