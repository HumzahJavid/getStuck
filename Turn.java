package getstuck;

/**
 *
 * @author humzah
 */
import java.util.List;

public class Turn {

    private String currentTurnColour;
    private String startingPlayerColour;

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
        return true;
    }
}//end turn
