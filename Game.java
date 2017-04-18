package getstuck;

/**
 *
 * @author humzah
 */
import java.util.ArrayList;
import java.util.List;

public class Game {
    
    int numberOfDeals;
    int numberOfMatches;
    List<Player> players;

    public Game() {
        this.numberOfDeals = 0;
        this.numberOfMatches = 0;
        this.players = null;
    }
    public int getNumberOfDeals() {
        return this.numberOfDeals;
    }

    public int getNumberOfMatches() {
        return this.numberOfMatches;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void setNumberOfDeals(int n) {
        this.numberOfDeals = n;
    }

    public void setNumberOfMatches(int n) {
        this.numberOfMatches = n;
    }

    public void setPlayers(List<Player> n) {
        this.players = n;
    }

    public void calculateScore() {
    }

    public boolean checkForStuckPlayer() {
        return true;
    }

    public Player getHighestScoringPlayer(List<Player> players) {
        return null;
    }

    public Player getStuckPlayer(List<Player> player) {
        return null;
    }

    public void register(String name1, String col1, String name2, String col2) {
    }
}//end game 

