package getstuck;

/**
 *
 * @author humzah
 */
public class Player {

    private String colour;
    private boolean isStuck;
    private boolean isWinner;
    private String name;
    private int number;
    private int score;

    public Player(String name, String colour, int number) {
        this.setColour(colour);
        this.setIsStuck(false);
        this.setIsWinner(false);
        this.setName(name);
        this.setNumber(number);
        this.setScore(0);
    }

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

    @Override
    public String toString() {
        return "Name: "+ this.getName() + ", Colour: " + this.getColour() + ", Number: " + this.getNumber();
    }

}//end player
