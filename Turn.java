package getstuck;

/**
 *
 * @author humzah
 */
import java.util.List;

public class Turn {

    private String currentTurnColour;
    private String startingPlayerColour;

    public Turn(String colour) {
        this.setCurrentTurnColour(colour);
        this.setStartingPlayerColour(colour);
    }

    public String getCurrentTurnColour() {
        return this.currentTurnColour;
    }

    public String getStartingPlayerColour() {
        return this.startingPlayerColour;
    }

    public void setCurrentTurnColour(String n) {
        this.currentTurnColour = n;
    }

    public void setStartingPlayerColour(String n) {
        this.startingPlayerColour = n;
    }

    public boolean isValidMove(List<Card> cards) {
        if (!cards.isEmpty()) {
            //if this list is not empty 

            if (cards.get(0).isFaceDown == false) {
                return true;
            } else {
                for (Card currentCard : cards) {
                    if ((currentCard instanceof CourtCard) && (currentCard.isFaceDown == false)) {
                        return true;
                    }
                }
                return false;
            }
        } else {
            return false;
        }
    }
}//end turn
