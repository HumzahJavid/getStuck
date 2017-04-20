package getstuck;
//Lines 96, 99; 127 and 130 have been left commented out for the scoring to be verified more easily

/**
 *
 * @author humzah
 */
import java.util.List;
import java.util.ArrayList;

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

    public int calculateStuckPlayerScore(List<List<Card>> cards) {
        int oldScore = this.getScore();
        int newScore = 0;
        int currentScore = 0;
        List<Card> stuckCards = new ArrayList<Card>();
        String stuckColour = this.getColour();

        for (List<Card> row : cards) {
            for (Card card : row) {
                if (card.getColour().equalsIgnoreCase(stuckColour)) {
                    stuckCards.add(card);
                }
            }
        }
        //collects all the face up cards which match the stuck players colour 

        for (Card card : stuckCards) {
            if (card instanceof NumeralCard) {
                currentScore += card.getValue().getNumericValue();
                //System.out.println(card + " score " + card.getValue().getNumericValue());
            } else {
                currentScore -= 10;
                //System.out.println(card + " score - 10 ");
            }
        }

        newScore = currentScore + oldScore;
        this.setScore(newScore);
        return newScore;
    }

    public int calculateUnstuckPlayerScore(List<List<Card>> cards) {
        int oldScore = this.getScore();
        int newScore = 0;
        int currentScore = 0;
        List<Card> unstuckCards = new ArrayList<Card>();
        String unstuckColour = this.getColour();

        for (List<Card> row : cards) {
            for (Card card : row) {
                if (card.getColour().equalsIgnoreCase(unstuckColour)) {
                    unstuckCards.add(card);
                }
            }
        }
        //collects all the face up cards which match the unstuck players colour 

        for (Card card : unstuckCards) {
            if (card instanceof NumeralCard) {
                currentScore += card.getValue().getNumericValue();
                //System.out.println(card + " score " + card.getValue().getNumericValue());
            } else {
                currentScore += 10;
               // System.out.println(card + " score 10 ");
            }
        }

        newScore = currentScore + oldScore;
        this.setScore(newScore);
        return newScore;
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + ", Colour: " + this.getColour() + ", Number: " + this.getNumber() + ", isStuck: " + this.getIsStuck() + ", isWinner: " + this.getIsWinner() + ", score: " + this.getScore();
    }

}//end player
