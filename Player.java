package getstuck;

/**
 *
 * @author humzah
 */
public class Player {

    String colour;
    boolean isStuck;
    boolean isWinner;
    String name;
    int number;
    int score;

    public String getColour() {
        return this.colour;
    }

    public boolean getIsStuck() {
        return this.isStuck;
    }

    public boolean getIsWinner() {
        return this.isWinner;
    }

    public String getName() {
        return this.name;
    }

    public int getNumber() {
        return this.number;
    }

    public int getScore() {
        return this.score;
    }

    public void setColour(String n) {
        this.colour = n;
    }

    public void setIsStuck(boolean n) {
        this.isStuck = n;
    }

    public void setIsWinner(boolean n) {
        this.isWinner = n;
    }
    
    public void setName(String n) {
        this.name = n;
    }

    public void setNumber(int n) {
        this.number = n;
    }

    public void setScore(int n) {
        this.score = n;
    }    

    public int calculateStuckPlayerScore() {
        int n = 0;
        return n;
    }

    public int calculateUnstuckPlayerScore() {
        int n = 0;
        return n;
    }
}//end player
