import java.awt.Rectangle;

public class Hero {

    protected int xPosition = 0;
    protected int yPosition = 0;
    protected int xDirection = 0;
    protected int yDirection = 0;
    protected boolean isAlive = true;

    public int GetXPosition() {
        return this.xPosition;
    }

    public int GetYPosition() {
        return this.yPosition;
    }

    public boolean GetIsAlive() {
        return this.isAlive;
    }

    public boolean Move(Map mapPacman) {
        if (this.GetIsAlive()) {
            if (
                (this.xPosition + this.xDirection < 19 || this.xDirection + this.xPosition > (mapPacman.GetWidth() - 2) * 19)
                    && (mapPacman.GetMap((this.xDirection*18 + this.xPosition) / 19, this.yPosition / 19)) != 0 
                    && this.yPosition%19==0){
                if (this.xPosition + this.xDirection == 0)
                    this.xPosition = 19 * (mapPacman.GetWidth() - 1) - 1;
                else if (this.xPosition + this.xDirection == 19 * (mapPacman.GetWidth() - 1))
                    this.xPosition = 1;
                else {
                    this.xPosition = this.xPosition + this.xDirection;
                }
                return true;
            } else if (
                mapPacman.GetMap((this.xPosition + this.xDirection) / 19,(this.yPosition + this.yDirection) / 19) != 0
                    && mapPacman.GetMap(((this.xPosition + this.xDirection + 18) / 19),((this.yPosition + this.yDirection + 18) / 19)) != 0
                    && mapPacman.GetMap(((this.xPosition + this.xDirection) / 19),((this.yPosition + this.yDirection + 18) / 19)) != 0
                    && mapPacman.GetMap(((this.xPosition + this.xDirection + 18) / 19),((this.yPosition + this.yDirection) / 19)) != 0
                   )
                     {
                this.xPosition += this.xDirection;
                this.yPosition += this.yDirection;
                return true;
            } else {
                this.xDirection = 0;
                this.yDirection = 0;
                return false;
            }

        }
        return false;
    }

    public boolean TryToKill(Hero killed) {
        Rectangle rectKilled = new Rectangle(killed.GetXPosition() - 3, killed.GetYPosition() - 3, 13, 13);
        Rectangle rectKiller = new Rectangle(this.GetXPosition() - 3, this.GetYPosition() - 3, 13, 13);

        if (rectKiller.intersects(rectKilled) && this.isAlive && killed.isAlive) {
            killed.isAlive = false;
            return true;
        } else
            return false;

    }

}
