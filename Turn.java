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
}//end turn
